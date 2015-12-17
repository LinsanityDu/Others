一个在微软呆了八年的东欧人, 在FB seattle 呆了两年;
   Log<fun_name,timestap,isStart>   给一串fun_name调用的log,返回一个Map,key是fun_name, value是fun实际调用时间。
   如: f1, t1, true;  f2, t2, true; f2, t3, false; f1, t4, false;    返回 f2, t3-t2;  f1, t4-t1-(t3-t2)

import java.io.*;
import java.util.*;
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {

  private static class Entry {
    String taskName;
    int timestamp;
    boolean start;
    public Entry(String name, int timestamp, boolean start) {
      this.taskName = name;
      this.timestamp = timestamp;
      this.start = start;
    }
  }
  private void findExecuteTime(List<Entry> entries) {
    Stack<Entry> stk = new Stack<>();
    for (Entry entry : entries) {
      if (entry.start) {
        stk.push(entry);
      } else {
        int gap = entry.timestamp - stk.pop().timestamp;
        System.out.println(entry.taskName + "#" + gap);
        for (Entry e : stk) {
          e.timestamp += gap;
        }
      }
    }
  }
 
  public static void main(String[] args) {
    Solution s = new Solution();
    List<Entry> arr = new ArrayList<>();
    arr.add(new Entry("D", 0, true));
    arr.add(new Entry("A", 1, true));
    arr.add(new Entry("B", 2, true));
    arr.add(new Entry("B", 5, false));
    arr.add(new Entry("C", 9, true));
    arr.add(new Entry("C", 11, false));
    arr.add(new Entry("A", 15, false));
    arr.add(new Entry("D", 18, false));
    s.findExecuteTime(arr);
  }
}


Log 给一串fun_name调用的log,返回一个Map,key是fun_name, value是fun实际调用时间。
如: f1, t1, true; f2, t2, true; f2, t3, false; f1, t4, false; 返回 f2, t3-t2; f1, t4-t1-(t3-t2)

import java.io.*;
import java.util.*;
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
 
  private static class Entry {
    String taskName;
    int timestamp;
    boolean start;
    public Entry(String name, int timestamp, boolean start) {
      this.taskName = name;
      this.timestamp = timestamp;
      this.start = start;
    }
  }
  private void findExecuteTime(List entries) {
    Stack stk = new Stack<>();
    for (Entry entry : entries) {
      if (entry.start) {
        stk.push(entry);
      } else {
        int gap = entry.timestamp - stk.pop().timestamp;
        System.out.println(entry.taskName + "#" + gap);
        for (Entry e : stk) {
          e.timestamp += gap;
        }
      }
    }
  }
  
  public static void main(String[] args) {
    Solution s = new Solution();
    List arr = new ArrayList<>();
    arr.add(new Entry("D", 0, true));
    arr.add(new Entry("A", 1, true));
    arr.add(new Entry("B", 2, true));
    arr.add(new Entry("B", 5, false));
    arr.add(new Entry("C", 9, true));
    arr.add(new Entry("C", 11, false));
    arr.add(new Entry("A", 15, false));
    arr.add(new Entry("D", 18, false));
    s.findExecuteTime(arr);
  }
}