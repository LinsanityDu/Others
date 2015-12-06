/*
3. 感谢面经~这是二叉树经典题目 BST转换成循环双向链表。leetcode类似题目请点击这里：Flatten Binary Tree to Linked List. from: 1point3acres.com/bbs 
其实我在面经贴：这里下面给出了自己的代码了。
具体思路是：
1. 拿出根节点。. From 1point 3acres bbs
2. 递归处理left， 找到左边最后一个节点，并且连接root到这个节点上。.1point3acres缃�
3. 递归处理right， 找到右边最前面的节点，并且连接root到这个节点上。
4. return。
recurrence
T(n) = 2T(n / 2) + 2logn => T(N) = AN + BlogN + C
*/

public class Solution{
    
    
    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        
        public TreeNode(int val){
            this.val = val;
        }
    }
    
    public TreeNode bstToLinkedList(TreeNode root){
        if(root == null){
            return null;
        }
        helper(root);
        //double linkedlist
        TreeNode head = root, tail = root;
        while(head.left != null){
            head = head.left;
        }
        while(tail.right != null){
            tail = tail.right;
        }
        return head;
    }
    
    private void helper(TreeNode root){
        if(root == null){
            return;
        }
        if(root.left != null){
            TreeNode leftChild = root.left;
            helper(leftChild);
            TreeNode pred = leftChild;
            while(pred.right != null){
                pred = pred.right;
            }
            //link to the root
            pred.right = root;
            root.left = pred;
        }
        if(root.right != null){
            TreeNode rightChild = root.right;
            helper(rightChild);
            TreeNode succ = rightChild;
            while(succ.left != null){
                succ = succ.left;
            }
            //link to the root
            root.right = succ;
            succ.left = root;
        }
    }
    
    public static void main(String[] args){
        TreeNode root = new TreeNode(10);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(30);
        node2.left = node3;
        node2.right = node4;
        root.left = node1;
        root.right = node2;
        TreeNode head = new Solution().bstToLinkedList(root);
        
        while(head != null){
            System.out.println(head.val);
            head = head.right;
        }
    }   
}


第一题
1. 问清楚input output之后，开始写recursion，递归处理左边，连上root，递归处理右边，连上root，最后找到头尾，连上循环。. 鍥磋鎴戜滑@1point 3 acres
2. 问complexity， 因为我处理完左边返回的是左边链表的中间，所以需要move到右端，所以时间是O(N^2)。
3. 怎样做更快，答用stack做inorder travasal。我问要不要写代码，他说不用。
这时候还有13分钟左右，开始第二题。

具体思路是：
1. 拿出根节点。
2. 递归处理left， 找到左边最后一个节点，并且连接root到这个节点上。. 
3. 递归处理right， 找到右边最前面的节点，并且连接root到这个节点上。. 鍗氬鏈夋洿澶氭枃绔�,
4. return。. 

public class SolutionConvert {
    public void solve(TreeNode root) {
        if (root == null) {
            return;. 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
        }. 鍥磋鎴戜滑@1point 3 acres
        convertToDoubleLinkedList(root);           
    }
    
    public void convertToDoubleLinkedList(TreeNode root) {. 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
        if (root == null) {
            return;
        }
        if (root.left != null) {
            TreeNode left = root.left;
            convertToDoubleLinkedList(left);
            while (left.right != null) {
                left = left.right;
            }-google 1point3acres
            left.right = root;
            root.left = left;
        }
        if (root.right != null) {
            TreeNode right = root.right;
            convertToDoubleLinkedList(right);
            while (right.left != null) {
                right = right.left;.鏈枃鍘熷垱鑷�1point3acres璁哄潧
            }
            right.left = root;
            root.right = right;
        }
    }
    
    public void test(String[] args) {
        TreeNode a = new TreeNode("10");
        TreeNode b = new TreeNode("12");
        TreeNode c = new TreeNode("15");
        TreeNode d = new TreeNode("25");
        TreeNode e = new TreeNode("30");
        TreeNode f = new TreeNode("36");
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        solve(a);
        while (a.left != null) {
            a = a.left;
        }
        while (a != null) {
            System.out.print(a.val + " ");
            a = a.right;
        }      . 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
    }
}