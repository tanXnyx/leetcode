public class StoneGamell1140 {
    /*
     * Alice and Bob continue their games with piles of stones.  There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].  The objective of the game is to end with the most stones. 

Alice and Bob take turns, with Alice starting first.  Initially, M = 1.

On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).

The game continues until all the stones have been taken.

Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.

 

Example 1:

Input: piles = [2,7,9,4,4]
Output: 10
Explanation:  If Alice takes one pile at the beginning, Bob takes two piles, then Alice takes 2 piles again. Alice can get 2 + 4 + 4 = 10 piles in total. If Alice takes two piles at the beginning, then Bob can take all three piles left. In this case, Alice get 2 + 7 = 9 piles in total. So we return 10 since it's larger. 
Example 2:

Input: piles = [1,2,3,4,5,100]
Output: 104
 

Constraints:

1 <= piles.length <= 100
1 <= piles[i] <= 104
Intuition ✒️
The game requires Alice and Bob to maximize the number of stones they can collect, with the additional twist that the number of piles they can take from is limited by the value of M, which can grow based on their choices. This dynamic nature suggests that the problem can be approached using dynamic programming, where we can recursively determine the optimal choices for Alice and Bob.
Approach
1.Suffix Sum Calculation:
We first calculate a suffix sum array, where each entry suffix_sum[i] represents the total stones from pile i to the last pile. This helps quickly calculate the total stones available from any given starting point.
2.Dynamic Programming (DP) Table:
We define dp[i][m] as the maximum stones Alice can get starting from index i with the current M = m.
If Alice can take all the remaining piles (i + 2 * m >= n), then she takes them all.
Otherwise, Alice will consider taking between 1 to 2 * mpiles and will choose the option that maximizes her stones while accounting for Bob’s best possible play after her move.
3.Optimal Strategy:
For each position i and each possible value of M, we compute the best outcome by trying all possible moves (x piles) Alice can take and then using the DP table to determine how Bob would respond optimally. The strategy ensures that Alice's choice maximizes her stones by considering both her immediate gain and the impact of Bob's subsequent move.
Time complexity:
The time complexity is O(n 
3
 ) where n is the number of piles. This arises because:
We have two nested loops: one for each starting index i and one for each possible value of M.
Inside these loops, we have another loop that iterates up to 2 * M, leading to a cubic complexity.
Space complexity:
The space complexity is O(n 
2
 ) for storing the DP table dp[i][m] and the suffix sum array. Both require space proportional to the number of piles squared.

     */
    public int stoneGameII(int[] piles) {
        int n = piles.length; 
        
        int[][] dp = new int[n][n + 1];
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        
        for (int i = n - 1; i >= 0; i--) {
            for (int m = 1; m <= n; m++) {
                if (i + 2 * m >= n) {
                    dp[i][m] = suffixSum[i];
                } else {
                    for (int x = 1; x <= 2 * m; x++) {
                        dp[i][m] = Math.max(dp[i][m], suffixSum[i] - dp[i + x][Math.max(m, x)]);
                    }
                }
            }
        }
        
        return dp[0][1];
    }
    /*
     * class Solution {

    public int stoneGameII(int[] piles) {
        // Store the suffix sum of all array elements.
        int[] suffixSum = Arrays.copyOf(piles, piles.length);

        for (int i = suffixSum.length - 2; i >= 0; i--) {
            suffixSum[i] += suffixSum[i + 1];
        }
        return maxStones(suffixSum, 1, 0, new int[piles.length][piles.length]);
    }

    private int maxStones(
        int[] suffixSum,
        int maxTillNow,
        int currIndex,
        int[][] memo
    ) {
        // If currIndex + 2*maxTillNow lies outside the array, pick all remaining stones.
        if (currIndex + 2 * maxTillNow >= suffixSum.length) {
            return suffixSum[currIndex];
        }
        if (memo[currIndex][maxTillNow] > 0) return memo[currIndex][maxTillNow];
        int res = Integer.MAX_VALUE;
        // Find the minimum value res for the next move possible.
        for (int i = 1; i <= 2 * maxTillNow; i++) {
            res = Math.min(
                res,
                maxStones(
                    suffixSum,
                    Math.max(i, maxTillNow),
                    currIndex + i,
                    memo
                )
            );
        }
        // Memoize the difference of suffixSum[p] and res. This denotes the maximum
        // stones that can be picked.
        memo[currIndex][maxTillNow] = suffixSum[currIndex] - res;
        return memo[currIndex][maxTillNow];
    }
}
     */
}
