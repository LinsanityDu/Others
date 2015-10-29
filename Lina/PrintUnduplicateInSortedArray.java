/*Given an array, print all the item that only has one occurance. Eg: [1,2,2,3] > [1, 3]

*/
import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static void print (int[] nums) {
  if (nums == null || nums.length == 0) return;
  int i = 0;
  while (i < nums.length - 1) {
    if (nums[i] == nums[i + 1]) {
      int value = nums[i];
      while (nums[i] == value && i < nums.length - 1) {
        i++;
      }
    } else {
      System.out.println(nums[i]);
      i++;
    }
  }
    if (nums.length >= 2 && nums[nums.length - 1] != nums[nums.length - 2]) {
      System.out.println(nums[nums.length - 1]);
    }
}
  
  public static void main(String[] args) {
    int[] nums = {1,2,2,3,3,3,4,5,6,6,7,7,7,8};
    print(nums);
  }
}
