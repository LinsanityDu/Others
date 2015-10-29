/*Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

*/


AnotherVersion:
Given a string , " This is a test" reverse it: " tset a si siht" 
Do this recursively.

class StringReverse {
    public static void main(String []args) {
        System.out.println(revString("This is a test"));
    }
    
    static String revString(String str) {
        System.out.println(str);

        int length = str.length()-1; 

        if ( length ==0 )   
            return String.valueOf(str.charAt(0));

        if ( length == 1 )
            return String.valueOf(str.charAt(1)) + String.valueOf(str.charAt(0));

        return String.valueOf(str.charAt(length)) + revString(str.substring(1, length)) + String.valueOf(str.charAt(0));
    }
}



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