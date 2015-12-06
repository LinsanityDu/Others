/*A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?


Note: m and n will be at most 100.*/

public class Solution {
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        int state[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            state[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            state[0][i] = 1;
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                state[i][j] = state[i - 1][j] + state[i][j - 1];
            }
        }
        return state[m - 1][n - 1];
    }
}

// Space Optimization
public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (m == 1 || n == 1) {
            return 1;
        }

        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
}

// 求最短路问题
广搜，入门题。

　　题意：

　　给你一个5*5的迷宫，0代表通路，1代表墙，找到从迷宫左上角到达右下角的最短路径，并输出路径。

　　思路：

　　这道题是一道比较简单的广搜题目，为什么是广搜？因为题意是要找最短路径，这类题基本上就是用广搜。但是与其他直接输出最短路径的步数的不同，这道题要输出的是最短路径，是要输出这个路径，所以就要考虑状态了，每一个状态都应该存储到达这个状态的路径。其他就没什么好说的了，总体上是一道较为简单的广搜入门题。

　　代码：

复制代码
 1 #include <iostream>
 2 #include <stdio.h>
 3 #include <string.h>
 4 #include <queue>
 5 using namespace std;
 6 
 7 bool isw[5][5];
 8 int a[5][5];
 9 int dx[4] = {0,1,0,-1};
10 int dy[4] = {1,0,-1,0};
11 
12 struct Node{
13     int x;
14     int y;
15     int s;
16     short l[30];
17 };
18 
19 bool judge(int x,int y)
20 {
21     if(x<0 || x>=5 || y<0 || y>=5)
22         return true;
23     if(isw[x][y])
24         return true;
25     if(a[x][y]==1)
26         return true;
27     return false;
28 }
29 
30 Node bfs()
31 {
32     queue <Node> q;
33     Node cur,next;
34     cur.x = 0;
35     cur.y = 0;
36     cur.s = 0;
37     isw[cur.x][cur.y] = true;
38     q.push(cur);
39     while(!q.empty()){
40         cur = q.front();
41         q.pop();
42         if(cur.x==4 && cur.y==4)
43             return cur;
44         int i,nx,ny;
45         for(i=0;i<4;i++){
46             nx = cur.x + dx[i];
47             ny = cur.y + dy[i];
48             if(judge(nx,ny))
49                 continue;
50             //可以走
51             next = cur;
52             next.x = nx;
53             next.y = ny;
54             next.s = cur.s + 1;
55             next.l[cur.s] = i; 
56             q.push(next); 
57         } 
58     }
59     return cur;
60 }
61 
62 
63 int main()
64 {
65      int i,j;
66     for(i=0;i<5;i++){   //读入迷宫
67         for(j=0;j<5;j++){
68             scanf("%d",&a[i][j]);
69         }
70     }
71     memset(isw,0,sizeof(isw));
72     Node ans = bfs();
73     int x,y;
74     x = 0,y = 0;
75     for(i=0;i<=ans.s;i++){
76          printf("(%d, %d)\n",x,y);
77          x+=dx[ans.l[i]];
78          y+=dy[ans.l[i]];
79     }        
80     return 0;
81 }