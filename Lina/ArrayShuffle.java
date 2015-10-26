/*public static int[] RandomizeArray(int[] array){
		Random rgen = new Random();  // Random number generator			
 
		for (int i=0; i<array.length; i++) {
		    int randomPosition = rgen.nextInt(array.length);
		    int temp = array[i];
		    array[i] = array[randomPosition];
		    array[randomPosition] = temp;
		}
 
		return array;
	}*/

public void shuffle(int[] A) {
    if (A == null || A.length == 0) return;
    for (int i = 0; i < A.length-1; ++i) {
        int random = Math.random()*(A.length-i) + i;
        int temp = A[i];
        A[i] = A[random];
        A[random] = temp;
    }
}



Java Random使用
Random.nextInt(int n): return a pseudorandom, uniformly distributed int value between 0 (inclusive) and the specified value (exclusive).

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

class Test
{
  public static void main(String args[])
  {
    int[] solutionArray = { 1, 2, 3, 4, 5, 6, 16, 15, 14, 13, 12, 11 };

    shuffleArray(solutionArray);
    for (int i = 0; i < solutionArray.length; i++)
    {
      System.out.print(solutionArray[i] + " ");
    }
    System.out.println();
  }

  // Implementing Fisher–Yates shuffle
  static void shuffleArray(int[] ar)
  {
    // If running on Java 6 or older, use `new Random()` on RHS here
    Random rnd = ThreadLocalRandom.current();
    for (int i = ar.length - 1; i > 0; i--)
    {
      int index = rnd.nextInt(i + 1);
      // Simple swap
      int a = ar[index];
      ar[index] = ar[i];
      ar[i] = a;
    }
  }
}
// From GeeksForGeeks
	To shuffle an array a of n elements (indices 0..n-1):
  for i from n - 1 downto 1 do
       j = random integer with 0 <= j <= i
       exchange a[j] and a[i]
       
How does this work?
The probability that ith element (including the last one) goes to last position is 1/n, because we randomly pick an element in first iteration.

The probability that ith element goes to second last position can be proved to be 1/n by dividing it in two cases.
Case 1: i = n-1 (index of last element):
The probability of last element going to second last position is = (probability that last element doesnt stay at its original position) x (probability that the index picked in previous step is picked again so that the last element is swapped)
So the probability = ((n-1)/n) x (1/(n-1)) = 1/n
Case 2: 0 < i < n-1 (index of non-last):
The probability of ith element going to second position = (probability that ith element is not picked in previous iteration) x (probability that ith element is picked in this iteration)
So the probability = ((n-1)/n) x (1/(n-1)) = 1/n

