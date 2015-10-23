/*You have two arrays of integers, where the integers do not repeat and the two arrays have no common integers. 

Let x be any integer in the first array, y any integer in the second. Find min(Abs(x-y)). That is, find the smallest difference between any of the integers in the two arrays. 

Assumptions: Assume both arrays are sorted in ascending order.*/

public class MinimumDiffBetweenArrays { 
	public static void main(String[] args) { 
		int i=0,j=0, min; 
		int[] a = {1,2,3,9}; 
		int[] b = {7,10,16,19}; 
		int ans = Math.abs(a[i]-b[j]); 
		while(i<a.length && j<b.length) { 
		if(a[i] < b[j]) { 
		min = b[j] - a[i]; 
		i++; 
		} else { 
		min = a[i] - b[j]; 
		j++; 
		} 
		ans = ans>min?min:ans; 
		} 
		System.out.println(ans); 
	} 
}


