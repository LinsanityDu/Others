public Node<T> compact(Node<T> root, int N) {
	Queue<Node<T>> queue = new LinkedList<Node<T>>();
	ArrayList<Node<T>> list = new ArrayList<Node<T>>();
	queue.offer(root);
	while (!queue.isEmpty()) {
		int size = queue.size();
		for (int i = 0; i < size; i++) {
			Node<T> node = queue.poll();
			list.add(node);
			for (Node<T> n : node.adj()) {
				queue.offer(n);
			}
		}
	}
	// Current List
	for (int i = 0; i < list.size(); i++) {
		Node<T> father = list.get(i);
		for (n = 1; n <= N; n++) {
			if (i * N + n >= list.size()) {
				return list.get(0);
			}
			father.adj.add(list.get(i * N + n));
		}
		list.set(i, father);
	}
	return list.get(0);
}

两个队列一个队列用来存等待设置N节点的节点，另一个队列保存bfs的结果。每次从BFS的队列中poll出peek，需要做三件事情，第一是将它设置为第一个队列peek的儿子，另外一个是将它的儿子放在BFS的队列中，第三个是将它放在第一个队列中等待设置NChild，需要给第一个队列的peek设置一个count，记录他已经设置了多少儿子。到N以后出队，count归0。