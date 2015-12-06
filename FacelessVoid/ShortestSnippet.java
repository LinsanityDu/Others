/*
Find shortest snippet，比如给一个Document是A,X,X,B,A,X,B，Query是A,B，要求返回shortestSnippet
第一问：如果Query有序（即A一定要在B前面），那么要返回A,X,B. 1point3acres.com/bbs
Follow up：如果Query无序（即B在A前面也可以），那么要返回B,A
再Follow up：如果Document非常大，如何再优化？
*/
import java.util.*;
public class ShortestSnippet{
    
    private HashMap<String, ArrayList<Integer>> hashmap;
    private String[] document;
    public ShortestSnippet(String[] document){
        if(document == null || document.length == 0){
            throw new IllegalArgumentException();
        }
        this.document = document;
        hashmap = new HashMap<String, ArrayList<Integer>>();
        for(int i = 0; i < document.length; i++){
            String word = document[i];
            if(!hashmap.containsKey(word)){
                hashmap.put(word, new ArrayList<Integer>());
            }
            hashmap.get(word).add(i);
        }
    }
    
    public String query(String wordA, String wordB){
        if(wordA == null || wordB == null){
            throw new IllegalArgumentException();
        }
        if(!hashmap.containsKey(wordA) || !hashmap.containsKey(wordB)){
            return "not found";
        }
        ArrayList<Integer> listA = hashmap.get(wordA);
        ArrayList<Integer> listB = hashmap.get(wordB);
        int i = 0, j = 0, minA = 0, minB = 0, minDist = Integer.MAX_VALUE;
        while(i < listA.size() && j < listB.size()){
            if(listA.get(i) > listB.get(j)){
                j++;
            }else{
                if(minDist > listB.get(j) - listA.get(i)){
                    minDist = listB.get(j) - listA.get(i);
                    minA = listA.get(i);
                    minB = listB.get(j);
                }
                i++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int k = minA; k <= minB; k++){
            sb.append(document[k]);
        }
        return sb.toString();
    }
    
    //follow up
    public String query2(String wordA, String wordB){
        if(wordA == null || wordB == null){
            throw new IllegalArgumentException();
        }
        if(!hashmap.containsKey(wordA) || !hashmap.containsKey(wordB)){
            return "not found";
        }
        ArrayList<Integer> listA = hashmap.get(wordA);
        ArrayList<Integer> listB = hashmap.get(wordB);
        int i = 0, j = 0, minA = 0, minB = 0, minDist = Integer.MAX_VALUE;
        while(i < listA.size() && j < listB.size()){
            int posA = listA.get(i), posB = listB.get(j);
            if(minDist > Math.abs(posA - posB)){
                minDist = Math.abs(posA - posB);
                minA = posA;
                minB = posB;
            }
            if(posA < posB){
                i++;
            }else{
                j++;
            }
        }
        StringBuilder sb = new StringBuilder();
        int start = Math.min(minA, minB), end = Math.max(minA, minB);
        for(int k = start; k <= end; k++){
            sb.append(document[k]);
        }
        return sb.toString();
    }
    
    
    public static void main(String[] args){
        String[] document = "A B C D E C B C A L I N".split(" ");
        ShortestSnippet sn = new ShortestSnippet(document);
        System.out.println(sn.query2("A", "C"));
        
    }
    
    
    
    
    
    
}