/*Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.*/
// Any Char solution
public class Solution {
        public boolean isAnagram(String s, String t) {
            if (s.length()!=t.length()) return false;
            int[] c=new int[256];
            for (int i=0; i<s.length(); ++i){
                c[s.charAt(i)]++; 
                c[t.charAt(i)]--;
            }

            for (int i=0; i<256; ++i){
                if (c[i]!=0) return false;
            }
            return true;
        }
}


public class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        Arrays.sort(sArray);
        Arrays.sort(tArray);
        for (int i = 0; i < sArray.length; i++) {
            if (sArray[i] != tArray[i]) {
                return false;
            }
        }
        return true;
    }
}


public class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (char schar : s.toCharArray()) {
            if (!map.containsKey(schar)) {
                map.put(schar, 1);
            } else {
                map.put(schar, map.get(schar) + 1);
            }
        }

        for (char tchar : t.toCharArray()) {
            if (!map.containsKey(tchar)) {
                return false;
            } else {
                map.put(tchar, map.get(tchar) - 1);
                if (map.get(tchar) == 0) {
                    map.remove(tchar);
                }
            }
        }
        return true;
    }
}


// Discussion Solution
public class Solution {
  public boolean isAnagram(String s, String t) {
      int[] alphabet = new int[26];
      for (int i = 0; i < s.length(); i++) alphabet[s.charAt(i) - 'a']++;
      for (int i = 0; i < t.length(); i++) {
        alphabet[t.charAt(i) - 'a']--;
        if(alphabet[t.charAt(i) - 'a'] < 0) return false;
      }
      for (int i : alphabet) if (i != 0) return false;
      return true;
  }
}


// Another pass
public class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        int[] count = new int[26];
        for(int i=0;i<s.length();i++){
            count[s.charAt(i)-'a']++;
            count[t.charAt(i)-'a']--;
        }
        for(int i:count){
            if(i!=0){
                return false;
            }
        }
        return true;
    }
}