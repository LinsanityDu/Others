/*The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.*/
我们可以使用递归来做。规律是：

一部分是n-1位格雷码，再加上 1<<(n-1)和n-1位格雷码的逆序的和
这道题要求求出n位的格雷码对应的二进制数，主要在于找到一种格雷码的递增方法（格雷码并不是唯一的，可以有多种）。
我们来看看有了n-1位的格雷码如何得到n位的格雷码呢？其实方法比较简单，首先在n-1位的格雷码前面都添加0作为前2^(n-1)个格雷码，它们一定是合法的因为除了第一位（都是0）其余位都跟n-1的格雷码一致，所以两两之间只相差一位，满足要求。接下来看看如何接上剩下的2^(n-1)个，我们把n-1位的格雷码倒序排列，然后在每个前面添加1，然后接到上述的前2^(n-1)个就可以了。首先因为是倒序过来，所以中间两个元素除去第一位其他都是一样的（因为原来最后一个，现在倒序过来就是第一个），而他们第一位分别是0和1，满足格雷码的要求。而剩下的元素因为我们是n-1位的格雷码倒序排列，所以两两都是满足要求的，加上前面都一样的位1，仍然满足条件。最后看看这些数字是不是都不一样，前半部分和后半部分肯定不会一样，而因为前半部分都是0开头，后半部分都是1打头，所以互相之间也不会有重复，可以看出覆盖了所有数字，而且依次下来均满足条件。
如此我们提出了格雷码的递推方法，我们只需要做一次位数的循环，每次根据上面结果构造当前位数的结果即可。算法复杂度是O(2+2^2+...+2^n-1)=O(2^n)，所以是指数量级的，因为是结果数量无法避免。空间复杂度则是结果的大小，也是O(2^n)。代码如下：

// Recursion
public class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> ret = new ArrayList<Integer>();
          if (n == 0) {
            ret.add(0);
             return ret;
         }
         
         ret = grayCode(n - 1);
         
         for (int i = ret.size() - 1; i >= 0; i--) {
             int num = ret.get(i);
             num += 1 << (n - 1);
             ret.add(num);
         }
         
         return ret;
    }
}

// No recursion
public class Solution {
    public ArrayList<Integer> grayCode(int n) {
        ArrayList<Integer> res=new ArrayList<Integer>();
        if(n<0)
            return res;
        if(n==0)
        {
            res.add(0);
            return res;
        }
        //n=1的格雷码
        res.add(0);
        res.add(1);
        for(int i=2;i<=n;i++)
        {
            int num=1<<(i-1);
            //第i位格雷码为（第i-1位格雷码前面+0）和（第i-1位格雷码倒序后前面+1）
            //而前面+0即为i-1位的格雷码，所以不需要做
            //只需要对i-1位进行倒序，然后前面+1
            for(int j=res.size()-1;j>=0;j--)
                res.add(res.get(j)+num);
        }
        return res;
    }
}


// Discussion
