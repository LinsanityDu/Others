/*Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].*/


public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
        	return res;
        }
        Arrays.sort(nums);
        dfs(res, new ArrayList<Integer>(), nums, new boolean[nums.length]);
        return res;
    }

    public void dfs(List<List<Integer>> res, List<Integer> list, int nums[], boolean visit[]) {
    	if (list.size() == nums.length) {
    		res.add(new ArrayList<Integer>(list));
    		return;
    	}
    	for (int i = 0; i < nums.length; i++) {
    		if (i != 0 && nums[i] == nums[i - 1] && !visit[i - 1]) {
    			continue;
    		} 
    		
    		if(!visit[i]) {
    			visit[i] = true;
    			list.add(nums[i]);
    			dfs(res, list, nums, visit);
    			list.remove(list.size() - 1);
    			visit[i] = false;
    		}
    	}
    }
}