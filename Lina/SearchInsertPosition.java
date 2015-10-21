/*
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
*/

public class Solution {
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
        	return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid = start + (end - start) / 2;
        while (start + 1 < end) {
        	mid = start + (end - start) / 2;
        	if (nums[mid] > target) {
        		end = mid;
        	} else if (nums[mid] < target) {
        		start = mid;
        	} else {
        		return mid;
        	}
        }
        if (nums[start] == target) {
        	return start;
        } 
        if (nums[end] == target) {
        	return end;
        }
        if (target < nums[start]) {
        	return start;
        } else if (target > nums[start] && target < nums[end]) {
        	return end;
        } else if (target > nums[end]) {
        	return end + 1;
        }
        return -1;
    }
}


// Nine Chapter
public class Solution {
    public int searchInsert(int[] A, int target) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int start = 0, end = A.length - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (A[start] >= target) {
            return start;
        } else if (A[end] >= target) {
            return end;
        } else {
            return end + 1;
        }
    }
}
