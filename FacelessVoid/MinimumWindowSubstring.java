// Code Ganker

/*这道题是字符串处理的题目，和Substring with Concatenation of All Words思路非常类似，同样是建立一个字典，然后维护一个窗口。区别是在这道题目中，因为可以跳过没在字典里面的字符（也就是这个串不需要包含且仅仅包含字典里面的字符，有一些不在字典的仍然可以满足要求），所以遇到没在字典里面的字符可以继续移动窗口右端，而移动窗口左端的条件是当找到满足条件的串之后，一直移动窗口左端直到有字典里的字符不再在窗口里。在实现中就是维护一个HashMap，一开始key包含字典中所有字符，value就是该字符的数量，然后遇到字典中字符时就将对应字符的数量减一。算法的时间复杂度是O(n),其中n是字符串的长度，因为每个字符再维护窗口的过程中不会被访问多于两次。空间复杂度则是O(字典的大小)，也就是代码中T的长度。代码如下： */


public String minWindow(String S, String T) {
    if(S==null || S.length()==0)
        return "";
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    for(int i=0; i<T.length();i++)
    {
        if(map.containsKey(T.charAt(i)))
        {
            map.put(T.charAt(i),map.get(T.charAt(i))+1);
        }
        else
        {
            map.put(T.charAt(i),1);
        }
    }
    int left = 0;
    int count = 0;
    int minLen = S.length()+1;
    int minStart = 0;
    for(int right=0; right<S.length();right++)
    {
        if(map.containsKey(S.charAt(right)))
        {
            map.put(S.charAt(right),map.get(S.charAt(right))-1);
            if(map.get(S.charAt(right))>=0)
            {
                count++;
            }
            while(count == T.length())
            {
                if(right-left+1<minLen)
                {
                    minLen = right-left+1;
                    minStart = left;                    
                }
                if(map.containsKey(S.charAt(left)))
                {
                    map.put(S.charAt(left), map.get(S.charAt(left))+1);
                    if(map.get(S.charAt(left))>0)
                    {
                        count--;
                    }
                }
                left++;
            }
        }
    }
    if(minLen>S.length())
    {
        return "";
    }
    return S.substring(minStart,minStart+minLen);
}

/*Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.*/


public class Solution {
    public String minWindow(String s, String t) {
        
    }
}





public class Solution {
    //方法一:
    int initTargetHash(int []targethash, String Target) {
        int targetnum =0 ;
        for (char ch : Target.toCharArray()) {
            targetnum++;
            targethash[ch]++;
        }
        return targetnum;
    }
    boolean valid(int []sourcehash, int []targethash) {
        
        for(int i = 0; i < 256; i++) {
            if(targethash[i] > sourcehash[i])    
                return false;
        }
        return true;
    }
    public String minWindow(String Source, String Target) {
        // queueing the position that matches the char in T
        int ans = Integer.MAX_VALUE;
        String minStr = "";
        
        int[] sourcehash = new int[256];
        int[] targethash = new int[256];
        
        initTargetHash(targethash, Target);
        int j = 0, i = 0;
        for(i = 0; i < Source.length(); i++) {
            while( !valid(sourcehash, targethash) && j < Source.length()  ) {
                sourcehash[Source.charAt(j)]++;
                if(j < Source.length() )
                    j++;
                else 
                    break;
            }
            if(valid(sourcehash, targethash) ){
                if(ans > j - i ) {
                    ans = Math.min(ans, j - i );
                    minStr = Source.substring(i, j );
                }
            }
            sourcehash[Source.charAt(i)]--;
        }
        return minStr;
    }
    方法二:
    public class Solution {
    int initTargetHash(int []targethash, String Target) {
        int targetnum =0 ;
        for (char ch : Target.toCharArray()) {
            targetnum++;
            targethash[ch]++;
        }
        return targetnum;
    }
    public String minWindow(String Source, String Target) {
        // queueing the position that matches the char in T
        int ans = Integer.MAX_VALUE;
        String minStr = "";
        
        int[] targethash = new int[256];
        
        int targetnum = initTargetHash(targethash, Target);
        int sourcenum = 0;
        int j = 0, i = 0;
        for(i = 0; i < Source.length(); i++) {
            if(targethash[Source.charAt(i)] > 0)
                sourcenum++;
            
            targethash[Source.charAt(i)]--;
            while(sourcenum>=targetnum) {
                if(ans > i - j + 1) {
                    ans = Math.min(ans, i - j + 1);
                    minStr = Source.substring(j, i + 1);
                }
                targethash[Source.charAt(j)]++;
                if(targethash[Source.charAt(j)] > 0)
                    sourcenum--;
                j ++;
            }
        }
        return minStr;
    }
    
}