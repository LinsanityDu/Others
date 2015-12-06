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
