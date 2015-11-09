public class Solution {
	public static List<Integer> union(Iterator<Integer> iter1, Iterator<Integer> iter2) {
		List<Integer> res = new ArrayList<Integer>();
		if (iter1 == null && iter2 == null) return res;
		int prev1 = 0;
		int prev2 = 0;
		boolean next1 = true;
		boolean next2 = true;
		while (iter1.hasNext() && iter2.hasNext()) {
			if (next1) prev1 = iter1.next();
			if (next2) prev2 = iter2.next();
			if (prev1 < prev2) {
				res.add(prev1);
				next1 = true;
				next2 = false;
			} else if (prev1 > prev2) {
				res.add(prev2);
				next1 = false;
				next2 = true;
			} else {
				res.add(prev1);
				next1 = true;
				next2 = true;
			}
		}
		while (iter1.hasNext()) {
			res.add(prev1);
			prev1 = iter1.next();
		}
		while (iter2.hasNext()) {
			res.add(prev2);
			prev2 = iter2.next();
		}
		return res;
	}

	public static List<Integer> union(Iterator<Integer> iter1, Iterator<Integer> iter2) {
		List<Integer> res = new ArrayList<Integer>();
		IntIter iit1 = new IntIter(iter1);
		IntIter iit2 = new IntIter(iter2);
		while (iit1.iter.hasNext() && iit2.iter.hasNext()) {
			if (iit1.next < iit2.next) {
				res.add(iit1.next);
				iit1 = new IntIter(iit1.iter);
			} else if (iit1.next > iit2.next) {
				res.add(iit2.next);
				iit2 = new IntIter(iit2.iter);
			} else {
				res.add(iit1.next);
				iit1 = new IntIter(iit1.iter);
				iit2 = new IntIter(iit2.iter);
			}
		}
		if (!iit1.iter.hasNext() && !iit2.iter.hasNext()) {
			if (iit1.next > iit2.next) {
				res.add(iit2.next);
				res.add(iit1.next);
			} else if (iit1.next < iit2.next) {
				res.add(iit1.next);
				res.add(iit2.next);
			} else {
				res.add(iit1.next);
			}
			return res;
		}
		if (iit1.iter.hasNext()) {
			IntIter exist = iit1;
			IntIter last = iit2;
		} else {
			IntIter last = iit1;
			IntIter exist = iit2;
		}
		boolean lastone = false;
		while (exist.iter.hasNext() && exist.next < last.next) {
				res.add(exist.next);
				exist = new IntIter(exist.iter);
		}
		while (exist.iter.hasNext()) {
			res.add(exist.next);
			exist = new IntIter(exist.iter);
		}
		res.add(exist.next);
		return res;
	}

	public class IntIter {
        int next;
        Iterator<Integer> iter;
        public IntIter(Iterator<Integer> iter) {
            this.next = iter.next();
            this.iter = iter;
        }
    }
}



public class Solution {
	public static void union(int[] array1, int[] array2) {
		int index1 = 0;
		int index2 = 0;
		while (index1 < array1.length && index2 < array2.length) {
			if (array1[index1] < array2[index2]) {
				print(array1[index1]);
				index1++;
			} else if (array1[index1] > array2[index2]) {
				print(array2[index2]);
				index2++;
			} else {
				print(array1[index1]);
				index1++;
				index2++;
			}
		}
		if (index1 < array1.length) {
			while (index1 < array1.length) {
				print(array1[index1++]);
			}
		}
		if (index2 < array2.length) {
			while (index2 < array2.length) {
				print(array2[index2++]);
			}
		}
		return;
	}
}

// Working
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
    List<Integer> res = new ArrayList<Integer>();
    IntIter iit1 = new IntIter(iter1);
    IntIter iit2 = new IntIter(iter2);
    while (iit1.iter.hasNext() && iit2.iter.hasNext()) {
      if (iit1.next < iit2.next) {
        res.add(iit1.next);
        iit1 = new IntIter(iit1.iter);
      } else if (iit1.next > iit2.next) {
        res.add(iit2.next);
        iit2 = new IntIter(iit2.iter);
      } else {
        res.add(iit1.next);
        iit1 = new IntIter(iit1.iter);
        iit2 = new IntIter(iit2.iter);
      }
    }
      // if 1 and 2 reach the end at the same time
    if (!iit1.iter.hasNext() && !iit2.iter.hasNext()) {
      if (iit1.next > iit2.next) {
        res.add(iit2.next);
        res.add(iit1.next);
      } else if (iit1.next < iit2.next) {
        res.add(iit1.next);
        res.add(iit2.next);
      } else {
        res.add(iit1.next);
      }
      return res;
    }
    IntIter exist, last;
    if (iit1.iter.hasNext()) {
      exist = iit1;
      last = iit2;
    } else {
      last = iit1;
      exist = iit2;
    }
    boolean lastB = false;
    while (exist.iter.hasNext()) {
      if (exist.next <= last.next) {
        res.add(exist.next);
        exist = new IntIter(exist.iter);
      } else if (exist.next > last.next) {
        res.add(last.next);
        lastB = true;
        break;
      } 
    }
    while (exist.iter.hasNext()) {
      res.add(exist.next);
      exist = new IntIter(exist.iter);
    }
    res.add(exist.next);
    if (!lastB && last.next > exist.next) res.add(last.next);
    return res;
  }
  
  static class IntIter {
        int next;
        Iterator<Integer> iter;
        IntIter(Iterator<Integer> iter) {
            this.next = iter.next();
            this.iter = iter;
        }
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
