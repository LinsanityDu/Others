// Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

// According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

// For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.

// Note: If there are several possible values for h, the maximum one is taken as the h-index.

// Hint:

// An easy approach is to sort the array first.
// What are the possible values of h-index?
// A faster approach is to use extra space.


public class Solution {
    public int hIndex(int[] citations) {
        
    }
}

// Discussion
/*Explanation: The idea is to use another array, index is the citation and value is the number of papers that has at least the citation. Since the h-index can only be n, the new array will only need the index to be at most n, thus the array size will only need n+1. Papers that have more than n citations will store in array[n]. Go through the array based on h index definition: array[i]>=i, find the max value of i.*/

public int hIndex(int[] citations) {
    int len = citations.length;
    int[] count = new int[len + 1];

    for (int c: citations)
        if (c > len) 
            count[len]++;
        else 
            count[c]++;


    int total = 0;
    for (int i = len; i >= 0; i--) {
        total += count[i];
        if (total >= i)
            return i;
    }

    return 0;
}


public class Solution {
    // 9.3 70 years diaoZhaTian China jiaYou 
    public int hIndex(int[] citations) {
        int length = citations.length;
        if (length == 0) {
            return 0;
        }

        int[] array2 = new int[length + 1];
        for (int i = 0; i < length; i++) {
            if (citations[i] > length) {
                array2[length] += 1;
            } else {
                array2[citations[i]] += 1;
            }
        }
        int t = 0;
        int result = 0;

        for (int i = length; i >= 0; i--) {
            t = t + array2[i];
            if (t >= i) {
                return i;
            }
        }
        return 0;
    }
}

// Using Sorting
public class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) return 0;
        Arrays.sort(citations);
        int len = citations.length;
        for (int i = 0; i < citations.length; i++) {
            if (len <= citations[i])
                return len;
            else
                len--;
        }
        return len;
    }
}



1. LC， H-index， 不过换了种问法 给一个数去， 里面的数都是[0..n] 让你找出最大的一个值max, 在数组里面存在max 个>=max的数， 
想了半天发现是H-index

[ans:]
way 1: sort the array, left tranverse, find the first arr>=len-i; 
public class Solution {
public int hIndex(int[] citations) {
if(citations==null || citations.length==0) return 0; 
Arrays.sort(citations);
int len=citations.length; 

for(int i=0; i if(citations>=len-i) return len-i; 
}

return 0; 
}
}
way 2: sort the array, use binary search to find the arr>=len-i;
public class Solution {
public int hIndex(int[] citations) {
if(citations==null || citations.length==0) return 0; 
int len=citations.length; 

int begin=0, end=len-1; 
while(begin<=end){
int mid=begin+(end-begin)/2;
if(citations[mid] else end=mid-1; 
}
// if(begin==len) return 0; 
return len-begin;
}
}
way 3: bucket sort idea, 
public class Solution {
public int hIndex(int[] citations) {
if(citations==null || citations.length==0) return 0; 
int len=citations.length;

int[] arr=new int[len+1];
for(int i=0; i if(citations>=len) arr[len]++;
else arr[citations]++;
}

for(int i=len; i>=0; i–){
if(arr>=i) return i; 
if(i>0) arr[i-1]+=arr;
}
return 0; 
}
}