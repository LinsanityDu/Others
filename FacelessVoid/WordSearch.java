/*Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ["ABCE"],
  ["SFCS"],
  ["ADEE"]
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.*/

// Nine Chapter
public class Solution {
    // recursion
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0)
            return false;
        if(word.length() == 0)
            return true;
        
        for(int i = 0; i< board.length; i++){
            for(int j=0; j< board[0].length; j++){
                if(board[i][j] == word.charAt(0)){
                    
                    boolean rst = find(board, i, j, word, 0);
                    if(rst)
                        return true;
                }
            }
        }
        return false;
    }
    
    private boolean find(char[][] board, int i, int j, String word, int start){
        if(start == word.length())
            return true;
        
        if (i < 0 || i>= board.length || 
     j < 0 || j >= board[0].length || board[i][j] != word.charAt(start)){
            return false;
	 }
        
        board[i][j] = '#'; // should remember to mark it
        boolean rst = find(board, i-1, j, word, start+1) 
		|| find(board, i, j-1, word, start+1) 
		|| find(board, i+1, j, word, start+1) 
		|| find(board, i, j+1, word, start+1));
        board[i][j] = word.charAt(start);
        return rst;
    }
}


// My Code
public class Solution {
    public boolean exist(char[][] board, String word) {  
        if(word==null || word.length()==0)  
            return true;  
        if(board==null || board.length==0 || board[0].length==0)  
            return false;  
        boolean[][] used = new boolean[board.length][board[0].length];  
        for(int i=0;i<board.length;i++)  
        {  
            for(int j=0;j<board[0].length;j++)  
            {  
                if(search(board,word,0,i,j,used))  
                    return true;  
            }  
        }  
        return false;  
    }  
    private boolean search(char[][] board, String word, int index, int i, int j, boolean[][] used)  
    {  
        if(index == word.length())  
            return true;  
        if(i<0 || j<0 || i>=board.length || j>=board[0].length || used[i][j] || board[i][j]!=word.charAt(index))  
            return false;  
        used[i][j] = true;  
        boolean res = search(board,word,index+1,i-1,j,used)   
                    || search(board,word,index+1,i+1,j,used)  
                    || search(board,word,index+1,i,j-1,used)   
                    || search(board,word,index+1,i,j+1,used);  
        used[i][j] = false;  
        return res;  
    }

}


// My way
public class Solution {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.length() == 0) {
            return false;
        }
        int row = board.length;
        int col = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (dfs(board, word, new boolean[row][col], i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean dfs(char[][] board, String word, boolean[][] visit, int row, int col, int index) {
        if(row < 0 || col < 0 || row >= board.length || col >= board[0].length || 
            visit[row][col] || board[row][col]!=word.charAt(index)) {
                return false;
            }
        if (index == word.length() - 1 && board[row][col] == word.charAt(index)) {
            return true;
        }
        visit[row][col] = true;
        boolean res = dfs(board,word,visit,row + 1, col, index + 1) ||
                      dfs(board,word,visit,row - 1, col, index + 1) ||
                      dfs(board,word,visit,row, col + 1, index + 1) ||
                      dfs(board,word,visit,row, col - 1, index + 1);
        visit[row][col] = false;
        return res;
    }
}


