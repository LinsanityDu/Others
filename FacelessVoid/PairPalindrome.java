Given a string list，find all pairs of strings which can be combined to be a palindrome. eg: cigar + tragic -> cigartragic, none + xenon -> nonexenon。如果有n个词，每个词长度m，用HashSet可以用O(nm)做出来。第二道是find distance between two nodes in a binary tree。做到这道题的时候只剩十分钟左右了，做的险象环生，还好最后做出来了。

把所有的词丢进hashset。然后对每一个word, for each i in [0, word.length()), 将word拆成两个substring [0, i] and [i + 1, word.length())，如果一个词是palindrome而另一个词在set里，说明可以combine成palindrome，然后就可以返回这个pair了。

  private void findPalinPair(String[] arr) {
  HashSet<String> set = new HashSet<String>();
  for (String a : arr) {
   set.add(a);
  }
  for (String s : arr) {
   for (int i = 0; i < s.length(); i++) {
    String s1 = s.substring(0, i);
    String s2 = s.substring(i);
    if (isPalin(s1)) {
     char cs[] = s2.toCharArray();
     reverse(cs);
     if (set.contains(new String(cs))) {
      System.out.println(new String(cs) + "" + s);
     }
    }
   }
  }
 }

 private void reverse(char cs[]) {
  int s = 0;
  int e = cs.length - 1;
  while (s <= e) {
   char tmp = cs[s];
   cs[s] = cs[e];
   cs[e] = tmp;
   s++;
   e--;
  }
 }

 private boolean isPalin(String str) {
  int s = 0;
  int e = str.length() - 1;
  while (s <= e) {
   if (str.charAt(s++) != str.charAt(e--)) {
    return false;
   }
  }
  return true;
 }


/*
Given a string list，find all pairs of strings which can be combined to be a palindrome. 
eg: cigar + tragic -> cigartragic, none + xenon -> nonexenon
*/

import java.util.*;

public class Solution{
    
    public List<List<String>> pairPalindrome(List<String> words){
        List<List<String>> res = new ArrayList<List<String>>();
        if(words == null || words.size() == 0){
            return res;
        }
        HashSet<String> hashset = new HashSet<String>();
        for(String word : words){
            hashset.add(word);
        }
        for(String word : words){
            int N = word.length();
            for(int i = 0; i < N; i++){
                String prefix = word.substring(0, i);
                String suffix = word.substring(i, N);
                String reverseSuffix = reverse(suffix);
                if(isPalindrome(prefix) && hashset.contains(reverseSuffix)){
                    
                    List<String> sol = new ArrayList<String>();
                    sol.add(reverseSuffix);
                    sol.add(word);
                    res.add(sol);
                }
            }
        }
        return res;
    }
    
    private boolean isPalindrome(String word){
        int start = 0, end = word.length() - 1;
        while(start < end){
            if(word.charAt(start) != word.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    
    private String reverse(String word){
        char[] chars = word.toCharArray();
        int start = 0, end = chars.length - 1;
        while(start < end){
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
        return new String(chars);
    }
    
    
    
    
    
    public static void main(String[] args){
        List<String> list = new ArrayList<String>();
        list.add("cigar");
        list.add("tragic");
        list.add("none");
        list.add("xenon");
        Solution sol = new Solution();
        System.out.println(sol.pairPalindrome(list));
        
    }
}