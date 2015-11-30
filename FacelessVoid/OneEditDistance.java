/*Given two strings S and T, determine if they are both one edit distance apart.*/


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