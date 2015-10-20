/*Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].*/

// My suck Code
public class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (i % 2 == 0) {
                if (nums[i] <= nums[i + 1]) {
                    continue;
                } else {
                    swap(nums, i, i + 1);
                }
            } else {
                if (nums[i] >= nums[i + 1]) {
                    continue;
                } else {
                    swap(nums, i, i + 1);
                }
            }
        }
        return;
    
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        return;
    }
}
//Discussion

public class Solution {
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++)
            if ((i % 2 == 1 && nums[i] < nums[i - 1]) || (i % 2 == 0 && nums[i] > nums[i - 1])) {
                int tmp = nums[i];
                nums[i] = nums[i - 1];
                nums[i - 1] = tmp;
            }
    }
}


// Pochman God
public void wiggleSort(int[] nums) {
    for (int i=1; i<nums.length; i++) {
        int a = nums[i-1];
        if ((i%2 == 1) == (a > nums[i])) {
            nums[i-1] = nums[i];
            nums[i] = a;
        }
    }
}