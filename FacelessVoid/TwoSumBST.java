private boolean twoSumBST(TreeNode root, int sum) {
     Stack<TreeNode> stk1 = new Stack<TreeNode>();
     TreeNode cur1 = root;
     Stack<TreeNode> stk2 = new Stack<TreeNode>();
     TreeNode cur2 = root;
     boolean nextV1 = true;
     boolean nextV2 = true;
  int val1 = 0;
  int val2 = 0;
     while (true) {
      if (nextV1) {
       while (true) {
        if (cur1 != null) {
         stk1.push(cur1);
         cur1 = cur1.left;
        } else {
         cur1 = stk1.pop();
         val1 = cur1.val;
         cur1 = cur1.right;
         break;
        }
       }
      }
      
      if (nextV2) {
       while (true) {
        if (cur2 != null) {
         stk2.push(cur2);
         cur2 = cur2.right;
        } else {
         cur2 = stk2.pop();
         val2 = cur2.val;
         cur2 = cur2.left;
         break;
        }
       }
      }
      System.out.println(val1 + "+" + val2);
      if (val1 >= val2) {
       break;
      }
      if (val1 + val2 == sum) {
       return true;
      } else if (val1 + val2 < sum) {
       nextV1 = true;
       nextV2 = false;
      } else if (val1 + val2 > sum) {
       nextV1 = false;
       nextV2 = true;
      }
     }
     return false;
    }