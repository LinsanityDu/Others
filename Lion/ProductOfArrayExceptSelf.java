/*Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].

Follow up:
Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)*/


public class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int[] order = new int[nums.length];
        int[] reverse = new int[nums.length];
        order[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            order[i] = nums[i] * order[i - 1];
        }
        reverse[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            reverse[i] = nums[i] * reverse[i + 1];
        }

        int[] res = new int[nums.length];
        res[0] = reverse[1];
        res[nums.length - 1] = order[nums.length - 2];
        for (int i = 1; i < nums.length - 1; i++) {
            res[i] = order[i - 1] * reverse[i + 1];
        }

        return res;
    }
}


// My new Solution O(1) Space
public class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int[] res = new int[nums.length];
        res[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] * nums[i]; 
        }
        
        int cur = nums[nums.length - 1];
        res[nums.length - 1] = res[nums.length - 2];
        for (int i = nums.length - 2; i > 0; i--) {
            res[i] = res[i - 1] * cur;
            cur *= nums[i]; 
        }
        res[0] = cur;
        return res;
    }
}

// Jizhi's Solution
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] res = new int[n];
    res[0] = 1;
    for (int i = 1; i < n; i++) {
        res[i] = res[i - 1] * nums[i - 1];
    }
    int right = 1;
    for (int i = n - 1; i >= 0; i--) {
        res[i] *= right;
        right *= nums[i];
    }
    return res;
}