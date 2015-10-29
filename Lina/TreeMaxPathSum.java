/*For each node, 4 conditions: 
1. Node only （因为本题中的节点可能是负值！） 
2. L-sub + Node 
3. R-sub + Node 
4. L-sub + Node + R-sub (in here the max value cannot be passed to the parent)*/

public static int maxPathSum(TreeNode root) {  
        int[] max = {Integer.MIN_VALUE};        // 因为Java里都是pass by value所以利用数组传！  
        rec(root, max);  
        return max[0];  
    }  

    public static int rec(TreeNode root, int[] max){  
        if(root == null){  
            return 0;  
        }  

        int leftSubtreeMaxSum = rec(root.left, max);        // 左子树的最大和  
        int rightSubtreeMaxSum = rec(root.right, max);      // 右子树的最大和  
        int arch = leftSubtreeMaxSum + root.val + rightSubtreeMaxSum;       // 从左子树经过root到右子树的最大和  

        // 表示通过root节点能走到root的parent的最大和，这个值作为返回对象返给调用父函数  
        // 只有3中情况: 1 从左子树到root再到parent  
        // 2 从右子树到root再到parent  
        // 3 直接从root到parent  
        // 注意arch那条路是不可能走到parent，因为那条路已经经过root了，除非折回来重复走！但这是不允许的  
        int maxPathAcrossRootToParent = Math.max(root.val, Math.max(leftSubtreeMaxSum, rightSubtreeMaxSum)+root.val);  

        // max[0] 保存在所有递归过程中的最大值，这时也要考虑arch可能最大  
        max[0] = Math.max(max[0], Math.max(arch, maxPathAcrossRootToParent));  

        return maxPathAcrossRootToParent;  
    }  

}  
