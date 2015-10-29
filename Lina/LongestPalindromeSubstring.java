// O(n^2) time and O(1) space
// Given a center, either one letter or two letter, 
// Find longest palindrome
private String expandCenter(String s, int left, int right) {
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
        --left;
        ++right;
    }
    ++left;
    return s.substring(left, right);
}

public int longestPalindromicSubstring(String s) {
    if (s == null || s.isEmpty()) return 0; //return "" here if it wants the actual string
    String max = s.substring(0,1);
    for (int i = 0; i < s.length()-1; ++i) { //it could be no "-1", also correct
        // get longest palindrome with center of i, i+1
        String palindrome = expandCenter(s, i, i+1);
        max = palindrome.length() > max.length() ? palindrome : max;
        // get longest palindrome with center of i
        palindrome = expandCenter(s, i, i);
        max = palindrome.length() > max.length() ? palindrome : max;
    }
    return max.length();//return max here if it wants  the max string
}