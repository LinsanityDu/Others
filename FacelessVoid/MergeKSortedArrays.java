Other than exponential time complexity, a much better method is to use divide-and-conquer. Since merging two sorted lists is easy, we can recursively divide the k lists into two parts until there are no more than two lists. Then we can use a merge method similiar to the merge sort to merge the two lists together.

Assume the longest list contains n elements, the time required for dividing and merging lists is T(k)=2T(k/2)+O(nk). According to the Master Theorem, the time complexity is O(nklogk). The space complexity of this algorithm is O(1).

Following is my C++ implementation of the divide-conquer method.

- See more at: http://www.bo-yang.net/2014/07/21/merge-k-sorted-lists/#sthash.tFCp2gZ1.dpuf


如果要好一点的就用heap来选择头，O(L*K*log K)
或者每次合并两个，直到全合并完，也是O(L*K*log K)

Divide and Conquer
vector mergeKVector(vector> &lists) {
    vector result;
    if(lists.empty()) return result;
    return helper(lists, 0, lists.size() - 1);
}

vector helper(vector> &lists, int low, int high) {
    if(low >= high) return lists[low];
    int mid = low + (high - low) / 2;
    vector left = helper(lists, low, mid);
    vector right = helper(lists, mid + 1, high);
    return mergeTwoVector(left, right);
}

vector mergeTwoVector(vector a, vector b) {
    vector result(a.size() + b.size(), 0);
    int i = 0, j = 0;
    for(int k = 0; k < result.size(); ++k) {
        if(i < a.size() && j < b.size()) {
            if(a[i] < b[j]) result[k] = a[i++];
            else result[k] = b[j++];
        } else if(i < a.size()) {
            result[k] = a[i++];
        } else if(j < b.size()) {
            result[k] = b[j++];
        }
    }
    return result;
}


第二题也是LC但是略微神奇... 原题是Merge K Sorted Lists，但是面试官给的是Merge Sorted Arrays... 当时就傻乎乎的问了那size呢...然后还傻乎乎自言自语，最暴力的方法就是全部merge然后再sort咯，但是这样的话Sorted Array就没有意义了诶...面试官听到了就问我这样暴力解决的话时间复杂度是多少，我弱弱的回答说我知道Arrays.sort()是O(nlgn)。面试官表示嗯嗯这样太不好了，怎么改进。于是终于想起来了用Priority Queue。但是时间来不及了没有Code完，只是说了大概思路，面试官表示可以OK

We can merge arrays in O(nk*Logk) time using Min Heap. Following is detailed algorithm.

1. Create an output array of size n*k.
2. Create a min heap of size k and insert 1st element in all the arrays into a the heap
3. Repeat following steps n*k times.
     a) Get minimum element from heap (minimum is always at root) and store it in output array.
     b) Replace heap root with next element from the array from which the element is extracted. If the array doesn’t have any more elements, then replace root with infinite. After replacing the root, heapify the tree.

NKLOGK

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

// Nine Chapter
class Element {
    public int row, col, val;
    Element(int row, int col, int val) {
        this.row = row;
        this.col = col;
        this.val = val;
    }
}

public class Solution {
    private Comparator<Element> ElementComparator = new Comparator<Element>() {
        public int compare(Element left, Element right) {
            return left.val - right.val;
        }
    };
    
    /**
     * @param arrays k sorted integer arrays
     * @return a sorted array
     */
    public int[] mergekSortedArrays(int[][] arrays) {
        if (arrays == null) {
            return new int[0];
        }
        
        int total_size = 0;
        Queue<Element> Q = new PriorityQueue<Element>(
            arrays.length, ElementComparator);
            
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                Element elem = new Element(i, 0, arrays[i][0]);
                Q.add(elem);
                total_size += arrays[i].length;
            }
        }
        
        int[] result = new int[total_size];
        int index = 0;
        while (!Q.isEmpty()) {
            Element elem = Q.poll();
            result[index++] = elem.val;
            if (elem.col + 1 < arrays[elem.row].length) {
                elem.col += 1;
                elem.val = arrays[elem.row][elem.col];
                Q.add(elem);
            }
        }
        
        return result;
    }
}