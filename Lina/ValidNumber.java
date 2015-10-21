/*Write a function to determine if a string is a number without using any built-in function.*/
public bool IsNumber(String s) {
	    if (s == null || s.length() == 0 || s.trim().length() == 0) {
            return false;
        }
        s = s.trim();
        int len = s.length();
        int start = 0;
        int end = len - 1;
        if (s.charAt(start) == '+' || s.charAt(start) == '-') start++;
        boolean num = false;
        boolean dot = false;
        while (start <= end) {
            char c = s.charAt(start);
            if (Character.isDigit(c)) {
                num = true;
            } else if (c == '.') {
                if (dot) return false;
                dot = true;
			} else if (c == '+' || c == '-') {
                return false;
            } else {
                return false;
            }
            start++;
        }
        return num;
}
// Test Case


public class Solution {
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0 || s.trim().length() == 0) {
            return false;
        }
        s = s.trim();
        int len = s.length();
        int start = 0;
        int end = len - 1;
        if (s.charAt(start) == '+' || s.charAt(start) == '-') start++;
        boolean num = false;
        boolean dot = false;
        boolean exp = false;
        while (start <= end) {
            char c = s.charAt(start);
            if (Character.isDigit(c)) {
                num = true;
            } else if (c == '.') {
                if (exp || dot) return false;
                dot = true;
            } else if (c == 'e') {
                if (exp || num == false) return false;
                exp = true;
                num = false;
            } else if (c == '+' || c == '-') {
                if (s.charAt(start - 1) != 'e') return false;
            } else {
                return false;
            }
            start++;
        }
        return num;
    }
}