/*关于这题的思路与代码：
说实话，单纯从思路的角度而言，并不难，但是考虑到2个难点，要在20分钟之内写完，并且到bug free，我觉得基本上是不太可能的。
两个难道是什么呢？？ 1. 我们要返回什么？不难看出来， 最后要返回一个node，所以TreeNode 肯定是需要返回的变量之一；另外一个条件是什么？最长路径或者说最大深度，嗯，所以说，每个node往上层返的时候都需要返回这2个变量，一个是当前以node为root的时候最深common节点，一个是深度deep。为什么说这是难点之一？大部分leetcode的树的题，基本上都是只需要返回一个变量的，这时候要返回两个变量？怎么办？最普遍的2个办法，一种是另外创建一个类，这个类里面包含一个node跟一个int类型的MAXdeep变量。第二种是就在TreeNode这个类里面加一个int类型的变量depth，（请注意，这个depth并不是表示所在的instance的node的深度，比如node5->deep = 5,并不是说node5的深度是5哦，只是完全用来满足 返回给父函数的时候传递的第二个变量罢了）。 所以说当对任意一个节点调用这个函数的时候，都将返回，commonAncestorOfDeepest的节点，以及这个节点所在路径的depth。 

第二个难点在哪里？多叉树，而不是二叉树。或许你觉得多叉数，本质上与二叉树没有区别，嗯嗯嗯，不就是多了几个下一级的指针嘛？二叉树只有left, right。 多叉树则有N个，仅此而已。可是就因为需要用一个list来保存多叉树的指针，又会影响递归函数内的判断逻辑的复杂程度。说的更加直白一点，二叉树，你只需要比较一下left跟right就好了，而多叉树你需要写loop，以及count来遍历，所有的指针。*/

class TreeNode{
        int val;
        ArrayList<TreeNode> children;
        public TreeNode(int val){
                this.val = val;. from: 1point3acres.com/bbs 
                children = new ArrayList<>();
        }
}-google 1point3acres
class Result{
        TreeNode node;
        int maxDepth;
        public Result(TreeNode node, int maxDepth){
                this.node = node;
                this.maxDepth = maxDepth;
        }
}
public class LowestCommonAncestorForAnyTree{. From 1point 3acres bbs
        public static TreeNode find(TreeNode root){
        if(root == null || root.children.isEmpty()) return root;
                return helper(root).node;
        }
        public static Result helper(TreeNode root){. visit 1point3acres.com for more.
                if(root.children.isEmpty()) return new Result(root, 1);. From 1point 3acres bbs
                int size = root.children.size();
                int maxDepth = Integer.MIN_VALUE;
                Result r = new Result(root, maxDepth);
                for(int i = 0; i < size; i++){
                        Result tmp = helper(root.children.get(i).node);
                        if(tmp.maxDepth > maxDepth){
                                maxDepth = tmp.maxDepth;
                                r.node = tmp.node;
    // 把r.node=tmp.node 改成 r.node =maxDepth==1?root:tmp.node; 就可以了

                                r.maxDepth = tmp.maxDepth + 1;
                        }
                        else if(tmp.maxDepth == maxDepth){
                                r.node = root; 
                        }        
                }
     // 在return r前面加一句 if(r.maxDepth == 2) r.node = root;就好了
                return r;
        }


        public static void main(String[] args){
                TreeNode n1 = new TreeNode(1);
                TreeNode n2 = new TreeNode(2);
                TreeNode n3 = new TreeNode(3);
                TreeNode n4 = new TreeNode(4);
                TreeNode n5 = new TreeNode(5);
                TreeNode n6 = new TreeNode(6);. more info on 1point3acres.com
                TreeNode n7 = new TreeNode(7);. 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
                TreeNode n8 = new TreeNode(8);
                TreeNode n9 = new TreeNode(9);. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
                TreeNode n10 = new TreeNode(10);. 鍥磋鎴戜滑@1point 3 acres
                n1.children.add(n2);
                n1.children.add(n3);
                n1.children.add(n4);
                n2.children.add(n5);
                n2.children.add(n6);
                n4.children.add(n7);
                n5.children.add(n8);
                n5.children.add(n9);
                n6.children.add(n10);. from: 1point3acres.com/bbs 
                TreeNode res = find(n1);
                System.out.println(res.val);

        }
}