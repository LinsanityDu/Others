/*Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.*/

	public class Solution {
	    public String longestPalindrome(String s) {
	        if (s.length() <= 1) {
	    		return s;
	    	}
	    	int max = 1;
	    	int low, high;
	    	int[] pos = new int[2];
	    	for (int i = 1; i < s.length(); i++) {
	    		// find the even length substring whose center (right) is i
	    		low = i - 1;
	    		high = i;
	    		while (low >= 0 && high < s.length() && s.charAt(low) == s.charAt(high)) {
	    			if (high - low + 1 > max) {
	    				pos[0] = low;
	    				pos[1] = high;
	    				max = high - low + 1;
	    			}
	    			low--;
	    			high++;
	    		}

	    		//find the odd length substring whose center is i
	    		low = i - 1;
	    		high = i + 1;
	    		while (low >= 0 && high < s.length() && s.charAt(low) == s.charAt(high)) {
	    			if (high - low + 1 > max) {
	    				pos[0] = low;
	    				pos[1] = high;
	    				max = high - low + 1;
	    			}
	    			low--;
	    			high++;
	    		}
	    	}
	    	return s.substring(pos[0], pos[1] + 1);
	    }
	}



	public class Solution {
	    public String longestPalindrome(String s) {
	        if (s == null || s.length() == 0) {
	            return "";
	        }
	        String longest = "";
	        int len = s.length();
	        // dp[i][j] means substring of s from i to j is palindrome 
	        boolean[][] dp = new boolean[len][len];
	        // why i decrease from (len-1) to 0, but j increase from i to (len-1)?
	        // think about it! 
	        for (int i = len - 1; i >= 0; i--) {
	            for (int j = i; j < len; j++) {
	                if (i == j) {
	                    dp[i][j] = true;
	                } else if (i + 1 == j) {
	                    dp[i][j] = s.charAt(i) == s.charAt(j);
	                } else {
	                    // important to note: dp[i+1][j-1]
	                    // i depends on (i+1), so i from large to small
	                    // j is just the opposite, small to large
	                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i+1][j-1];
	                }
	                if (dp[i][j] && j + 1 - i > longest.length()) {
	                    longest = s.substring(i, j + 1);
	                }
	            }
	        }
	        return longest;
	    }
	}


// Another 
public class Solution {
private int lo, maxLen;

public String longestPalindrome(String s) {
    int len = s.length();
    if (len < 2)
        return s;

    for (int i = 0; i < len-1; i++) {
        extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
        extendPalindrome(s, i, i+1); //assume even length.
    }
    return s.substring(lo, lo + maxLen);
}

private void extendPalindrome(String s, int j, int k) {
    while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
        j--;
        k++;
    }
    if (maxLen < k - j - 1) {
        lo = j + 1;
        maxLen = k - j - 1;
    }
}
}