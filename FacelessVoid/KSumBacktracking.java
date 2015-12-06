/*Given n unique integers, number k (1<=k<=n) and target.

Find all possible k integers where their sum is target.*/

public class Solution {
    /**
     * @param A: an integer array.
     * @param k: a positive integer (k <= length(A))
     * @param target: a integer
     * @return a list of lists of integer 
     */ 
    public ArrayList<ArrayList<Integer>> kSumII(int A[], int k, int target) {
        // write your code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (A == null || A.length == 0 || k <= 0) {
            return result;
        }
        ArrayList<Integer> path = new ArrayList<Integer>();
        helper(result, path, A, target, 0, k);
        return result;
    }
    
    private void helper(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> path,
    int[] A, int target, int pos, int k) {
        if (target == 0 && path.size() == k) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        
        for (int i = pos; i < A.length; i++) {
            if (A[i] > target) {
                break;
            }
            path.add(A[i]);
            helper(result, path, A, target - A[i], i + 1, k);
            path.remove(path.size() - 1);
        }
    }
}

