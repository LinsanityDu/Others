/*Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.*/

// My suck code
public class Solution {
     public int findKthLargest(int[] a, int k) {
        int n = a.length;
        int p = quickSelect(a, 0, n - 1, n - k + 1);
        return a[p];
      }
    
      // return the index of the kth smallest number
      int quickSelect(int[] a, int lo, int hi, int k) {
        // use quick sort's idea
        // put nums that are <= pivot to the left
        // put nums that are  > pivot to the right
        int store = lo;
        int pivot = a[hi];
        for (int i = lo; i < hi; i++) {
            if (a[i] < pivot) {
                swap(a, store, i);
                store++;
            }
        }
        swap(a, store, hi);
    
        // count the nums that are <= pivot from lo
        int m = store - lo + 1;
    
        // pivot is the one!
        if (m == k)     return store;
        // pivot is too big, so it must be on the left
        else if (m > k) return quickSelect(a, lo, store - 1, k);
        // pivot is too small, so it must be on the right
        else            return quickSelect(a, store + 1, hi, k - m);
      }
    
      void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
      }
}


// Another way use parition and iterative
public class Solution {
    public int findKthLargest(int[] nums, int k) {
        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            final int j = partition(nums, lo, hi);
            if(j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }
    
    private int partition(int[] nums, int start, int end) {
        int pivot = nums[end];
        int store = start;
        for (int i = start; i < end; i++) {
            if (nums[i] < pivot) {
                swap(nums, i, store);
                store++;
            }
        }
        swap(nums, store, end);
        return store;
    }
    
    private void swap(int[] a, int i, int j) {
        final int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}