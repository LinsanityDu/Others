/*Implement int sqrt(int x).

Compute and return the square root of x.*/

public class Solution {
    public int mySqrt(int x) {
        if (x <= 1) return x;
        long start = 0;
        long end = x;
        long mid;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (mid * mid > x) {
                end = mid;
            } else if (mid * mid < x) {
                start = mid;
            } else {
                return (int)mid;
            }
        }
        return (int)start;
    }
}

// Nine Chapter
class Solution {
    /**
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
        // find the last number which square of it <= x
        long start = 1, end = x;
        while (start + 1 < end) {
            long mid = start + (end - start) / 2;
            if (mid * mid <= x) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (end * end <= x) {
            return (int) end;
        }
        return (int) start;
    }
}