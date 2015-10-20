/*Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.*/

// My Suck Code

public class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];
        max[0] = min[0] = nums[0];
        int res = max[0];
        for (int i = 1; i < nums.length; i++) {
            max[i] = Math.max(nums[i], Math.max(nums[i] * max[i - 1], nums[i] * min[i - 1]));
            min[i] = Math.min(nums[i], Math.min(nums[i] * max[i - 1], nums[i] * min[i - 1]));
            res = Math.max(res, max[i]);
        }
        
        return res;
        
    }
}


// O(1) Space
public class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int maxHere = nums[0];
        int minHere = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = maxHere;
            maxHere = Math.max(nums[i], Math.max(temp * nums[i], minHere * nums[i]));
            minHere = Math.min(nums[i], Math.min(temp * nums[i], minHere * nums[i]));
            res = Math.max(res, maxHere);
        }
        return res;
    }
}

// Another O1
int maxProduct(int A[], int n) {
    if (n == 0) return 0;
    int maxProduct = A[0];
    int minProduct = A[0];
    int maxRes = A[0];
    for (int i = 1; i < n; i++)
    {
        if (A[i] >= 0)
        {
            maxProduct = max(maxProduct * A[i], A[i]);
            minProduct = min(minProduct * A[i], A[i]);
        }
        else
        {
            int temp = maxProduct;
            maxProduct = max(minProduct * A[i], A[i]);
            minProduct = min(temp * A[i], A[i]);
        }
        maxRes = max(maxRes, maxProduct);
    }
    return maxRes;
}