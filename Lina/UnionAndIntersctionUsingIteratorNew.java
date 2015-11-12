import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */
//  请随便改代码 ^_^
public class Solution {
    public static Iterable<Integer> union(Iterator<Integer> iter1, Iterator<Integer> iter2) {
    int prev1 = 0, prev2 = 0;
    if (iter1.hasNext()) {
      prev1 = iter1.next();
    }
    if (iter2.hasNext()){
      prev2 = iter2.next();
    }  
    List<Integer> res = new ArrayList<Integer>();
    while (iter1.hasNext() && iter2.hasNext()) {
      if (prev1 < prev2) {
        res.add(prev1);
        prev1 = iter1.next();
      } else if (prev1 > prev2) {
        res.add(prev2);
        prev2 = iter2.next();
      } else {
        res.add(prev1);
        prev1 = iter1.next();
        prev2 = iter2.next();
      }
    }
      if (!iter1.hasNext() && !iter2.hasNext()) {
        if (prev1 > prev2) {
          res.add(prev2);
          res.add(prev1);
        } else if (prev1 < prev2) {
          res.add(prev1);
          res.add(prev2);
        } else {
          res.add(prev1);
        }
        return res;
      } 
      if (iter1.hasNext()) {
        while (prev1 < prev2) {
          res.add(prev1);
          prev1 = iter1.next();
        }
        res.add(prev2);
        while (iter1.hasNext()) {
          res.add(prev1);
          prev1 = iter1.next();
        }
        res.add(prev1);
      }
      if (iter2.hasNext()) {
        while (prev2 < prev1) {
          res.add(prev2);
          prev2 = iter2.next();
        }
        res.add(prev1);
        while (iter2.hasNext()) {
          res.add(prev2);
          prev2 = iter2.next();
        }
        res.add(prev2);
      }

            return res;

    }

    
  
  public static void main(String[] args) {
    
    List<Integer> list1 = new ArrayList<Integer>();
    List<Integer> list2 = new ArrayList<Integer>();
    list1.add(1);
    list1.add(2);
    list1.add(3);
    list1.add(4);
    list1.add(5);
    list1.add(10);
    list1.add(11);


    list2.add(1);
    list2.add(2);
    list2.add(3);
    list2.add(4);
    list2.add(9);

    Iterable<Integer> res = union(list1.iterator(),list2.iterator());
    for (Integer i : res) {
      System.out.print(i + ",");
    }
  }
}


public class Solution {
    public static Iterable<Integer> inter(Iterator<Integer> iter1, Iterator<Integer> iter2) {
    int prev1 = 0, prev2 = 0;
    if (iter1.hasNext()) {
      prev1 = iter1.next();
    }
    if (iter2.hasNext()){
      prev2 = iter2.next();
    }  
    List<Integer> res = new ArrayList<Integer>();
    while (iter1.hasNext() && iter2.hasNext()) {
      if (prev1 < prev2) {
        prev1 = iter1.next();
      } else if (prev1 > prev2) {
        prev2 = iter2.next();
      } else {
        res.add(prev1);
        prev1 = iter1.next();
        prev2 = iter2.next();
      }
    }
      if (!iter1.hasNext() && !iter2.hasNext()) {
        if (prev1 == prev2) {
          res.add(prev2);
        }
        return res;
      } 

      if (iter1.hasNext()) {
        while (iter1.hasNext()) {
          if (prev1 == prev2) {
            res.add(prev1);
            return res;
          }
          prev1 = iter1.next();
        }
      }
      if (iter2.hasNext()) {
        while (iter2.hasNext()) {
          if (prev1 == prev2) {
            res.add(prev2);
            return res;
          }
          prev2 = iter2.next();
        }
      }


            return res;

    }

    
  
  public static void main(String[] args) {
    
    List<Integer> list1 = new ArrayList<Integer>();
    List<Integer> list2 = new ArrayList<Integer>();
    list1.add(1);
    list1.add(2);
    list1.add(3);
    list1.add(4);
    list1.add(5);
    list1.add(10);
    list1.add(11);


    list2.add(1);
    list2.add(2);
    list2.add(3);
    list2.add(4);
    list2.add(9);

    Iterable<Integer> res = inter(list1.iterator(),list2.iterator());
    for (Integer i : res) {
      System.out.print(i + ",");
    }
  }
}