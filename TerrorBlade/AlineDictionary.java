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
        Map<Character, Set<Character>> graph = new HashMap<>();
        for(int i = 0; i < words.length; i++) {
            for(int j = 0; j < words[i].length(); j++) {
                if(!graph.containsKey(words[i].charAt(j))) {
                    graph.put(words[i].charAt(j), new HashSet<Character>());  
                }
            }
            if(i > 0) {
                getOrder(words[i - 1], words[i], graph);
            }
        }
        return topSort(graph);
    }

    private String topSort(Map<Character, Set<Character>> graph) {
        StringBuilder sb = new StringBuilder();
        Map<Character, Integer> indegree = new HashMap<>();
        Queue<Character> queue = new LinkedList<>();
        for(char c : graph.keySet()) {
            for(char a : graph.get(c)) {
                int count = indegree.containsKey(a) ? indegree.get(a) + 1 : 1;
                indegree.put(a, count);
            }
        }
        for(char c : graph.keySet()) {
            if(!indegree.containsKey(c)) {
                queue.offer(c);
            }
        }
        while(!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);
            for(char a : graph.get(c)) {
                indegree.put(a, indegree.get(a) - 1);
                if(indegree.get(a) == 0) {
                    queue.offer(a);
                }
            }
        }
        for(int a : indegree.values()) {
            if(a != 0) return "";
        }
        return sb.toString();
    }

    private void getOrder(String s, String t, Map<Character, Set<Character>> graph) {
        for(int i = 0; i < Math.min(s.length(), t.length()); i++) {
            char c1 = s.charAt(i), c2 = t.charAt(i);
            if(c1 != c2) {
                if(!graph.get(c1).contains(c2)) {
                    graph.get(c1).add(c2);
                }
                break;
            }
        }
    }
}