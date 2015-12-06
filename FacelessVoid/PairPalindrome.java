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