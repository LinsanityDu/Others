/*Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.*/

// My suck code
public class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] state = new int[nums.length];
        state[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            state[i] = Math.max(state[i - 1] + nums[i], nums[i]);
            res = Math.max(res, state[i]);
        }
        return res;
    }
}


// Dicussion Solution
public class Solution {
    public int maxSubArray(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int maxSoFar=A[0], maxEndingHere=A[0];
        for (int i=1;i<A.length;++i){
            maxEndingHere= Math.max(maxEndingHere+A[i],A[i]);
            maxSoFar=Math.max(maxSoFar, maxEndingHere); 
        }
        return maxSoFar;
    }
}

//D & C
public class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return helper(nums, 0 , nums.length - 1);
    }
    
    private int helper(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        int mid = start + (end - start) / 2;
        int left = helper(nums, start, mid);
        int right = helper(nums, mid + 1, end);
        int leftSub = nums[mid];
        int rightSub = nums[mid + 1];
        int temp = 0;
        for (int i = mid; i >= start; i--) {
            temp += nums[i];
            if (temp > leftSub) leftSub = temp;
        }
        temp = 0;
        for (int i = mid + 1; i < nums.length; i++) {
            temp += nums[i];
            if (temp > rightSub) rightSub = temp;
        }
        return Math.max(leftSub + rightSub, Math.max(left, right));
    }
}







