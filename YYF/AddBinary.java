/*Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".*/


public class Solution {
    public String addBinary(String a, String b) {
            if (a == null || b == null) {
                return null;
            }
            
            int ia = a.length() - 1;
            int ib = b.length() - 1;
            
            StringBuilder sb = new StringBuilder();
            
            int carry = 0;
            while (ia >= 0 || ib >= 0 || carry == 1) {
                int sum = carry;
                if (ia >= 0) {
                    sum += a.charAt(ia) - '0';
                    ia--;
                }
                
                if (ib >= 0) {
                    sum += b.charAt(ib) - '0';
                    ib--;
                }
                
                carry = sum / 2;
                sum %= 2;
                sb.insert(0, sum);
            }
            
            return sb.toString();
    }
}

// Discussion
public class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() -1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }
}