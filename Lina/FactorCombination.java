// Leetcode
public class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (n <= 1) return result;
        dfs(result, new ArrayList<Integer>(), n, 2);
        return result;
    }
    
    private void dfs(List<List<Integer>> result, List<Integer> list, int n, int start) {
        if (n <= 1) {
            if (list.size() > 1) {
                result.add(new ArrayList<Integer>(list));
            }
            return;
        }
        for (int i = start; i <= n; i++) {
            if (n % i == 0) {
                list.add(i);
                dfs(result, list, n / i, i);
                list.remove(list.size() - 1);  
            }
        }
    }
}


import java.util.*;
public class Test {
    public List<List<Integer>> factorCombinations(int n) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        helper(ans, n, n/2, new ArrayList<Integer>());
        return ans;
    }
    private void helper(List<List<Integer>> ans, int num, int largestFactor, List<Integer> path) {
        if (num == 1) {
            ans.add(new ArrayList<Integer>(path));
            return;
        }
        for (int i = largestFactor; i > 1; i--) {
            if (num % i == 0) {
                path.add(i);
                helper(ans, num / i, i, path);
                path.remove(path.size() - 1);
            }
        }
    }
    public static void main(String[] args) {
        Test t = new Test();
        List<List<Integer>> l = t.factorCombinations(24);
        for (List<Integer> li : l) {
            for (Integer i : li) {
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }
}