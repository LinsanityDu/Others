/*Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.*/


// My solution
public class Solution {
    public void moveZeroes(int[] nums) {
        int firstZero = 0;
        while (firstZero < nums.length && nums[firstZero] != 0) {
            firstZero++;
        }
        for (int i = firstZero + 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            } else {
                nums[firstZero] = nums[i];
                nums[i] = 0;
                firstZero++;
            }
        }
    }
}

// Swap

public void moveZeroes(int[] nums) {
    int j = 0;
    for(int i = 0; i < nums.length; i++) {
        if(nums[i] != 0) {
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
            j++;
        }
    }
}


// Discusion
public class Solution {
    public void moveZeroes(int[] nums) {
        int nonZeroIndex = 0;
        int ptrIndex = 0;
        while (ptrIndex < nums.length) {
            if (nums[ptrIndex] == 0) {
                ptrIndex++;
            } else {
                int tmp = nums[ptrIndex];
                nums[ptrIndex] = nums[nonZeroIndex];
                nums[nonZeroIndex] = tmp;
                nonZeroIndex++;
                ptrIndex++;
            }
        }
    }
}

// Other
public void moveZeroes(int[] nums) {
    int zero_num = 0;
    for(int i = 0; i < nums.length; i++){
        if(nums[i] == 0) {
            zero_num++;
        }
        else{
            nums[i - zero_num] = nums[i];
            if(zero_num != 0)
                nums[i] = 0;
        }
    }
}

 
// Insert zeroes
public void moveZeroes(int[] nums) {
    if (nums == null || nums.length == 0) return;        

    int insertPos = 0;
    for (int num: nums) {
        if (num != 0) nums[insertPos++] = num;
    }        

    while (insertPos < nums.length) {
        nums[insertPos++] = 0;
    }
}
