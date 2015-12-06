/*Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2.*/
输出时间？
。。。我几个月前面G家intern转正也问的这道题。。。当时也没想好怎么做。。。也栽了T T
这道题可以从左往右扫描时间线。全局维护一个count，如果扫到的时间是start time，就输出当前count以后++count。如果扫到的是end time，就输出count以后--count。
排序时间线用O(nlgn)，扫一遍O(n)。。

 在一些区间中，寻找一个点，使这个点能够落入尽量多的区间，提示我用暴力解法
           比如 区间： 2,3 | 3,5 | 4,5 | 1,5 | 1,2         那么4和5都是答案
这个题，把所有线段的起始点和终点排序，然后遍历，设置一个counter，遇到起始点就加1，遇到终点就减1，找counter的最大值就可以了。注意排序的时候，如果一个起始点和一个终点的值相同，起始点放前面。

/**
 * Definition of Interval:
 * public classs Interval {
 *     int flag, end;
 *     Interval(int flag, int end) {
 *         this.flag = flag;
 *         this.end = end;
 *     }
 */
求room number:
class Point{
    int time;
    int flag;

    Point(int t, int s){
      this.time = t;
      this.flag = s;
    }
    public static Comparator<Point> PointComparator  = new Comparator<Point>(){
      public int compare(Point p1, Point p2){
        if(p1.time == p2.time) return p1.flag - p2.flag;
        else return p1.time - p2.time;
      }
    };
}
  
class Solution {
    /**
     * @param intervals: An interval array
     * @return: Count of airplanes are in the sky.
     */
  public int countOfAirplanes(List<Interval> airplanes) { 
    List<Point> list = new ArrayList<>(airplanes.size()*2);
    for(Interval i : airplanes){
      list.add(new Point(i.start, 1));
      list.add(new Point(i.end, 0));
    }

    Collections.sort(list,Point.PointComparator );
    int count = 0, ans = 0;
    for(Point p : list){
      if(p.flag == 1) count++;
      else count--;
      ans = Math.max(ans, count);
    }

    return ans;
  }
    
}


/*Just want to share another idea that uses min heap, average time complexity is O(nlogn).*/

public int minMeetingRooms(Interval[] intervals) {
    if (intervals == null || intervals.length == 0)
        return 0;

    // Sort the intervals by start time
    Arrays.sort(intervals, new Comparator<Interval>() {
        public int compare(Interval a, Interval b) { return a.start - b.start; }
    });

    // Use a min heap to track the minimum end time of merged intervals
    PriorityQueue<Interval> heap = new PriorityQueue<Interval>(intervals.length, new Comparator<Interval>() {
        public int compare(Interval a, Interval b) { return a.end - b.end; }
    });

    // start with the first meeting, put it to a meeting room
    heap.offer(intervals[0]);

    for (int i = 1; i < intervals.length; i++) {
        // get the meeting room that finishes earliest
        Interval interval = heap.poll();

        if (intervals[i].start >= interval.end) {
            // if the current meeting starts right after 
            // there's no need for a new room, merge the interval
            interval.end = intervals[i].end;
        } else {
            // otherwise, this meeting needs a new room
            heap.offer(intervals[i]);
        }

        // don't forget to put the meeting room back
        heap.offer(interval);
    }

    return heap.size();
}

// Another Discussion
public int minMeetingRooms(Interval[] intervals) {
    if (intervals == null || intervals.length == 0)
        return 0;

    Comparator<Interval> comp = new Comparator<Interval>() {
        @Override
        public int compare(Interval o1, Interval o2) {
            return o1.start - o2.start;
        }
    };
    Arrays.sort(intervals, comp);

    PriorityQueue<Interval> queue = new PriorityQueue<Interval>(intervals.length, new Comparator<Interval>() {
        @Override
        public int compare(Interval o1, Interval o2) {
            return o1.end - o2.end;
        }
    }
    );

    for (int i = 0; i < intervals.length; i++) {
        if (queue.isEmpty()) {
            queue.offer(intervals[i]); //start the first meeting in a new room.
        } else {
            Interval finishingMeeting = queue.poll(); // get the previous meeting with earliest finishing time.
            if (intervals[i].start < finishingMeeting.end) {
                queue.offer(intervals[i]); //the meeting isn't finished yet, start meeting in a new room.
            } else {
                finishingMeeting.end = intervals[i].end; // using the room by the previous meeting.
            }
            queue.offer(finishingMeeting);
        }

    }
    return queue.size();  
}