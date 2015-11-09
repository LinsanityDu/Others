// Add interval:
public class MyIntervals implements Intervals {
    List<Interval> intervals = new ArrayList<Interval>();
    @Override
    public void addInterval(int from, int to) {
        intervals.add(new Interval(from, to));
    }

    @Override
    public int getTotalCoveredLength {
        Collections.sort(intervals);
        int res = 0;
        Interval last = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            if (curr.start <= last.end) {
                last.end = Math.max(last.end, curr.end);
            } else {
                res += last.end - last.start;
                last = curr;
            }
        }
        return totalLen;
    }


    class Interval implements Comparable<Interval> {
        public int start, end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public compareTo(Interval o) {
            if(o.start-this.start == 0) {
                return 0;
            }
            if(o.start-this.start > 0) {
                return -1;
            }
            if(o.start-this.start < 0) {
                return 1;
            }
        }
    }

}



// Another way
public class MyIntervals implements Intervals {
    List<Length> l = new LinkedList<Length>();
    @Override
    public void addInterval(int from, int to) {
        l.add(new Length(x,y));
    }

    @Override
    public int getTotalCoveredLength {
        Collections.sort(l);
        int retLength = 0;
        Length lastone = new Length(0,0);
        for(Length len : l) {
            if(len.x > lastLen.y) {//locate apart
                totalLen += len.y - len.x;
                lastLen = len;
            } else if(len.y >lastlen.y) { //overlapping
                totalLen += len.y-lastLen.y;
                lastLen = len;
            }
            //注意这里不需要考虑如果后一个在前一个里面会怎么样，因为lastLen会维持一样，写一次仍然跟前一个做比较
        }
        return totalLen;
    }

}

public class Length implements Comparable<Length> {
    public int x, y;
    public Length(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public compareTo(Length o) {
        if(o.x-this.x == 0) {
            return 0;
        }
        if(o.x-this.x > 0) {
            return -1;
        }
        if(o.x-this.x < 0) {
            return 1;
        }
    }
}


public interface Intervals {
    /**
     * Adds an interval [from, to] into internal structure.
     */
    void addInterval(int from, int to);
    /**
     * Returns a total length covered by intervals.
     * If several intervals intersect, intersection should be counted only once.
     * Example:
     *
     * addInterval(3, 6)
     * addInterval(8, 9)
     * addInterval(1, 5)
     *
     * getTotalCoveredLength() -> 6
     * i.e. [1,5] and [3,6] intersect and give a total covered interval [1,6]
     * [1,6] and [8,9] don't intersect so total covered length is a sum for both intervals, that is 6.
     *
     */
    int getTotalCoveredLength();

}

public class IntervalsImp implements Intervals {
    
    class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    private List<Interval> intervals = new ArrayList<Interval>();

    @Override
    void addInterval(int from, int to) {
        Inteval newInterval = new Interval(from, to);
        intervals.add(newInterval);

        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        List<Interval> temp = new ArrayList<Interval>();

        Interval last = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curt = intervals.get(i);
            if (curt.start <= last.end ){
                last.end = Math.max(last.end, curt.end);
            }else{
                temp.add(last);
                last = curt;
            }
        }
        temp.add(last);
        intervals = new ArrayList<Interval>(temp);
    }

    @Override
    int getTotalCoveredLength() {
        int length = 0;
        for (Inteval interval : intervals) {
            length += interval.end - interval.start;
        }
        return length;
    }
}


// Another Solution
public interface Intervals {

    /**
     * Adds an interval [from, to] into internal structure.
     */
    void addInterval(int from, int to);


    /**
     * Returns a total length covered by intervals.
     * If several intervals intersect, intersection should be counted only once.
     * Example:
     *
     * addInterval(3, 6)
     * addInterval(8, 9)
     * addInterval(1, 5)
     *
     * getTotalCoveredLength() -> 6
     * i.e. [1,5] and [3,6] intersect and give a total covered interval [1,6]
     * [1,6] and [8,9] don't intersect so total covered length is a sum for both intervals, that is 6.
     *
     *                   _________
     *                                               ___
     *     ____________
     *
     * 0  1    2    3    4    5   6   7    8    9    10
     *
     */
    int getTotalCoveredLength();
}



// Merge Intervals
public class Solution {
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }
        
        Collections.sort(intervals, new IntervalComparator());       
  
        ArrayList<Interval> result = new ArrayList<Interval>();
        Interval last = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curt = intervals.get(i);
            if (curt.start <= last.end ){
                last.end = Math.max(last.end, curt.end);
            }else{
                result.add(last);
                last = curt;
            }
        }
        
        result.add(last);
        return result;
    }
    
    
    private class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval a, Interval b) {
            return a.start - b.start;
        }
    }

}

/*Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        
    }
}


// Nine Chapter
public class Solution {
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        if (newInterval == null || intervals == null) {
            return intervalsLina;
        }

        ArrayList<Interval> results = new ArrayList<Interval>();
        int insertPos = 0;

        for (Interval interval : intervals) {
            if (interval.end < newInterval.start) {
                results.add(interval);
                insertPos++;
            } else if (interval.start > newInterval.end) {
                results.add(interval);
            } else {
                newInterval.start = Math.min(interval.start, newInterval.start);
                newInterval.end = Math.max(interval.end, newInterval.end);
            }
        }

        results.add(insertPos, newInterval);

        return results;
    }
}