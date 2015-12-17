/*给一个质数数组，返回所有可能的product，顺序不管

比如给 [2,3,5] 返回 [2,3,5,6,10,15,30]

数组中的数如果有重复则需要去重，不允许用set。

比如给 [2,2,2] 返回 [2,4,8]，顺序不用管。*/
这个答案不对，看下一个！

public List<Integer> combinePrimeProduct(int[] primes) {  
    Arrays.sort(primes);  
    List<Integer> result = new ArrayList<Integer>();  
    combinePrimes(result, primes, 1, 0);  
    return result;  
}  
  
private void combinePrimes(List<Integer> result, int[] primes, int product, int start) {  
    if(start > primes.length) return;  
    if(product != 1) {  
        result.add(product);  
    }  
    for(int i=start; i<primes.length; i++) {  
        if(i>start && primes[i] == primes[i-1]) continue;  
        combinePrimes(result, primes, primes[i]*product, i+1);  
    }  
}  