Given a list of child->parent relationships, build a binary tree out of it. All the element Ids inside the tree are unique. 

Example: 

Given the following relationships: 

Child Parent IsLeft 
15 20 true 
19 80 true 
17 20 false 
16 80 false 
80 50 false 
50 null false 
20 50 true 


You should return the following tree: 
50 
/ \ 
20 80 
/ \ / \ 
15 17 19 16 


Function Signature 


/** 
* Represents a pair relation between one parent node and one child node inside a binary tree 
* If the _parent is null, it represents the ROOT node 
*/ 
public class Relation { 
	public Integer _parent; 
	public Integer _child; 
	public boolean _isLeft; 
} 


/** 
* Represents a single Node inside a binary tree 
*/ 
public class Node { 
	public Integer _id; 
	public Node _left; 
	public Node _right; 
} 

/** 
* Implement a method to build a tree from a list of parent-child relationships 
* And return the root Node of the tree 
*/ 
public Node buildTree (List<Relation> data) 
{ 
  
}

public static Node buildTree(List<Relation> data) {
		if (data == null || data.isEmpty()) {
			return null;
		}
		
		Node root = null;
		Map<Integer, Node> mapNode = new HashMap<Integer, Node>();
		for (Relation relation : data) {
			if (relation.parent == null) {
				if (mapNode.containsKey(relation.child)) {
					root = mapNode.get(relation.child);
				} else {
					root = new Node(relation.child);
					mapNode.put(root.val, root);
				}
				continue;
			}
			
			Node parent;
			if (mapNode.containsKey(relation.parent)) {
				parent = mapNode.get(relation.parent);
				addChild(mapNode, relation, parent);
			} else {
				parent = new Node(relation.parent);
				mapNode.put(parent.val, parent);
				addChild(mapNode, relation, parent);
			}
		}
		
		return root;
	}

private static void addChild(Map<Integer, Node> mapNode, Relation relation, Node parent) {
		Node childNode = mapNode.containsKey(relation.child) ? mapNode.get(relation.child) : new Node(relation.child);
		if (relation.isLeft) {
			parent.left = childNode;
		} else {
			parent.right = childNode;
		}
		mapNode.put(childNode.val, childNode);
	}



O(n) solution using a map [O(n) memory] 

public Node buildTree(List<Relation> data)
{
	HashMap<Integer, Node> map = new HashMap<Integer, Node>();
	Node root = null;

	for(Relation r: data) {
		Node parent, child;

		if ((child = map.get(r.child)) == null) {
			System.out.println("Here");
			child = new Node();
			child.data = r.child;
			map.put(r.child, child);
		}
		if (r.parent == 0) {
				root = child;
				continue;
		}
		if ((parent = map.get(r.parent)) == null) {
			parent = new Node();
			parent.data = r.parent;
			map.put(r.parent, parent);
		}

		if (r.isLeft) {
			parent.left = child;
		} else {
			parent.right = child;
		}
	}
	return root;
}