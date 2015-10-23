Tournament tree 找secMin;
Tournament tree 的定义是parent 是孩子node的最小值， 如下例 return 5

                2
               /  \
              2    7
            /  \   | \
           5    2  8  7




public class Solution {
    public static class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            public TreeNode(int v) {
                    val = v;
            }
    }
    
    public static int secMin(TreeNode root) {
            if (root == null || (root.left == null && root.right == null)) {
                    return -1;
            }
            int left = -1, right = -1;
            if (root.left.val == root.val) {
                    left = secMin(root.left);
            } else {
                    left = root.left.val;
            }
            if (root.right.val == root.val) {. 1point3acres.com/bbs
                    right = secMin(root.right);
            } else {
					right = root.right.val;
            }
            if (left == -1 || right == -1) {
                    return left == -1 ? right : left;
            }
            return Math.min(left, right);
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(2);. Waral 鍗氬鏈夋洿澶氭枃绔�,
        root.right.left = new TreeNode(8);
        root.right.right = new TreeNode(7);. visit 1point3acres.com for more.
        System.out.println(secMin(root));
    }
}
a O(logn) solution.        

public int getSecondMin(TreeNode root) {
         if (root == null || root.left == null) return Integer.MAX_VALUE;
                int candidate1 = Math.max(root.left.val, root.right.val);
                int candidate2 = root.left.val == root.val ? getSecondMin(root.left) : getSecondMin(root.right);
                return Math.min(candidate1, candidate2);
        }

// Iterative
        public static int getSec(TreeNode root) {
        int min = Integer.MAX_VALUE;
        int sMin = Integer.MAX_VALUE;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
            if(node.val < min) {-google 1point3acres
                sMin = min;. Waral 鍗氬鏈夋洿澶氭枃绔�,
                min = node.val;
            } else if (node.val < sMin){
                sMin = node.val;. visit 1point3acres.com for more.
            }
            if(root.left != null) {
                if(sMin == Integer.MAX_VALUE || root.left.val <= sMin) {
                    stack.push(root.left);
                }
            }
            if(root.right != null && root.right.val <= min) {
                if(sMin == Integer.MAX_VALUE || root.right.val <= sMin) {
                    stack.push(root.right);.鏈枃鍘熷垱鑷�1point3acres璁哄潧
                }
            }. 鍥磋鎴戜滑@1point 3 acres
        }
        return sMin;
    }

// Python

/*我觉得这道题说白了就是 给你一个list of integers,找second_min. 
常规的方法，就是一个个比较，比较的次数为 2*N-3, time complexity 是 O(N).
Optimize的方法就是1）一开始两两比较，每次取小，最后还剩一个的时候就是 最小
2）然后找第二小，要抓住的一点就是，第二小只会出现在，之前已经跟最小数比较过的
数中。. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴

比如这道题就是7和5 ： 在第一层的时候，我们知道最小值是2.；
进入第二层，我们发现left childer.val 是2，right.childreb是7，7比2大，就有可能是sec_min.. 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
但是7底下的sub tree就都不用考虑了，因为这个sub tree的所有值都肯定比7大；
所以我们就继续往left sub tree 考虑，发现5>2,所以5也是我们的candidate之一；如果5
下面还有subtree,同理也不需要考虑（因为它们都比5大）。
用这个方法找 最小值还是O(N). 找第二小的值是 O(log(N). 因为树的一层比较一次。
. Waral 鍗氬鏈夋洿澶氭枃绔�,
贴一下代码，不知道有没有考虑所有情况:import sys
class TreeNode:. 1point3acres.com/bbs
    def __init__(self,val):
        self.left  = None
        self.right = None. 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
        self.val   = val

def second_min(root):
    if not root.left and not root.right:
        return sys.maxsize
    if root.left.val == root.val:
        p = second_min(root.left)
        q = root.right.val
    else:
        p = second_min(root.right). 
        q = root.left.val. 
    return min(p,q)*/