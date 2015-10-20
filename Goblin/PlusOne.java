/*Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.*/

public class Solution {
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            int[] res = new int[]{0};
        }
        if (digits[digits.length - 1] != 9) {
            digits[digits.length - 1] += 1;
            return digits;
        }
        digits[digits.length - 1] = 0;
        int len = digits.length - 2;
        int carry = 1;
        while (len >= 0 && carry == 1) {
            int temp = digits[len] + carry;
            digits[len] = temp % 10;
            carry = temp / 10;
            len--;
        }
        if (len < 0 && carry == 1) {
            int[] result = new int[digits.length + 1];
            result[0] = carry;
            for (int i = 1; i < digits.length + 1; i++) {
                result[i] = digits[i - 1];
            }
            return result;
        } 
        return digits;
    }
}


// Nice small trick
public int[] plusOne(int[] digits) {
    for (int i = digits.length - 1; i >=0; i--) {
        if (digits[i] != 9) {
            digits[i]++;
            break;
        } else {
            digits[i] = 0;
        }
    }
    if (digits[0] == 0) {
        int[] res = new int[digits.length+1];
        res[0] = 1;
        return res;
    }
    return digits;
}