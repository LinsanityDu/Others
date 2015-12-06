/*面试官说是道新题  finding ali baba
就是ali baba是个怪兽，他可能在[0，1, ..., numCaves-1]出现，他每隔一天就要换
一个地方，但他每次只能移左右一格。
然后给你一个strategy[]数组，数组里面是猜测每天ali baba的位置。如果里面有一个
猜中了，这个strategy就是正确的。
问题就是让你写一个判断函数 alibaba(int numCaves, int[] strategy)来判别这个
strategy数组是不是稳赢的策略*/

/*Let's define a boolean array of the length n as pos. If pos is true, it means alibaba can be in the ith house at this moment. For each day, we update the pos. If we find that there is no where alibaba can be, it turns out to be successful.*/

这道其实是老题了，之前就有出现过。很快这道题就会放到leetcode上的，你到时可以去试试。对于这道题，既然是判断时候稳赢，意思就是对于无论ali的起始位置在哪儿，我们都能抓到它。所以很自然，ali所在的位置肯定会作为状态的一部分。另外一个显然的需要记录的状态，就是strategy的当前位置。所以我们可以很容易写下如下的代码：

public static boolean catchAlibaba(int numCaves, int[] strategy){
        int i,j,l=strategy.length;
        boolean[][] dp = new boolean[numCaves][l];
        dp[strategy[l-1]][l-1] = true;
        
        for(j=l-2;j>=0;j--){
                dp[0][j] = dp[1][j+1];
                for(i=1;i<numCaves-1;i++){
                        dp[i][j] = dp[i-1][j+1] && dp[i+1][j+1]; 
                }
                dp[numCaves-1][j] = dp[numCaves-2][j+1];
                dp[strategy[j]][j] = true;
        }
        
        for(i=0;i<numCaves;i++){
                if(!dp[i][0]) return false;
        }. 1point3acres.com/bbs
        return true;
}. From 1point 3acres bbs
        
public static void main(String[] args){
        System.out.println(catchAlibaba(3, new int[]{1,1}));
        System.out.println(catchAlibaba(4, new int[]{1,1,2,2,1}));
}
