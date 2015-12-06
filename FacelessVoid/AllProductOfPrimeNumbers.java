/*电面就是一道题加follow up
combination product， 给一个质数数组，输出所有可能的product，输出顺序无所谓。
比如给一个数组 [2,3,5] 输出 [2,3,5,6,10,30] 这个比较简单
follow up是数组里有重复，结果没有重复。比如 给 [2,2,2]，要输出 [2,4,8]，顺序无所谓。
这道题没见过，感觉好像也不是leetcode上面的去重的那题。*/

import java.util.*;
public class Solution{
    
    public List<Integer> allProduct(int[] num){
        List<Integer> res = new ArrayList<Integer>();
        if(num == null){
            throw new IllegalArgumentException();
        }
        if(num.length == 0){
            return res;
        }
        Arrays.sort(num);
        dfs(res, num, 1, 0);
        return res;
    }
    
    private void dfs(List<Integer> res, int[] num, int product, int pos){
        for(int i = pos; i < num.length; i++){
            if(i != pos && num[i - 1] == num[i]) continue;
            product = product * num[i];
            res.add(product);
            dfs(res, num, product, i + 1);
            product = product / num[i];
        }
    }
    
    
    public static void main(String[] args){
        int[] nums = {2, 2, 2};
        System.out.println(new Solution().allProduct(nums));
        
    }
}