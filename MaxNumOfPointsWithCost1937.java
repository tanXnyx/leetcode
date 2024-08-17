public class MaxNumOfPointsWithCost1937 {
    /*
     * You are given an m x n integer matrix points (0-indexed). Starting with 0 points, you want to maximize the number of points you can get from the matrix.

To gain points, you must pick one cell in each row. Picking the cell at coordinates (r, c) will add points[r][c] to your score.

However, you will lose points if you pick a cell too far from the cell that you picked in the previous row. For every two adjacent rows r and r + 1 (where 0 <= r < m - 1), picking cells at coordinates (r, c1) and (r + 1, c2) will subtract abs(c1 - c2) from your score.

Return the maximum number of points you can achieve.

abs(x) is defined as:

x for x >= 0.
-x for x < 0.
 

Example 1:


Input: points = [[1,2,3],[1,5,1],[3,1,1]]
Output: 9
Explanation:
The blue cells denote the optimal cells to pick, which have coordinates (0, 2), (1, 1), and (2, 0).
You add 3 + 5 + 3 = 11 to your score.
However, you must subtract abs(2 - 1) + abs(1 - 0) = 2 from your score.
Your final score is 11 - 2 = 9.
Example 2:


Input: points = [[1,5],[2,3],[4,2]]
Output: 11
Explanation:
The blue cells denote the optimal cells to pick, which have coordinates (0, 1), (1, 1), and (2, 0).
You add 5 + 3 + 4 = 12 to your score.
However, you must subtract abs(1 - 1) + abs(1 - 0) = 1 from your score.
Your final score is 12 - 1 = 11.
 

Constraints:

m == points.length
n == points[r].length
1 <= m, n <= 105
1 <= m * n <= 105
0 <= points[r][c] <= 105
     */
    public long maxPoints(int[][] points) {
        int row = points.length;
        int col = points[0].length;
        long[] dp = new long[col];
        
        for (int i = 0; i < col; i++) {
            dp[i] = points[0][i];
        }
        
        for (int r = 1; r < row; r++) {
            long[] leftMax = new long[col];
            long[] rightMax = new long[col];
            long[] newDp = new long[col];
            
            leftMax[0] = dp[0];
            for (int i = 1; i < col; i++) {
                leftMax[i] = Math.max(leftMax[i-1], dp[i] + i);
            }
            
            rightMax[col-1] = dp[col-1] - (col-1);
            for (int i = col-2; i >= 0; i--) {
                rightMax[i] = Math.max(rightMax[i+1], dp[i] - i);
            }
            
            for (int i = 0; i < col; i++) {
                newDp[i] = Math.max(leftMax[i] - i, rightMax[i] + i) + points[r][i];
            }
            
            dp = newDp;
        }
        
        long result = Long.MIN_VALUE;
        for (long value : dp) {
            result = Math.max(result, value);
        }
        
        return result;
        /*class Solution {
    public long maxPoints(int[][] points) {
		long[] frame = new long[points[0].length];

		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j <frame.length; j ++) frame[j] += points[i][j];

			for (int j = 1; j < frame.length; j ++) frame[j] = Math.max(frame[j], frame[j - 1] - 1);

			for (int j=frame.length-2;j>=0;j--) frame[j] = Math.max(frame[j], frame[j + 1] - 1);

			for(long k:frame) System.out.println(k);
		}


		long ans = 0;
		for (int i = 0; i < frame.length; i ++) {
			ans = Math.max(ans, frame[i]);
		}
		return ans;
	}
}
         * 
         */
    }
}
