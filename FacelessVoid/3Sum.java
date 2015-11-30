/*
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
The solution set must not contain duplicate triplets.
    For example, given array S = {-1 0 1 2 -1 -4},

    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)
 */


    public class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            if (nums == null || nums.length < 3) {
                return res;
            }
            Arrays.sort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (i != 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int start = i + 1;
                int end = nums.length - 1;
                int target = nums[i] * (-1);
                while (start < end) {
                        if (nums[start] + nums[end] == target) {
                            List<Integer> temp = new ArrayList<Integer>();
                            temp.add(nums[i]);
                            temp.add(nums[start]);
                            temp.add(nums[end]);
                            res.add(temp);
                            start++;
                            end--;
                            while (start < end && nums[start] == nums[start - 1]) { // to skip duplicates
    						    start++;
    					    }
    					    while (start < end && nums[end] == nums[end + 1]) { // to skip duplicates
    						    end--;
    					    }
                        } else if (nums[start] + nums[end] > target) {
                            end--;
                        } else {
                            start++;
                        }
                    }
            }
            return res;
        }
    }