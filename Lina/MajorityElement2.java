public class Solution {
    /**
     * @param nums: A list of integers
     * @return: The majority number that occurs more than 1/3
     */
    public int majorityNumber(ArrayList<Integer> nums) {
        // write your code
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        int count1 = 0;
        int count2 = 0;
        int number1 = nums.get(0);
        int number2 = nums.get(0);
        
        for (Integer num : nums) {
            if (count1 == 0) {
                number1 = num;
                count1++;
            } else if (count2 == 0 && num != number1) {
                number2 = num;
                count2++;
            } else {
                if (num == number1) {
                    count1++;
                } else if (num == number2) {
                    count2++;
                } else {
                    count1--;
                    count2--;
                }
            }
        }
        
        count1 = 0;
        count2 = 0;
        for (Integer num : nums) {
            if (num == number1) {
                count1++;
            } else if (num == number2) {
                count2++;
            }
        }
        
        return count1 > count2 ? number1 : number2;
    }
}

