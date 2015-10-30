/* input是个integer stream然后要output按照input顺序出现的unique的number
比如11134555211 => 13452*/

public class Solution {
	public void uniqueNumber(int[] nums) {
		if (nums == null || nums.length == 0) return;
		boolean[] visit = new boolean[10];
		visit[nums[0]] = true;
		System.out.print(nums[0]);
		for (int i = 1; i < nums.length; i++) {
			if (visit[nums[i]]) {
				continue;
			} else {
				System.out.print(nums[i]);
				visit[nums[i]] = true;
			}
		}
		return;
	}
}



class Solution {
  public static void uniqueNumber(int[] nums) {
    if (nums == null || nums.length == 0) return;
    boolean[] visit = new boolean[10];
    visit[nums[0]] = true;
    System.out.print(nums[0]);
    for (int i = 1; i < nums.length; i++) {
      if (visit[nums[i]]) {
        continue;
      } else {
        System.out.print(nums[i]);
        visit[nums[i]] = true;
      }
    }
    return;
  }
  public static void main(String[] args) {
    int[] nums = {1,1,1,3,4,5,5,5,2,1,1};
    uniqueNumber(nums);
  }
}