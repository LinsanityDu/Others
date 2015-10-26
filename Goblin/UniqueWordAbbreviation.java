/*An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example: 
Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true*/

public class ValidWordAbbr {

	Map<String, String> map = new HashMap<>();

	public ValidWordAbbr(String[] dictionary) {
	    for (String dic : dictionary) {
	        String key = getKey(dic);
	        if (map.containsKey(key)) {
	            map.put(key, "");
	        } else {
	            map.put(key, dic);
	        }
	    }
	}

	public boolean isUnique(String word) {
	    String key = getKey(word);
	    return !map.containsKey(key) || map.get(key).equals(word);
	}

	private String getKey(String word) {
	    String key = word.charAt(0) + Integer.toString(word.length() - 2) + word.charAt(word.length() - 1);
	    return key;
	}

}

// Another
HashMap<String, HashSet<String>> abbr_map = new HashMap<String, HashSet<String>> ();

public ValidWordAbbr(String[] dictionary) {
    for (String word : dictionary) {
        if (word == null || word.length() <= 2)
            continue;
        String abbr_word = getAbbrStr(word);
        if (abbr_map.containsKey(abbr_word)) {
            abbr_map.get(abbr_word).add(word);
        } else{
            HashSet<String> item = new HashSet<String> ();
            item.add(word);
            abbr_map.put(abbr_word, item);
        }
    }
}


public boolean isUnique(String word) {
    if (word == null || word.length() <= 2)
        return true;
    String abbr_word = getAbbrStr(word);
    if (abbr_map.containsKey(abbr_word)) {
        if (abbr_map.get(abbr_word).size() >= 2)
            return false;
        if (!abbr_map.get(abbr_word).contains(word))
            return false;
    }
    return true;
}


private String getAbbrStr(String s) {
    int len = s.length();
    return s.substring(0, 1) + (len - 2) + s.substring(len - 1);
}