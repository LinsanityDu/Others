/*Given an integer array, find a subarray where the sum of numbers is zero. Your code should return the index of the first number and the index of the last number.

*/
第一问，是否存在subarray,其数字和等于target, 用hashset保存从0到当前数字的和，再重新遍历一遍，如果有两个数字和只差等于target，那么这两个数字和中间的数字就是所求的子数组。
bool existSubarray(vector &data, int target) {
    unordered_set st;
    sum[0] = data[0];
    st.insert(sum[0]);
    for (int i = 1; i < data.size(); ++i) {
    sum += sum[i-1] + data;
    st.insert(sum);
    }
    for (int i = 0; i < data.size(); ++i) {
    if (st.find(sum - target) != st.end()) {
    return true;
    }
    }
    return false;
}

Minimum Size with positive and negative numbers:
int minSubArrayLen(int s, vector& nums) {
        vector sumArray(nums.size(), 0);
        sumArray[0] = nums[0];
        for(int i = 1; i < nums.size(); ++i) sumArray[i] = sumArray[i-1] + nums[i];
        unordered_map table;
        int minLen = INT_MAX;
        for(int i = 0; i < sumArray.size(); ++i) {
            int target = sumArray[i] - s;
            if(table.count(target)) {
                minLen = min(minLen, i - table[target]);
            } else table[sumArray[i]] = i;
        }
        
        return minLen == INT_MAX ? 0 : minLen;
    }



public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number
     *          and the index of the last number
     */
    public ArrayList<Integer> subarraySum(int[] nums) {
        // write your code here
       
        int len = nums.length;
       
        ArrayList<Integer> ans = new ArrayList<Integer>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
       
        map.put(0, -1);
       
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
           
            if (map.containsKey(sum)) {
                ans.add(map.get(sum) + 1);
                ans.add(i);
                return ans;
            }
            
            map.put(sum, i);
        }
       
        return ans;
    }
}