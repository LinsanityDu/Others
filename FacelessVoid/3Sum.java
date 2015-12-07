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


// HashTable
public class Solution {
public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
    final int length = num.length;
    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    HashMap<Integer, int[]> hashMap = new HashMap<Integer, int[]>();

    // if length is less than 3, return empty result set
    if (length < 3) return result;

    Arrays.sort(num);

    for (int i = 0; i < length - 2; i++) {
        if (num[i] > 0) break;
        hashMap.clear();

        if (i == 0 || num[i] > num[i - 1]) {
            for (int j = i + 1; j < length; j++) {
                if (hashMap.containsKey(num[j])) { // found target
                    ArrayList<Integer> elem = new ArrayList<Integer>(3);

                    elem.add(hashMap.get(num[j])[0]);
                    elem.add(hashMap.get(num[j])[1]);
                    elem.add(num[j]);

                    result.add(elem);

                    // remove duplicated elements
                    while (j < (length - 1) && num[j] == num[j + 1]) j++;
                } else {
                    int[] temp = new int[2];
                    temp[0] = num[i];
                    temp[1] = num[j];
                    hashMap.put(0 - (num[i] + num[j]), temp);
                }
            }
        }
    }
    return result;
}
}