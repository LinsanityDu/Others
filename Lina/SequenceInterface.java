


/*There is a particular sequence that only uses numbers 1, 2, 3, 4 and no two adjacent numbers are the same. 
Write a program that given n1 1s, n2 2s, n3 3s, n4 4s will output the number of such sequences using all these numbers. 
Output your answer modulo 1000000007 (10^9 + 7).*/
#include <vector>
using namespace std;

class Solution {
public:
    int permutation_dfs(int n1, int n2, int n3, int n4) {. 鍥磋鎴戜滑@1point 3 acres
        return permutation_dfs(n1, n2, n3, n4, 0);. 1point3acres.com/bbs
    }.鐣欏璁哄潧-涓€浜�-涓夊垎鍦�
    .1point3acres缃�
    int permutation_dfs(int n1, int n2, int n3, int n4, int last) {
        if (n1 == 0 && n2 == 0 && n3 == 0 && n4 == 0) return 1;
        
        int cnt = 0;
        if (n1 > 0 && last != 1) {  // 保证不与last重复
            cnt += permutation_dfs(n1 - 1, n2, n3, n4, 1);
        }
        if (n2 > 0 && last != 2) {
            cnt += permutation_dfs(n1, n2 - 1, n3, n4, 2);. from: 1point3acres.com/bbs 
        }
        if (n3 > 0 && last != 3) {. 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
            cnt += permutation_dfs(n1, n2, n3 - 1, n4, 3);
        }
        if (n4 > 0 && last != 4) {
            cnt += permutation_dfs(n1, n2, n3, n4 - 1, 4);
        }
        
        return cnt;
    }-google 1point3acres
    
    int permutation_dp(int n1, int n2, int n3, int n4) {. 鍥磋鎴戜滑@1point 3 acres
        // 令 dp1(n1,n2,n3,n4)是以1结尾的排列数量
        // 令 dp2(n1,n2,n3,n4)是以2结尾的排列数量
        // 令 dp3(n1,n2,n3,n4)是以3结尾的排列数量. From 1point 3acres bbs
        // 令 dp4(n1,n2,n3,n4)是以4结尾的排列数量
        // dp1(n1,n2,n3,n4) = dp2(n1-1,n2,n3,n4) + dp3(n1-1,n2,n3,n4) + dp4(n1-1,n2,n3,n4)
        // dp2(n1,n2,n3,n4) = dp1(n1,n2-1,n3,n4) + dp3(n1,n2-1,n3,n4) + dp4(n1,n2-1,n3,n4)
        // dp3(n1,n2,n3,n4) = dp1(n1,n2,n3-1,n4) + dp2(n1,n2,n3-1,n4) + dp4(n1,n2,n3-1,n4)
        // dp4(n1,n2,n3,n4) = dp1(n1,n2,n3,n4-1) + dp2(n1,n2,n3,n4-1) + dp3(n1,n2,n3,n4-1)
        // 初始条件
        // dp1(1,0,0,0) = 1
        // dp2(0,1,0,0) = 1
        // dp3(0,0,1,0) = 1.1point3acres缃�
        // dp4(0,0,0,1) = 1
        . 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
        vector<vector<vector<vector<vector<int>>>>> dp(4,
                                                        vector<vector<vector<vector<int>>>>(n1 + 1,
                                                        vector<vector<vector<int>>>(n2 + 1,
                                                        vector<vector<int>>(n3 + 1,
                                                        vector<int>(n4 + 1)))));
        . 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
        dp[0][1][0][0][0] = 1;. 1point 3acres 璁哄潧
        dp[1][0][1][0][0] = 1;
        dp[2][0][0][1][0] = 1;
        dp[3][0][0][0][1] = 1;
        for (int i1 = 0; i1 <= n1; i1++) {
            for (int i2 = 0; i2 <= n2; i2++) {
                for (int i3 = 0; i3 <= n3; i3++) {
                    for (int i4 = 0; i4 <= n4; i4++) {
                        // 跳过初始条件
                        if (i1 + i2 + i3 + i4 <= 1) continue;
                        
                        if (i1) {
                            dp[0][i1][i2][i3][i4] = mod(dp[1][i1-1][i2][i3][i4] + dp[2][i1-1][i2][i3][i4] +
                                                        dp[3][i1-1][i2][i3][i4]);
                        }.1point3acres缃�
                        if (i2) {
                            dp[1][i1][i2][i3][i4] = mod(dp[0][i1][i2-1][i3][i4] + dp[2][i1][i2-1][i3][i4] +
                                                        dp[3][i1][i2-1][i3][i4]);-google 1point3acres
                        }
                        if (i3) {
                            dp[2][i1][i2][i3][i4] = mod(dp[0][i1][i2][i3-1][i4] + dp[1][i1][i2][i3-1][i4] +
                                                        dp[3][i1][i2][i3-1][i4]);
                        }
                        if (i4) {. more info on 1point3acres.com
                            dp[3][i1][i2][i3][i4] = mod(dp[0][i1][i2][i3][i4-1] + dp[1][i1][i2][i3][i4-1] +
                                                        dp[2][i1][i2][i3][i4-1]);
                        }
                    }
                }
            }
        }
        
        return mod(dp[0][n1][n2][n3][n4] + dp[1][n1][n2][n3][n4] +-google 1point3acres
                   dp[2][n1][n2][n3][n4] + dp[3][n1][n2][n3][n4]);
    }. 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
    . 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
    int mod(int num) {
        return num % 1000000007;
    }
};

// DP
 public int Sequence2(int n1, int n2, int n3, int n4)
        {
            var dp1 = new int[n1 + 1, n2 + 1, n3 + 1, n4 + 1];
            var dp2 = new int[n1 + 1, n2 + 1, n3 + 1, n4 + 1];
            var dp3 = new int[n1 + 1, n2 + 1, n3 + 1, n4 + 1];
            var dp4 = new int[n1 + 1, n2 + 1, n3 + 1, n4 + 1];

            const int MOD = 1000000007;
            dp1[1, 0, 0, 0] = 1;
            dp2[0, 1, 0, 0] = 1;
            dp3[0, 0, 1, 0] = 1;
            dp4[0, 0, 0, 1] = 1;

            for (int i = 0; i <= n1; i++)
            {
                for (int j = 0; j <= n2; j++)
                {
                    for (int k = 0; k <= n3; k++)
                    {
                        for (int l = 0; l <= n4; l++)                                        {                             
                            if (i + j + k + l > 1)
                            {
                                if (i > 0) dp1[i, j, k, l] = dp2[i - 1, j, k, l] + dp3[i - 1, j, k, l] + dp4[i - 1, j, k, l] % MOD;
                                if (j > 0) dp2[i, j, k, l] = dp1[i, j - 1, k, l] + dp3[i, j - 1, k, l] + dp4[i, j - 1, k, l] % MOD;
                                if (k > 0) dp3[i, j, k, l] = dp2[i, j, k - 1, l] + dp1[i, j, k - 1, l] + dp4[i, j, k - 1, l] % MOD;
                                if (l > 0) dp4[i, j, k, l] = dp2[i, j, k, l - 1] + dp3[i, j, k, l - 1] + dp1[i, j, k, l - 1] % MOD;
                            }
                        }
                    }

                }
            }
            return dp1[n1, n2, n3, n4] + dp2[n1, n2, n3, n4] + dp3[n1, n2, n3, n4] + dp4[n1, n2, n3, n4] % MOD;
        }


package simple;

public class DFLSeq {
	public int numSeq(int ones, int twos, int threes, int fours){
		System.out.println("numSeq " + ones + " " + twos + " " + threes + " " +fours);

		 return numSeqHelper(ones, twos, threes, fours, 0) ; 
	}
	public static void main(String[] args) {
		int[] n = {4, 1, 1, 1};
		DFLSeq  d = new DFLSeq();
		System.out.println( d.numSeq(n[0],n[1],n[2],n[3]));
	}
	public int numSeqHelper(int ones, int twos, int threes, int fours, int last){
		if(ones ==0 && twos ==0 && threes ==0 && fours ==0)
			return 1; 
		System.out.println("" + ones + " " + twos + " " + threes + " " +fours + " last=" +last);
		int onesSeq = 0; 
		int twosSeq = 0;
		int threesSeq = 0;
		int foursSeq = 0;

		if(last!= 1 && ones != 0)
			onesSeq = numSeqHelper(ones-1, twos, threes, fours, 1); 
		if(last!= 2 && twos != 0)
			twosSeq = numSeqHelper(ones, twos-1, threes, fours, 2); 
		if(last!= 3 && threes != 0)
			threesSeq = numSeqHelper(ones, twos, threes-1, fours, 3); 
		if(last!= 4 && fours != 0)
			foursSeq = numSeqHelper(ones, twos, threes, fours-1, 4); 

		int res= onesSeq +twosSeq+ threesSeq + foursSeq;  
		System.out.println("res="+res);
		return res;
	}
}