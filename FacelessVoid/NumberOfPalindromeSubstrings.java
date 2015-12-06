/*Find the number of substrings of a string that are palindromes.*/

另外感觉没必要单算单个字符的数量。
我改了一下：
public static int partition(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int l = i, r = i; l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r); l--, r++) {
                count++;
            }
            for (int l = i, r = i + 1; l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r); l--, r++) {
                count++;
            }
        }
        return count;
    }



    public static int partition(String s) {
        int count = 0;
        // single char is palindrome
        for (int i = 0; i < s.length(); i++) count++;
        for (int i = 1; i < s.length(); i++) {
            // even position
            for (int l = i - 1, r = i; l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r); l--, r++) {
                count++;
            }
            // odd. 
            for (int l = i - 1, r = i + 1; l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r); l--, r++) {
                count++;
            }
        }
        return count;
    }

这叫做DP么？。。。
很直接的思想做一下。这个题目的变形题https://leetcode.com/problems/longest-palindromic-substring/。
设定一个boolean[j]代表i~j是否是一个回文。最后数一下boolean数组里面的true的个数就ok了。
快速的方法是 如果我们已经知道 i-1, j-1是一个回文，并s.charAt(i) == s.charAt(j)的话 那么i j 就是一个回文了。
举个栗子，abba, 已经处理了bb了，存在了flag[1][2], 那么flag[0][3] = flag[1][2] && ('a' == 'a') = true; . more info on 1point3acres.com
所以第一步初始化1和相邻位置
然后枚举长度，依次递推。


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