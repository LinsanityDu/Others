/*
  第一轮的问题是：给一个int矩阵，0代表empty，1代表obstacle，find whether there's a path between 2 nodes.  
  我这道题上来直接就用了dfs，而且写的磕磕绊绊，大概半小时才完全写好，导致整轮只做了一道题。后来想想这道题用bfs 或者 bidirectional bfs会更好。
  follow up是当图变得极大时，dfs会有什么问题。还有当我们有一个计算机集群的时候，可以如何加速算法。面试之后两天都没有消息，我就知道不妙。
  一周之后通知被加了一轮电面。
  
  0 1 0 0 0 0 1 0
  0 1 0 0 1 0 1 0
  0 0 1 0 0 1 0 0*/

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