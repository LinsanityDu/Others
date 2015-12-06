/*Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Hint:

Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.*/

这题有不同做法，可以用dfs判断联通和找环，也可以用并查集来做。
并查集的理解思路相对简单一些，首先初始化一个长度为n的并查集，遍历所有edge，首先find这个edge的两个节点，如果已经有同一个祖先，则表明存在环，也就不可能是树。构建并查集之后，再扫一遍找出祖先的数量即可，超过一个就不是树。
注意，这里是无向图，对有向图来说，需要判断所有节点的入度是否大于1。可以用一个入度的数组来保存。


// Discussion Three Solution
class Node{
    int val;
    Node parent;
    public Node(int val)
    {
        this.val = val;
    }
}

public class Solution {
    Map<Integer, Node> forest;

    public boolean validTree(int n, int[][] edges) {
        return unionFind(n, edges);
    }

    private boolean unionFind(int n, int[][] edges)
    {
        // make set for each node
        forest = new HashMap<Integer, Node>();
        for(int i = 0; i < n; i++)
            forest.put(i, makeSet(i));

        // for the two vertice of each edge, find if they are in the same set,
        // If so, there is a cycle, return false.
        for(int[] edge : edges)
        {
            if(find(edge[0]) == find(edge[1]))
                return false;

            union(edge[0], edge[1]);
        }

        return edges.length == n - 1;
    }

    private Node makeSet(int val)
    {
        Node node = new Node(val);
        node.parent = node;
        return node;
    }

    private Node find(int node)
    {
        if(forest.get(node).parent.val == node)
            return forest.get(node);

        return find(forest.get(node).parent.val);
    }

    private void union(int node1, int node2)
    {
        Node set1 = find(node1);
        Node set2 = find(node2);
        set1.parent = set2;
    }

    // DFS, using stack
    private boolean validDFS(int n, int[][] edges)
    {
        // build the graph using adjacent list
        List<Set<Integer>> graph = new ArrayList<Set<Integer>>();
        for(int i = 0; i < n; i++)
            graph.add(new HashSet<Integer>());
        for(int[] edge : edges)
        {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // no cycle
        boolean[] visited = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.push(0);
        while(!stack.isEmpty())
        {
            int node = stack.pop();
            if(visited[node])
                return false;
            visited[node] = true;
            for(int neighbor : graph.get(node))
            {
                stack.push(neighbor);
                graph.get(neighbor).remove(node);
            }
        }

        // fully connected
        for(boolean result : visited)
        {
            if(!result)
                return false;
        }

        return true;
    }

    // BFS, using queue
    private boolean valid(int n, int[][] edges)
    {
        // build the graph using adjacent list
        List<Set<Integer>> graph = new ArrayList<Set<Integer>>();
        for(int i = 0; i < n; i++)
            graph.add(new HashSet<Integer>());
        for(int[] edge : edges)
        {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // no cycle
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<Integer>();
        queue.add(0);
        while(!queue.isEmpty())
        {
            int node = queue.poll();
            if(visited[node])
                return false;
            visited[node] = true;
            for(int neighbor : graph.get(node))
            {
                queue.offer(neighbor);
                graph.get(neighbor).remove((Integer)node);
            }
        }

        // fully connected
        for(boolean result : visited)
        {
            if(!result)
                return false;
        }

        return true;
    }
}

public class Solution {
    public boolean validTree(int n, int[][] edges) {
        
    }
}





public class Solution {
    public boolean validTree(int n, int[][] edges) {
        // initialize n isolated islands
        int[] nums = new int[n];
        Arrays.fill(nums, -1);

        // perform union find
        for (int i = 0; i < edges.length; i++) {
            int x = find(nums, edges[i][0]);
            int y = find(nums, edges[i][1]);

            // if two vertices happen to be in the same set
            // then there's a cycle
            if (x == y) return false;

            // union
            nums[x] = y;
        }

        return edges.length == n - 1;
    }

    int find(int nums[], int i) {
        if (nums[i] == -1) return i;
        return find(nums, nums[i]);
    }
}


public class Solution {
    public boolean validTree(int n, int[][] edges) {
        // initialize adjacency list
        List<List<Integer>> adjList = new ArrayList<List<Integer>>(n);

        // initialize vertices
        for (int i = 0; i < n; i++)
            adjList.add(i, new ArrayList<Integer>());

        // add edges    
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0], v = edges[i][1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        boolean[] visited = new boolean[n];

        // make sure there's no cycle
        if (hasCycle(adjList, 0, visited, -1))
            return false;

        // make sure all vertices are connected
        for (int i = 0; i < n; i++) {
            if (!visited[i]) 
                return false;
        }

        return true;
    }

    // check if an undirected graph has cycle started from vertex u
    boolean hasCycle(List<List<Integer>> adjList, int u, boolean[] visited, int parent) {
        visited[u] = true;

        for (int i = 0; i < adjList.get(u).size(); i++) {
            int v = adjList.get(u).get(i);

            if ((visited[v] && parent != v) || (!visited[v] && hasCycle(adjList, v, visited, u)))
                return true;
        }

        return false;
    }
}