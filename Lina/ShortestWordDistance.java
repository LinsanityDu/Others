/*Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.*/




// My suck code
public class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        List<Integer> index1 = new ArrayList<>();
        List<Integer> index2 = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                index1.add(i);
            }
            if (words[i].equals(word2)) {
                index2.add(i);
            }
        }
        int res = words.length;
        for (int i = 0; i < index1.size(); i++) {
            for (int j = 0; j < index2.size(); j++) {
                res = Math.min(res, Math.abs(index1.get(i) - index2.get(j)));
            }
        }
        return res;
    }
}

// Improve my code
public class Solution {
        public int shortestDistance(String[] words, String word1, String word2) {
            List<Integer> w1occ=new ArrayList<Integer>();
            List<Integer> w2occ=new ArrayList<Integer>();

            for (int i=0; i<words.length; ++i){
                if (words[i].equals(word1)){
                    w1occ.add(i);
                }
                if (words[i].equals(word2)){
                    w2occ.add(i);
                }
            }

            int min=words.length;
            int p1=0;
            int p2=0;
            while (p1<w1occ.size() && p2<w2occ.size()){
                min=Math.min(Math.abs(w1occ.get(p1)-w2occ.get(p2)), min);
                if (w1occ.get(p1)<w2occ.get(p2)){
                    p1++;
                } else 
                    p2++;
            }
            return min;
        }
    }


// Concise
public int shortestDistance(String[] words, String word1, String word2) {
    int p1 = -1, p2 = -1, min = Integer.MAX_VALUE;

    for (int i = 0; i < words.length; i++) {
        if (words[i].equals(word1)) 
            p1 = i;

        if (words[i].equals(word2)) 
            p2 = i;

        if (p1 != -1 && p2 != -1)
            min = Math.min(min, Math.abs(p1 - p2));
    }

    return min;
}

// Concise2
public int shortestDistance(String[] words, String word1, String word2) {
    int ret = Integer.MAX_VALUE, index1 = -1, index2 = -1;
    for(int i = 0; i < words.length; i++) {
        if(words[i].equals(word1)) {
            index1 = i; 
            if(index2 >= 0) ret = Math.min(ret, i - index2);
        } else if(words[i].equals(word2)) {
            index2 = i;
            if(index1 >= 0) ret = Math.min(ret, i - index1);
        }
    }
    return ret;
}