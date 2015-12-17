
新鲜二面奉上，很nice的三哥。只面了一道题。
给定一个array,条件：1. A = A+-1; 2. There is only one peek or one valey in the array, return the index of that peek or valey.
刚开始说了一下O(n)顺着找的办法，三哥说更好的办法呢？我说那就是binary search了。结果做着做着发现这样搞还是O(N)。然后三哥开导我不要用recursion呢，用binary search该怎么搞。我又仔细看了一下，发现直接比较头尾大小，O(1)就能解决。。然后压时间写完了。


我的想法是先用nums[start] - nums[start +1] 的结果的正负判断是先升后降，还是先降后升。然后直接看中间的点处的斜率
front = nums[mid] - nums[mid-1];
Rear = nums[mid +1] - nums[mid] 
如果是先降后升，mid这儿又是升的话，就接着判断左边的中点的斜率。同理，mid处降的话就找右边。如果mid这儿的front 和rear异号的话，那mid就是要找的点了。
请问这样对吗？



/*
find the maximum number in an integer array. 
The numbers in the array increase first, then decreases. Maybe there’s only increase or decrease
*/
import java.util.*;
public class Solution{
     
    public int findMax(int[] array){
        if(array == null || array.length == 0){
            throw new IllegalArgumentException();
        }
        //length 1
        if(array.length == 1){
            return array[0];
        }
        //length 2
        if(array.length == 2){
            return (array[0] > array[1])? array[0] : array[1];
        }
        int start = 0, end = array.length - 1;
        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            if(array[mid] > array[mid  - 1] && array[mid + 1] > array[mid]){
                start = mid;
            }else if(array[mid] > array[mid - 1] && array[mid] > array[mid + 1]){
                return array[mid];
            }else if(array[mid - 1] > array[mid] && array[mid] > array[mid + 1]){
                end = mid;
            }
        }
        return (array[start] > array[end])? array[start] : array[end];
         
    }
     
 
     
     
     
     
     
    public static void main(String[] args){
        int[] nums = {1};
        int[] nums1 = {1, 2};
        int[] nums2 = {1, 3, 2};
        int[] nums3 = {1, 2, 3, 4, 5, 6, 7};
        int[] nums4 = {7, 6, 5, 4, 3, 2, 1};
        int[] nums5 = {1, 2, 3, 4, 5, 6, 8, 7, 4};
        Solution ins = new Solution();
        System.out.println(ins.findMax(nums));
        System.out.println(ins.findMax(nums1));
        System.out.println(ins.findMax(nums2));
        System.out.println(ins.findMax(nums3));
        System.out.println(ins.findMax(nums4));
        System.out.println(ins.findMax(nums5));
    }
     
}
