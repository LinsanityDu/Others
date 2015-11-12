/*maximum palindrome subarray. For example: [4, 4, 1, 2, 4, 4] should return 5(4, 4, 1, 4, 4 or 4, 4, 2, 4, 4)*/

// DP
/*Given a sequence, find the length of the longest palindromic subsequence in it. For example, if the given sequence is “BBABCBCAB”, then the output should be 7 as “BABCBAB” is the longest palindromic subseuqnce in it. “BBBBB” and “BBCBB” are also palindromic subsequences of the given sequence, but not the longest ones.

The naive solution for this problem is to generate all subsequences of the given sequence and find the longest palindromic subsequence. This solution is exponential in term of time complexity. Let us see how this problem possesses both important properties of a Dynamic Programming (DP) Problem and can efficiently solved using Dynamic Programming.*/

/*1) Optimal Substructure: 
Let X[0..n-1] be the input sequence of length n and L(0, n-1) be the length of the longest palindromic subsequence of X[0..n-1].

If last and first characters of X are same, then L(0, n-1) = L(1, n-2) + 2.
Else L(0, n-1) = MAX (L(1, n-1), L(0, n-2)).

Following is a general recursive solution with all cases handled.

// Everay single character is a palindrom of length 1
L(i, i) = 1 for all indexes i in given sequence

// IF first and last characters are not same
If (X[i] != X[j])  L(i, j) =  max{L(i + 1, j),L(i, j - 1)} 

// If there are only 2 characters and both are same
Else if (j == i + 1) L(i, j) = 2  

// If there are more than two characters, and first and last 
// characters are same
Else L(i, j) =  L(i + 1, j - 1) + 2 */

/*http://www.geeksforgeeks.org/dynamic-programming-set-12-longest-palindromic-subsequence/*/


public int longestPalindromeSubsequence(int[] nums) {
	if (nums == null || nums.length == 0) {
		return 0;
	}
	int len = nums.length;
	int[][] dp = new int[len][len];
   // Strings of length 1 are palindrome of lentgh 1
	for (int i = 0; i < len; i++) {
		dp[i][i] = 1;
	}
    // Build the table. Note that the lower diagonal values of table are
    // useless and not filled in the process. The values are filled in a
    // manner similar to Matrix Chain Multiplication DP solution (See
    // http://www.geeksforgeeks.org/archives/15553). cl is length of
    // substring
    for (int l = 2; l <= len; l++) {
    	for (int i = 0; i < len - l + 1; i++) {
    		int j = i + l - 1;
    		if (nums[i] == nums[j] && l = 2) {
    			dp[i][j] = 2;
    		} else if (nums[i] == nums[j]) {
    			dp[i][j] = dp[i + 1][j - 1] + 2;
    		} else {
    			dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
    		}
    	}
    }
    return dp[0][len - 1];


}

// 地里面经
Find the size of longest palindrome subset of an array
感觉就是leetcode Palindrome Partitioning的变形啊public static int longestPalindrome(int[] nums) {
                if (nums == null || nums.length == 0) {
                        return 0;
                }
                int[][] dp = new int[nums.length][nums.length];
                for (int i = 0; i < nums.length; i++) {
                        dp[i][i] = 1;
                }
                for (int len = 2; len <= nums.length; len++) {. from: 1point3acres.com/bbs 
                        for (int i = 0; i <= nums.length - len; i++) {
                                if (nums[i] == nums[i + len - 1]) {
                                        if (i + 1 <= i + len - 2) {
                                                dp[i][i + len - 1] = dp[i + 1][i + len - 2] + 2;
                                        } else {
                                                dp[i][i + len - 1] = 2;
                                        }
                                } else {
                                        if (i + 1 <= i + len - 2) {
                                                dp[i][i + len - 1] = Math.max(dp[i][i + len -2], dp[i + 1][i + len - 1]);. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
                                        } else { 鏉ユ簮涓€浜�.涓夊垎鍦拌鍧�. 
                                                dp[i][i + len - 1] = 1;
                                        }
                                }
                        }
                }
                return dp[0][nums.length - 1];
        }. 鐣欏鐢宠




public static void main(String[] args) {
    int arr[] = new int[] { 4, 1, 2, 3, 4, 5, 6, 5, 4, 3, 4, 4, 4, 4, 4, 4,
        4 };
    int n = arr.length;
    int[][] DP = new int[n + 1][n + 1];
    int[][] backPointers = new int[n + 1][n + 1];
    for (int i = 1; i < DP.length; i++) {
      DP[i][i] = 1;
    }

    for (int l = 2; l <= n; l++) {
      for (int i = 1; i <= n - l + 1; i++) {
        int j = i + l - 1;
        if (arr[i - 1] == arr[j - 1]) {
          DP[i][j] = 2 + DP[i + 1][j - 1];
          backPointers[i][j] = 1;
        } else {
          if (DP[i][j - 1] > DP[i + 1][j]) {
            DP[i][j] = DP[i][j - 1];
          } else {
            DP[i][j] = DP[i + 1][j];
          }
        }
      }
    }
    System.out.println("max palindrome length " + DP[1][n]);
  }


int max (int x, int y) { return (x > y)? x : y; }
 
// Returns the length of the longest palindromic subsequence in seq
int lps(char *str)
{
   int n = strlen(str);
   int i, j, cl;
   int L[n][n];  // Create a table to store results of subproblems
 
 
   // Strings of length 1 are palindrome of lentgh 1
   for (i = 0; i < n; i++)
      L[i][i] = 1;
 
    // Build the table. Note that the lower diagonal values of table are
    // useless and not filled in the process. The values are filled in a
    // manner similar to Matrix Chain Multiplication DP solution (See
    // http://www.geeksforgeeks.org/archives/15553). cl is length of
    // substring
    for (cl=2; cl<=n; cl++)
    {
        for (i=0; i<n-cl+1; i++)
        {
            j = i+cl-1;
            if (str[i] == str[j] && cl == 2)
               L[i][j] = 2;
            else if (str[i] == str[j])
               L[i][j] = L[i+1][j-1] + 2;
            else
               L[i][j] = max(L[i][j-1], L[i+1][j]);
        }
    }
 
    return L[0][n-1];
}