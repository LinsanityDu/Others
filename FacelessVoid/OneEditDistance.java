/*Given two strings S and T, determine if they are both one edit distance apart.*/
public class Solution {
    public boolean isOneEditDistance(String s, String t) {
          if (s == null || t == null)
            return false;
        
          if (s.length() > t.length())
            return isOneEditDistance(t, s);
        
          int i = 0, j = 0;
        
          while (i < s.length() && j < t.length()) {
            if (s.charAt(i) != t.charAt(j)) {
              // we try to replace s[i] with s[j] or insert s[j] to s[i]
              // then compare the rest and see if they are the same
              return s.substring(i + 1).equals(t.substring(j + 1)) ||
                     s.substring(i).equals(t.substring(j + 1));
            }
        
            i++; 
            j++;
          }
        
          return t.length() - j == 1;        
    }
}

// With Explanation
/*The basic idea is we keep comparing s and t from the beginning, once there's a difference, we try to replace s(i) with t(j) or insert t(j) to s(i) and see if the rest are the same.

Example: i and j are the two pointers of S and T, we found that 'b' != 'c' and we try to replace it:

     i                           i
S: a c d      replace       S: a b d
T: a b c d   --------->     T: a b c d    --->  "d" != "cd", no good
     j                           j
now we try to insert T(j) to S(i) and we get:

     i                           i
S: a c d      insert        S: a b c d
T: a b c d   --------->     T: a b c d    --->  "cd" == "cd", viola!
     j                           j
To keep the code simple, we make s is always shorter than t, so we don't need to try deletion.

Code:*/

public boolean isOneEditDistance(String s, String t) {
  if (s == null || t == null)
    return false;

  if (s.length() > t.length())
    return isOneEditDistance(t, s);

  int i = 0, j = 0;

  while (i < s.length() && j < t.length()) {
    if (s.charAt(i) != t.charAt(j)) {
      // we try to replace s[i] with s[j] or insert s[j] to s[i]
      // then compare the rest and see if they are the same
      return s.substring(i + 1).equals(t.substring(j + 1)) ||
             s.substring(i).equals(t.substring(j + 1));
    }

    i++; 
    j++;
  }

  return t.length() - j == 1;
}

// 只有一个指针变量
public class OneEditDistance {
  public static boolean solve(String s1, String s2) {
    if (s1.length() > s2.length()) {
      return solve(s2, s1);
    }
    if (s1.length() + 1 < s2.length()) {
      return false;
    }
    for (int i = 0; i < s1.length(); i++) {
      if (s1.charAt(i) != s2.charAt(i)) {
        if (s1.length() == s2.length()) {
/*          System.out.println(s1.substring(i + 1));
          System.out.println(s2.substring(i + 1));*/
          return s1.substring(i + 1).equals(s2.substring(i + 1));
        }
        else {
          return s1.substring(i).equals(s2.substring(i + 1));
        }
      }
    }
    return s1.length() != s2.length();
  }
}



// 如果给的是Iterator
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=121661&extra=&highlight=facebook&page=2
import java.util.*;
. 鍥磋鎴戜滑@1point 3 acres
public class Solution {
    public static boolean isOneEditDistance(Scanner a, Scanner b) {
        boolean isSame = true, isChanged = false, isAdd = false, isRemove = false;
        boolean addRemoveFlag = false;
        boolean changeFlag = false;
        int prevA = 0, prevB = 0, curA = 0, curB = 0;
   
        while (a.hasNext() && b.hasNext()) {
            prevA = curA;
            prevB = curB;
            curA = Integer.parseInt(a.next());
            curB = Integer.parseInt(b.next());
            if (curA != curB) {
            //maintain same
                if (isSame) {
                    isSame = false;
                }. 鍥磋鎴戜滑@1point 3 acres
                
            //maintain change
                if (!changeFlag) {
                    changeFlag = true;
                    isChanged = true;
                } else {. Waral 鍗氬鏈夋洿澶氭枃绔�,
                    isChanged = false;
                }
                
            //maintain add or insert
                if (!addRemoveFlag) {
                    addRemoveFlag = true;
                    isAdd = true;. from: 1point3acres.com/bbs 
                    isRemove = true;.鐣欏璁哄潧-涓€浜�-涓夊垎鍦�
                    continue;. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
                }
            }
            . 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
            //maintain add or insert
            if (addRemoveFlag) {
                isAdd = isAdd && (curA == prevB);
                isRemove = isRemove && (curB == prevA);
            }
            
            if (!(isSame || isChanged || isAdd || isRemove)) {
                return false;
            }
            . 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
           
        }
. from: 1point3acres.com/bbs         
        if (a.hasNext()) {
            int taila = Integer.parseInt(a.next());
            return (isSame || (isAdd && taila == curB)) && !a.hasNext();
        }
        
        if (b.hasNext()) {. 1point3acres.com/bbs
            int tailb = Integer.parseInt(b.next());
            return (isSame || (isRemove && tailb == curA)) && !b.hasNext();
        }
        
        return isSame || isChanged;
        
.鐣欏璁哄潧-涓€浜�-涓夊垎鍦�
    }.1point3acres缃�
    . 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
    
    public static void main(String[] args) {
      //TODO: give some testing values. 1point3acres.com/bbs
      String s1 = "";
      String s2 = "";

      Scanner scanner1 = new Scanner(s1);
      Scanner scanner2 = new Scanner(s2);
     . 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
      System.out.println("s1="+ s1 + " ," + "s2=" + s2 + " :" + isOneEditDistance(scanner1, scanner2));-google 1point3acres
      
      // close the scanner
      scanner1.close();. 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
      scanner2.close();
   }

}


// With Explanation
/*The basic idea is we keep comparing s and t from the beginning, once there's a difference, we try to replace s(i) with t(j) or insert t(j) to s(i) and see if the rest are the same.

Example: i and j are the two pointers of S and T, we found that 'b' != 'c' and we try to replace it:

     i                           i
S: a c d      replace       S: a b d
T: a b c d   --------->     T: a b c d    --->  "d" != "cd", no good
     j                           j
now we try to insert T(j) to S(i) and we get:

     i                           i
S: a c d      insert        S: a b c d
T: a b c d   --------->     T: a b c d    --->  "cd" == "cd", viola!
     j                           j
To keep the code simple, we make s is always shorter than t, so we don't need to try deletion.

Code:*/

public boolean isOneEditDistance(String s, String t) {
  if (s == null || t == null)
    return false;

  if (s.length() > t.length())
    return isOneEditDistance(t, s);

  int i = 0, j = 0;

  while (i < s.length() && j < t.length()) {
    if (s.charAt(i) != t.charAt(j)) {
      // we try to replace s[i] with s[j] or insert s[j] to s[i]
      // then compare the rest and see if they are the same
      return s.substring(i + 1).equals(t.substring(j + 1)) ||
             s.substring(i).equals(t.substring(j + 1));
    }

    i++; 
    j++;
  }

  return t.length() - j == 1;
}


/*Improve
Excellent idea! But I think we have no need to maintain index j on String t, since i and j are always in the in the same pace until we return. I have implemented your idea without using index j, and it was accepted.*/

public boolean isOneEditDistance(String s, String t) {
    int s_len = s.length();
    int t_len = t.length();
    if (t_len < s_len)
        return isOneEditDistance(t, s);
    if (t_len - s_len > 1)
        return false;
    int i = 0;
    while (i < s_len) {
        if (s.charAt(i) != t.charAt(i))
            return s.substring(i+1).equals(t.substring(i+1)) || s.substring(i).equals(t.substring(i+1));
        i++;
    }
    return s_len + 1 == t_len;
}


// Discuss One
public boolean isOneEditDistance(String s, String t) {
    if(Math.abs(s.length() - t.length()) > 1)  return false;
    int i = 0, j = 0,err = 0;
    while(i<s.length() && j<t.length())
    {
        if(s.charAt(i) != t.charAt(j))
        {
            err++;
            if(err > 1)
                return false;
            if(s.length() > t.length())
                j--;
            else if(s.length() < t.length())
                i--;
        }
        i++;
        j++;
    }
    return (err == 1 || (err == 0 && t.length() != s.length()))? true: false;
}




public boolean isOneEditDistance(String s, String t) {
    if(Math.abs(s.length()-t.length()) > 1) return false;
    if(s.length() == t.length()) return isOneModify(s,t);
    if(s.length() > t.length()) return isOneDel(s,t);
    return isOneDel(t,s);
}
public boolean isOneDel(String s,String t){
    for(int i=0,j=0;i<s.length() && j<t.length();i++,j++){
        if(s.charAt(i) != t.charAt(j)){
            return s.substring(i+1).equals(t.substring(j));
        }
    }
    return true;
}
public boolean isOneModify(String s,String t){
    int diff =0;
    for(int i=0;i<s.length();i++){
        if(s.charAt(i) != t.charAt(i)) diff++;
    }
    return diff==1;
}