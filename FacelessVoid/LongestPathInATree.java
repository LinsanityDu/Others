int helper(Node *root, int &result) {
    if(!root) return 0;
    int l = helper(root->left, result);
    int r = helper(root->right, result);
    result = max(result, l + r + 1);
    return max(l + 1, r + 1);
}

java 要把result设置成全局变量吧

int longestPath(Node *root) {
    int result = 0;
    helper(root, result);
    return result;
}




class Solution {

public:

	int max_len;

	int height(TreeNode* root) {
		if(root == NULL) return 0;
		
		int l = height(root->left);
		int r = height(root->right);
		
		max_len = max(max_len, l + r + 1);

		return max(l, r) + 1;
	}

	int long_path(TreeNode* root) {
		max_len = 0;
		height(root);
		return max_len;
	}

};




