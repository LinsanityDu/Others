In String[] text, find shortest sub array with all words provided HashSet<String>query

public class Solution {

	public static String[] shortest(String[] text, Set<String> query) {
		if (text == null || text.length == 0 || query == null || query.size() == 0) {
			return null;
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (String s : query) {
			map.put(s, 1);
		}
		int left = 0;
		int count = 0;
		int minLen = text.length + 1;
		int minStart = 0;
		for (int right = 0; right < text.length; right++) {
			if (map.containsKey(text[right])) {
				map.put(text[right], map.get(text[right]) - 1);
				if (map.get(text[right] >= 0)) {
					count++;
				}
				while (count == query.size()) {
					if (right - left + 1 < minLen) {
						minLen = right - left + 1;
						minStart = left;
					}
					if (map.containsKey(text[left])) {
						map.put(text[left], map.get(text[left]) + 1);
						if (map.get(text[left]) > 0) {
							count--;
						}
					}
					left++;
				}
			}
		}
		if (minLen > text.length) {
			return null;
		}
		String[] res = new String[minLen];
		int index = 0;
		for (int i = minStart; i < minStart + minLen; i++) {
			res[index++] = text[i];
		}
		return res;
	}


	public static void main(String[] args) {
		String[] test = {"A","D","O","B","E","C","O","D","E","B","A","N","C"};
		Set<String> query = new HashSet<String>();
		query.add("A");
		query.add("B");
		query.add("C");
		String[] res = shortest(test, query);
		for (String s : res) {
			System.out.print(s + " ");
		}
	}
}



public String minWindow(String S, String T) {
    if(S==null || S.length()==0)
        return "";
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    for(int i=0; i<T.length();i++)
    {
        if(map.containsKey(T.charAt(i)))
        {
            map.put(T.charAt(i),map.get(T.charAt(i))+1);
        }
        else
        {
            map.put(T.charAt(i),1);
        }
    }
    int left = 0;
    int count = 0;
    int minLen = S.length()+1;
    int minStart = 0;
    for(int right=0; right<S.length();right++)
    {
        if(map.containsKey(S.charAt(right)))
        {
            map.put(S.charAt(right),map.get(S.charAt(right))-1);
            if(map.get(S.charAt(right))>=0)
            {
                count++;
            }
            while(count == T.length())
            {
                if(right-left+1<minLen)
                {
                    minLen = right-left+1;
                    minStart = left;                    
                }
                if(map.containsKey(S.charAt(left)))
                {
                    map.put(S.charAt(left), map.get(S.charAt(left))+1);
                    if(map.get(S.charAt(left))>0)
                    {
                        count--;
                    }
                }
                left++;
            }
        }
    }
    if(minLen>S.length())
    {
        return "";
    }
    return S.substring(minStart,minStart+minLen);
}