/*第二题 find random maximum。就是一个int array 里面会有一到多个maximum，返回一个随机maximum的index
我说要3 pass，他提醒了一下，改成了2 pass
然后他问了一个特别坑爹的问题——能不能one pass 搞定，还说“我看你简历上说懂cryptography，你应该知道”……我知道个毛线啊！
赶着扯赶着支支吾吾然后他跟我说了一个关键词“reservoir sampling”*/

public static int index(int[] nums) {
	int max = Integer.MIN_VALUE;
	List list = new ArrayList<>();
	for (int i = 0; i < nums.length; i++) {
	max = Math.max(max, nums);
	}

	for (int i = 0; i < nums.length; i++) {
	if (max == nums) {
	list.add(i);
	}
	}
	return list.get(new Random().nextInt(list.size()));
}

补充内容 (2015-10-28 16:04):
2 pass, o(n) time, o(n) space， 欢迎拍砖

2 pass, o(n) time, o(1) space， 欢迎拍砖
public static int index0(int[] nums) {
int max = Integer.MIN_VALUE;
for (int i = 0; i < nums.length; i++) {
max = Math.max(max, nums);
}
int j = 0;
for (int i = 0; i < nums.length; i++) {
if (max == nums) {
nums[j] = i;
j++;
}
}
return nums[new Random().nextInt(j)];
}

如果是按照reservoir sampling，应该是用一个count算maximum的个数 
从头到尾一次便利就可以，先让array[0]作为选出的maximum，当前元素比之前的大，就直接替换，count = 1，遇到当前比选出的元素小的，忽略，一样的，用1/count的概率替换

public class findIndexOfMaxValue {
	public int find(int[] A) {
		Random rand = new Random();
		int[] reservior = new int[1];
		int maxValue = Integer.MIN_VALUE;
		int numOfMaxValue = 0;
		for(int i = 0; i < A.length; i++) {
			if(A[i] == maxValue) {
				numOfMaxValue++;
				int randNum = rand.nextInt(numOfMaxValue);
				if(randNum < 1) {
					reservior[0] = i;
				}
			}

			if(A[i] > maxValue) {
				maxValue = A[i];
				numOfMaxValue = 1;
				reservior[0] = i;
			}
		}
		return reservior[0];
	}	
	public static void main(String[] args) {
		findIndexOfMaxValue example = new findIndexOfMaxValue();
		int count4 = 0, count6 = 0;
		int[] A = {1,2,3,4,5,3,5};
		for(int i = 0; i<10000; i++) {	
			int res = example.find(A);
			if(res == 4) {
				count4 ++;
			}
			if(res == 6) {
				count6 ++;
			}
		}
		System.out.println("6 appears " + count6 + " times");
		System.out.println("4 appears " + count4 + " times");
        }
}