Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
You may assume each number in the sequence is unique.
Follow up:
Could you do it using only constant space complexity?


public class Solution {
    public boolean verifyPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return true;
        }
        return check(preorder, 0, preorder.length - 1);
    }
   
    private boolean check(int[] arr, int start, int end) {
        if (start >= end) {
            return true;
        }
        int root = arr[start];
       
        int right = start + 1;
        for (;right <= end; right++) {
            if (arr[right] > root) {
                break;
            }
        }
       
        for (int i = right; i <= end; i++) {
            if (arr[i] < root) return false;
        }
        return check(arr, start + 1, right - 1) && check(arr, right, end);
    }
}