1. Task schedule: 给一个task list 比如： ABCDAABA， colddown time 是2， 问需要多长时间能完成这些任务。 每个task花费的时间是1， colddown time是指相同的任务必须等待冷却时间才能再一次执行， 要不然就得 空闲来等待。拿例子来说， 要完成这个任务需要的时间是11， ABCDA__AB_A，下划线是等待时

我的思路是跟LZ差不多，我是用Java的, 用HashMap 记录每个task(no matter char or other object), value 是index， 每次到一个task, 去hashMap 查询，如果查得到，并且currentIndex - index + 1 < 2. 时间就要补差值 1 or 2. 如果其他的情况，都可以直接运行task，然后更新hashMap

给定任务AABCB, 冷却时间k（相同任务之间的最短距离时间），任务顺序不能变，问完成任务的总时间。
例子：AABCB, k=2, A**ABC*B, 时间为8.
解法：用hashtable保存上次的时间。
Followup1：如果k很小，怎么优化？ 
解法：之前的hashtable的大小是unique task的大小，如果k很小，可以只维护k那么大的hashtable。
Followup2: 如果可以改变任务的顺序，最短的任务时间是多少？
例子：AABBC, K=2, AB*ABC, 时间为6.
解法：根据每个任务出现的频率排序，优先处理频率高的。但是具体细节没有时间讨论。
感觉前两问回答的还好，就是细节和反应有点慢，最后一问没时间讨论。预感非常强，肯定会被加面，果然。

感觉第一题的followup 是用LinkedHashMap去维护Ksize的list
第二个followup可以用TreeMap，每次把最早的任务挑出来.
/*- target arrangement I and II
	1. you have serveral tasks. k is the interval time between two same task. you should keep the order of the tasks
how much time you should spend to complete all the tasks:ABACD, 3 -> 7 because AB__ACD 
	use a hashtable to record the lastime of the each task
	2. you don't need to keep the order of the tasks, find 
the shortest time you spend to complete all the problems. Use greedy, count the frequence to each task. Then always choose the valid task with highest frequence. each time you should also update the frequence. */


public class TaskArrangeMentI {
	public static int getTime(String task, int k) {
		if (task == null || task.length() == 0) {
			return 0;
		}
		if (k == 0) {
			return task.length();
		}
		Map<Character, Integer> lastTime = new HashMap<Character, Integer>();
		int count = 0;
		for (int i = 0; i < task.length(); i++) {
			count++;
			if (lastTime.containsKey(task.charAt(i))) {
				int last = lastTime.get(task.charAt(i));
				if (count - last < k) {
					count = last + k;
				}
				lastTime.put(task.charAt(i), count);
			}
			else {
				lastTime.put(task.charAt(i), count);
			}
		}
		return count;
	}
}

2）Task Schedule 
Tasks : AABABCD. more info on 1point3acres.com
Cooled Time: 2
output ： 输出总共时间
要求保持Tasks 的执行顺序不变。执行一个task的时间是1， 求执行tasks系列一共需要多少时间，比如他的例子： A－－AB－ABCD return 10

public int getTime(String str, int cool) {
    HashMap<Character, Integer> map = new HashMap<>();
    int i = 0;
    int time = 0;
    while (i < str.length()) {
      char c = str.charAt(i);
      if (!map.containsKey(c) || map.get(c) <= time) {
        map.put(c, time + cool + 1);
        i++;
        time++;
      } else {
        time = map.get(c);
      }
    }
    return time;
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println(s.getTime("AABABCD", 2));
  }


  

int taskScheduler(String s) {
	char cold1 = '*';
	char cold2 = '*';
	int result = 0;
	for (int i = 0; i < s.length(); i++) {
		if (cold2 == s.charAt(i)) {
			result += 3;
			cold1 = cold2 = '*';
		} else if (cold1 = s.charAt(i)) {
			result += 2;
			cold1 = cold2;
			cold2 = '*';
		} else {
			result++;
			cold1 = cold2;
			cold2 = s.charAt(i);
		}
	}
	return result;
}



public static int  task(int [] input ,int N){
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); 
        map.put(input[0], 1);
        for(int i = 1 ; i < input.length; ++i){. more info on 1point3acres.com
            if(map.containsKey(input[i])){
               int maxNum = Math.max(map.get(input[i-1]) + 1, map.get(input[i]) + N + 1);
               map.put(input[i], maxNum);
            } else{
               map.put(input[i],map.get(input[i-1]) + 1);
           }
        }. more info on 1point3acres.com
        return map.get(input[input.length - 1]);    
    }




// Another
    /*
[1,2,1,2] N=3
1,2,_,_,1,2 得到len=6
因为要保持任务执行顺序一样
所以第二个任务1只能等上3个单位时间 才能执行 这三个单位时间 第一个被任务2占据 后两个是用'_'来表示单位时间
又比如.
[1] N=4 无论N是多少 都只输出长度1.
因为后面已经没有要继续执行的任务了，尤其是相同的任务
又比如
[1,2,1,2] N=2
[1,2,_,1,2] 长度应该是5
*/
import java.util.*;
public class Solution{
    
    
    public int executeTime(int[] tasks, int N){
        if(tasks == null){
            throw new IllegalArgumentException();
        }
        if(tasks.length == 0){
            return 0;
        }
        //(task ID, last execute time)
        HashMap<Integer, Integer> hashmap = 
                new HashMap<Integer, Integer>(); 
        int count = 0;
        for(int i = 0; i < tasks.length; i++){
            int task = tasks[i];
            if(!hashmap.containsKey(task)){
                count++;
                hashmap.put(task, count);
            }else{
                int lastTime = hashmap.get(task);
                if(count - lastTime >= N){
                    count++;
                }else{
                    count = lastTime + N;
                    count++;
                }
                hashmap.put(task, count);
            }
        }
        return count;
    }
    
    private class Entry implements Comparable<Entry>{
        int task;
        int count;
        
        public Entry(int t, int c){
            task = t;
            count = c;
        }
        
        public int compareTo(Entry that){
            return that.count - count;
        }
    }
    
    public int[] optimal(int[] tasks, int N){
        if(tasks == null || tasks.length == 0){
            throw new IllegalArgumentException();
        }
        int len = tasks.length;
        int[] res = new int[len];
        HashMap<Integer, Integer> hashmap = 
            new HashMap<Integer, Integer>();
        for(int task : tasks){
            if(!hashmap.containsKey(task)){
                hashmap.put(task, 1);
            }else{
                hashmap.put(task, hashmap.get(task) + 1);
            }
        }
        Entry[] entries = new Entry[hashmap.size()];
        int src = 0, total = 0;
        for(int task : hashmap.keySet()){
            entries[src++] = new Entry(task, hashmap.get(task));
            total += hashmap.get(task);
        }
        Arrays.sort(entries);
        //arrange
        src = 0;
        int gap = 0;
        while(total != 0){
            for(int i = 0; i < entries.length; i++){
                Entry entry = entries[i];
                if(entry.count != 0){
                    res[src++] = entry.task;
                    gap++;
                    total--;
                    entry.count--;
                }
                if(gap == N + 1){
                    gap = 0;
                    break;
                }
            }
        }
        return res;
    }
    
    
    
    
    public static void main(String[] args){

        int[] tasks = {1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
        Solution ins = new Solution();
        System.out.println(ins.executeTime(tasks, 2));
        int[] res = ins.optimal(tasks, 2);
        for(int i : res){
            System.out.print(i);
        }
    }
    
    
    
    
    
    
}
