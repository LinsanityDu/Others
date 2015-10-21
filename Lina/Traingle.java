public interface Triangle {
 
    /**
     * Three segments of lengths A, B, C form a triangle iff
     *
     *      A + B > C
     *      B + C > A
     *      A + C > B
     *
     * e.g.
     *  6, 4, 5 can form a triangle
     * 10, 2, 7 can't
     *
     * Given a list of segments lengths algorithm should find at least one triplet of segments that form a triangle (if any).
     *
     * Method should return an array of either:
     * - 3 elements: segments that form a triangle (i.e. satisfy the condition above)
     * - empty array if there are no such segments
     */
    int[] getTriangleSides(int[] segments);
}

puclic class TriangleImp implements Triangle {
    @Override
    int[] getTriangleSides(int[] segments) {
        if (segments == null || segments.length < 3) {
            return null;
        }
        int[] result = new int[3];
        Arrays.sort(segments);
        for (int i = 0; i < segments.length - 2; i++) {
            int j = i + 1;
            for (int k = j + 1; k < segments.length; k++) {
                if (segments[i] + segments[j] > segments[k]) {
                    result[0] = segments[i];
                    result[1] = segments[j];
                    result[2] = segments[k];
                    return result;
                } else {
                    j++;
                }
            }
        }
        return null;
    }


// Triangle Count
public class Solution {
    /**
     * @param S: A list of integers
     * @return: An integer
     */
    public int triangleCount(int S[]) {
        // write your code here
        int left = 0, right = S.length - 1;
        int ans = 0;
        Arrays.sort(S);
        for(int i = 0; i < S.length; i++) {
            left = 0;
            right = i - 1;
            while(left < right) {
                if(S[left] + S[right] > S[i]) {
                    ans = ans + (right - left);
                    right --;
                } else {
                    left ++;
                }
            }
        }
        return ans;
    }
}