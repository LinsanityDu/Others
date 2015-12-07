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


这是一个非常好的搜索向dp方向进行时间优化，又进行空间优化的例子。

（1）从正常的解题思路，我觉得最开始的想法应该是搜索dfs，先搜索怪兽k天（就是strategy[]的大小）的所有跳跃路径，如果至少存在一种路径怪兽幸存，那说明策略不可行，具体搜索过程如下：
这里初始位置n个（格子数目），每次有2个方向可移动，总共移动k次，所以搜索复杂度O(n*2^k)。

>  (某海外论坛提到O(n*n^k)的复杂度，我认为这是有问题的（或者理解不对请指正），首先所有路径最多N^K种，同时还要满足左右方向移动的限制，不合法的路径肯定不会搜索到，所以最坏时间复杂度远小于N^K，当然你也可以认为不符合移动规则的直接去掉，这可能是我理解的剪枝)

（2）记忆化搜索，如果经过第i天第j个位置之后的所有路径都不合法（比如strategy【i】==j），那可以记录f(i,j)为false，表示经过第i天第j个格子的路径都不可行，下次再次来到这个位置，可以不用继续搜索。到此你会发现，其实记忆化搜索（dfs下的dp），这样(i,j)位置只会访问一次，而总共有kxN个位置，此时的时间和空间复杂度变为O(KN)。

（3）如果还要进行空间优化，就要转换成递推形式，f(i, j)表示路径经过第i天第j个格子是否可行：

初始化f(i,j) = true;
f(i,strategy【i】) = false; //false表示不可行的路径

if f(i-1, j-1) == false && f(i-1, j+1) == false
then f(i, j) = false
//如果前一天两个相邻的位置都不可达，那这个位置也不可达

最后结果就是检查f(k, 1...n)， 如果第k天，全部位置都是false，说明这个策略就是必赢的。然后空间优化就是使用滚动数组皆可。

有问题，请指正。