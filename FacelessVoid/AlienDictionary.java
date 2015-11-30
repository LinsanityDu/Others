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
// Java BFS Good Solution
public String alienOrder(String[] words) {
    Map<Character, Set<Character>> map=new HashMap<Character, Set<Character>>();
    Map<Character, Integer> degree=new HashMap<Character, Integer>();
    String result="";
    if(words==null || words.length==0) return result;
    for(String s: words){
        for(char c: s.toCharArray()){
            degree.put(c,0);
        }
    }
    for(int i=0; i<words.length-1; i++){
        String cur=words[i];
        String next=words[i+1];
        int length=Math.min(cur.length(), next.length());
        for(int j=0; j<length; j++){
            char c1=cur.charAt(j);
            char c2=next.charAt(j);
            if(c1!=c2){
                Set<Character> set=new HashSet<Character>();
                if(map.containsKey(c1)) set=map.get(c1);
                if(!set.contains(c2)){
                    set.add(c2);
                    map.put(c1, set);
                    degree.put(c2, degree.get(c2)+1);
                }
                break;
            }
        }
    }
    Queue<Character> q=new LinkedList<Character>();
    for(char c: degree.keySet()){
        if(degree.get(c)==0) q.add(c);
    }
    while(!q.isEmpty()){
        char c=q.remove();
        result+=c;
        if(map.containsKey(c)){
            for(char c2: map.get(c)){
                degree.put(c2,degree.get(c2)-1);
                if(degree.get(c2)==0) q.add(c2);
            }
        }
    }
    if(result.length()!=degree.size()) return "";
    return result;
}

// Java DFS
public String alienOrder(String[] words) {
    Map<Character, List<Character>> graph = new HashMap<>();
    String ans = null;
    Set<Character> charSet = new HashSet<>(); //Maintain a set of chars that are not in the graph.
    buildGraph(words, graph, charSet);
    ans = toposortDFS(graph, charSet);
    return ans;
}

private Set<Character> getCharSet(String[] words, Set<Character> charSet) {
    for (String word : words) {
        for (char c : word.toCharArray()) {
            charSet.add(c);
        }
    }
    return charSet;
}

private String toposortDFS(Map<Character, List<Character>> graph, Set<Character> charSet) {
    StringBuffer ans = new StringBuffer();
    boolean[] visited = new boolean[26]; //permanent marker.
    boolean[] tempMark = new boolean[26]; //temporary marker

    for (Character v : graph.keySet()) {
        if (!visited[v - 'a']) {
            if (!visitDFS(ans, graph, visited, v, tempMark)) {
                return "";
            }               
        }   
    }

    for (Character c : charSet) { //insert orphan chars.
        ans.insert(0, c);
    }
    return ans.reverse().toString();
}

private boolean visitDFS(StringBuffer ans, Map<Character, List<Character>> graph, boolean[] visited,
        Character node, boolean[] marked) {
    if (marked[node - 'a']) {  //if temporally marked already, it is not DAG. return failure(false);
        return false;
    } 
    if (!visited[node - 'a']) {
        marked[node - 'a'] = true; //mark the current node temporally.
        List<Character> adjList = graph.get(node);
        if (adjList != null) {
            for (Character m : graph.get(node)) {
                if (!visitDFS(ans, graph, visited, m, marked))
                    return false;
            }
        }
        //there is no adjacent node, it is a leaf, output the node(char).
        visited[node - 'a'] = true; //mark the node permanently.
        marked[node - 'a'] = false; //remove the temp mark.
        ans.append(node);
    }
    return true;
}

private void buildGraph(String[] words, Map<Character, List<Character>> graph, Set<Character> charSet) {

    getCharSet(words, charSet); //Find all chars in the words.

    for (int i = 0; i < words.length - 1; i++) {
        for (int j = i + 1; j < words.length; j++) {
            for (int k = 0; k < Math.min(words[i].length(), words[j].length()); k++) {
                if (words[i].charAt(k) != words[j].charAt(k)) {

                    charSet.remove(words[i].charAt(k)); //Remove the chars that are in the graph from charSet.
                    charSet.remove(words[j].charAt(k));

                    if (graph.containsKey(words[i].charAt(k))) {
                        List<Character> adjList = graph.get(words[i].charAt(k));
                        if (!adjList.contains(words[j].charAt(k)))
                            graph.get(words[i].charAt(k)).add(words[j].charAt(k));
                    } else {
                        List<Character> adjList = new ArrayList<Character>();
                        adjList.add(words[j].charAt(k));
                        graph.put(words[i].charAt(k), adjList);
                    }
                    break;
                }
            }
        }
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




