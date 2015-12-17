facebook intern，面挂了也要来做做贡献，看了很多帖子，对我的面试很有帮助，在此谢谢大家的分享。就这一道题，水平太差，时间完了也没搞定。有做过的同学也麻烦分享下code，谢谢啦。

题目如下：
class IntFileIterator {
  boolean hasNext();
  int next();
}

class FileCompare {
  public boolean isDistanceZeroOrOne(IntFileIterator a, IntFileIterator b)；

}
// return if the distance between a and b is at most 1.. Waral 鍗氬鏈夋洿澶氭枃绔�,
// Distance: minimum number of modifications to make a=b. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
// Modification:. 1point3acres.com/bbs
//   1. change an int in a
//   2. insert an int to a.鏈枃鍘熷垱鑷�1point3acres璁哄潧
//   3. remove an int from a

public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        List<Character> s1 = new ArrayList<>();
        for (char c : s.toCharArray()) {
            s1.add(c);
        }
        List<Character> t1 = new ArrayList<>();
        for (char c : t.toCharArray()) {
            t1.add(c);
        }
        if (s.equals(t)) return false;
        return isOneEditDistance(s1.iterator(), t1.iterator());
    }
   
    public static boolean isOneEditDistance(Iterator<Character> a, Iterator<Character> b) {
        boolean isSame = true, isChanged = false, isAdd = false, isRemove = false;
        int prevA = 0, prevB = 0, curA = 0, curB = 0;
        while (a.hasNext() && b.hasNext()) {
            prevA = curA;
            prevB = curB;
            curA = a.next();
            curB = b.next();
            if (isSame) {
                if (curA != curB) {
                    isSame = false;
                    isChanged = true;
                    isAdd = true;
                    isRemove = true;
                }
            } else {
                if (isChanged) {
                    if (curA != curB) {
                        isChanged = false;
                    }
                }
                if (isAdd) {
                    if (prevA != curB) {
                        isAdd = false;
                    }
                }
                if (isRemove) {
                    if (curA != prevB) {
                        isRemove = false;
                    }
                }
            }
            if (!isSame && !isChanged && !isAdd && !isRemove) {
                return false;
            }
        }
        if (isSame) {
            if (a.hasNext()) {
                a.next();
                return !a.hasNext();
            } else if (b.hasNext()) {
                b.next();
                return !b.hasNext();
            } else {
                return true;
            }
        }
        if (isChanged) {
            if (!a.hasNext() && !b.hasNext()) {
                return true;
            }
        }
        if (isRemove) {
            if (a.hasNext()) {
                prevA = a.next();
                return prevA == curB && !a.hasNext();
            }
        }
        if (isAdd) {
            if (b.hasNext()) {
                prevB = b.next();
                return prevB == curA && !b.hasNext();
            }
        }
        return false;
    }
}