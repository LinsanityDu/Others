/*接下来介绍一种比较容易理解的线性算法，称为rolling hash，想具体了解的朋友可以参见Rolling hash - Wikipedia。基本思想是用一个hashcode来表示一个字符串，为了保证hash的唯一性，我们用比字符集大的素数为底，以这个素数的幂为基。举例来说，字符集是小写字母集，取素数29为底。比如字符串“abacd",转化为hashcode=1+2*29+1*29^2+3*29^3+4*29^4。然后是如何在前进一步的时候计算新的hashcode，比如匹配串是原串是”abacde“，匹配串长度为5，根据以上的方法计算”abacd“的hashcode=h，那么下一步”bacde“的hashcode=h/29+5*29^4。这是一个constant的操作，所以检测所有子串的时间复杂度只需要O(m+n-m)=O(n)，也是一个线性算法。代码如下：*/



/*Easy Hash Function

15% Accepted
In data structure Hash, hash function is used to convert a string(or any other type) into an integer smaller than hash size and bigger or equal to zero. The objective of designing a hash function is to "hash" the key as unreasonable as possible. A good hash function can avoid collision as less as possible. A widely used hash function algorithm is using a magic number 33, consider any string as a 33 based big integer like follow:

hashcode("abcd") = (ascii(a) * 333 + ascii(b) * 332 + ascii(c) *33 + ascii(d)) % HASH_SIZE 

                              = (97* 333 + 98 * 332 + 99 * 33 +100) % HASH_SIZE

                              = 3595978 % HASH_SIZE

here HASH_SIZE is the capacity of the hash table (you can assume a hash table is like an array with index 0 ~ HASH_SIZE-1).

Given a string as a key and the size of hash table, return the hash value of this key.f*/

class Solution {
    /**
     * @param key: A String you should hash
     * @param HASH_SIZE: An integer
     * @return an integer
     */
    public int hashCode(char[] key,int HASH_SIZE) {
        // write your code here
        if (key == null || key.length == 0) {
            return 0;
        }
        long result = 0;
        for (int i = 0; i < key.length; i++) {
            result = result * 33 + (int)key[i];
            result %= HASH_SIZE;
        }
        return (int)result;
    }
}






public String strStr(String haystack, String needle) {  
    if(haystack==null || needle==null) return null;  
    if(haystack.length()==0){  
        return needle.length()==0?"":null;  
    }  
    if(needle.length()==0) return haystack;  
    if(haystack.length()<needle.length()) return null;  
  
 int base = 29;  
 long patternHash = 0;  
    long tempBase = 1;  
  
    for(int i=needle.length()-1; i>=0; i--){  
     patternHash += (int)needle.charAt(i)*tempBase;  
     tempBase *= base;  
    }  
  
    long hayHash = 0;  
    tempBase = 1;  
    for(int i=needle.length()-1; i>=0; i--){  
     hayHash += (int)haystack.charAt(i)*tempBase;  
     tempBase *= base;  
    }  
    tempBase /= base;  
  
    if(hayHash == patternHash){  
     return haystack;  
    }  
  
    for(int i=needle.length(); i<haystack.length(); i++){  
     hayHash = (hayHash - (int)haystack.charAt(i-needle.length())*tempBase)*base+(int)haystack.charAt(i);  
        if(hayHash == patternHash){  
      return haystack.substring(i-needle.length()+1);  
     }  
    }  
    return null;  
}   



// Another
package rollingHash;

/**
 * User: yanghua
 * Date: 5/11/13
 * Time: 10:54 AM
 * Copyright (c) 2013 yanghua. All rights reserved.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * rolling Hash(Rabin-Karp Algorithm)练习
 * 功能：求给定串的anagram 子串
 * 示例：GetAnagram("abcdbcsdaqdbahs","scdcb") --> cdbcs 【Google面试题】
 * 资料：http://blog.csdn.net/yanghua_kobe/article/details/8914970
 */
public class RollingHash {


	//the simple hash calculate expression is : (a[0] + a[1] + a[2]+ .... + a[n]) * FACTOR
	private static final int FACTOR = 41;
	private static long hashValueOfPattern;


	/**
	 * generate the pattern's hash
	 *
	 * @param patternStr the pattern string
	 */
	private static void generatePatternHash(String patternStr) {
		if (null == patternStr || patternStr.isEmpty()) {
			throw new IllegalArgumentException("the arg:patternStr can not be null or empty");
		}

		hashValueOfPattern = 0;
		int sum = 0;

		for (int i = 0; i < patternStr.length(); i++) {
			char c = patternStr.charAt(i);
			sum += (int) c;
		}

		hashValueOfPattern = sum * FACTOR;
	}

	/**
	 * find the matched anagram-str
	 *
	 * @param searchingStr the searching string
	 * @param patternStr   the pattern for searching string
	 * @return matched count
	 */
	private static int findAnargamStr(String searchingStr, String patternStr) {
		if (null == searchingStr || searchingStr.isEmpty()) {
			throw new IllegalArgumentException("the arg:searchingStr can not be null or empty");
		}

		if (null == patternStr || patternStr.isEmpty()) {
			throw new IllegalArgumentException("the arg:patternStr can not be null or empty");
		}

		if (searchingStr.length() < patternStr.length()) {
			return 0;
		}

		int count = 0;

		//generate hashmap and hashvalue
		generatePatternHash(patternStr);
		long tmpHashValue = 0;

		int l = patternStr.length();
		int n = searchingStr.length();

		for (int i = 0; i < n; i++) {
			char c = searchingStr.charAt(i);

			//calculate the first sub-string (0:pattern.length()-1) which length equal to pattern
			if (i < l) {
				tmpHashValue += ((int) c) * FACTOR;
			} else {
				//new tmpHashValue: (a[in-index]-a[out-index]) * FACTOR
				tmpHashValue += ((int) c - (int) searchingStr.charAt(i - l)) * FACTOR;

				//if the hash-value matched, compare each character
				//注：如果这里采用一个好的hash函数 或者 增大hash 槽 或者给字符串进行hash签名来避免过多的hash碰撞，
				// 那么这里就可以大大简化对isAnagram的调用，从而使得整个问题的复杂度逼近O(n)
				if (hashValueOfPattern == tmpHashValue)
					if (isAnagram(searchingStr, i - l + 1, i, patternStr))
						count++;
			}

		}

		return count;
	}

	/**
	 * is the two string anagram （因为哈希碰撞的存在，该函数用于验证字符串是否确实相等）
	 *
	 * @param comparedStr compared string
	 * @param startIndex  start index
	 * @param endIndex    end index
	 * @param pattern     pattern string
	 * @return true / false
	 */
	private static boolean isAnagram(String comparedStr, int startIndex, int endIndex, String pattern) {

		if (null == comparedStr || comparedStr.isEmpty()) {
			throw new IllegalArgumentException("the arg:comparedStr can not be null or empty");
		}

		if (null == pattern || pattern.isEmpty()) {
			throw new IllegalArgumentException("the arg:pattern can not be null or empty");
		}

		if (startIndex > endIndex || endIndex - startIndex != pattern.length() - 1) {
			throw new IllegalArgumentException("the arg:startIndex or endIndex is illegal");
		}

		boolean anagram = true;

		int[] letterCountOfPattern = new int[256];
		//not only number and letter , contain backspace and special symbol
		for (int j = 0; j < 256; j++) {
			letterCountOfPattern[j] = 0;
		}

		for (int k = 0; k < pattern.length(); k++) {
			++letterCountOfPattern[pattern.charAt(k)];
		}

		for (int i = startIndex; i <= endIndex; i++) {
			--letterCountOfPattern[comparedStr.charAt(i)];
		}

		for (int m = 0; m < 256; m++) {
			if (letterCountOfPattern[m] != 0) {
				anagram = false;
				break;
			}
		}

		return anagram;
	}


	public static void main(String[] args) {
		String searchingStr = "abcdbcsdaqdbahs";
		String patternStr = "scdcb";

		int count=findAnargamStr(searchingStr,patternStr);
		System.out.println(count);
	}

}

// 