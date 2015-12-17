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

state: f[i][j][t]前i个数取j个数出来能否和为t

function: f[i][j][t] = f[i - 1][j - 1][t - a[i]] or

 f[i - 1][j][t]

1. 问是否可行 (DP) - f[x][0][0] = true

2. 问方案总数 (DP) - f[x][0][0] = 1

3. 问所有方案 (递归/搜索)

// K Sum How many solutions?
public class Solution {
    /**
     * @param A: an integer array.
     * @param k: a positive integer (k <= length(A))
     * @param target: a integer
     * @return an integer
     */
    public int  kSum(int A[], int k, int target) {
        int n = A.length;
        int[][][] f = new int[n + 1][k + 1][target + 1];
        for (int i = 0; i < n + 1; i++) {
            f[i][0][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k && j <= i; j++) {
                for (int t = 1; t <= target; t++) {
                    f[i][j][t] = 0;
                    if (t >= A[i - 1]) {
                        f[i][j][t] = f[i - 1][j - 1][t - A[i - 1]];
                    }
                    f[i][j][t] += f[i - 1][j][t];
                } // for t
            } // for j
        } // for i
        return f[n][k][target];
    }
}