/*Given a string, determine if a permutation of the string could form a palindrome.

For example,
"code" -> False, "aab" -> True, "carerac" -> True.*/

// My suck code
public class Solution {
    public boolean canPermutePalindrome(String s) {
        if (s.length() == 0) return true;
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            if (!map.containsKey(ch)) {
                map.put(ch, 1);
            } else {
                map.put(ch, map.get(ch) + 1);
            }
        }
        
        boolean only = true;
        for (char ch : map.keySet()) {
            int number = map.get(ch);
            if (number % 2 != 0) {
                if (only) {
                    only = false;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}

// Good solution
public class Solution {
    public boolean canPermutePalindrome(String s) {
        Set<Character> set=new HashSet<Character>();
        for(int i=0; i<s.length(); ++i){
            if (!set.contains(s.charAt(i)))
                set.add(s.charAt(i));
            else 
                set.remove(s.charAt(i));
        }
        return set.size()==0 || set.size()==1;
    }
}