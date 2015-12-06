/*
第二题：给定两个array，一个是actual numbers, 另一个是position array, 按照position array将actual number array排序。
例子：
actual number array : [4 2 1 5 3]
position array : [3 1 0 4 2]
=>
actual number array变成 [1 2 3 4 5]
*/
 
public class Solution{
     
    public void sortOrder(int[] array, int[] order){
        if(array == null || order == null){
            throw new IllegalArgumentException();
        }
        if(array.length == 0 && order.length == 0){
            return;
        }
        if(array.length != order.length){
            return;
        }
         
        int N = array.length;
        for(int i = 0; i < N; i++){
            while(i != order[i]){
                swap(array, i, order[i]);
                swap(order, i, order[i]);
            }
        }
    }
     
    private void swap(int[] num, int i, int j){
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }
     
    public static void main(String[] args){
        int[] array = {4, 2, 1, 5, 3}, order = {3, 1, 0, 4, 2};
        new Solution().sortOrder(array, order);
        for(int i : array){
            System.out.println(i);
        }
    }
}