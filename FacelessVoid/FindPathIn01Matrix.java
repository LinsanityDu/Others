/*
  第一轮的问题是：给一个int矩阵，0代表empty，1代表obstacle，find whether there's a path between 2 nodes.  
  我这道题上来直接就用了dfs，而且写的磕磕绊绊，大概半小时才完全写好，导致整轮只做了一道题。后来想想这道题用bfs 或者 bidirectional bfs会更好。
  follow up是当图变得极大时，dfs会有什么问题。还有当我们有一个计算机集群的时候，可以如何加速算法。面试之后两天都没有消息，我就知道不妙。
  一周之后通知被加了一轮电面。
  
  0 1 0 0 0 0 1 0
  0 1 0 0 1 0 1 0
  0 0 1 0 0 1 0 0*/


print path from leftup to rightdown
M*N的地图上0表示可以走，1表示不能走，求从左上角到右下角的最短路径
很显然的BFS，也很快把基本的写出来了，然而我BFS每一步存了路径，大哥说这样内存消耗太大，怎么improve，然后在提示下想到每个node存一个路径上从哪来的信息，最后反过来打印。
然而悲剧就此来了，因为存的坐标信息是（x,y)，python的tuple是immutable的，存[x,y]的list初始化成M*N又有问题。然后想着建个class用coordinates表示x,y，然后一直有bug，到结束了都没有run出来，面完多看了眼就发现初始化coordinate的class应该初始化M*N个而不是直接复制（那样都是引用，导致最后改一个都改了）。因为这种问题跪了真是不甘心

import java.util.LinkedList;

public class Solution {

 public String findPath(int[][] board) {
  int m = board.length;
  int n = board[0].length;
  LinkedList<int[]> queue = new LinkedList<int[]>();
  queue.add(new int[] { 0, 0 });
  boolean find = false;
  while (!queue.isEmpty()) {
   int count = queue.size();
   while (count-- > 0) {
    int[] point = queue.poll();
    int x = point[0];
    int y = point[1];
    if (x == m - 1 && y == n - 1) {
     find = true;
     break;
    }
    if (x > 0 && board[x - 1][y] == 0) {
     board[x - 1][y] = 2;// up
     queue.add(new int[] { x - 1, y });
    }
    if (x < m - 1 && board[x + 1][y] == 0) {
     board[x + 1][y] = 3;// down
     queue.add(new int[] { x + 1, y });
    }
    if (y > 0 && board[x][y - 1] == 0) {
     board[x][y - 1] = 4;// left
     queue.add(new int[] { x, y - 1 });
    }
    if (y < n - 1 && board[x][y + 1] == 0) {
     board[x][y + 1] = 5;
     queue.add(new int[] { x, y + 1 });
    }
   }
   if (find) break;
  }
  if (!find) return "No Path";
  String res = "";
  int x = m - 1;
  int y = n - 1;
  while (x != 0 || y != 0) {
   int dir = board[x][y];
   if (dir == 2) {
    res = "up ->" + res;
    x++;
   } else if (dir == 3) {
    res = "down ->" + res;
    x--;
   } else if (dir == 4) {
    res = "left ->" + res;
    y++;
   } else if (dir == 5) {
    res = "right ->" + res;
    y--;
   }
  }
  return res;
 }

 public static void main(String args[]) {
  Solution s = new Solution();
  int[][] board = {{0, 1, 1, 1, 0}, {0, 1, 0, 0, 0}, {0, 1, 0, 1, 0}, {0, 0, 0, 1, 0}};
  System.out.println(s.findPath(board));
 }
}


import java.util.*;
public class MatrixCC{
    
    private int[][] id;
    private int count;
    private boolean[][] marked;
    
    public MatrixCC(int[][] matrix){
        if(matrix == null || matrix.length == 0){
            throw new IllegalArgumentException();
        }
        int m = matrix.length, n = matrix[0].length;
        id = new int[m][n];
        count = 1;
        marked = new boolean[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(!marked[i][j] && matrix[i][j] == 0){
                    dfs(matrix, i, j);
                    count++;
                }
            }
        }
    }
    
    private void dfs(int[][] matrix, int i, int j){
        if(!isValid(matrix, i, j)){
            return;
        }
        if(marked[i][j]){
            return;
        }
        if(matrix[i][j] == 1){
            return;
        }
        marked[i][j] = true;
        id[i][j] = count;
        //dfs
        dfs(matrix, i + 1, j);
        dfs(matrix, i - 1, j);
        dfs(matrix, i, j + 1);
        dfs(matrix, i, j - 1);
    }
    
    private boolean isValid(int[][] matrix, int i, int j){
        return i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length;
    }
    
    public boolean isConnected(int x, int y, int x1, int y1){
        if(!isValid(id, x, y) || !isValid(id, x1, y1)){
            throw new IllegalArgumentException();
        }
        return id[x][y] == id[x1][y1];
    }
    
    
    
    
    public static void main(String[] args){
        int[][] matrix = {{0, 1, 1, 1, 0, 0, 0},
                          {0, 1, 0, 0, 0, 0, 0},
                          {0, 1, 0, 0, 0, 0, 0}};
        MatrixCC ins = new MatrixCC(matrix);
        System.out.println(ins.isConnected(0, 4, 2, 2));
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 7; j++){
                System.out.print(ins.id[i][j]);
            }
            System.out.println();
        }
        
    }
}

// BFS
import java.util.*;

public class Maze {

  public static int[][] arr = new int[][] {
            {0,0,0,0,0,0,0,0,0},
            {5,5,5,0,0,0,0,0,0},
            {0,0,0,5,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,9},
    };

  private static class Point {
        int x;
        int y;
        Point parent;

        public Point(int x, int y, Point parent) {
            this.x = x;
            this.y = y;
            this.parent = parent;
        }

        public Point getParent() {
            return this.parent;
        }

        public String toString() {
            return "x = " + x + " y = " + y;
        }
  }

  public static Queue<Point> q = new LinkedList<Point>();

    public static Point getPathBFS(int x, int y) {

        q.add(new Point(x,y, null));

        while(!q.isEmpty()) {
            Point p = q.remove();

            if (arr[p.x][p.y] == 9) {
                System.out.println("Exit is reached!");
                return p;
            }

            if(isFree(p.x+1,p.y)) {
                arr[p.x][p.y] = -1;
                Point nextP = new Point(p.x+1,p.y, p);
                q.add(nextP);
            }

            if(isFree(p.x-1,p.y)) {
                arr[p.x][p.y] = -1;
                Point nextP = new Point(p.x+1,p.y, p);
                q.add(nextP);
            }

            if(isFree(p.x,p.y+1)) {
                arr[p.x][p.y] = -1;
                Point nextP = new Point(p.x,p.y+1, p);
                q.add(nextP);
            }

             if(isFree(p.x,p.y-1)) {
                arr[p.x][p.y] = -1;
                Point nextP = new Point(p.x,p.y-1, p);
                q.add(nextP);
            }

        }
        return null;
    }


    public static boolean isFree(int x, int y) {
        if((x >= 0 && x < arr.length) && (y >= 0 && y < arr[x].length) && (arr[x][y] == 0 || arr[x][y] == 9)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        Point p = getPathBFS(0,0);

         for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }

        while(p.getParent() != null) {
            System.out.println(p);
            p = p.getParent();
        }

    }

}