/*There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

For example,
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Note:
You may assume all letters are in lowercase.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.*/


public class Solution {
    public String alienOrder(String[] words) {
        
    }
}




/*Two similar solutions. Both first go through the word list to find letter pairs (a, b) where a must come before b in the alien alphabet. The first solution just works with these pairs, the second is a bit smarter and uses successor lists and predecessor counters. Doesn't make a big difference here, though, I got both accepted in 48 ms.*/

public String alienOrder(String[] words) {
    if(words.length == 1) return words[0];
    HashMap<Character, HashSet<Character>> map = new HashMap<Character, HashSet<Character>>();
    for(int i=1; i<words.length; i++){
        String word1 = words[i-1], word2 = words[i];
        boolean stop = false;
        for(int j=0; j<Math.max(word1.length(), word2.length()); j++){
            if(j < word1.length() && !map.containsKey(word1.charAt(j)))
                map.put(word1.charAt(j), new HashSet<Character>());
            if(j < word2.length()&& !map.containsKey(word2.charAt(j)))
                map.put(word2.charAt(j), new HashSet<Character>());
            if(j < word1.length() && j < word2.length() && !stop && word1.charAt(j) != word2.charAt(j)){
                map.get(word1.charAt(j)).add(word2.charAt(j));
                stop = true;
            }
        }
    }
    return  topoSort(map);
}

public String topoSort(HashMap<Character, HashSet<Character>> map){
    String[] order = new String[]{""};
    HashSet<Character> visited = new HashSet<Character>() , records = new HashSet<Character>();
    for(char key : map.keySet()){
        if(!dfs(map,visited,records,key,order))
            return "";
    }
    StringBuilder sb = new StringBuilder(order[0]);
    return sb.reverse().toString();
}

public boolean dfs(HashMap<Character, HashSet<Character>> map, HashSet<Character> visited, HashSet<Character> records, char cur, String[] order){
    if(records.contains(cur)) return false;
    if(visited.contains(cur)) return true;
    visited.add(cur);
    records.add(cur);
    for(char c : map.get(cur))
        if(!dfs(map,visited,records,c,order))
            return false;
    records.remove(cur);
    order[0] += cur;
    return true;
}




