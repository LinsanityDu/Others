/*Related to question Excel Sheet Column Title

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 */

找到公式以后从前向后遍历一次。这种题目估计可能会出个数据结构之类的题结合excel column number一起考。

Time Complexity - O(n)， Space Complexity - O(1)。


public int titleToNumber(String s) {
    int result = 0;
    for(int i = 0 ; i < s.length(); i++) {
      result = result * 26 + (s.charAt(i) - 'A' + 1);
    }
    return result;
}




public class Solution {
    public int titleToNumber(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        int res = 0;
        int start = 0;
        int base = 1;
        while (length > 1) {
            base *= 26;
            length--;
        }
        length = s.length();
        while (length > 1) {
            char temp = s.charAt(start);
            res += (temp - 'A' + 1) * base;
            length--;
            start++;
            base /= 26;
        }
        res += s.charAt(start) - 'A' + 1;
        return res;
    }
}


// Java One Line
public class Solution {
    public int titleToNumber(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); result = result * 26 + (s.charAt(i) - 'A' + 1), i++);
        return result;
    }
}

