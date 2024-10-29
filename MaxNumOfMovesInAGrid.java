public class MaxNumOfMovesInAGrid {
    /*class Solution {
    private int solve(int i, int j, int n, int m, int[][] grid, int[][] dp) {
        if (i < 0 || j < 0 || i >= n || j >= m) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int ans = 0;
        if (i - 1 >= 0 && j + 1 < m && grid[i][j] < grid[i - 1][j + 1]) {
            ans = Math.max(ans, 1 + solve(i - 1, j + 1, n, m, grid, dp));
        }
        if (j + 1 < m && grid[i][j] < grid[i][j + 1]) {
            ans = Math.max(ans, 1 + solve(i, j + 1, n, m, grid, dp));
        }
        if (i + 1 < n && j + 1 < m && grid[i][j] < grid[i + 1][j + 1]) {
            ans = Math.max(ans, 1 + solve(i + 1, j + 1, n, m, grid, dp));
        }

        dp[i][j] = ans;
        return dp[i][j];
    }

    public int maxMoves(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        for (int[] r : dp) {
            Arrays.fill(r, -1);
        }

        int maxMoves = 0;
        for (int i = 0; i < n; i++) {
            
                maxMoves = Math.max(maxMoves, solve(i, 0, n, m, grid, dp));
            
        }

        return maxMoves;
    }
}
You are given a 0-indexed m x n matrix grid consisting of positive integers.

You can start at any cell in the first column of the matrix, and traverse the grid in the following way:

From a cell (row, col), you can move to any of the cells: (row - 1, col + 1), (row, col + 1) and (row + 1, col + 1) such that the value of the cell you move to, should be strictly bigger than the value of the current cell.
Return the maximum number of moves that you can perform.

 

Example 1:


Input: grid = [[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]
Output: 3
Explanation: We can start at the cell (0, 0) and make the following moves:
- (0, 0) -> (0, 1).
- (0, 1) -> (1, 2).
- (1, 2) -> (2, 3).
It can be shown that it is the maximum number of moves that can be made.
Example 2:


Input: grid = [[3,2,4],[2,1,9],[1,1,7]]
Output: 0
Explanation: Starting from any cell in the first column we cannot perform any moves.
 

Constraints:

m == grid.length
n == grid[i].length
2 <= m, n <= 1000
4 <= m * n <= 105
1 <= grid[i][j] <= 106
Intuition
The problem is about finding the longest path in a grid where you can move only to the right and must always increase in value. The allowed moves make it possible to explore paths either directly right, up-right, or down-right. Given that starting from each cell in the first column might give different paths, we aim to maximize moves by taking advantage of all possible increasing paths.

A key observation is that the problem resembles dynamic programming, where we want to store and build upon solutions for each cell to avoid redundant calculations, especially since the grid size can be large.

Approach
Dynamic Programming (DP) Setup: We'll use a dp table, where dp[row][col] holds the maximum number of moves that can be made starting from grid[row][col].
Reverse DP Calculation: To avoid recalculations, we’ll populate the DP table from the last column back to the first. This way, each cell's result can be built using already-calculated values in the next column.
Transitions: For each cell in the grid, check if moving to the right, up-right, or down-right is possible and leads to a strictly greater value. Update the dp table based on these moves.
Result Extraction: The answer will be the maximum value in the first column of the dp table, representing the longest increasing path from any starting cell in the first column.
Complexity
Time complexity: (O(m \times n)), where (m) and (n) are the grid dimensions. We examine each cell and compute possible moves in constant time.
Space complexity: (O(m \times n)) for the DP table.
Step-by-Step Detailed Explanation
Initialize DP Table: We start by creating a DP table dp to store the maximum moves from each cell.
Backwards Traversal: By processing each column from right to left, each cell’s value is updated based on possible moves to strictly greater values in the next column.
Calculate Maximum: Finally, by iterating over the first column, we can determine the maximum number of moves possible by starting from any cell in this column.
*/
    public int maxMoves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        int maxMoves = 0;

        for (int col = n - 2; col >= 0; col--) {
            for (int row = 0; row < m; row++) {
                if (row > 0 && grid[row][col] < grid[row - 1][col + 1]) {
                    dp[row][col] = Math.max(dp[row][col], dp[row - 1][col + 1] + 1);
                }
                if (grid[row][col] < grid[row][col + 1]) {
                    dp[row][col] = Math.max(dp[row][col], dp[row][col + 1] + 1);
                }
                if (row < m - 1 && grid[row][col] < grid[row + 1][col + 1]) {
                    dp[row][col] = Math.max(dp[row][col], dp[row + 1][col + 1] + 1);
                }
            }
        }

        for (int row = 0; row < m; row++) {
            maxMoves = Math.max(maxMoves, dp[row][0]);
        }
        return maxMoves;
    }
}
