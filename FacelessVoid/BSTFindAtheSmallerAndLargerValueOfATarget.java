2. Given the root of a binary search tree of integers and another integer ‘target’, find the number which is just smaller and just larger than the target.
其实就是找inorder traversal的predecessor 和 successor。但是这样的complexity是linear的，问能不能优化，我就说可以binary search 到target， 说不定能到O(log(n))。当时犹豫的点是，找到了inorder succesor, 还得记录parent啥的。总之大脑一片混乱。。讨论了不少example，花费了很多时间。。。最后还是没搞清，说不如直接写code吧。
写着其实就明白了，就是search到target， 同时update相应的smaller number 和 larger number。代码如下。 最后找到的时候，还要看看children的情况。。。。
差点没写出来，写完了之后，好多需要优化，比如largestoftree啥的我一开始还逐个比较。。。其实直接找到最右边的child就好了。。。

class Node{
public:
    int val;
    Node *left, *right;
    Node(int val) {
        this->val = val;
        left = NULL;
        right = NULL;
    }
};

Node* findMinNode(Node *root) {
    if(!root) return NULL;
    while(root->left) root = root->left;
    return root;
}

Node* findMaxNode(Node *root) {
    if(!root) return NULL;
    while(root->right) root = root->right;
    return root;
}

void findNumbers(Node *root, int target, Node *&minNode, Node *&maxNode) {
    if(!root) return;
    if(root->val < target) {
        minNode = root;
        findNumbers(root->right, target, minNode, maxNode);
    } else if(root->val > target) {
        maxNode = root;
        findNumbers(root->left, target, minNode, maxNode);
    } else if(root->val == target) {
        if(root->left) minNode = findMaxNode(root->left);
        if(root->right) maxNode = findMinNode(root->right);
    }
}

void inordrerPrint(Node *root) {
    if(!root) return;
    if(root->left) inordrerPrint(root->left);
    cout << root->val << ", ";
    if(root->right) inordrerPrint(root->right);
}

int main(char **argv, int argc)
{
    Node *root = new Node(10);
    root->left = new Node(5);
    root->left->left = new Node(3);
    root->left->right = new Node(8);

    root->right = new Node(15);
    root->right->left = new Node(12);
    root->right->left->left = new Node(11);
    root->right->left->right = new Node(14);
    root->right->right = new Node(18);
    root->right->right->left = new Node(16);
    root->right->right->right = new Node(19);

inordrerPrint(root);
cout << endl;

    Node *prev = NULL, *next = NULL;
    for(int i = 4; i <= 18; ++i) {
        int target = i;
        findNumbers(root, target, prev, next);
        cout << "prev : " << prev->val << "  target : " << target << "  next : " << next->val << endl;
    }

    return 0;
}