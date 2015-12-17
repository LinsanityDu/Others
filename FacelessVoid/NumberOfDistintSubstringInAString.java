Example:
input = "aaaa", 
output = 4 (the 4 substrings are "a", "aa", "aaa", "aaaa")

input = "abcd", 
output = 10 ("a", "b", "c", "d", "ab", "bc", "cd", "abc", "bcd", "abcd")

input = "banana", 
output = 15 ("a", "an", "ana", "anan", "anana", "b", "ba", "ban", "bana", "banan", "banana", "n", "na", "nan", "nana")

private static int numOfDistinct(String str) {
    List<String> suffix = new ArrayList<>();
    int res = 0;
    for (int i = 0; i < str.length(); i++) {
      res += str.length() - i;
      suffix.add(str.substring(i, str.length()));
    }
    Collections.sort(suffix);
    for (int i = 0; i < suffix.size() - 1; i++) {
      String str1 = suffix.get(i);
      String str2 = suffix.get(i + 1);
      int common = 0;
      for (; common < str1.length() && common < str2.length(); common++) {
        if (str1.charAt(common) != str2.charAt(common)) {
          break;
        }
      }
      res -= common;
    }
    return res;
  }