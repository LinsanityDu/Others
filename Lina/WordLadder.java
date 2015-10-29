/*Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.*/




public class Solution {
    public int ladderLength(String beginWord, String endWord, Set<String> wordDict) {
        // write your code here
        if (wordDict == null || wordDict.size() == 0) {
            return 0;
        }
        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        wordDict.remove(beginWord);
        int length = 1;
        
        while (!queue.isEmpty()) {
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                String current = queue.poll();
                for (char c = 'a'; c <= 'z'; c++) {
                    for (int j = 0; j < current.length(); j++) {
                        if (c == current.charAt(j)) {
                            continue;
                        }
                        String temp = replace(current, j, c);
                        if (temp.equals(endWord)) {
                            return length + 1;
                        }
                        if (wordDict.contains(temp)) {
                            queue.offer(temp);
                            wordDict.remove(temp);
                        }
                    }
                }
            }
            length++;
        }
        return 0;
    }
    
    private String replace(String s, int pos, char c) {
        char[] chars = s.toCharArray();
        chars[pos] = c;
        return new String(chars);
    }
}


// Code Ganker
public class Solution {
    public int ladderLength(String start, String end, Set<String> dict) {
        //edge case
        if(start == null || end == null || start.length() == 0 || end.length()== 0 || dict.size() == 0) {
            return 0;
        }
        int ladder = 2;
        LinkedList<String> queue = new LinkedList<String>();
        HashSet<String> visited = new HashSet<String>();
        //这里要注意，只要加入queue的就必须放入visited
        queue.offer(start);
        visited.add(start);
        //这个纪录level的方法不错。我之前是用null加到queue里面表示一个level，现在可以直接count每层的数量。每visited一次lastNum--，每add一个cur++， 然后当lastNum==0时，表示一层。curNum传给lastNum然后重新变为0
        int lastNum = 1;  
        int curNum = 0;  
        while(queue.size() != 0) {
            String cur = queue.poll();
            lastNum--;
            for(int l = 0 ; l < cur.length(); l++) {
                char[] curArray = cur.toCharArray();
                for(char c = 'a'; c <= 'z'; c++) {
                    //原来在想这里不需要看会不会与自己（cur）是同一个string吗，其实不怕，因为这个case只会visited一次
                    curArray[l] = c;
                    String curT = new String(curArray);
                    if(curT.equals(end)){
                        return ladder;
                    } else {
                      if(dict.contains(curT) && !visited.contains(curT)) {
                          curNum++;
                          queue.offer(curT);
                          visited.add(curT);
                      }  
                    }
                }
            }
            if(lastNum==0) {  
                lastNum = curNum;  
                curNum = 0;  
                ladder++;  
            } 
        }
        return 0;
    }
}




public class Solution {
    public int ladderLength(String start, String end, Set<String> dict) {
        if (dict == null || dict.size() == 0) {
            return 0;
        }

        HashSet<String> hash = new HashSet<String>();
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        hash.add(start);
        
        int length = 1;
        while(!queue.isEmpty()) {
            length++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                for (String nextWord: getNextWords(word, dict)) {
                    if (hash.contains(nextWord)) {
                        continue;
                    }
                    if (nextWord.equals(end)) {
                        return length;
                    }
                    
                    hash.add(nextWord);
                    queue.offer(nextWord);
                }
            }
        }
        return 0;
    }

    // replace character of a string at given index to a given character
    // return a new string
    private String replace(String s, int index, char c) {
        char[] chars = s.toCharArray();
        chars[index] = c;
        return new String(chars);
    }
    
    // get connections with given word.
    // for example, given word = 'hot', dict = {'hot', 'hit', 'hog'}
    // it will return ['hit', 'hog']
    private ArrayList<String> getNextWords(String word, Set<String> dict) {
        ArrayList<String> nextWords = new ArrayList<String>();
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i < word.length(); i++) {
                if (c == word.charAt(i)) {
                    continue;
                }
                String nextWord = replace(word, i, c);
                if (dict.contains(nextWord)) {
                    nextWords.add(nextWord);
                }
            }
        }
        return nextWords;
    }
}


