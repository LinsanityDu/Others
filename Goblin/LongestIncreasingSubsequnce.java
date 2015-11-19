/*Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?*/


public class Solution {
    // O(n^2) Solution
    public int lengthOfLIS(int[] nums) {
        int N = nums.length;
        if (N == 0) return 0;
        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
    // O(n^log(n)) Solution.
    // http://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        int len = 0, N = nums.length;
        int[] tailTable = new int[N];   
        tailTable[len++] = nums[0];
        for (int i = 1; i < N; i++) {
            if (nums[i] < tailTable[0]) tailTable[0] = nums[i];
            else if (nums[i] > tailTable[len - 1]) tailTable[len++] = nums[i];
            else {
                tailTable[binarySearch(tailTable, 0, len - 1, nums[i])] = nums[i];
            }
        }
        return len;
    }
    private int binarySearch(int[] tails, int start, int end, int target) {
        while (start < end) {
            int mid = start + (end - start)/2;
            if (tails[mid] >= target) end = mid;
            else start = mid + 1;
        }
        return end;
    }
}