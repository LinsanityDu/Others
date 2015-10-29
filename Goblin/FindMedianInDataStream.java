/*Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
For example:

add(1)
add(2)
findMedian() -> 1.5
add(3) 
findMedian() -> 2*/


class MedianFinder {

    Queue<Integer> q = new PriorityQueue(), z = q, t,
                   Q = new PriorityQueue(Collections.reverseOrder()); 

    public void addNum(int num) {
        (t=Q).add(num);
        (Q=q).add((q=t).poll());
    }

    public double findMedian() {
        return (Q.peek() + z.peek()) / 2.;
    }
};

Or:

class MedianFinder {

    Queue[] q = {new PriorityQueue(), new PriorityQueue(Collections.reverseOrder())};
    int i = 0;

    public void addNum(int num) {
        q[i].add(num);
        q[i^=1].add(q[i^1].poll());
    }

    public double findMedian() {
        return ((int)(q[1].peek()) + (int)(q[i].peek())) / 2.0;
    }
};


// Pochman
class MedianFinder {

    private Queue<Long> small = new PriorityQueue(),
                        large = new PriorityQueue();

    public void addNum(int num) {
        large.add((long) num);
        small.add(-large.poll());
        if (large.size() < small.size())
            large.add(-small.poll());
    }

    public double findMedian() {
        return large.size() > small.size()
               ? large.peek()
               : (large.peek() - small.peek()) / 2.0;
    }
};