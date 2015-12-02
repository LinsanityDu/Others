/*You're given an array of integers(eg [3,4,7,1,2,9,8]) Find the index of values that satisfy A+B = C + D, where A,B,C & D are integers values in the array. Eg: Given [3,4,7,1,2,9,8] array The following 3+7 = 1+ 9 satisfies A+B=C+D so print (0,2,3,5)*/

- threeSum->fourSum(same is find A+B=C+D)
	+ naive way is to add a new for loop and reduce this problem to threeSum. Another way is to use hashtable. First
generate all the pairs (O(n^2)), then use two sum algorithm to search for the two pairs.

// 4Sum???
public class Solution {

	public static void twoSumEqual(int[] nums) {
		if (nums == null || nums.length < 4) return;
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				int target = nums[i] + nums[j];
				Map<Integer, Integer> map = new HashMap<>();
				for (int k = 0; k < nums.length; k++) {
					if (k == i || k == j) {
						continue;
					}
					if (map.containsKey(target - nums[k])) {
						System.out.print(i);
						System.out.print(j);
						System.out.print(map.get(target - nums[k]));
						System.out.print(k);
						//return??
					} else {
						map.put(nums[k], k);
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] nums = new int[]{3,4,7,1,2,9,8};
		twoSumEqual(nums);
	}
}


public class FindAPlusBEqualsToCPlusD {
	public static int[] solve2(int[] nums) {
		if (nums == null || nums.length < 4) {
			return new int[]{-1, -1, -1, -1};
		}
		boolean[] used = new boolean[nums.length];
		for (int i = 0; i < nums.length; i++) {
			used[i] = true;
			for (int j = i + 1; j < nums.length; j++) {
				used[j] = true;
				int[] sum = twoSum(nums, nums[i] + nums[j], used);
				if (sum[0] == -1) {
					used[j] = false;
					continue;
				}
				else {
					int[] ret = new int[]{i, j, sum[0], sum[1]};
					Arrays.sort(ret);
					return ret;
				}
			}
			used[i] = false;
		}
		return new int[]{-1, -1, -1, -1};
	}
}