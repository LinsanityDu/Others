In quicksort, there is a subprocedure called partition that can, in linear time, group a list (ranging from indices left to right) into two parts, those less than a certain element, and those greater than or equal to the element. Here is pseudocode that performs a partition about the element list[pivotIndex]:

 function partition(list, left, right, pivotIndex)
     pivotValue := list[pivotIndex]
     swap list[pivotIndex] and list[right]  // Move pivot to end
     storeIndex := left
     for i from left to right-1
         if list[i] < pivotValue
             swap list[storeIndex] and list[i]
             increment storeIndex
     swap list[right] and list[storeIndex]  // Move pivot to its final place
     return storeIndex

/*Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.*/


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

// Another discussion
public class Solution {
        public int findKthLargest(int[] nums, int k) {
            return findK(nums,nums.length - k,0,nums.length-1);
        }

        private int findK(int[] nums,int k, int start, int end){
            int parti = nums[start],i=start,m=start;
            for(int j=start+1;j<=end;j++){
                if(nums[j]>parti)
                    continue;
                if(nums[j]<=parti){
                    swap(nums,++i,j);
                    if(nums[j] != parti)
                        swap(nums,m++,i);
                }
            }
            if(k>=m && k<=i)
                return nums[k];
            else if(k < m)
                return findK(nums,k,start,m-1);
            else 
                return findK(nums,k,i+1,end);
        }

        private void swap(int[] nums, int a, int b){
            int temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
        }
    }

Java PriorityQueue O(n * log k)

public class Solution {
            public int findKthLargest(int[] nums, int k) {
                PriorityQueue<Integer> largeK = new PriorityQueue<Integer>(k + 1);

                for(int el : nums) {
                    largeK.add(el);
                    if (largeK.size() > k) {
                        largeK.poll();
                    }
                }

                return largeK.poll();
            }
}


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


// Nine Chapter
class Solution {
    //param k : description of k
    //param numbers : array of numbers
    //return: description of return
    public int kthLargestElement(int k, ArrayList<Integer> numbers) {
        if (numbers == null || numbers.size() == 0) {
            return 0;
        }
        if (k <= 0) {
            return 0;
        }
        return helper(numbers, 0, numbers.size() - 1, k);
    }
    
    public int helper(ArrayList<Integer> numbers, int l, int r, int k) {
        if (l == r) {
            return numbers.get(l);
        }
        int position = partition(numbers, l, r);
        if (position + 1 == k) {
            return numbers.get(position);
        } else if (position + 1 < k) {
            return helper(numbers, position + 1, r, k);
        }  else {
            return helper(numbers, l, position - 1, k);
        }
    }
    
    public int partition(ArrayList<Integer> numbers, int l, int r) {
        if (l == r) {
            return l;
        }
        int num = numbers.get(r);
        int index = l;
        for (int i = l; i < r; i ++) {
            if (numbers.get(i) >= num) {
                int temp = numbers.get(i);
                numbers.set(i, numbers.get(index));
                numbers.set(index, temp);
                index ++;
            }
        }
        
        numbers.set(r, numbers.get(index));
        numbers.set(index, num);
        return index;         
    }
};