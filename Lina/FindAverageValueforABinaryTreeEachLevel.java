
45min 面试
前10min讨论简历 以及对fb有兴趣和自己有兴趣的domain
technical ： find average value for each level in binary tree
solution : level order traversal

recursion
这个题目挺有意思的，明天要面他家，顺便做了下。其实map里value不用存储list，只用存上sum和count就可以了，这样space cost是O(H)而不是O(N)。祝LZ好运！

        class SumCnt {
                int sum;
                int count;
. more info on 1point3acres.com
                void accumulate(int val) {
                        sum += val;
                        count++;
                }. 1point3acres.com/bbs
                
                double avg() {
                        return (double) sum / count;.鏈枃鍘熷垱鑷�1point3acres璁哄潧
                }.鐣欏璁哄潧-涓€浜�-涓夊垎鍦�
                
                SumCnt(int val) {
                        sum = val;
                        count = 1;.鐣欏璁哄潧-涓€浜�-涓夊垎鍦�
                }
        }

        public List<Double> findAvgForEachLevel(TreeNode root) {
                List<Double> ret = new ArrayList<Double>();
                if (root == null) {.鐣欏璁哄潧-涓€浜�-涓夊垎鍦�
                        return ret;
                }
                
                Map<Integer, SumCnt> map = new HashMap<Integer, SumCnt>();
                int depth = dfs(root, map, 1);        鏉ユ簮涓€浜�.涓夊垎鍦拌鍧�. 
                
                for (int i = 1; i <= depth; ++i) {
                        ret.add(map.get(i).avg());
                }

                return ret;
        }

        // Return the depth of this tree.
        private int dfs(TreeNode root, Map<Integer, SumCnt> map, int level) {
                if (map.containsKey(level)) {
                        map.get(level).accumulate(root.val);
                } else {
                        map.put(level, new SumCnt(root.val));
                }
                
                int leftDepth = 0, rightDepth = 0;
                . 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
                if (root.left != null) {
                        leftDepth = dfs(root.left, map, level + 1);
                }
                
                if (root.right != null) {
                        rightDepth = dfs(root.right, map, level + 1);. more info on 1point3acres.com
                }
                . 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
                return Math.max(leftDepth, rightDepth) + 1;
        }