/*同步问题核心在于：如何保证同一资源被多个线程并发访问时的完整性。常用的同步方法是采用信号或加锁机制，保证资源在任意时刻至多被一个线程访问。Java语言在多线程编程上实现了完全对象化，提供了对同步机制的良好支持。在Java中一共有五种方法支持同步，其中前四个是同步方法，一个是管道方法。
wait() / notify()方法
await() / signal()方法
BlockingQueue阻塞队列方法
Semaphore方法*/

1. Wait/Notify
package test;
 
public class Hosee
{
    private static Integer count = 0;
    private final Integer FULL = 10;
    private static String LOCK = "LOCK";
 
    class Producer implements Runnable
    {
        @Override
        public void run()
        {
            for (int i = 0; i < 10; i++)
            {
                try
                {
                    Thread.sleep(3000);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                synchronized (LOCK)
                {
                    while (count == FULL)
                    {
                        try
                        {
                            LOCK.wait();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName()
                            + "生产者生产，目前总共有" + count);
                    LOCK.notifyAll();
                }
            }
        }
 
    }
 
    class Consumer implements Runnable
    {
 
        @Override
        public void run()
        {
            for (int i = 0; i < 10; i++)
            {
                try
                {
                    Thread.sleep(3000);
                }
                catch (InterruptedException e1)
                {
                    e1.printStackTrace();
                }
                synchronized (LOCK)
                {
                    while (count == 0)
                    {
                        try
                        {
                            LOCK.wait();
                        }
                        catch (Exception e)
                        {
                            // TODO: handle exception
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName()
                            + "消费者消费，目前总共有" + count);
                    LOCK.notifyAll();
                }
            }
 
        }
 
    }
 
    public static void main(String[] args) throws Exception
    {
        Hosee hosee = new Hosee();
        new Thread(hosee.new Producer()).start();
        new Thread(hosee.new Consumer()).start();
        new Thread(hosee.new Producer()).start();
        new Thread(hosee.new Consumer()).start();
 
        new Thread(hosee.new Producer()).start();
        new Thread(hosee.new Consumer()).start();
        new Thread(hosee.new Producer()).start();
        new Thread(hosee.new Consumer()).start();
    }
 
}


// Bounded Queue
package test;
 
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
 
public class Hosee {
    private static Integer count = 0;
    final BlockingQueue<Integer> bq = new ArrayBlockingQueue<Integer>(10);
    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    bq.put(1);
                    count++;
                    System.out.println(Thread.currentThread().getName()
                            + "生产者生产，目前总共有" + count);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
 
    class Consumer implements Runnable {
 
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    bq.take();
                    count--;
                    System.out.println(Thread.currentThread().getName()
                            + "消费者消费，目前总共有" + count);
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        }
 
    }
 
    public static void main(String[] args) throws Exception {
        Hosee hosee = new Hosee();
        new Thread(hosee.new Producer()).start();
        new Thread(hosee.new Consumer()).start();
        new Thread(hosee.new Producer()).start();
        new Thread(hosee.new Consumer()).start();
 
        new Thread(hosee.new Producer()).start();
        new Thread(hosee.new Consumer()).start();
        new Thread(hosee.new Producer()).start();
        new Thread(hosee.new Consumer()).start();
    }
 
}

// Semaphore
package test;
 
import java.util.concurrent.Semaphore;
 
public class Hosee
{
    int count = 0;
    final Semaphore notFull = new Semaphore(10);
    final Semaphore notEmpty = new Semaphore(0);
    final Semaphore mutex = new Semaphore(1);
 
    class Producer implements Runnable
    {
        @Override
        public void run()
        {
            for (int i = 0; i < 10; i++)
            {
                try
                {
                    Thread.sleep(3000);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                try
                {
                    notFull.acquire();//顺序不能颠倒，否则会造成死锁。
                    mutex.acquire();
                    count++;
                    System.out.println(Thread.currentThread().getName()
                            + "生产者生产，目前总共有" + count);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    mutex.release();
                    notEmpty.release();
                }
 
            }
        }
    }
 
    class Consumer implements Runnable
    {
 
        @Override
        public void run()
        {
            for (int i = 0; i < 10; i++)
            {
                try
                {
                    Thread.sleep(3000);
                }
                catch (InterruptedException e1)
                {
                    e1.printStackTrace();
                }
                try
                {
                    notEmpty.acquire();//顺序不能颠倒，否则会造成死锁。
                    mutex.acquire();
                    count--;
                    System.out.println(Thread.currentThread().getName()
                            + "消费者消费，目前总共有" + count);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    mutex.release();
                    notFull.release();
                }
 
            }
 
        }
 
    }
 
    public static void main(String[] args) throws Exception
    {
        Hosee hosee = new Hosee();
        new Thread(hosee.new Producer()).start();
        new Thread(hosee.new Consumer()).start();
        new Thread(hosee.new Producer()).start();
        new Thread(hosee.new Consumer()).start();
 
        new Thread(hosee.new Producer()).start();
        new Thread(hosee.new Consumer()).start();
        new Thread(hosee.new Producer()).start();
        new Thread(hosee.new Consumer()).start();
    }
 
}