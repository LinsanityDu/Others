/*Given two strings S and T, determine if they are both one edit distance apart.*/


for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
    if (s.charAt(i) != t.charAt(i)) {
        return s.substring(i + (s.length() >= t.length() ? 1 : 0)).equals(t.substring(i + (s.length() <= t.length() ? 1 : 0)));
    }
}
return Math.abs(s.length() - t.length()) == 1;


// Another

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


public class Solution {
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
}