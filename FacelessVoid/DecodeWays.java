/*A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.*/
递归怎么写？？
Transformation function as:
Count[i] = Count[i-1]  if S[i-1] is a valid char
or   = Count[i-1]+ Count[i-2]  if S[i-1] and S[i-2] together is still a valid char.

我们使用DP来处理这个题目。算是比较简单基础的一维DP啦。

1. D[i] 表示前i个字符能解的方法。

2. D[i] 有2种解法：

 1）. 最后一个字符单独解码。 如果可以解码，则解法中可以加上D[i - 1]

 2）. 最后一个字符与上一个字符一起解码。 如果可以解码，则解法中可以加上D[i - 2]

 以上2种分别判断一下1个，或是2个是不是合法的解码即可。

// Nine Chapter
public class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] nums = new int[s.length() + 1];
        nums[0] = 1;
        nums[1] = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= s.length(); i++) {
            if (s.charAt(i - 1) != '0') {
                nums[i] = nums[i - 1];
            }
            
            int twoDigits = (s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0';
            if (twoDigits >= 10 && twoDigits <= 26) {
                nums[i] += nums[i - 2];
            }
        }
        return nums[s.length()];
    }
}

// O1 Space
int numDecodings(string s) {
    if (!s.size() || s.front() == '0') return 0;
    // r2: decode ways of s[i-2] , r1: decode ways of s[i-1] 
    int r1 = 1, r2 = 1;

    for (int i = 1; i < s.size(); i++) {
        // zero voids ways of the last because zero cannot be used separately
        if (s[i] == '0') r1 = 0;

        // possible two-digit letter, so new r1 is sum of both while new r2 is the old r1
        if (s[i - 1] == '1' || s[i - 1] == '2' && s[i] <= '6') {
            r1 = r2 + r1;
            r2 = r1 - r2;
        }

        // one-digit letter, no new way added
        else {
            r2 = r1;
        }
    }

    return r1;
}


// Recursive
public class Solution {
    int num = 0;
    public int numDecodings(String s) {
        if(s==null || s.length() ==0) return 0;
        helper(s, 0);
        return num;
    }
    void helper(String s, int start){
        if(start >= s.length()){
            num++;
            return;
        }
        while( start < s.length() &&s.charAt(start)=='0') start++;
        helper(s, start+1);
        if(start+1 <s.length()){
            char c1 = s.charAt(start);
            char c2 = s.charAt(start+1);
            int tmp = (c1-'0')*10 + c2-'0';
            if(tmp <= 26 && tmp >= 10){
                helper(s, start+2);
            }
        }
    }
}

// Another Discussion
public int numDecodings(String s) {

    if(s== null || s.isEmpty() || s.charAt(0) == '0') return 0;
    int[] dp = new int[s.length()+1];
    dp[0] = 1;
    dp[1] = 1;

    for(int i = 2 ; i <= s.length() ;i++){
        int num = Integer.parseInt(s.substring(i-2,i));
        int twoStepsBehind = (num <= 26 && num >= 10) ? dp[i-2] : 0;
        int oneStepBehind = (Integer.parseInt(s.substring(i-1,i)) != 0) ? dp[i-1] : 0;
        dp[i] = twoStepsBehind + oneStepBehind; // can reach here by either hopping 2 steps or 1 step
    }

    return dp[s.length()];

}

// Discussion
public class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        if (n == 0) return 0;

        int[] memo = new int[n+1];
        memo[n]  = 1;
        memo[n-1] = s.charAt(n-1) != '0' ? 1 : 0;

        for (int i = n - 2; i >= 0; i--)
            if (s.charAt(i) == '0') continue;
            else memo[i] = (Integer.parseInt(s.substring(i,i+2))<=26) ? memo[i+1]+memo[i+2] : memo[i+1];

        return memo[0];
    }
}