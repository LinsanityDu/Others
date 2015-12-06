
一题给一个数组，一个int k，要求输出数组内每k个数的平均值 这个能给个例子吗？

input: array = {1,3,5,7,9}; k = 2;
output: {2,4,6,8}
public class Solution {
    public static List<Integer> aveK(int[] nums, int k) {
      int sum = 0;
      for (int i = 0; i < k; i++) {
        sum += nums[i];
      }
      List<Integer> res = new ArrayList<>();
      res.add(sum / k);
      for (int i = k; i < nums.length; i++) {
        sum = sum + nums[i] - nums[i - k];
        res.add(sum / k);
      }
      return res;
    }
 
  public static void main(String[] args) {
    int[] arr = {1,3,5,7,9};
    List<Integer> res = aveK(arr, 2);
    for (Integer i : res) {
      System.out.println(i);
    }
  }
}