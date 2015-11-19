在原来的输入中会有重复Time，但排序后，是整理完1秒的数据后，才往Map<Time, Name>里写新的
比如<3, Cathy><1, Alice><1, Bob><2, Bob><1, Bob><3, Bob>. 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
排序后变为
<1, Alice><1, Bob><1, Bob><2, Bob><3, Bob><3, Cathy>
然后先一个一个看
1： Alice: 1 最大是Alice: 1
2： Bob: 1 最大是Alice: 1
3： Bob: 2 最大是Bob: 2.1

这个选票过后第一秒结束了，所以在Map<Time, Name>中写入<1, Bob>. 1point 3acres 璁哄潧

4： Bob: 3 最大是Bob: 3

这个选票过后第二秒结束了，所以在Map<Time, Name>中写入<2, Bob>. visit 1point3acres.com for more.

5： Bob: 4 最大是Bob: 4. from: 1point3acres.com/bbs 
6： Cathy: 1 最大是Bob: 4

这个选票过后第三秒结束了，所以在Map<Time, Name>中写入<3, Bob>

这样Map<Time, Name>就建好了，有三个值：. from: 1point3acres.com/bbs 
<1, Bob><2, Bob><3, Bob>
然后再取任意秒的时候就是O(1)

但是如果原题是这样：输入List是一个Stream，要求在有一个接口可以返回在当前时刻之前任意时刻的最多票数者，就不能这么做了吧，因为无法对Stream排序


hashMap<TimeStamp, HashMap<People, count>> 这样update 是o(1) search是o(n) 想不到search是o(logn) 的方法?

Map<name, count> m1 
Map<time, CurrentWinner> m2
CurrentWinner{
   int count;
   string name
}
public void load(List<vote> l){
    for(vote v: l){
       update m1; 
       update m2; 
   }
}

public String get(int second){
    m2.get(second).name
}