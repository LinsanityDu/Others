/*Given an index k, return the kth row of the Pascals triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?*/


public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<Integer>();
        if (rowIndex < 0) return res;
        res.add(1);
        for (int i = 1; i <= rowIndex; i ++) {
            List<Integer> cur = new ArrayList<Integer>(i + 1);
            for (int j = 0; j < i + 1; j ++) {
                if (j == 0 || j == i) {
                    cur.add(1);
                } else {
                    cur.add(res.get(j - 1) + res.get(j));
                }
            }
            res = cur;
        }

        return res;
    }
}