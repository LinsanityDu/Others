/*Find palindom Pair
*/

// C++
vector<pair<string, string> > palindomPair(vector<string> &words) {
    vector<pair<string, string> > rst;
    unordered_set<string> dict;
    for(int i = 0; i < words.size(); i ++) {
        dict.insert(words[i]);
    }
    for(int i = 0; i < words.size(); i ++) {
        int len = words[i].size();
        for(int l = 0; l <= len; l ++) {
            string sub1 = words[i].substr(0, l);
            string sub2 = words[i].substr(l, len-l);
            if(isPal(sub2)){
                string tmp = sub1;
                reverse(tmp.begin(), tmp.end());
                if(dict.find(tmp) != dict.end() && words[i] != tmp) {
                    rst.push_back(pair<string, string>(words[i], tmp));
                }
            }
            if(isPal(sub1)){
                string tmp = sub2;
                reverse(tmp.begin(), tmp.end());
                if(dict.find(tmp) != dict.end() && words[i] != tmp) {
                    rst.push_back(pair<string,string>(tmp, words[i]));
                }
            }
        }
    }
    return rst;
}

You are given a list of word. Find if two words can be joined to-gather to form a palindrome. eg Consider a list {bat, tab, cat} Then bat and tab can be joined to gather to form a palindrome. 

Expecting a O(nk) solution where n = number of works and k is length 

There can be multiple pairs

public static boolean isPalindrome(String inputString) {
    if (inputString.length() == 0 || inputString.length() == 1) {
        return true;
    }
    boolean currentSame = inputString.charAt(0) == inputString.charAt(inputString.length() - 1);
    String remaining = inputString.substring(1, inputString.length() - 1);
    return (same && isPalindrome(remaining));
}

public static class Pair<F, S> {
    F first;
    S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }
}

public static List<Pair<String, String>> getPalindromes(List<String> inputList) {
    Set<String> inputSet = new HashSet<>();
    inputSet.addAll(inputList);
    for (String current : inputList) {
        StringBuilder sb = new StringBuilder(current);
        String reverse = sb.reverse().toString();
        for (int i = 0; i < reverse.length(); i++) {
            String currentReverse = reverse.substring(i);
            if (stringSet.contains(currentReverse)) {
                if (isPalindrome(current + currentReverse)) {
                    returnList.add(new Pair(current, currentReverse));
                }
            }
        }
    }
    return returnList;
}
