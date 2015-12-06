/*Given a collection of integers that might contain duplicates, nums, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]*/

// Nine Chapter
public class Solution {
    public ArrayList<ArrayList<Integer>> subsets(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(num == null || num.length ==0) {
            return result;
        }
        Arrays.sort(num);
        subsetsHelper(result, list, num, 0);

        return result;
    }

    private void subsetsHelper(ArrayList<ArrayList<Integer>> result,
        ArrayList<Integer> list, int[] num, int pos) {

        result.add(new ArrayList<Integer>(list));
        
        for (int i = pos; i < num.length; i++) {
            if ( i != pos && num[i] == num[i - 1]) {
                continue;
            }    
            list.add(num[i]);
            subsetsHelper(result, list, num, i + 1);
            list.remove(list.size() - 1);
        }
    }
}


public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
        	return res;
        }
        Arrays.sort(nums);
        dfs(res, new ArrayList<Integer>(), nums, new boolean[nums.length], 0);
        return res;
    }
    
    
    public void dfs(List<List<Integer>> res, List<Integer> list, int nums[], boolean visit[], int pos) {
    	res.add(new ArrayList<Integer>(list));
    	for (int i = pos; i < nums.length; i++) {
    		if (i != 0 && nums[i] == nums[i - 1] && !visit[i - 1]) {
    			continue;
    		} 
    		
    		if(!visit[i]) {
    			visit[i] = true;
    			list.add(nums[i]);
    			dfs(res, list, nums, visit, i);
    			list.remove(list.size() - 1);
    			visit[i] = false;
    		}
    	}
    }
}