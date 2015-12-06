/*Given two numbers represented as strings, return multiplication of the numbers as a string.

Note: The numbers can be arbitrarily large and are non-negative.*/

 		if (num1 == null || num2 == null) {
            return null;
        }
        
        int len1 = num1.length(), len2 = num2.length();
        int len3 = len1 + len2;
        int i, j, product, carry;

        int[] num3 = new int[len3];
        for (i = len1 - 1; i >= 0; i--) {
            carry = 0;
            for (j = len2 - 1; j >= 0; j--) {
                product = carry + num3[i + j + 1] +
                    Character.getNumericValue(num1.charAt(i)) *
                    Character.getNumericValue(num2.charAt(j));
                num3[i + j + 1] = product % 10;
                carry = product / 10;
            }
            num3[i + j + 1] = carry;
        }

        StringBuilder sb = new StringBuilder();
        i = 0;

        while (i < len3 - 1 && num3[i] == 0) {
            i++;
        }

        while (i < len3) {
            sb.append(num3[i++]);
        }

        return sb.toString();

1 建立数组，双层循环遍历两个string，把单位的乘积累加到数组相应的位置
2 处理进位并输出
3 注意前导零的corner case

public class Solution {
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return null;
        }
        
        int len1 = num1.length();
        int len2 = num2.length();
        
        int[] product = new int[len1 + len2];
        
        // 计算相应位置的product.
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                // 注意，这里要使用+=以不断累加乘积
                product[i + j] += (num1.charAt(len1 - 1 - i) - '0') * (num2.charAt(len2 - 1 - j) - '0');
            }
        }
        
        StringBuilder ret = new StringBuilder();
        
        int carry = 0;
        // 计算进位
        for (int i = 0; i < len1 + len2; i++) {
            product[i] = product[i] + carry;
            int digit = product[i] % 10;
            carry = product[i] / 10;
            ret.insert(0, digit);
        }
        
        // 去掉前导0
        while (ret.length() > 1 && ret.charAt(0) == '0') {
            ret.deleteCharAt(0);
        }
        
        return ret.toString();
    }
}