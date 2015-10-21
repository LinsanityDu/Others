/*Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Ensure that numbers within the set are sorted in ascending order.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]*/

public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (k < 1 || k > 9 || n < 1 || n > 45) {
        	return res;
        }
        dfs(res, new ArrayList<Integer>(), 1, k, n);
        return res;
    }
    
    
    public void dfs(List<List<Integer>> res, List<Integer> list, int pos, int k, int n) {
    	if (n == 0 && list.size() == k) {
    		res.add(new ArrayList<Integer>(list));
    		return;
    	} else if (n == 0 && list.size() != k) {
    		return;
    	} else if (n < 0) {
    		return;
    	}

    	for (int i = pos; i <= 9; i++) {
    		list.add(i);
    		dfs(res, list, i + 1, k, n - i);
    		list.remove(list.size() - 1);
    	}
    }
}