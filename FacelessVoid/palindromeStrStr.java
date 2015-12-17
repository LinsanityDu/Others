/*implement function strstrp(String a, String b) returns index where any permutation of b is a substring of a.
e.g.
strstrp("hello", ''ell") returns 1
strstrp("hello",  "lle") returns 1
strstrp("hello",  "wor") returns -1*/

要nmlogm,  n 是a长度 m是b长度。  在a的每个位置截取a.substring(i,i+m) 排序，跟排序后的b相比 一样就返回。再好点的方法是： 在a的每个位置截取c=a.substring(i,i+m) ，把c每个字符和次数存进hashmap,然后拿b来对比  时间复杂度是O(m),总时间复杂度是O(mn)


import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {

  public static boolean isExist(String str, String t) {
    int req = t.length();
    HashMap<Character, Integer> map = new HashMap<>();
    for (char c : t.toCharArray()) {
      if (map.containsKey(c)) map.put(c, map.get(c) + 1);
      else map.put(c, 1);
    }
    int s = 0;
    int e = 0;
    while (true) {
      if (e - s < t.length()) {
        if (e == str.length()) break;
        char c = str.charAt(e);
        if (map.containsKey(c)) {
          if (map.get(c) > 0) {
            req--;
          }
          map.put(c, map.get(c) - 1);
        }
        e++;
      }
      if (e - s == t.length()) {
        if (req == 0) return true;
        char c = str.charAt(s);
        if (map.containsKey(c)) {
          if (map.get(c) >= 0) {
            req++;
          }
          map.put(c, map.get(c) + 1);
        }
        s++;
      }   
    }
    return false;
  }

  public static void main(String[] args) {
    System.out.println(isExist("hello", "oleld"));
  }
}

  private static boolean isSame(int[] arr, int[] arr1) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] != arr1[i]) return false;
    }
    return true;
  }
 
  public static boolean isExist(String str, String t) {
    int req = t.length();
    int arr[] = new int[256];
    for (char c : t.toCharArray()) {
      arr[c]++;
    }
    int s = 0;
    int e = 0;
    int arr2[] = new int[256];
    for (int i = 0; i < t.length(); i++) {
      arr2[str.charAt(i)]++;
    }
    for (int i = t.length(); i <= str.length(); i++) {
      boolean isSame = isSame(arr, arr2);
      if (isSame) return true;
      arr2[str.charAt(i - t.length())]--;
      if (i != str.length()) arr2[str.charAt(i)]++;
    }
    return false;
  }
 
