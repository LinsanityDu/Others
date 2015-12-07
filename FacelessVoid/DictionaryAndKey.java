Word Break?
/*You're given a dictionary of strings, and a key. Check if the key is composed of an arbitrary number of concatenations of strings from the dictionary. For example: dictionary: "world", "hello", "super", "hell" key: "helloworld" --> return true key: "superman" --> return false key: "hellohello" --> return true*/

public class Solution {

	public static boolean valid(Set<String> dic, String str) {
		boolean[] table = new boolean[str.length() + 1];
		table[0] = true;
		for (int i = 0; i < str.length(); i++) {
			for (int j = i; j >= 0; j--) {
				if (table[j] && dic.contains(str.substring(j, i + 1))) {
					table[i + 1] = true;
					break;
				}
			}
		}
		return table[table.length - 1];
	}

	public static void main(String[] args) {
		Set<String> dic = new HashSet<>();
		dic.add("world");
		dic.add("hello");
		dic.add("super");
		dic.add("dell");
		String str = "helloworld";
		boolean v = valid(dic, str);
		if (v) {
			System.out.println("Contains");
		} else {
			System.out.println("Not Contains");
		}
	}
}


/*bool concantention(unordered_set<string> &dict, string words) {
    vector<bool> table(false, words.size()+1);
    table[0] = true;
    for(int i = 0; i < words.size(); i ++) {
        for(int j = i; j >= 0; j --) {
            if(table[j] && dict.find(words.substr(j,i-j+1)) != dict.end()) {
                table[i+1] = true;
                break;
            }
        }
    }
    return table[words.size()];
}*/