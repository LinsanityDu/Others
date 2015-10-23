public interface FirstCommonAncestor {

    /**
     * Given two nodes of a tree,
     * method should return the deepest common ancestor of those nodes.. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
     *
     *          A 
     *         / \
     *        B   C
     *       / \
     *      D   E
     *         / \
     *        G   F
     *
     *  commonAncestor(D, F) = B
     *  commonAncestor(C, G) = A
     *  commonAncestor(E, B) = B
     */
    Node commonAncestor(Node one, Node two);
}

class Node {

    final Node parent;
    final Node left;
    final Node right;


    public Node(Node parent, Node left, Node right) {
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    bool isRoot() {

        return parent == null;
    }
}