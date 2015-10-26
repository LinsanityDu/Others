/*Given a tree string expression in balanced parenthesis format:
[A[B[C][D]][E][F]]
Construct a tree and return the root of the tree.
                A
            /   |  \
          B    E   F
         / \
       C   D*/

public Node builtTree(String str) {
	Stack<Node> stack = new Stack<Node>();
	int index = 0;
	while (i < str.length() - 1) {
		if (str.charAt(i) == '[') {
			Node node = new Node(str.charAt(i + 1));
			stack.push(node);
			i = i + 2;
		} else if (str.charAt(i) == ']') {
			Node temp = stack.pop();
			stack.peek().adj().add(temp);
			i++;
		}
	}
	return stack.peek();
}