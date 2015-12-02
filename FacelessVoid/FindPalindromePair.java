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
