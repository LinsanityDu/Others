/*Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.
*/



public class Solution {
    public int firstMissingPositive(int[] A) {
        //将正数放到值-1的位置上，这样1放在0号位置，2放在1号位置，。。。。
        if(A==null || A.length==0)
            return 1;
        for(int i=0;i<A.length;i++)
        {
            if(A[i]<=A.length && A[i]>0 && A[A[i]-1]!=A[i])
            {
                int temp=A[A[i]-1];
                A[A[i]-1]=A[i];
                A[i]=temp;
                i--;
            }
        }

        for(int i=0;i<A.length;i++)
        {
            if(A[i]!=(i+1))
                return i+1;
        }
        return A.length+1;
    }
}