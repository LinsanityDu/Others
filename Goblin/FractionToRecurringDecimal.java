/*Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".*/

/*The important thing is to consider all edge cases while thinking this problem through, including: negative integer, possible overflow, etc.

Use HashMap to store a remainder and its associated index while doing the division so that whenever a same remainder comes up, we know there is a repeating fractional part.

Please comment if you see something wrong or can be improved. Cheers!*/

public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        if (denominator == 0) return "";
        
        StringBuilder res = new StringBuilder();
        // "+" or "-"
        res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);
        
        
        res.append(num / den);
        num %= den;
        if (num == 0) {
            return res.toString();
        }
        res.append(".");
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        Long r = num % den;
        while (r > 0) {
            if (map.containsKey(r)) { // remainder appeared before
                res.insert(map.get(r), "("); // insert an open paren
                res.append(")"); // append a close paren
                break;
            }
            map.put(r, res.length());
            r *= 10;
            res.append(r / den);
            r = r % den;
        }
        return res.toString();
    }
}


