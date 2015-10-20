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
35 
247 
968 
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
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Node node = queue.poll();
				if (node.left != null) {
					queue.offer(node.left);
				} else {
					queue.offer(node.right);
				}
				System.out.print(node.value + " ");
			}
			System.out.println("");
		}
		return; 
	}
}