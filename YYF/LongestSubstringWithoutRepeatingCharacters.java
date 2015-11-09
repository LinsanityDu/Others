/*Given a string, find the length of the longest substring without repeating characters. For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.*/

	public class Solution {
	    public int lengthOfLongestSubstring(String s) {
	        if (s == null || s.length() == 0) {
	            return 0;
	        }
	        int res = 0;
	        int left = 0;
	        Set<Character> set = new HashSet<Character>();
	        for (int i = 0; i < s.length(); i++) {
	            if (!set.contains(s.charAt(i))) {
	                set.add(s.charAt(i));
	                res = Math.max(res, i - left + 1);
	            } else {
	                while (left < i && s.charAt(left) != s.charAt(i)) {
	                    set.remove(s.charAt(left));
	                    left++;
	                }
	                left++;
	            }
	        }
	        return res;
	    }
	}



	public class Solution {
	    //动态规划
	    public int lengthOfLongestSubstring(String s) {
	        if(s==null) return 0;
	        char[] c=s.toCharArray();
	        if(c.length==0) return 0;
	        int maxsublength=1;
	        int barrier=0;
	        for(int i=1;i<c.length;i++)
	        {
	            for(int j=i-1;j>=barrier;j--)
	            {
	                if(c[i]==c[j])
	                {
	                    barrier=j+1;
	                    break;
	                }
	            }
	            maxsublength=Math.max(maxsublength,i-barrier+1);
	        }
	        return maxsublength;
	    }
	}