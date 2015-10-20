/** Implement a method which takes an integer array and returns an integer array (of equal size) in
* which each element is the product of every number in the input array with the exception of the
* number at that index.
*
* Example:
*   [3, 1, 4, 2] => [8, 24, 6, 12]
*/
public int[] selfExcludingProduct(int[] nums) {
    // implementation...
    if (nums == null || nums.length == 0) {
        return null;
    }
    int[] res = new int[nums.length];
    res[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
        res[i] = res[i - 1] * nums[i]; 
    }
    int cur = 1;
    for (int i = nums.length - 1; i > 0; i--) {
        res[i] = res[i - 1] * cur;
        cur *= nums[i]; 
    }
    res[0] = cur;
    return res;
}
