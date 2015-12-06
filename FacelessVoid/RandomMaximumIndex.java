翻了一圈儿其他关于 reservoir sampling 的帖子，可算是明白这题的意思了，留个记录给有需要的同学参考吧。
randomly return the index of max element in array

最简单的解法当然是跑一遍array，把max elements的index记录到list中，然后random一下list，即可满足题意。
. visit 1point3acres.com for more.
这样是O(N) time, O(N) space

follow up: 如果要求O（1）space呢？（或者说如果是个infinite array呢）
-google 1point3acres
这时候不能记录 index 了，只能在遍历数组时，记录一个当前遇到的最大值：max，以及最大值的个数：counter
同时维持一个 ret 变量，每次遇到 max 时，以 1/count 的概率更新 ret 为当前位置。

这样最后能保证 ret 是随机的一个max的index么，可以的，比如：

【1,2,3,3,3,3,1,2】

可以用递推证明，比如遇到第k个max时，假设它被返回的概率是随机的，即1/k，那么第k + 1个max出现的时候，按我们的替换方式，有 k/k+1的可能k ＋ 1不会被选中，也就是第k个max 幸存的概率为 被选中的概率*不被k+1换走的概率 = 1/k * k/(k + 1) = 1/(k + 1)


就是打比方说你有个数组[1,2,3,3,3,3,3]
然后你就一个个往后扫嘛，扫到某一个的时候要求等概率返回其中一个最大值，比如说扫到[1,2 这里，最大值是2，就他自己一个，就直接返回了，反正是100%的概率， 但是比如扫到了[1,2,3,3,3,3]这里的时候， 最大值3， 并且你现在扫出来了4个3， 于是乎你就要按1/4的概率返回其中的一个3.

第一次出现的最大值，那就是他自己了， 之后加入出现同样等于最大值的值，就用1/count, count=当前该最大值出现的次数。


第一题: 给一个全是数字的数组，随机返回0到当前位置中最大值得坐标
比如【1,2,3,3,3,3,1,2】
在最后一个2的时候有4个3都是最大值，要按1/4的概率返回其中一个3的index
public int findMax(vector<int> &arr){
        int len = arr.length();
        int ret =-1, max = INT_MIN;
        int count=0;-google 1point3acres
        for(int i=0; i<len; i++){
                if(arr==max){
                        count++;
                        srand(time(NULL));
                        int judge = rand()%count;
                        if(judge==0){
                                ret = i;
                        }
                }
                else if(max==INT_MIN || arr>max){
                        max = arr;
                        ret = i;
                        count=1;
                }
        }
        return ret;
}


给你一个array，返回array里面最大数字的index，但是必须是最大数字里面随机的一个index。比如[2,1,2,1,5,4,5,5]必须返回[4,6,7]中的随机的一个数字，要求O(1)space。
出现过很多次的FB的题，naive的做法是先扫一遍，找出最大值和最大值的个数。然后从头再扫一遍即可。

用Reservoir Sampling思路做，one pass就可以了。
int random_max(const vector<int>& nums) {
    int ret = 0, count = 0, max = INT_MIN;
    for(int i = 0; i < nums.size(); ++i) {
        if(nums[i] > max) {
            max = nums[i];
            count = 1;
            ret = i;
        } else if(nums[i] == max) {
            if ((rand() % ++count) == 0) ret = i;
        }
    }
    return ret;
}



// This is the text editor interface. 
// Anything you type or change here will be seen by the other person in real time.
/*
第一题: 给一个全是数字的数组，随机返回0到当前位置中最大值得坐标
比如【1,2,3,3,3,3,1,2】
在最后一个2的时候有4个3都是最大值，要按1/4的概率返回其中一个3的index
[1, 2, 3, 3, 3, 3]

k largest elements 
k + 1 
keep it with p 1 / k + 1
1    k
-  ------- =  1 / k + 1
k  k + 1
*/
import java.util.*;
public class Sample{
    
    public static int sampleIdx(int[] array){
        if(array == null || array.length == 0){
            throw new IllegalArgumentException();
        }
        Random rnd = new Random();
        int res = 0, max = Integer.MIN_VALUE, count = 0;
        for(int i = 0; i < array.length; i++){
            if(max < array[i]){
                max = array[i];
                res = i;
                count = 1;
            }else if(max == array[i]){
                count++;
                int idx = rnd.nextInt(count); //(0, k - 1)
                if(idx == 0){
                    res = i;
                }
            }
        }
        return res;
    }
    
    public static void main(String[] args){
        int[] nums = {1, 2, 3, 3, 3, 3, 1, 2};
        System.out.println(sampleIdx(nums));
        int[] counter = new int[nums.length];
        for(int i = 0; i < 1000; i++){
                int idx = sampleIdx(nums);
                counter[idx]++;
        }
        for(int i = 0; i < nums.length; i++){
                System.out.println(i + " " + counter[i]);
        }
    }
}