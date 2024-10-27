public class CountSquareSubmatricesWithAllOnes {
    /*
     * class Solution {
    public int countSquares(int[][] matrix) {
        int rowSize  = matrix.length;
        int colSize = matrix[0].length;

        for(int i = 1; i < rowSize; i++)
        {
            for(int j = 1; j < colSize; j++)
            {
                if(matrix[i][j] == 1)
                    matrix[i][j] += Math.min(matrix[i - 1][j], Math.min(matrix[i - 1][j - 1], matrix[i][j - 1]));
            }
        }

        int total = 0;
        for(int i = 0; i < rowSize; i++)
        {
            for(int j = 0; j < colSize; j++)
            {
                total += matrix[i][j];
            }
        }

        return total;
    }
}
Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.

 

Example 1:

Input: matrix =
[
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]
Output: 15
Explanation: 
There are 10 squares of side 1.
There are 4 squares of side 2.
There is  1 square of side 3.
Total number of squares = 10 + 4 + 1 = 15.
Example 2:

Input: matrix = 
[
  [1,0,1],
  [1,1,0],
  [1,1,0]
]
Output: 7
Explanation: 
There are 6 squares of side 1.  
There is 1 square of side 2. 
Total number of squares = 6 + 1 = 7.
 

Constraints:

1 <= arr.length <= 300
1 <= arr[0].length <= 300
0 <= arr[i][j] <= 1
     */
    public int countSquares(int[][] matrix) {
        // Get dimensions of the matrix
        int n = matrix.length;    // number of rows
        int m = matrix[0].length; // number of columns
        
        // Create a DP table with same dimensions as matrix
        int[][] dp = new int[n][m];
        
        // Variable to store total count of squares
        int ans = 0;
        
        // Initialize first column of DP table
        for (int i = 0; i < n; i++) {
            dp[i][0] = matrix[i][0];
            ans += dp[i][0];
        }
        
        // Initialize first row of DP table
        for (int j = 1; j < m; j++) {
            dp[0][j] = matrix[0][j];
            ans += dp[0][j];
        }
        
        // Fill the DP table for remaining cells
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                if(matrix[i][j] == 1) {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1]);
                }
                ans += dp[i][j];
            }
        }
        
        return ans;
    }
}
