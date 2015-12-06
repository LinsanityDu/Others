/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.
*/


public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
                if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        	return false;
        }
        // Search row
        int start = 0;
        int end = matrix.length - 1;
        int mid = start + (end - start) / 2;
        while (start + 1 < end) {
        	mid = start + (end - start) / 2;
        	if (matrix[mid][0] == target) {
        		return true;
        	} else if (matrix[mid][0] > target) {
        		end = mid;
        	} else {
        		start = mid;
        	}
        }
        if (matrix[start][0] == target || matrix[end][0] == target) {
        	return true;
        }
        // Search column
        int row = 0;
        if (target < matrix[start][0]) {
        	if (start > 0) {
        	   row = start - 1;
        	} else {
        	    row = 0;
        	}
        } else if (target > matrix[start][0] && target < matrix[end][0]) {
        	row = start;
        } else {
        	row = end;
        }
        start = 0;
        end = matrix[row].length - 1;
        while (start + 1 < end) {
        	mid = start + (end - start) / 2;
        	if (matrix[row][mid] == target) {
        		return true;
        	} else if (matrix[row][mid] > target) {
        		end = mid;
        	} else {
        		start = mid;
        	}
        }
        if (matrix[row][start] == target || matrix[row][end] == target) {
        	return true;
        }
        return false;

    }
}



// Search once
// The same time complexity; but pay attention to the overflow
// Pay attention to the transfer from ID to position
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int start = 0;
        int row = matrix.length;
        int column = matrix[0].length;
        int end = row * column - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (matrix[mid / column][mid % column] > target) {
                end = mid;
            } else if (matrix[mid / column][mid % column] < target) {
                start = mid;
            } else {
                return true;
            }
        }
        if (matrix[start / column][start % column] == target || matrix[end / column][end % column] == target) {
            return true;
        }
        return false;
    }
}



// Nine Chapter Solution
// Binary Search Twice
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        
        int row = matrix.length;
        int column = matrix[0].length;
        
        // find the row index, the last number <= target 
        int start = 0, end = row - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (matrix[end][0] <= target) {
            row = end;
        } else if (matrix[start][0] <= target) {
            row = start;
        } else {
            return false;
        }
        
        // find the column index, the number equal to target
        start = 0;
        end = column - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (matrix[row][mid] == target) {
                return true;
            } else if (matrix[row][mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (matrix[row][start] == target) {
            return true;
        } else if (matrix[row][end] == target) {
            return true;
        }
        return false;
    }
}

// Binary Search Once
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        
        int row = matrix.length, column = matrix[0].length;
        int start = 0, end = row * column - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int number = matrix[mid / column][mid % column];
            if (number == target) {
                return true;
            } else if (number < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (matrix[start / column][start % column] == target) {
            return true;
        } else if (matrix[end / column][end % column] == target) {
            return true;
        }
        
        return false;
    }
}
