Implement a cyclical buffer of bytes of fixed size n.
Support the creation and enqueue/dequeue operations.
The buffer is intended to be use in a high throughput environment.

class MyQueue {
	static int[] queue;
	int front;
	int end;
	int count;

	void initialise(int queueSize) {
		queue = new int[queueSize];
		front = end = 0;
	}

	void enqueue(int element) {

		synchronized(queue) {
			if(count == queue.length - 1)
				System.out.println("Queue is Full"+count);
			else{
				if(front == 0) {
					front = 1;
				}
				end = (end % (queue.length-1)) + 1;
				queue[end] = element;
				System.out.println("Element entered:\t"+ end + "\t" + element);
				count++;
			}	
		}		
	}

	void dequeue() {
		synchronized(queue) {
			if(count == 0)
				System.out.println("Queue is Empty");
			else{
				System.out.println("Element Deleted:\t" + front + "\t" + queue[front]);
				front = (front % (queue.length-1)) + 1;
				count--;
			}	
		}
	}

	void display()
	{
		int temp = end;
		System.out.println("\nElements in the Queue:");
		int c = count;
		while(c > 0)
		{
			System.out.println(temp + "\t" + queue[temp]);
			temp = (temp % (queue.length-1)) - 1;
			c = c -1 ;
		}
		System.out.println("Front:\t" + queue[front] + "\tEnd:\t" + queue[end] + "\n");
	}
}