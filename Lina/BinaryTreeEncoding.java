Design an algorithm and write code to serialize and deserialize a binary tree. Writing the tree to a file is called 'serialization' and reading back from the file to reconstruct the exact same binary tree is 'deserialization'.

There is no limit of how you deserialize or serialize a binary tree, you only need to make sure you can serialize a binary tree to a string and deserialize this string to the original structure.

An example of testdata: Binary tree {3,9,20,#,#,15,7}, denote the following structure:



/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
class Solution {
    /**
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        // write your code here
        if (root == null) {
            return "#,";
        } 
        
        String mid = root.val + ",";
        String left = serialize(root.left);
        String right = serialize(root.right);
        
        mid += left + right;
        return mid;
    }
    
    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    private String data = "";
    public TreeNode deserialize(String data) {
        // write your code here
        this.data = data;
        return desHelper();
    }
    
    public TreeNode desHelper() {
        if (this.data.indexOf("#,") == 0) {
            this.data = this.data.substring(data.indexOf(",") + 1);
            return null;
        }
        String midVal = this.data.substring(0, this.data.indexOf(","));
        TreeNode mid = new TreeNode(Integer.parseInt(midVal));
        this.data = this.data.substring(data.indexOf(",") + 1);
        TreeNode left = deserialize(data);
        TreeNode right = deserialize(data);
        mid.left = left;
        mid.right = right;
        return mid;
    }
}

// Another
Assume we have a binary tree below:

    _30_ 
   /    \    
  10    20
 /     /  \ 
50    45  35
Using pre-order traversal, the algorithm should write the following to a file:

30 10 50 # # # 20 45 # # 35 # #

/*SERIALIZE: The pre-order traversal code below does all the job to serialize a binary tree, believe it or not!*/

void writeBinaryTree(BinaryTree *p, ostream &out) {
  if (!p) {
    out << "# ";
  } else {
    out << p->data << " ";
    writeBinaryTree(p->left, out);
    writeBinaryTree(p->right, out);
  }
}
//deserialize
void readBinaryTree(BinaryTree *&p, ifstream &fin) {
  int token;
  bool isNumber;
  if (!readNextToken(token, fin, isNumber)) 
    return;
  if (isNumber) {
    p = new BinaryTree(token);
    readBinaryTree(p->left, fin);
    readBinaryTree(p->right, fin);
  }
}




Below is algorithm for encoding:

function EncodeSuccinct(node n, bitstring structure, array data) {
    if n = nil then
        append 0 to structure;
    else
        append 1 to structure;
        append n.data to data;
        EncodeSuccinct(n.left, structure, data);
        EncodeSuccinct(n.right, structure, data);
}
And below is algorithm for decoding

function DecodeSuccinct(bitstring structure, array data) {
    remove first bit of structure and put it in b
    if b = 1 then
        create a new node n
        remove first element of data and put it in n.data
        n.left = DecodeSuccinct(structure, data)
        n.right = DecodeSuccinct(structure, data)
        return n
    else
        return nil
}


// This function fills lists 'struc' and 'data'.  'struc' list
// stores structure information. 'data' list stores tree data
void EncodeSuccinct(Node *root, list<bool> &struc, list<int> &data)
{
    // If root is NULL, put 0 in structure array and return
    if (root == NULL)
    {
        struc.push_back(0);
        return;
    }
 
    // Else place 1 in structure array, key in 'data' array
    // and recur for left and right children
    struc.push_back(1);
    data.push_back(root->key);
    EncodeSuccinct(root->left, struc, data);
    EncodeSuccinct(root->right, struc, data);
}
 
// Constructs tree from 'struc' and 'data'
Node *DecodeSuccinct(list<bool> &struc, list<int> &data)
{
    if (struc.size() <= 0)
        return NULL;
 
    // Remove one item from from structure list
    bool b = struc.front();
    struc.pop_front();
 
    // If removed bit is 1,
    if (b == 1)
    {
         // remove an item from data list
        int key = data.front();
        data.pop_front();
 
        // Create a tree node with the removed data
        Node *root = newNode(key);
 
        // And recur to create left and right subtrees
        root->left = DecodeSuccinct(struc, data);
        root->right = DecodeSuccinct(struc, data);
        return root;
    }
 
    return NULL;
}
