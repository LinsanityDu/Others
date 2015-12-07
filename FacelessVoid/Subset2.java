/*Given a collection of integers that might contain duplicates, nums, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]*/

// Nine Chapter
public class Solution {
    public ArrayList<ArrayList<Integer>> subsets(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(num == null || num.length ==0) {
            return result;
        }
        Arrays.sort(num);
        subsetsHelper(result, list, num, 0);

        return result;
    }

    private void subsetsHelper(ArrayList<ArrayList<Integer>> result,
        ArrayList<Integer> list, int[] num, int pos) {

        result.add(new ArrayList<Integer>(list));
        
        for (int i = pos; i < num.length; i++) {
            if ( i != pos && num[i] == num[i - 1]) {
                continue;
            }    
            list.add(num[i]);
            subsetsHelper(result, list, num, i + 1);
            list.remove(list.size() - 1);
        }
    }
}


public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
        	return res;
        }
        Arrays.sort(nums);
        dfs(res, new ArrayList<Integer>(), nums, new boolean[nums.length], 0);
        return res;
    }
    
    
    public void dfs(List<List<Integer>> res, List<Integer> list, int nums[], boolean visit[], int pos) {
    	res.add(new ArrayList<Integer>(list));
    	for (int i = pos; i < nums.length; i++) {
    		if (i != 0 && nums[i] == nums[i - 1] && !visit[i - 1]) {
    			continue;
    		} 
    		
    		if(!visit[i]) {
    			visit[i] = true;
    			list.add(nums[i]);
    			dfs(res, list, nums, visit, i);
    			list.remove(list.size() - 1);
    			visit[i] = false;
    		}
    	}
    }
}



/*To solve this problem, it is helpful to first think how many subsets are there. If there is no duplicate element, the answer is simply 2^n, where n is the number of elements. This is because you have two choices for each element, either putting it into the subset or not. So all subsets for this no-duplicate set can be easily constructed: num of subset

(1 to 2^0) empty set is the first subset
(2^0+1 to 2^1) add the first element into subset from (1)
(2^1+1 to 2^2) add the second element into subset (1 to 2^1)
(2^2+1 to 2^3) add the third element into subset (1 to 2^2)
....
(2^(n-1)+1 to 2^n) add the nth element into subset(1 to 2^(n-1))
Then how many subsets are there if there are duplicate elements? We can treat duplicate element as a spacial element. For example, if we have duplicate elements (5, 5), instead of treating them as two elements that are duplicate, we can treat it as one special element 5, but this element has more than two choices: you can either NOT put it into the subset, or put ONE 5 into the subset, or put TWO 5s into the subset. Therefore, we are given an array (a1, a2, a3, ..., an) with each of them appearing (k1, k2, k3, ..., kn) times, the number of subset is (k1+1)(k2+1)...(kn+1). We can easily see how to write down all the subsets similar to the approach above.*/


public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> empty = new ArrayList<Integer>();
        result.add(empty);
        Arrays.sort(num);

        for (int i = 0; i < num.length; i++) {
            int dupCount = 0;
            while( ((i+1) < num.length) && num[i+1] == num[i]) {
                dupCount++;
                i++;
            }
            int prevNum = result.size();
            for (int j = 0; j < prevNum; j++) {
                List<Integer> element = new ArrayList<Integer>(result.get(j));
                for (int t = 0; t <= dupCount; t++) {
                    element.add(num[i]);
                    result.add(new ArrayList<Integer>(element));
                }
            }
        }
        return result;
    }
}