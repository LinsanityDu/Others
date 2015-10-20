/*A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

For example, the numbers "69", "88", and "818" are all strobogrammatic.*/


public class Solution {
    public boolean isStrobogrammatic(String num) {
        if (num.length() == 0) return true;
        int start = 0;
        int end = num.length() - 1;
        while (start < end) {
            if ((num.charAt(start) == '1' && num.charAt(end) == '1') || (num.charAt(start) == '8' && num.charAt(end) == '8')
            || (num.charAt(start) == '6' && num.charAt(end) == '9') || (num.charAt(start) == '9' && num.charAt(end) == '6')
            || (num.charAt(start) == '0' && num.charAt(end) == '0')) {
                start++;
                end--;
            } else {
                return false;
            }
        }
        if (start == end) {
            return num.charAt(start) == '8' || num.charAt(start) == '1' || num.charAt(start) == '0';
        }
        return true;
    }
}


// Pochman God
public boolean isStrobogrammatic(String num) {
    for (int i=0, j=num.length()-1; i <= j; i++, j--)
        if (!"00 11 88 696".contains(num.charAt(i) + "" + num.charAt(j)))
            return false;
    return true;
}

// Another Map Solution
public boolean isStrobogrammatic(String num) {
    Map<Character, Character> map = new HashMap<Character, Character>();
    map.put('6', '9');
    map.put('9', '6');
    map.put('0', '0');
    map.put('1', '1');
    map.put('8', '8');

    int l = 0, r = num.length() - 1;
    while (l <= r) {
        if (!map.containsKey(num.charAt(l))) return false;
        if (map.get(num.charAt(l)) != num.charAt(r))
            return false;
        l++;
        r--;
    }

    return true;
}


// Switch Case
public class Solution {
    public boolean isStrobogrammatic(String num) {
        int start = 0;
        int end = num.length() - 1;
        while (start <= end) {
            switch(num.charAt(start)) {
                case '0':
                case '1':
                case '8':
                    if (num.charAt(end) != num.charAt(start)) {
                        return false;
                    }
                    break;
                case '6':
                    if (num.charAt(end) != '9') {
                        return false;
                    }
                    break;
                case '9':
                    if (num.charAt(end) != '6') {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
            start++;
            end--;
        }
        return true;
    }
}