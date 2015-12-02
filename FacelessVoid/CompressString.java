/*给你一个字符串，统计字符出现次数。输入"AABBBCCC"，输出"2A3B3C"*/


public class Solution {
  public static String compress(String s) {
    int len = s.length();
    int curr = 0, prev = 0, cnt = 0;
    StringBuilder result = new StringBuilder();
    while (curr < len) {
      if (s.charAt(curr) == s.charAt(prev)) {
        curr++;
        cnt++;
      } else {
        result.append(cnt);
        result.append(s.charAt(prev));
        cnt = 0;
        prev = curr;
      }
    }
    if (len > 0) {
      result.append(cnt);
      result.append(s.charAt(prev));
    }
    return result.toString();
  }

  public static void main(String[] args) {
    String str = "AABBBCCC";
    System.out.println(compress(str));
  }
}




/*string compressString(string s) {
    int len = s.length();
    int curr = 0, prev = 0, cnt = 0;
    string result;
    while(curr < len) {
        if(s[curr] == s[prev]) {. From 1point 3acres bbs
            curr++;
            cnt++;.鐣欏璁哄潧-涓€浜�-涓夊垎鍦�
        } else {
            result.push_back(cnt + '0');
            result.push_back(s[prev]);
            cnt = 0;. 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
            prev = curr;. Waral 鍗氬鏈夋洿澶氭枃绔�,
        }
    }
    if(len > 0) {
        result.push_back(cnt + '0');
        result.push_back(s[prev]);
    }*/



    return result;
}