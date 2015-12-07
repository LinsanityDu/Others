/*Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB */

// Discussion
public class Solution {
    public String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();

        while(n>0){
            n--;
            result.insert(0, (char)('A' + n % 26));
            n /= 26;
        }

        return result.toString();
    }
}


public class Solution {
    public String convertToTitle(int n) {
        if (n <= 0) return "";
        StringBuilder res = new StringBuilder();
        while ((n - 1) / 26 > 0) {
            int temp = (n - 1) % 26;
            res.insert(0, (char)('A' + temp));
            n = (n - 1) / 26;
        }
        res.insert(0, (char)('A' + (n - 1)));
        return res.toString();
    }
}

// Discuss2
public String convertToTitle(int n) {
        String res = "";
        while(n != 0) {
            res = (char)('A' + (n - 1) % 26) + res;
            n = (n - 1) / 26;
        }
        return res;
}

// One Line
return n == 0 ? "" : convertToTitle(--n / 26) + (char)('A' + (n % 26));