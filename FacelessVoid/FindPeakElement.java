/*
A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
*/
public class Solution {
    public int findPeakElement(int[] nums) {
        if (nums == null) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid = start + (end - start) / 2;

        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] > nums[mid - 1] && nums[mid] < nums[mid + 1]) {
                start = mid;
            } else if (nums[mid] > nums[mid + 1] && nums[mid] < nums[mid - 1]) {
                end = mid;
            } else if (nums[mid] < nums[mid - 1] && nums[mid] < nums[mid + 1]) {
                start = mid;
            } else {
                return mid;
            }
        }
        if (nums[end] >= nums[start]) {
            return end;
        } else {
            return start;
        }
    }
}