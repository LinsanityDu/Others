public class Solution {
    public static int[] merge(int[] A, int[] B) {
        if (A == null && B == null || A.length == 0 && B.length == 0) {
            return null;
        }
        if (A == null || A.length == 0) return B;
        if (B == null || B.length == 0) return A;
        int[] res = new int[A.length + B.length];
        int i = A.length - 1;
        int j = B.length - 1;
        int index = A.length + B.length - 1;
        while (i >= 0 && j >= 0) {
            if (A[i] > B[j]) {
                res[index--] = A[i--];
            } else {
                res[index--] = B[j--];
            }
        }
        while (i >= 0) {
            res[index--] = A[i--];
        }
        while (j >= 0) {
            res[index--] = B[j--];
        }
        return res;
    }
    
}



/*Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.*/

public class Solution {
    public void merge(int[] A, int m, int[] B, int n) {
        int i = m-1, j = n-1, index = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (A[i] > B[j]) {
                A[index--] = A[i--];
            } else {
                A[index--] = B[j--];
            }
        }
        while (i >= 0) {
            A[index--] = A[i--];
        }
        while (j >= 0) {
            A[index--] = B[j--];
        }
    }
}