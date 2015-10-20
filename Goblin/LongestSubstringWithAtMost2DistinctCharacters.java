/*Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

For example, Given s = “eceba”,

T is "ece" which its length is 3.*/


public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        
    }
}


int lengthOfLongestSubstringTwoDistinct(string s) {
        int i = 0, j = -1;
        int maxLen = 0;
        for (int k = 1; k < s.size(); k++) {
            if (s[k] == s[k-1]) continue;
            if (j > -1 && s[k] != s[j]) {
                maxLen = max(maxLen, k - i);
                i = j + 1;
            }
            j = k - 1;
        }
        return maxLen > (s.size() - i) ? maxLen : s.size() - i;
    }



/*
    This question belong to the same category as those such as "longest substring without repeating characters", "minimum window substring", and "substring with concatenation of all words". To solve this kind of question we can use two pointers and a hash table. When the key of the hash table is char, we can simply use an array as the hash table. The most important idea in solving this kind of questions is "how to update the "start" pointer" and the solution to these questions seem usually differ only in this respect.*/

int lengthOfLongestSubstringTwoDistinct(string s) {
    if(s.empty()) return 0;

    int dict[256]; 
    fill_n(dict,256,0);
    int start = 0, len = 1, count = 0;
    for(int i=0; i<s.length(); i++) {
        dict[s[i]]++;
        if(dict[s[i]] == 1) { // new char
            count++;
            while(count > 2) {
                dict[s[start]]--;
                if(dict[s[start]] == 0) count--; 
                start++;
            }
        }
        if(i-start+1 > len) len = i-start+1;
    }
    return len;
}