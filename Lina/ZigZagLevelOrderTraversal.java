/* 

Tree: 
1 
/ \ 
3 5 
/ \ \ 
2 4 7 
/ \ \ 
9 6 8 

========== 
Expected Output: 
1 
53 
247 
869 
*/ 

class TreePrinter { 

	static class Node { 
		int value; 
		Node left; 
		Node right; 
		public Node(int value, Node left, Node right) { 
			this.value = value; 
			this.left = left; 
			this.right = right; 
		} 
	} 

	public void printTree(Node root) { 
		// implementation here
			if (root == null) return;
			Stack<Node> currLevel = new Stack<Node>();
			Stack<Node> nextLevel = new Stack<Node>();
			Stack<Node> temp;

			currLevel.push(root);
			boolean normalOrder = true;

			while (!currLevel.isEmpty()) {
				while (!currLevel.isEmpty()) {
					Node node = currLevel.pop();
					if (normalOrder) {
						if (node.left != null) {
							nextLevel.push(node.left);
						} 
						if (node.right != null) {
							nextLevel.push(node.right);
						}
					} else {
						if (node.right != null) {
							nextLevel.push(node.right);
						}
						if (node.left != null) {
							nextLevel.push(node.left);
						} 
					}
					System.out.print(node.value + " ");
				}
				System.out.println("");
				temp = currLevel;
	            currLevel = nextLevel;
	            nextLevel = temp;
	            normalOrder = !normalOrder;
			}
			return;
		} 
	}
}