public class Fib {
    
    /*
     * recursion O(2^N)
     */
    public static int fib(int n){
        if(n < 0){
            throw new IllegalArgumentException();
        }
        if(n == 0) return 0;
        if(n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }
    
    /*
     * DP O(n) time and space complexity 
     */
    public static int fib2(int n){
        if(n < 0){
            throw new IllegalArgumentException();
        }
        if(n == 0) return 0;
        if(n == 1) return 1;
        
        int[] states = new int[n + 1];
        states[0] = 0;
        states[1] = 1;
        for(int i = 2; i <=n; i++){
            states[i] = states[i - 1] + states[i - 2];
        }
        return states[n];
    }
    
    /*
     * Optimized DP O(n) time and O(1) space complexity 
     */
    public static int fib3(int n){
        if(n < 0){
            throw new IllegalArgumentException();
        }
        if(n == 0) return 0;
        if(n == 1) return 1;
        
        int num1 = 0, num2 = 1, res = 0;
        for(int i = 2; i <= n; i++){
            res = num1 + num2;
            num1 = num2;
            num2 = res;
        }
        return res;
    }
    
    /*
     * [1 1]  [Fn+1 Fn]
     * [1 0]  [Fn   Fn -1]
     * O(logn)
     */
    public static int fib4(int n){
        if(n < 0){
            throw new IllegalArgumentException();
        }
        if(n == 0) return 0;
        if(n == 1) return 1;
        
        int[][] matrix = {{1, 1}, {1, 0}};
        pow(matrix, n - 1);
        return matrix[0][0];
    }
    
    private static void pow(int[][] matrix, int n){
        if(n == 0 || n == 1){
            return;
        }
        pow(matrix, n / 2);
        multiply(matrix, matrix);
        if(n % 2 != 0){
            int[][] temp = {{1, 1}, {1, 0}};
            multiply(matrix, temp);
        }
    }
    
    private static void multiply(int[][] matrix, int[][] base){
        int x = matrix[0][0] * base[0][0] + matrix[0][1] * base[1][0];
        int y = matrix[0][0] * base[0][1] + matrix[0][1] * base[1][1];
        int z = matrix[1][0] * base[0][0] + matrix[1][1] * base[1][0];
        int k = matrix[1][0] * base[0][1] + matrix[1][1] * base[1][1];
        matrix[0][0] = x;
        matrix[0][1] = y;
        matrix[1][0] = z;
        matrix[1][1] = k;
    }
    
    
    public static void main(String[] args){
        for(int i = 0; i <= 20; i++){
            System.out.println(fib(i) + " " + fib2(i) + " " + fib3(i) + " " + fib4(i));
            
        }
    }
    
    

}






FibN

ON

int fib(int n) {
    int a = 0, b = 1, i;
    if( n == 0) return a;
    for (i = 2; i <= n; i++) {
        auto old = a + b;
        a = b;
        b = old;
    }
    return b;
}

Log(N)
public int getNthfibo(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n cannot be negative");
        }. Waral 鍗氬鏈夋洿澶氭枃绔�,
. 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
        if (n <= 1) return n;

        int[][] result = {{1, 0}, {0, 1}};
        int[][] fiboM = {{1, 1}, {1, 0}};

        while (n > 0) {
            if (n % 2 == 1) {
                multMatrix(result, fiboM);
            }
            n = n / 2;
            multMatrix(fiboM, fiboM);
        }

        return result[1][0];
    }

    private void multMatrix(int[][] m, int [][] n) {. visit 1point3acres.com for more.
        int a = m[0][0] * n[0][0] +  m[0][1] * n[1][0];
        int b = m[0][0] * n[0][1] +  m[0][1] * n[1][1];
        int c = m[1][0] * n[0][0] +  m[1][1] * n[1][0];
        int d = m[1][0] * n[0][1] +  m[1][1] * n[1][1];

        m[0][0] = a;
        m[0][1] = b;. 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
        m[1][0] = c;
        m[1][1] = d;. from: 1point3acres.com/bbs 
    }