/*
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:
Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
The solution set must not contain duplicate quadruplets.
    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

    A solution set is:
    (-1,  0, 0, 1)
    (-2, -1, 1, 2)
    (-2,  0, 0, 2)
 */


    public class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            if (nums == null || nums.length < 4) {
                return res;
            }
            Arrays.sort(nums);
            for (int i = 0; i < nums.length - 3; i++) {
                if (i != 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                for (int j = i + 1; j < nums.length - 2; j++) {
                    if (j != i + 1 && nums[j] == nums[j - 1]) {
                        continue;
                    }
                    int start = j + 1;
                    int end = nums.length - 1;
                    while (start < end) {
                        int sum = nums[start] + nums[end] + nums[i] + nums[j];
                        if (sum == target) {
                            List<Integer> temp = new ArrayList<Integer>();
                            temp.add(nums[i]);
                            temp.add(nums[j]);
                            temp.add(nums[start]);
                            temp.add(nums[end]);
                            res.add(temp);
                            start++;
                            end--;
                            while (start < end && nums[start] == nums[start - 1]) { // to skip duplicates
    						    start++;
    					    }
    					    while (start < end && nums[end] == nums[end + 1]) { // 		// to skip duplicates
    						    end--;
    					    }
                        } else if (sum > target) {
                            end--;
                        } else {
                            start++;
                        }
                    }
                } 
            }
            return res;
        }
    }


// Hash Answer:
static List<List<Integer>> fourSum(int[] num, int target) {

  HashMap<Integer, List<Integer>> hmap = new HashMap<Integer, List<Integer>>();
                List<List<Integer>> ret = new ArrayList<List<Integer>>();
                List<Integer> tup = new ArrayList<Integer>();
  int sz = num.length;

  for (int i = 0; i < sz; ++i) {
   for (int j = i + 1; j < sz; ++j) {
    //key: val, value: idx=i*sz+j;
    int val = num[i] + num[j];
    int idx = i * sz + j;                              // pair(i,j) <-> idx= i*sz +j;
    
                                if (hmap.containsKey(val)) {
     hmap.get(val).add(idx);
    } else {
     List<Integer> l = new ArrayList<Integer>();
     l.add(idx);
     hmap.put(val, l);
    }
   }
  }

  for (int i = 0; i < sz; ++i) {
   for (int j = i + 1; j < sz; ++j) {
    int val = num[i] + num[j];
    int o = target - val;
    
    if (hmap.containsKey(o)) {
     List<Integer> l = hmap.get(o);
     for (int pos : l) {
         if (j < pos / sz) {                             //!important, to avoid duplicate!     
      tup.add(num[i]);
      tup.add(num[j]);
      tup.add(num[pos / sz]);
      tup.add(num[pos % sz]);
      if (ret.isEmpty() || !tup.equals(ret.get(ret.size() - 1))) {
       ret.add(tup);
          }
     }
    }
   }
  }
  return ret;
 }
}




    public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
        Arrays.sort(num);
     
        HashSet<ArrayList<Integer>> hashSet = new HashSet<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
     
        for (int i = 0; i < num.length; i++) {
            for (int j = i + 1; j < num.length; j++) {
                int k = j + 1;
                int l = num.length - 1;
     
                while (k < l) {
                    int sum = num[i] + num[j] + num[k] + num[l];
     
                    if (sum > target) {
                        l--;
                    } else if (sum < target) {
                        k++;
                    } else if (sum == target) {
                        ArrayList<Integer> temp = new ArrayList<Integer>();
                        temp.add(num[i]);
                        temp.add(num[j]);
                        temp.add(num[k]);
                        temp.add(num[l]);
     
                        if (!hashSet.contains(temp)) {
                            hashSet.add(temp);
                            result.add(temp);
                        }
     
                        k++;
                        l--;
                    }
                }
            }
        }
     
        return result;
    }