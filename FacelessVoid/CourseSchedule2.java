/*There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]

4, [[1,0],[2,0],[3,1],[3,2]]
There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].*/

/*求Course Schedule，等同问题是有向图检测环，vertex是course， edge是prerequisite。我觉得一般会使用Topological Sorting拓扑排序来检测。一个有向图假如有环则不存在Topological Order。一个DAG的Topological Order可以有大于1种。 常用的Topological Sorting算法有两种

Kahn's Algorithms (wiki)： BFS based， start from with vertices with 0 incoming edge，insert them into list S，at the same time we remove all their outgoing edges，after that find new vertices with 0 incoming edges and go on. 详细过程见Reference里Brown大学的课件。
Tarjan's Algorithms (wiki)： DFS based， loop through each node of the graph in an arbitrary order，initiating a depth-first search that terminates when it hits any node that has already been visited since the beginning of the topological sort or the node has no outgoing edges (i.e. a leaf node). 详细过程见Reference里 NYU的课件。
知道了理论，下面就是coding了，早就发现自己的coding能力确实比较弱，即使明白算法也很难迅速写出正确的程序。多练习，一点点进步吧。互换edge[0]和edge[1]也能检测环，不过输出的就是逆序的Tolopolical order了。

Kahn's Algorithms:

Time Complexity - O(VE)， Space Complexity - O(V)。 这里需要再研究一下怎么做到O(V + E)。*/

// Discussion BFS
public boolean canFinish(int numCourses, int[][] prerequisites) {
    int[][] matrix = new int[numCourses][numCourses]; // i -> j
    int[] indegree = new int[numCourses];

    for (int i=0; i<prerequisites.length; i++) {
        int ready = prerequisites[i][0];
        int pre = prerequisites[i][1];
        if (matrix[pre][ready] == 0)
            indegree[ready]++; //duplicate case
        matrix[pre][ready] = 1;
    }

    int count = 0;
    Queue<Integer> queue = new LinkedList();
    for (int i=0; i<indegree.length; i++) {
        if (indegree[i] == 0) queue.offer(i);
    }
    while (!queue.isEmpty()) {
        int course = queue.poll();
        count++;
        for (int i=0; i<numCourses; i++) {
            if (matrix[course][i] != 0) {
                if (--indegree[i] == 0)
                    queue.offer(i);
            }
        }
    }
    return count == numCourses;
}

// Another BFS
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        Queue queue = new LinkedList();
        int count=0;

        for(int i=0;i<numCourses;i++)
            graph[i] = new ArrayList();

        for(int i=0; i<prerequisites.length;i++){
            degree[prerequisites[i][1]]++;
            graph[prerequisites[i][0]].add(prerequisites[i][1]);
        }
        for(int i=0; i<degree.length;i++){
            if(degree[i] == 0){
                queue.add(i);
                count++;
            }
        }

        while(queue.size() != 0){
            int course = (int)queue.poll();
            for(int i=0; i<graph[course].size();i++){
                int pointer = (int)graph[course].get(i);
                degree[pointer]--;
                if(degree[pointer] == 0){
                    queue.add(pointer);
                    count++;
                }
            }
        }
        if(count == numCourses)
            return true;
        else    
            return false;
    }
}


// DFS
public class Solution {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            ArrayList[] graph = new ArrayList[numCourses];
            for(int i=0;i<numCourses;i++)
                graph[i] = new ArrayList();

            boolean[] visited = new boolean[numCourses];
            for(int i=0; i<prerequisites.length;i++){
                graph[prerequisites[i][1]].add(prerequisites[i][0]);
            }

            for(int i=0; i<numCourses; i++){
                if(!dfs(graph,visited,i))
                    return false;
            }
            return true;
        }

        private boolean dfs(ArrayList[] graph, boolean[] visited, int course){
            if(visited[course])
                return false;
            else
                visited[course] = true;

            for(int i=0; i<graph[course].size();i++){
                if(!dfs(graph,visited,(int)graph[course].get(i)))
                    return false;
            }
            visited[course] = false;
            return true;
        }
}


// Discussion
This question asks for an order in which prerequisite courses must be taken first. This prerequisite relationship reminds one of directed graphs. Then, the problem reduces to find a topological sort order of the courses, which would be a DAG if it has a valid order.

public int[] findOrder(int numCourses, int[][] prerequisites) {
    int[] incLinkCounts = new int[numCourses];
    List<List<Integer>> adjs = new ArrayList<>(numCourses);
    initialiseGraph(incLinkCounts, adjs, prerequisites);
    //return solveByBFS(incLinkCounts, adjs);
    return solveByDFS(adjs);
}
The first step is to transform it into a directed graph. Since it is likely to be sparse,we use adjacency list graph data structure. 1 -> 2 means 1 must be taken before 2.

private void initialiseGraph(int[] incLinkCounts, List<List<Integer>> adjs, int[][] prerequisites){
    int n = incLinkCounts.length;
    while (n-- > 0) adjs.add(new ArrayList<>());
    for (int[] edge : prerequisites) {
        incLinkCounts[edge[0]]++;
        adjs.get(edge[1]).add(edge[0]);
    }
}
How can we obtain a topological sort order of a DAG?

We observe that if a node has incoming edges, it has prerequisites. Therefore, the first few in the order must be those with no prerequisites, i.e. no incoming edges. Any non-empty DAG must have at least one node without incoming links. You can draw a small graph to convince yourself. If we visit these few and remove all edges attached to them, we are left with a smaller DAG, which is the same problem. This will then give our BFS solution.

private int[] solveByBFS(int[] incLinkCounts, List<List<Integer>> adjs){
    int[] order = new int[incLinkCounts.length];
    Queue<Integer> toVisit = new ArrayDeque<>();
    for (int i = 0; i < incLinkCounts.length; i++) {
        if (incLinkCounts[i] == 0) toVisit.offer(i);
    }
    int visited = 0;
    while (!toVisit.isEmpty()) {
        int from = toVisit.poll();
        order[visited++] = from;
        for (int to : adjs.get(from)) {
            incLinkCounts[to]--;
            if (incLinkCounts[to] == 0) toVisit.offer(to);
        }
    }
    return visited == incLinkCounts.length ? order : new int[0]; 
}
Another way to think about it is the last few in the order must be those which are not prerequisites of other courses. Thinking it recursively means if one node has unvisited child node, you should visit them first before you put this node down in the final order array. This sounds like the post-order of a DFS. Since we are putting nodes down in the reverse order, we should reverse it back to correct ordering or use a stack.

private int[] solveByDFS(List<List<Integer>> adjs) {
    BitSet hasCycle = new BitSet(1);
    BitSet visited = new BitSet(adjs.size());
    BitSet onStack = new BitSet(adjs.size());
    Deque<Integer> order = new ArrayDeque<>();
    for (int i = adjs.size() - 1; i >= 0; i--) {
        if (visited.get(i) == false && hasOrder(i, adjs, visited, onStack, order) == false) return new int[0];
    }
    int[] orderArray = new int[adjs.size()];
    for (int i = 0; !order.isEmpty(); i++) orderArray[i] = order.pop();
    return orderArray;
}

private boolean hasOrder(int from, List<List<Integer>> adjs, BitSet visited, BitSet onStack, Deque<Integer> order) {
    visited.set(from);
    onStack.set(from);
    for (int to : adjs.get(from)) {
        if (visited.get(to) == false) {
            if (hasOrder(to, adjs, visited, onStack, order) == false) return false;
        } else if (onStack.get(to) == true) {
            return false;
        }
    }
    onStack.clear(from);
    order.push(from);
    return true;
}


// my suck code
public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) {
            int[] temp = new int[numCourses];
            for (int i = 0; i < temp.length; i++) {
                temp[i] = i;
            }
            return temp;
        }
        int[] inLevel = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            inLevel[prerequisites[i][0]]++; 
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < inLevel.length; i++) {
            if (inLevel[i] == 0) {
                queue.offer(i);
            }
        }
        if (queue.isEmpty()) return new int[0];;
        int edge = prerequisites.length;
        int[] res = new int[numCourses];
        boolean[] visit = new boolean[prerequisites.length];
        int index = 0;
        while (!queue.isEmpty()) {
            int start = queue.poll();
            res[index++] = start;
            for (int i = 0; i < prerequisites.length; i++) {
                if (!visit[i] && prerequisites[i][1] == start) {
                    visit[i] = true;
                    inLevel[prerequisites[i][0]]--;
                    if (inLevel[prerequisites[i][0]] == 0) {
                        queue.offer(prerequisites[i][0]);
                    }
                    edge--;
                }
            }
        }
        if (edge == 0) {
            return res;
        } else {
            return new int[0];
        }
    }
}
