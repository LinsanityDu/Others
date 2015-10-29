/*Given a collection of numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].*/

public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        dfs(res, new ArrayList<Integer>(), nums);
        return res;
    }
    
    public void dfs(List<List<Integer>> res, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) {
                continue;
            }
            list.add(nums[i]);
            dfs(res, list, nums);
            list.remove(list.size() - 1);
        }
    }
}

// Non-recursion
class Solution {
    /**
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> nums) {
        ArrayList<ArrayList<Integer>> permutations
             = new ArrayList<ArrayList<Integer>>();
        if (nums == null || nums.size() == 0) {
            return permutations;
        }
        
        int n = nums.size();
        ArrayList<Integer> stack = new ArrayList<Integer>();
        
        stack.add(-1);
        while (stack.size() != 0) {
            Integer last = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);
            
            // increase the last number
            int next = -1;
            for (int i = last + 1; i < n; i++) {
                if (!stack.contains(i)) {
                    next = i;
                    break;
                }
            }
            if (next == -1) {
                continue;
            }
            
            // generate the next permutation
            stack.add(next);
            for (int i = 0; i < n; i++) {
                if (!stack.contains(i)) {
                    stack.add(i);
                }
            }
            
            // copy to permutations set
            ArrayList<Integer> permutation = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                permutation.add(nums.get(stack.get(i)));
            }
            permutations.add(permutation);
        }
        
        return permutations;
    }
}

// Iterative
/*the basic idea is, to permute n numbers, we can add the nth number into the resulting List<List<Integer>> from the n-1 numbers, in every possible position.

For example, if the input num[] is {1,2,3}: First, add 1 into the initial List<List<Integer>> (let's call it "answer").

Then, 2 can be added in front or after 1. So we have to copy the List in answer (it's just {1}), add 2 in position 0 of {1}, then copy the original {1} again, and add 2 in position 1. Now we have an answer of {{2,1},{1,2}}. There are 2 lists in the current answer.

Then we have to add 3. first copy {2,1} and {1,2}, add 3 in position 0; then copy {2,1} and {1,2}, and add 3 into position 1, then do the same thing for position 3. Finally we have 2*3=6 lists in answer, which is what we want.*/


public List<List<Integer>> permute(int[] num) {
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    if (num.length ==0) return ans;
    List<Integer> l0 = new ArrayList<Integer>();
    l0.add(num[0]);
    ans.add(l0);
    for (int i = 1; i< num.length; ++i){
        List<List<Integer>> new_ans = new ArrayList<List<Integer>>(); 
        for (int j = 0; j<=i; ++j){            
           for (List<Integer> l : ans){
               List<Integer> new_l = new ArrayList<Integer>(l);
               new_l.add(j,num[i]);
               new_ans.add(new_l);
           }
        }
        ans = new_ans;
    }
    return ans;
}


// Solution Based on swap

/**
Solution 2
Using a swap function. every level you swap the current index with one of the element on you right you increment current index by one.
**/
public List<List<Integer>> permute(int[] num) {
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    if (num == null || num.length == 0) {
      return ans;
    }
    permute(num, 0, ans);
    return ans;
}

//helper function
private void permute(int[] num, int index, List<List<Integer>> ans) {
    if (index == num.length) {
      List<Integer> one = new ArrayList<Integer>();
      for (int i : num) {
        one.add(i);
      }
      ans.add(one);
    } else {
      for (int i = index; i < num.length; ++i) {
        swap(num, index, i);
        permute(num, index+1, ans);
        swap(num, index, i); //swap it back
      }
    }
}

private void swap(int[] num, int i, int j) {
    int temp = num[i];
    num[i] = num[j];
    num[j] = temp;
}


// C++
class Solution {
public:
    vector<vector<int> > permute(vector<int> &num) {
        vector<vector<int> > result;

        permuteRecursive(num, 0, result);
        return result;
    }

    // permute num[begin..end]
    // invariant: num[0..begin-1] have been fixed/permuted
    void permuteRecursive(vector<int> &num, int begin, vector<vector<int> > &result)    {
        if (begin >= num.size()) {
            // one permutation instance
            result.push_back(num);
            return;
        }

        for (int i = begin; i < num.size(); i++) {
            swap(num[begin], num[i]);
            permuteRecursive(num, begin + 1, result);
            // reset
            swap(num[begin], num[i]);
        }
    }
};