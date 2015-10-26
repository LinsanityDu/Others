/*Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] */

public class Solution {
    /**
     * @param candidates: A list of integers
     * @param target:An integer
     * @return: A list of lists of integers
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // write your code here
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (candidates == null) {
            return result;
        }
        Arrays.sort(candidates);
        helper(result, new ArrayList<Integer>(), candidates, 0, target);
        return result;
    }
    
    private static void helper(List<List<Integer>> result, List<Integer> list, 
    int[] candidates, int pos, int target) {
        if (target == 0) {
            result.add(new ArrayList<Integer>(list));
            return;
        }
        int prev = -1;
        for (int i = pos; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }
            //if (prev)
            list.add(candidates[i]);
            helper(result, list, candidates, i, target - candidates[i]);
            list.remove(list.size() - 1);
        }
    }
}



public class Solution {
    public  ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (candidates == null) {
            return result;
        }

        ArrayList<Integer> path = new ArrayList<Integer>();
        Arrays.sort(candidates);
        helper(candidates, target, path, 0, result);

        return result;
    }

     void helper(int[] candidates, int target, ArrayList<Integer> path, int index,
        ArrayList<ArrayList<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<Integer>(path));
            return;
        }

        int prev = -1;
        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }

            if (prev != -1 && prev == candidates[i]) {
                continue;
            }

            path.add(candidates[i]);
            helper(candidates, target - candidates[i], path, i, result);
            path.remove(path.size() - 1);

            prev = candidates[i];
        }
    }
}





