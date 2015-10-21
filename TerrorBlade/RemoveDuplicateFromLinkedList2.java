/*Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.*/


public class Solution {
    public List<String> letterCombinations(String digits) {
        ArrayList<String> result = new ArrayList<String>();

        if(digits==null) {
            return null;
        } else if(digits.length()==0) {
            //String str=hashTable.get(digits);
            //result.add("");
            return result;
        } 

        Map<Character, char[]> map = new HashMap<Character, char[]>();
        map.put('0', new char[] {});
        map.put('1', new char[] {});
        map.put('2', new char[] { 'a', 'b', 'c' });
        map.put('3', new char[] { 'd', 'e', 'f' });
        map.put('4', new char[] { 'g', 'h', 'i' });
        map.put('5', new char[] { 'j', 'k', 'l' });
        map.put('6', new char[] { 'm', 'n', 'o' });
        map.put('7', new char[] { 'p', 'q', 'r', 's' });
        map.put('8', new char[] { 't', 'u', 'v'});
        map.put('9', new char[] { 'w', 'x', 'y', 'z' });

        StringBuilder sb = new StringBuilder();
        helper(map, digits, sb, result);

        return result;
    }
    
     private void helper(Map<Character, char[]> map, String digits, 
        StringBuilder sb, ArrayList<String> result) {
        if (sb.length() == digits.length()) {
            result.add(sb.toString());
            return;
        }

        for (char c : map.get(digits.charAt(sb.length()))) {
            sb.append(c);
            helper(map, digits, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    
}

// Discussion

public class Solution {
        public static List<String> letterCombinations(String digits) {
            String digitletter[] = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
            List<String> result = new ArrayList<String>();

            if (digits.length()==0) return result;

            result.add("");
            for (int i=0; i<digits.length(); i++) 
                result = combine(digitletter[digits.charAt(i)-'0'],result);

            return result;
        }

        public static List<String> combine(String digit, List<String> l) {
            List<String> result = new ArrayList<String>();

            for (int i=0; i<digit.length(); i++) 
                for (String x : l) 
                    result.add(x+digit.charAt(i));

            return result;
        }
    }