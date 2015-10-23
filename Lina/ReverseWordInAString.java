/*Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

*/

// My solution
public class Solution {
    public String reverseWords(String s) {
        String str = s.trim();
        if (str.length() == 0) return str;
        String result = "";
        int left = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') continue;
            result = " " + str.substring(left, i) + result;
            while (str.charAt(i) == ' ') i++;
            left = i;
        }
        result = str.substring(left, str.length()) + result;
        return result;
    }
}

// Nine Chapter

public class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        String[] array = s.split(" ");
        StringBuilder sb = new StringBuilder();

        for (int i = array.length - 1; i >= 0; --i) {
            if (!array[i].equals("")) {
                sb.append(array[i]).append(" ");
            }
        }

        //remove the last " "
        return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
    }
}