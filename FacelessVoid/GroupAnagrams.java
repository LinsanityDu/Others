/*Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:
For the return value, each inner list's elements must follow the lexicographic order.
All inputs will be in lower-case.*/



// Another
// O(Nklogk)
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        // 如果输出不要求order可以不排序
        Arrays.sort(strs);
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String keyStr = String.valueOf(ca);
            if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<String>());
            map.get(keyStr).add(s);
        }
        return new ArrayList<List<String>>(map.values());
    }
}



// O(nk)
public class Solution {
    private int getHash(int[] count) {
        int hash = 0;
        int a = 378551;
        int b = 63689;
        for (int num : count) {
            hash = hash * a + num;
            a = a * b;
        }
        return hash;
    }
    
    public ArrayList<String> anagrams(String[] strs) {
        ArrayList<String> result = new ArrayList<String>();
        HashMap<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();

        for (String str : strs) {
            int[] count = new int[26];
            for (int i = 0; i < str.length(); i++) {
                count[str.charAt(i) - 'a']++;
            }

            int hash = getHash(count);
            if (!map.containsKey(hash)) {
                map.put(hash, new ArrayList<String>());
            }

            map.get(hash).add(str);
        }

        for (ArrayList<String> tmp : map.values()) {
            if (tmp.size() > 1) {
                result.addAll(tmp);
            }
        }

        return result;
    }
}

// O(Nklogk)
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new LinkedList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        Arrays.sort(strs);
        for (int i = 0; i < strs.length; i++) {
            char[] ch = strs[i].toCharArray();
            Arrays.sort(ch);
            // tip: char array to String --> String.valueOf(myarray);
            String newString = String.valueOf(ch);
            if (map.containsKey(newString)) {
                map.get(newString).add(strs[i]);
            } else {
                List<String> each = new LinkedList<>();
                each.add(strs[i]);
                map.put(newString, each);
            }
        }
        Iterator<List<String>> i = map.values().iterator();
        while (i.hasNext()) {
            List<String> item = i.next();
            res.add(item);
        }
        return res;
    }
}


O(NK)
Assign a prime number for a to z, and then multiply all prime numbers together to form a hash value.

    private static final int[] PRIMES = new int[]{2, 3, 5, 7, 11 ,13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 107};

    public List<String> anagrams(String[] strs) {
        List<String> list = new LinkedList<>();
        Map<Integer, List<String>> mapString = new HashMap<>();
        int result = -1;
        for (int i = 0; i < strs.length; i++){
            int mapping = 1;
            for (int j = 0, max = strs[i].length(); j < max; j++) {
                mapping *= PRIMES[strs[i].charAt(j) - 'a'];
            }
            List<String> strings = mapString.get(mapping);
            if (strings == null) {
                strings = new LinkedList<>();
                mapString.put(mapping, strings);
            }
            strings.add(strs[i]);
        }
        for (List<String> mapList : mapString.values()){
            if (mapList.size() > 1)
                list.addAll(mapList);
        }
        return list;
    }

