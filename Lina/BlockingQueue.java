public interface BlockingQueue
{
    /** Retrieve and remove the head of the queue, waiting if no elements
    are present. */
    T take();

    /** Add the given element to the end of the queue, waiting if necessary
    for space to become available. */
    void put (T obj);
}

看了网上的解答，很不错的解答，从这里来的：Good Solution (with a little tweak) 其中感觉有点错误，看我在代码中的comment

public class BlockingQueue {

  private List queue = new LinkedList();
  private int  limit = 10;

  public BlockingQueue(int limit){
    this.limit = limit;
  }


  public synchronized void enqueue(Object item)
  throws InterruptedException  {
    while(this.queue.size() == this.limit) {
      wait();
    }
    /** This is what comes from the post, but if you notify here, say a 'dequeue()' is waiting, the size would remain at 0 doesn't it? So I would call notify at the end
    if(this.queue.size() == 0) {
      notifyAll();
    }
    **/
    this.queue.add(item);
    notifyAll();
  }


  public synchronized Object dequeue()
  throws InterruptedException{
    while(this.queue.size() == 0){
      wait();
    }
    /** This is what comes from the post, but if you notify here, say a 'enqueue()' is waiting, the size would remain at the limit doesn't it? So I would call notify at the end

    if(this.queue.size() == this.limit){
      notifyAll();
    }
    **/

    Object ret = this.queue.remove(0);
    notifyAll();
    return ret;

  }

}


// Github
package concurrency;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement add() and remove() methods for the queue data structure.
 *
 * The queue should be a bounded queue i.e. at any point of time, it can only
 * hold a specified number of elements.
 *
 * The queue should be blocking i.e. if a thread is trying to read from the
 * queue, and does not find any elements left to read, then it should wait until
 * an element becomes available. Similarly, if a thread is trying to write to
 * the queue, and finds the queue to be full, then it should wait until an
 * element is removed from the queue.
 *
 * The queue should be fair i.e. if there are 10 readers waiting to read from
 * the queue, and if a queue element becomes available for reading, then the
 * reader thread that arrived earliest in time should be allowed access to read
 * the element, and other reader threads should continue to remain blocked.
 * Similarly, if there are 10 writers waiting to write to the queue, and if
 * slots become available in the queue for writing, then the writer thread that
 * arrived earliest in time should be allowed access to write to the free slot,
 * and the other writer threads should continue to remain blocked.
 */
public class BoundedBlockingQueue<T> {
  private final Queue<T> elementQueue;
  private final Queue<Long> readerQueue;
  private final Queue<Long> writerQueue;
  private final int capacity;

  public BoundedBlockingQueue(int capacity) {
    if (capacity < 0) {
      throw new IllegalArgumentException(String.format(
          "Queue size cannot be negative. You passed: %d", capacity));
    }

    this.capacity = capacity;
    this.elementQueue = new LinkedList<T>();
    this.readerQueue = new LinkedList<Long>();
    this.writerQueue = new LinkedList<Long>();
  }

  public synchronized void add(T element) throws InterruptedException {
    long threadId = Thread.currentThread().getId();
    writerQueue.add(threadId);

    while (elementQueue.size() == capacity
        || writerQueue.peek() != threadId) {
      wait();
    }

    writerQueue.remove();
    elementQueue.add(element);
    notifyAll(); // notify all waiting readers as well as writers.
  }

  public synchronized T remove() throws InterruptedException {
    long threadId = Thread.currentThread().getId();
    readerQueue.add(threadId);

    while (elementQueue.isEmpty() || readerQueue.peek() != threadId) {
      wait();
    }

    readerQueue.remove();
    T toReturn = elementQueue.remove();
    notifyAll(); // notify all waiting readers as well as writers.

    return toReturn;
  }
}

// 原帖
public class BlockingQueue {

  private List queue = new LinkedList();
  private int  limit = 10;

  public BlockingQueue(int limit){
    this.limit = limit;
  }


  public synchronized void enqueue(Object item)
  throws InterruptedException  {
    while(this.queue.size() == this.limit) {
      wait();
    }
    if(this.queue.size() == 0) {
      notifyAll();
    }
    this.queue.add(item);
  }


  public synchronized Object dequeue()
  throws InterruptedException{
    while(this.queue.size() == 0){
      wait();
    }
    if(this.queue.size() == this.limit){
      notifyAll();
    }

    return this.queue.remove(0);
  }

}