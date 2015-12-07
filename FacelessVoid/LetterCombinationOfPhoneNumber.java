/*Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.



Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:
Although the above answer is in lexicographical order, your answer could be in any order you want.*/
这道题目和求组合的思路差不多，比较简单。依次读取数字，然后把数字可以代表的字符依次加到当前的所有结果中，然后进入下一次迭代。假设总共有n个digit，每个digit可以代表k个字符，那么时间复杂度是O(k^n)，就是结果的数量，空间复杂度也是一样。代码如下： 代码如下： 

// NineChapter
public class Solution {
    public ArrayList<String> letterCombinations(String digits) {
        ArrayList<String> result = new ArrayList<String>();

        if (digits == null || digits.equals("")) {
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


// Iterative
/*Very good solution!! Thumb up! This is a iterative solution. For each digit added, remove and copy every element in the queue and add the possible letter to each element, then add the updated elements back into queue again. Repeat this procedure until all the digits are iterated.*/
public class Solution {
    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if (digits == null || digits.equals("")) {
            return ans;
        }
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for(int i =0; i<digits.length();i++){
            int x = Character.getNumericValue(digits.charAt(i));
            while(ans.peek().length()==i){
                String t = ans.remove();
                for(char s : mapping[x].toCharArray())
                    ans.add(t+s);
            }
        }
        return ans;
    }
}

// Another BFS
public List<String> letterCombinationsBFS(String digits) {
    List<String> res = new LinkedList<String>();
    if (null == digits || digits.length() == 0) {
        return res;
    }
    res.add("");
    String[] keyboards = new String[]{" ", "", "abc", "def",
            "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    for (int i = 0; i < digits.length(); i++) {
        String keyboard = keyboards[digits.charAt(i) - '0'];
        List<String> list = new LinkedList<String>();
        for (String cur : res) {
            for (int k = 0; k < keyboard.length(); k++) {
                list.add(cur + keyboard.charAt(k));
            }
        }
        res = list;
    }
    return res;
}


// Another Discussions
public ArrayList<String> letterCombinations(String digits) {
    ArrayList<String> res = new ArrayList<String>();
        res.add("");
    if(digits==null || digits.length()==0)
        return res;
    for(int i=0;i<digits.length();i++)
    {
        String letters = getLetters(digits.charAt(i));
        ArrayList<String> newRes = new ArrayList<String>();
        for(int j=0;j<res.size();j++)
        {
            for(int k=0;k<letters.length();k++)
            {    
                newRes.add(res.get(j)+Character.toString(letters.charAt(k)));
            }
        }
        res = newRes;
    }
    return res;
}
private String getLetters(char digit)
{
    switch(digit)
    {
        case '2':
            return "abc";
        case '3':
            return "def";
        case '4':
            return "ghi";
        case '5':
            return "jkl";
        case '6':
            return "mno";
        case '7':
            return "pqrs";
        case '8':
            return "tuv";
        case '9':
            return "wxyz";
        case '0':
            return " ";
        default:
            return "";
    }
}