/*A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.*/

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