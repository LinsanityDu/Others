1. Brute Force 递归。
基本的思想就是：在S1上找到一个切割点，左边长度为i, 右边长为len - i。 有2种情况表明它们是IsScramble
(1). S1的左边和S2的左边是IsScramble， S1的右边和S2的右边是IsScramble
(2). S1的左边和S2的右边是IsScramble， S1的右边和S2的左边是IsScramble （实际上是交换了S1的左右子树）

而i的取值可以是1  ~  len-1。 基于这个思想，我们可以写出以下的递归Brute Force 解：

引自stellari对复杂度的解释：

stellari
2014 年 5 月 12 日 at 14:22
看了你的不少文章，感觉收获良多！只是有点小问题想请教：按照我的理解，那个递归算法在最差情况下应该是O(3^n)，而非O(n^2)。理由是：假设函数运行时间为f(n)，那么由于在每次函数调用中都要考虑1~n之间的所有长度，并且正反都要检查，所以有
f(n) = 2[f(1) + f(n-1)] +2[f(2) + f(n-2)] … + 2[f(n/2) + f(n/2+1)]. 易推得f(n+1) = 3(fn), 故f(n) = O(3^n)。当然这是最差情况下的时间复杂度。那么你提到的O(n^2)，是否是通过其他数学方法得到的更tight的上限？欢迎探讨！

这一个解是不能通过LeetCode的检查的，复杂度是 3^N
2. 递归加剪枝
感谢unieagle的提示，我们可以在递归中加适当的剪枝，也就是说在进入递归前，先把2个字符串排序，再比较，如果不相同，则直接退出掉。这样也能有效地减少复杂度，具体多少算不清。但能通过leetcode的检查。

3. 递归加Memory

我们在递归中加上记忆矩阵，也可以减少重复运算，但是我们现在就改一下之前递归的结构以方便加上记忆矩阵，我们用index1记忆S1起始地址，index2记忆S2起始地址，len 表示字符串的长度。这样我们可以用一个三维数组来记录计算过的值，同样可以通过leetcode的检查。这个三维数组一个是N^3的复杂度，在每一个递归中，要从1-len地计算一次所有的子串，所以一共的复杂度是N^4

4. 动态规划。

其实如果写出了3，动态规划也就好写了。

三维动态规划题目：

我们提出维护量res[i][j][n]，其中i是s1的起始字符，j是s2的起始字符，而n是当前的字符串长度，res[i][j][len]表示的是以i和j分别为s1和s2起点的长度为len的字符串是不是互为scramble。
有了维护量我们接下来看看递推式，也就是怎么根据历史信息来得到res[i][j][len]。判断这个是不是满足，其实我们首先是把当前s1[i...i+len-1]字符串劈一刀分成两部分，然后分两种情况：第一种是左边和s2[j...j+len-1]左边部分是不是scramble，以及右边和s2[j...j+len-1]右边部分是不是scramble；第二种情况是左边和s2[j...j+len-1]右边部分是不是scramble，以及右边和s2[j...j+len-1]左边部分是不是scramble。如果以上两种情况有一种成立，说明s1[i...i+len-1]和s2[j...j+len-1]是scramble的。而对于判断这些左右部分是不是scramble我们是有历史信息的，因为长度小于n的所有情况我们都在前面求解过了（也就是长度是最外层循环）。
上面说的是劈一刀的情况，对于s1[i...i+len-1]我们有len-1种劈法，在这些劈法中只要有一种成立，那么两个串就是scramble的。
总结起来递推式是res[i][j][len] = || (res[i][j][k]&&res[i+k][j+k][len-k] || res[i][j+len-k][k]&&res[i+k][j][len-k]) 对于所有1<=k
如此总时间复杂度因为是三维动态规划，需要三层循环，加上每一步需要线行时间求解递推式，所以是O(n^4)。虽然已经比较高了，但是至少不是指数量级的，动态规划还是相当有用的，空间复杂度是O(n^3)。代码如下：

注：事实上这里最大的难点，是你怎么安排这三个循环。仔细看一下，计算len对应的解时，要用到一堆len-1的解。所以我们应该len 从0到1地这要子计算（三维啊都没办法通过画图来推导动态规划的递增关系了！）

public class Solution {
    public boolean isScramble(String s1, String s2) {
        if(s1 == null || s2 == null){
            return false;
        }
        if(s1.length() != s2.length()){
            return false;
        }
        return helper(s1, s2);
    }
    
    private boolean helper(String s1, String s2){
        if(s1.length() == 1){
            return s1.equals(s2);
        }
        
        int len = s1.length();
        for(int i = 1; i < len; i++){
            //left-left, right - right
            if( helper(s1.substring(0, i), s2.substring(0, i)) &&
                helper(s1.substring(i, len), s2.substring(i, len)) ){
                    return true;
            }
            //left-right, right-left
            if(helper(s1.substring(0, i), s2.substring(len - i, len)) &&
                helper(s1.substring(i, len), s2.substring(0, len - i))){
                    return true;
                }
        }
        return false;
    }
}


public class Solution {
    public boolean isScramble(String s1, String s2) {
        if(s1 == null || s2 == null){
            return false;
        }
        if(s1.length() != s2.length()){
            return false;
        }
        return helper(s1, s2);
    }
    
    private boolean helper(String s1, String s2){
        if(s1.length() == 1){
            return s1.equals(s2);
        }
        char[] chars = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        Arrays.sort(chars);
        Arrays.sort(chars2);
        String sorted1 = new String(chars);
        String sorted2 = new String(chars2);
        if(!sorted1.equals(sorted2)){
            return false;
        }
        
        
        int len = s1.length();
        for(int i = 1; i < len; i++){
            //left-left, right - right
            if( helper(s1.substring(0, i), s2.substring(0, i)) &&
                helper(s1.substring(i, len), s2.substring(i, len)) ){
                    return true;
            }
            //left-right, right-left
            if(helper(s1.substring(0, i), s2.substring(len - i, len)) &&
                helper(s1.substring(i, len), s2.substring(0, len - i))){
                    return true;
                }
        }
        return false;
    }
}

public class Solution {
    public boolean isScramble(String s1, String s2) {
        if(s1 == null || s2 == null){
            return false;
        }
        if(s1.length() != s2.length()){
            return false;
        }
        int len = s1.length();
        int[][][] mem = new int[len][len][len];
        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                for(int k = 0; k < len; k++){
                    mem[i][j][k] = -1;
                }
            }
        }
        return helper(s1, 0, s2, 0, len, mem);
    }
    
    private boolean helper(String s1, int idx1, String s2, int idx2, int len, int[][][] mem){
        if(len == 1){
            return s1.charAt(idx1) == s2.charAt(idx2);
        }
        int res = mem[idx1][idx2][len - 1];
        if(res != -1){
            return (res == 1)? true : false;
        }
        res = 0;
        for(int i = 1; i < len; i++){
            if(helper(s1, idx1, s2, idx2, i, mem) && helper(s1, idx1 + i, s2, idx2 + i, len - i, mem)){
                res = 1;
                break;
            }
            if(helper(s1, idx1, s2, idx2 + len - i, i, mem) &&
                helper(s1, idx1 + i, s2, idx2, len - i, mem)){
                res = 1;
                break;
            }
        }
        mem[idx1][idx2][len - 1] = res;
        return (res == 1)? true : false;
    }
}

public class Solution {
    public boolean isScramble(String s1, String s2) {
        if(s1 == null || s2 == null){
            return false;
        }
        if(s1.length() != s2.length()){
            return false;
        }
        int len = s1.length();
        boolean[][][] D = new boolean[len][len][len + 1];
        for(int k = 1; k <= len; k++){
            for(int i = 0; i <= len - k; i++){
                for(int j = 0; j <= len - k; j++){
                    if(k == 1){
                        D[i][j][k] = s1.charAt(i) == s2.charAt(j);
                        continue;
                    }
                    D[i][j][k] = false;
                    for(int l = 1; l <= k - 1; l++){
                        if(D[i][j][l] && D[i + l][j + l][k - l] ||
                            D[i][j + k - l][l] && D[i + l][j][k - l]){
                                D[i][j][k] = true;
                                break;
                            }
                    }
                }
            }
        }
        return D[0][0][len];
        
    }
    
    
}