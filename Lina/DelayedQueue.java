import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Delayed;


public class DelayedThreadPool
{

    private List<DelayedThread> workers;
    
    private DelayedQueue delayedQueue;

    public DelayedThreadPool()
    {
        workers = new ArrayList<DelayedThread>();
        delayedQueue = new DelayedQueue();
        
        for( int i = 0; i < 10; i++ )
        {
            DelayedThread newThread = new DelayedThread(delayedQueue, String.valueOf( i ));
            workers.add( newThread );
            newThread.start();
        }
    }
    
    public void addTask(DelayedTask<Delayed> taskToExecute)
    {
        this.delayedQueue.addTask( taskToExecute );
    }
    
    public void stop()
    {
        for( DelayedThread delayedThread : workers )
        {
            delayedThread.close();
        }
    }
}

/**
 * 
 */

/**
 * @author r7b
 *
 */
public class DelayedThread extends Thread
{

    private DelayedQueue delayedQueue;
    
    private boolean isStopped;
    
    public DelayedThread( DelayedQueue delayedQueue, String string )
    {
        this.delayedQueue = delayedQueue;
        setName( string );
    }
    
    @Override
    public void run()
    {
       
        while(!isStopped)
        try
        {
            Runnable task = delayedQueue.getTask();
            if(task != null) {
                task.run();
            }
        }
        catch( InterruptedException e )
        {
            e.printStackTrace();
        }
    }
    
    public void close()
    {
        isStopped = true;
    }
}
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author r7b
 */
public class DelayedQueue
{
    private LinkedList<DelayedTask<Delayed>> blockingQueue;

    public DelayedQueue()
    {
        this.blockingQueue = new LinkedList<DelayedTask<Delayed>>();
    }

    public void addTask( DelayedTask<Delayed> delayedTask )
    {
        this.blockingQueue.add( delayedTask );
    }

    public synchronized Runnable getTask() throws InterruptedException
    {
        /*
         * sort the taks and take the first task in the queue.
         */
        
        while( !this.blockingQueue.isEmpty() )
        {
            Collections.sort( this.blockingQueue );
            DelayedTask<Delayed> peekElement = this.blockingQueue.peek();
            long delay = peekElement.getDelay( TimeUnit.NANOSECONDS );
            if( delay == 0L || delay > 0L )
            {
                return this.blockingQueue.remove();
            }
            else
            {
                wait(10);
            }
        }
        return null;
    }
    @Override
    public String toString()
    {
        return this.blockingQueue.toString();
    }
}
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 
 */

/**
 * @author r7b
 */
public abstract class DelayedTask<E> implements Delayed, Runnable
{

    private long timeToExecute;

    public long getTimeToExecute()
    {
        return timeToExecute;
    }

    public DelayedTask( long timeToExecute )
    {
        this.timeToExecute = timeToExecute;
    }

    @Override
    public int compareTo( Delayed other )
    {
        if( this == other )
        {
            return 0;
        }
        if( getDelay( TimeUnit.NANOSECONDS ) < other.getDelay( TimeUnit.NANOSECONDS ) )
        {
            return 1;
        }
        else if( getDelay( TimeUnit.NANOSECONDS ) > other.getDelay( TimeUnit.NANOSECONDS ) )
        {
            return -1;
        }
        return 0;
    }

    @Override
    public long getDelay( TimeUnit unit )
    {
        long currentTimeMillis = System.currentTimeMillis();
        long timeRemaining = currentTimeMillis - this.timeToExecute;
        return timeRemaining;
    }
    
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append( "[" ).append( "Time To Execute:" ).append( this.timeToExecute ).append( "]" );
        return stringBuilder.toString();
    }
}