/*
problem 1: given a set of unique characters, find the minimum 
substring which cover all the characters in the set. 
leetcode 的简化版
set: [a, b, c]
string : abbcbcba => cba
*/
import java.util.*;
public class MinString{
    
    public static String minString(String str, Set<Character> set){
        if(str == null || set == null){
            throw new IllegalArgumentException();
        }
        if(set.size() == 0){
            return "";
        }
        if(str.length() == 0 || str.length() < set.size()){
            throw new IllegalArgumentException();
        }
        HashMap<Character, Integer> hashmap = new 
            HashMap<Character, Integer>();
        int total = set.size(), counter = 0;
        String res = "";
        int left = 0, right = 0;
        while(right < str.length()){
            char c = str.charAt(right);
            if(set.contains(c)){
                if(!hashmap.containsKey(c)){
                    hashmap.put(c, 1);
                    counter++;
                }else{
                    hashmap.put(c, hashmap.get(c) + 1);
                }
            }
            right++;
            if(counter == total){
                while(left < str.length()){
                    char leftChar = str.charAt(left);
                    if(set.contains(leftChar)){
                        hashmap.put(leftChar, hashmap.get(leftChar) - 1);
                        if(hashmap.get(leftChar) == 0){
                        	hashmap.remove(leftChar);
                            counter--;
                            if(res.length() == 0 || res.length() > right - left){
                                res = str.substring(left, right);
                            }
                            left++;
                            break;
                        }
                    }
                    left++;
                }
            }
            
        }
        return (res.length() == 0)? null : res;
    }
    
    
    
    public static void main(String[] args){
        Set<Character> set = new HashSet<Character>();
        set.add('a');
        set.add('b');
        set.add('c');
        String s1 = "facebookabcde";
        System.out.println(minString(s1, set));
        
        
    }
}