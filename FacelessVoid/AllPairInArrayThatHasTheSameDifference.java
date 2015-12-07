/*Given an array of positive, unique, increasingly sorted numbers A, e.g. A = [1, 2, 3, 5, 6, 8, 9, 11, 12, 13]. Given a positive value K, e.g. K = 3. Output all pairs in A that differ exactly by K. 
e.g. 2, 5 
3, 6 
5, 8 
6, 9 
8, 11 
9, 12 
what is the runtime for your code?*/

public static void computeDifference(int[] arr, int k){
		for(int i = 0,j = 1; j < arr.length && i < j; ){
			if((arr[j] - arr[i]) == k){
				System.out.println(arr[j]+" "+arr[i]);
				j++;
			}else if((arr[j] - arr[i]) > k){
				i++;
			}else if((arr[j] - arr[i]) < k){
				j++;
				
			}
		}
	}