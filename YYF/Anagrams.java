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