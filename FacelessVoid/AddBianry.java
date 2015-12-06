/*Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".*/


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


public String addBinary(String a, String b) {
    int lena = a.length();
    int lenb = b.length();
    int i =0, carry = 0;
    String res = "";
    while(i<lena || i<lenb || carry!=0){
        int x = (i<lena) ? Character.getNumericValue(a.charAt(lena - 1 - i)) : 0;
        int y = (i<lenb) ? Character.getNumericValue(b.charAt(lenb - 1 - i)) : 0;
        res = (x + y + carry)%2 + res;
        carry = (x + y + carry)/2;
        i++;
    }
    return res;
}


// Add 任意进制
string stringAddition(string s1, string s2) {
    if(s1.length() > s2.length()) return stringAddition(s2, s1); // s2's length is longer than s1
    string result(s2.length() + 1, '0');
    int i = s1.length() - 1, j = s2.length() - 1;
    int carry = 0;
    for(int k = result.length() - 1; k >= 0; –k) {
        int a = i >= 0 ? s1[i–] - '0' : 0;
        int b = j >= 0 ? s2[j–] - '0' : 0;
        result[k] = (a + b + carry) % 10 + '0';
        carry = (a + b + carry) / 10;
    }

    if(result[0] == '0') return result.substr(1);
    else return result;
}

int  main() {
    string s1 = "9", s2 = "99";
    cout << stringAddition(s1, s2) << endl;

    return 0;
}