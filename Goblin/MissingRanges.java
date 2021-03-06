/*Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].*/


public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        
    }
}

// My code
public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<String>();
        if (nums == null || nums.length == 0) {
            if (upper == lower) {
                res.add(String.valueOf(lower));
            }
            else {
                res.add(String.valueOf(lower) + "->" + String.valueOf(upper));
            }
            return res;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] + 1 == nums[i + 1]) {
                continue;
            } else {
                if (nums[i + 1] - nums[i] == 2) {
                    res.add(String.valueOf(nums[i] + 1));
                } else {
                    res.add(String.valueOf(nums[i] + 1) + "->" + String.valueOf(nums[i + 1] - 1));
                }
            }
        }
        if (nums[0] == lower + 1) {
            res.add(0, String.valueOf(lower));
        } else if (nums[0] > lower + 1) {
            res.add(0, String.valueOf(lower) + "->" + String.valueOf(nums[0] - 1));
        }
        if (nums[nums.length - 1] + 1 == upper) {
            res.add(String.valueOf(upper));
        } else if (nums[nums.length - 1] + 1 < upper) {
            res.add(String.valueOf(nums[nums.length - 1] + 1) + "->" + String.valueOf(upper));
        }
        return res;
    }
}


public class Solution {
    public List<String> findMissingRanges(int[] A, int lower, int upper) {
        List<String> result = new ArrayList<String>();
        int pre = lower - 1;
        for(int i = 0 ; i <= A.length; i++){
            int after = i == A.length ? upper + 1 : A[i]; 
            if(pre + 2 == after){
                result.add(String.valueOf(pre + 1));
            }else if(pre + 2 < after){
                result.add(String.valueOf(pre + 1) + "->" + String.valueOf(after - 1));
            }
            pre = after;
        }
        return result;
    }
}

// Another
public List<String> findMissingRanges(int[] a, int lo, int hi) {
  List<String> res = new ArrayList<String>();

  // the next number we need to find
  int next = lo;

  for (int i = 0; i < a.length; i++) {
    // not within the range yet
    if (a[i] < next) continue;

    // continue to find the next one
    if (a[i] == next) {
      next++;
      continue;
    }

    // get the missing range string format
    res.add(getRange(next, a[i] - 1));

    // now we need to find the next number
    next = a[i] + 1;
  }

  // do a final check
  if (next <= hi) res.add(getRange(next, hi));

  return res;
}

String getRange(int n1, int n2) {
  return (n1 == n2) ? String.valueOf(n1) : String.format("%d->%d", n1, n2);
}