public class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int start = 0;
        int end = nums.length - 1;
        int mid = start + (end - start) / 2;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] > nums[end]) {
                start = mid;
            } else if (nums[mid] < nums[end]) {
                end = mid;
            }
        }
        if (nums[start] > nums[end]) {
            return nums[end];
        } else {
            return nums[start];
        }
    }
}


// Duplicate
public class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid = start + (end - start) / 2;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] < nums[end]) {
                end = mid;
            } else if (nums[mid] > nums[end]) {
                start = mid;
            } else {
                end--;
            }
        }
        if (nums[start] > nums[end]) {
            return nums[end];
        } else {
            return nums[start];
        }
    }
}