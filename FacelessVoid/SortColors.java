/*Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.*/

// Nine Chapter
public class Solution {
    public void sortColors(int[] a) {
        if(a == null || a.length <= 1)
            return;
        
        int pl = 0;
        int pr = a.length - 1;
        int i = 0;
        while(i <= pr){
            if(a[i] == 0){
                swap(a, pl, i);
                pl++;
                i++;
            }else if(a[i] == 1){
                i++;
            }else{
                swap(a, pr, i);
                pr--;
            }
        }
    }
    
    private void swap(int[] a, int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}

// Discussion One Pass
public class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int left = 0;
        int right = nums.length - 1;
        for (int i = left; i <= right;) {
            if (nums[i] == 0) {
                swap(nums, left, i);
                i++;
                left++;
            } else if (nums[i] == 2) {
                swap(nums, right, i);
                right--;
            } else {
                i++;
            }
        }
    }
    
    private void swap(int[] nums, int pos1, int pos2) {
        int temp = nums[pos1];
        nums[pos1] = nums[pos2];
        nums[pos2] = temp;
        return;
    }
}


// Sort K colors
 public void sortColors2(int[] colors, int k) {
        int count = 0;
        int start = 0;
        int end = colors.length-1;
        while (count < k) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            
            for (int i = start; i < end; i++) {
                min = Math.min(min, colors[i]);
                max = Math.max(max, colors[i]);
            }
            int left = start;
            int right = end;
            int cur = left;
            while(cur <= right) {
                if (colors[cur] == min) {
                    swap(left, cur, colors);
                    cur++;
                    left++;
                } else if (colors[cur] > min && colors[cur] < max) {
                    cur++;
                } else {
                    int tmp = colors[cur];
                    swap(cur, right, colors);
                    right--;
                }
            }
            count += 2;
            start = left;
            end = right;
        }
    }
    
    void swap(int left, int right, int[] colors) {
        int tmp = colors[left];
        colors[left] = colors[right];
        colors[right] = tmp;
    }

// Sort Color 快排
    使用快排，时间复杂度是O(nlogn),空间复杂度是O(Log(N))


复制代码
 1 /**
 2      * @param colors: A list of integer
 3      * @param k: An integer
 4      * @return: nothing
 5      */
 6     /*
 7     Solution 1: Using the quick sort.
 8     */ 
 9     public void sortKColors1(int[] colors, int k) {
10         // write your code here
11         if (colors == null) {
12             return;
13         }
14         
15         quickSort(colors, 0, colors.length - 1);
16     }
17     
18     public void quickSort(int[] colors, int left, int right) {
19         if (left >= right) {
20             return;
21         }
22         
23         int pivot = colors[right];
24         
25         int pos = partition(colors, left, right, pivot);
26         
27         quickSort(colors, left, pos - 1);
28         quickSort(colors, pos + 1, right);
29     }
30     
31     public int partition(int[] colors, int left, int right, int pivot) {
32         int leftPoint = left - 1;
33         int rightPoint = right;
34         
35         while (true) {
36             while (colors[++leftPoint] < pivot);
37             
38             while (leftPoint < rightPoint && colors[--rightPoint] > pivot);
39             
40             if (leftPoint >= rightPoint) {
41                 break;
42             }
43             
44             swap(colors, leftPoint, rightPoint);
45         }
46         
47         swap(colors, leftPoint, right);
48         return leftPoint;
49     }
50     
51     public void swap(int[] colors, int left, int right) {
52         int tmp = colors[left];
53         colors[left] = colors[right];
54         colors[right] = tmp;
55     }


*/
// My suck code
public class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int start = 0;
        int end = nums.length - 1;
        int pivot = 2;
        while (start < end) {
            if (nums[start] < pivot) {
                start++;
            } else {
                swap(nums, start, end);
                end--;
            }
        }
        while (end >= 0 && nums[end] == pivot) {
            end--;
        }
        start = 0;
        pivot = 1;
        while (start < end) {
            if (nums[start] < pivot) {
                start++;
            } else {
                swap(nums, start, end);
                end--;
            }
        }
        return;
    }
    
    private void swap(int[] nums, int pos1, int pos2) {
        int temp = nums[pos1];
        nums[pos1] = nums[pos2];
        nums[pos2] = temp;
        return;
    }
}


// Couting Sort
public void sortColors(int[] nums) {
    // 2-pass
    int count0 = 0, count1 = 0, count2 = 0;
    for (int i = 0; i < nums.length; i++) {
        if (nums[i] == 0) {count0++;}
        if (nums[i] == 1) {count1++;}
        if (nums[i] == 2) {count2++;}
    }
    for(int i = 0; i < nums.length; i++) {
        if (i < count0) {nums[i] = 0;}
        else if (i < count0 + count1) {nums[i] = 1;}
        else {nums[i] = 2;}
    }
}