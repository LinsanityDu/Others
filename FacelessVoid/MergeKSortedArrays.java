第二题也是LC但是略微神奇... 原题是Merge K Sorted Lists，但是面试官给的是Merge Sorted Arrays... 当时就傻乎乎的问了那size呢...然后还傻乎乎自言自语，最暴力的方法就是全部merge然后再sort咯，但是这样的话Sorted Array就没有意义了诶...面试官听到了就问我这样暴力解决的话时间复杂度是多少，我弱弱的回答说我知道Arrays.sort()是O(nlgn)。面试官表示嗯嗯这样太不好了，怎么改进。于是终于想起来了用Priority Queue。但是时间来不及了没有Code完，只是说了大概思路，面试官表示可以OK


NLOGK

class ArrayContainer implements Comparable<ArrayContainer> {
	int[] arr;
	int index;
 
	public ArrayContainer(int[] arr, int index) {
		this.arr = arr;
		this.index = index;
	}
 
	@Override
	public int compareTo(ArrayContainer o) {
		if (this.arr[this.index] > o.arr[o.index]) {
			return 1;
		} else if (this.arr[this.index] < o.arr[o.index]) {
			return -1;
		} else {
			return 0;
		}
	}
}
public class KSortedArray {

	class ArrayContainer implements Comparable<ArrayContainer> {
		int[] arr;
		int index;
	 
		public ArrayContainer(int[] arr, int index) {
			this.arr = arr;
			this.index = index;
		}
	 
		@Override
		public int compareTo(ArrayContainer o) {
			if (this.arr[this.index] > o.arr[o.index]) {
				return 1;
			} else if (this.arr[this.index] < o.arr[o.index]) {
				return -1;
			} else {
				return 0;
			}
		}
	}

	public static int[] mergeKSortedArray(int[][] arr) {
		//PriorityQueue is heap in Java 
		PriorityQueue<ArrayContainer> queue = new PriorityQueue<ArrayContainer>();
		int total=0;
 
		//add arrays to heap
		for (int i = 0; i < arr.length; i++) {
			queue.add(new ArrayContainer(arr[i], 0));
			total = total + arr[i].length;
		}
 
		int m=0;
		int result[] = new int[total];
 
		//while heap is not empty
		while(!queue.isEmpty()){
			ArrayContainer ac = queue.poll();
			result[m++]=ac.arr[ac.index];
 
			if(ac.index < ac.arr.length-1){
				queue.add(new ArrayContainer(ac.arr, ac.index+1));
			}
		}
 
		return result;
	}
 
	public static void main(String[] args) {
		int[] arr1 = { 1, 3, 5, 7 };
		int[] arr2 = { 2, 4, 6, 8 };
		int[] arr3 = { 0, 9, 10, 11 };
 
		int[] result = mergeKSortedArray(new int[][] { arr1, arr2, arr3 });
		System.out.println(Arrays.toString(result));
	}
}