/*
Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
*/

public class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        if (nums == null || nums.length == 0) {
        	return res;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid = start + (end - start) / 2;
        // Search for left border
        while (start + 1 < end) {
        	mid = start + (end - start) / 2;
        	if (nums[mid] < target) {
        		start = mid;
        	} else if (nums[mid] > target) {
        		end = mid;
        	} else if (nums[mid] == target) {
        		end = mid;
        	}
        }
        if (nums[start] == target) {
        	res[0] = start;
        } else if (nums[end] == target) {
        	res[0] = end;
        }

        //Serach for right border
        start = 0;
        end = nums.length - 1;
        mid = start + (end - start) / 2;
        while (start + 1 < end) {
        	mid = start + (end - start) / 2;
        	if (nums[mid] < target) {
        		start = mid;
        	} else if (nums[mid] > target) {
        		end = mid;
        	} else if (nums[mid] == target) {
        		start = mid;
        	}
        }
        if (nums[end] == target) {
        	res[1] = end;
        } else if (nums[start] == target) {
        	res[1] = start;
        }
        return res;
    }
}