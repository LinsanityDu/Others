/*There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

2, [[1,0],[0,1]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.*/

// My such code
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
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
        if (queue.isEmpty()) return false;
        int edge = prerequisites.length;
        boolean[] visit = new boolean[prerequisites.length];
        while (!queue.isEmpty()) {
            int start = queue.poll();
            for (int i = 0; i < prerequisites.length; i++) {
                if (!visit[i] && prerequisites[i][1] == start) {
                    visit[i] = true;
                    inLevel[prerequisites[i][0]]--;
                    if (inLevel[prerequisites[i][0]] == 0) {
                        queue.offer(prerequisites[i][0]);
                    }
                    edge--;
                    if (edge == 0) return true;
                }
            }
        }
        return false;
    }
}