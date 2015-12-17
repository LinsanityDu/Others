第一题是字符串去重，只输出第一次出现的字母。

INPUT: "xyzabcxyaaxyd"
OUTPUT: "xyzabcd"
class Solution{
    public String removeD(String s){
        if(s==null || s.length()==0) return s;
        HashMap<Character, Boolean> hs = new HashMap<>();
        StringBuilder result = new StringBuilder();
        for(int i = 0; i<s.length(); i++){
            if(!hs.constainsKey(s.charAt(i)){ 
                hs.put(s.charAt(i), true);
                result.append(s.charAt(i));
            }
        }
        return new String(result);
    }
}