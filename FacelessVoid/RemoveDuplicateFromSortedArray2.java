/*Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.*/


public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        if (len <= 2) {
            return len;
        }
        int res = 0;
        int left = 0;
        int right = 0;
        while (left < len) {
            right = left + 1;
            while (right < len && nums[right] == nums[left]) {
                right++;
            }
            if (right - left >= 2) {
                nums[res++] = nums[right - 1];
                nums[res++] = nums[right - 1];
            } else {
                nums[res++] = nums[right - 1];
            }
            left = right;
        }
        return res;
    }
}

// Nine Chapter
  public int removeDuplicates(int[] nums) {
        // write your code here
        if(nums == null)
            return 0;
        int cur = 0;
        int i ,j;
        for(i = 0; i < nums.length;){
            int now = nums[i];
            for( j = i; j < nums.length; j++){
                if(nums[j] != now)
                    break;
                if(j-i < 2)
                    nums[cur++] = now; 
            }
            i = j;
        }
        return cur;
    }
    
// Pochman God's concise solution
public int removeDuplicates(int[] nums) {
    int i = 0;
    for (int n : nums)
        if (i < 2 || n > nums[i-2])
            nums[i++] = n;
    return i;
}