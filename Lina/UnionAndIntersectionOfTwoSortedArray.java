For example, if the input arrays are: 
arr1[] = {1, 3, 4, 5, 7}
arr2[] = {2, 3, 5, 6}
Then your program should print Union as {1, 2, 3, 4, 5, 6, 7} and Intersection as {3, 5}.


Algorithm Union(arr1[], arr2[]):
For union of two arrays, follow the following merge procedure.
1) Use two index variables i and j, initial values i = 0, j = 0
2) If arr1[i] is smaller than arr2[j] then print arr1[i] and increment i.
3) If arr1[i] is greater than arr2[j] then print arr2[j] and increment j.
4) If both are same then print any of them and increment both i and j.
5) Print remaining elements of the larger array.


Algorithm Intersection(arr1[], arr2[]):
For Intersection of two arrays, print the element only if the element is present in both arrays.
1) Use two index variables i and j, initial values i = 0, j = 0
2) If arr1[i] is smaller than arr2[j] then increment i.
3) If arr1[i] is greater than arr2[j] then increment j.
4) If both are same then print any of them and increment both i and j.

// Union
public class Solution {
	public static void union(int[] array1, int[] array2) {
		int index1 = 0;
		int index2 = 0;
		while (index1 < array1.length && index2 < array2.length) {
			if (array1[index1] < array2[index2]) {
				print(array1[index1]);
				index1++;
			} else if (array1[index1] > array2[index2]) {
				print(array2[index2]);
				index2++;
			} else {
				print(array1[index1]);
				index1++;
				index2++;
			}
		}
		if (index1 < array1.length) {
			while (index1 < array1.length) {
				print(array1[index1++]);
			}
		}
		if (index2 < array2.length) {
			while (index2 < array2.length) {
				print(array2[index2++]);
			}
		}
		return;
	}
}


// Intersection
	public static void intersection(int[] array1, int[] array2) {
		int index1 = 0;
		int index2 = 0;
		while (index1 < array1.length && index2 < array2.length) {
			if (array1[index1] < array2[index2]) {
				index1++;
			} else if (array1[index1] > array2[index2]) {
				index2++;
			} else {
				System.out.println(array1[index1]);
				index1++;
				index2++;
			}
		}
		return;
	}