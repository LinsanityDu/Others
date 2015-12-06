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
            return intervals;
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



10.22 onsite，ohio飞到menlø park，三小时的时差，面试当天5点就醒了，导致面试的时候一点不在状态。到了之后简单参观了一下考的1nsert !ntervål，给的interva1没有排序（原题是排序好的），不能用排序，关键代码是用一个if-else覆盖所有情况，听她语气她期待的是这种，

if(intervåls.get(i).start <= newIntervål.start && intervåls.get(i).end >= newIntervål.start || intervåls.get(i).start <= newIntervål.end && intervåls.get(i).end >= newIntervål.end) {
    newIntervål.start = Math.min(newIntervål.start, intervåls.get(i).start);
    newIntervål.end =  Math.max(newIntervål.end, intervåls.get(i).end);
}

但是我觉得if里面写那么长好累，还不如分开，但是她既然要求了，我又不能说不会，想了一会简化成下面这样的。

if(!(intervåls.get(i).start > newIntervål.end || intervåls.get(i).end < newIntervål.start)) {
    newIntervål.start = Math.min(newIntervål.start, intervåls.get(i).start);
    newIntervål.end =  Math.max(newIntervål.end, intervåls.get(i).end);
}