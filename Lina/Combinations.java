/*Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]*/

// My Suck Code
public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (k <= 0 || k > n) {
        	return res;
        }
        dfs(n, k, res, new ArrayList<Integer>(), 1);
        return res;
    }

    public void dfs(int n, int k, List<List<Integer>> res, List<Integer> list, int pos) {
    	if (list.size() == k) {
    		res.add(new ArrayList(list));
    		return;
    	}

    	for (int i = pos; i <= n; i++) {
    		list.add(i);
    		dfs(n, k, res, list, i + 1);
    		list.remove(list.size() - 1);
    	}
    }
}


// Discussion iterative solution
/*The idea is to iteratively generate combinations for all lengths from 1 to k. We start with a list of all numbers <= n as combinations for k == 1. When we have all combinations of length k-1, we can get the new ones for a length k with trying to add to each one all elements that are <= n and greater than the last element of a current combination.*/

public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        if (k == 0 || n == 0 || k > n) return Collections.emptyList();
        List<List<Integer>> combs = new ArrayList<>();
        for (int i = 1; i <= n; i++) combs.add(Arrays.asList(i));
        for (int i = 2; i <= k; i++) {
            List<List<Integer>> newCombs = new ArrayList<>();
            for (int j = i; j <= n; j++) {
                for (List<Integer> comb : combs) {
                    if (comb.get(comb.size()-1) < j) {
                        List<Integer> newComb = new ArrayList<>(comb);
                        newComb.add(j);
                        newCombs.add(newComb);
                    }
                }
            }
            combs = newCombs;
        }
        return combs;
    }
}

