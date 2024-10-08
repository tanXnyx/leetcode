import java.util.Arrays;
/*
 * There is a strange printer with the following two special properties:

The printer can only print a sequence of the same character each time.
At each turn, the printer can print new characters starting from and ending at any place and will cover the original existing characters.
Given a string s, return the minimum number of turns the printer needed to print it.

 

Example 1:

Input: s = "aaabbb"
Output: 2
Explanation: Print "aaa" first and then print "bbb".
Example 2:

Input: s = "aba"
Output: 2
Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
 

Constraints:

1 <= s.length <= 100
s consists of lowercase English letters.
Intuition
The problem is about minimizing the number of times a printer needs to print characters from a string, where the printer can print a contiguous block of the same character. The key insight is that if the same character appears multiple times in the string, we can leverage this to reduce the number of print operations.

For example, if we have the string "aaabbb", we can first print 'a' three times, and then print 'b' three times, resulting in two print operations instead of printing each character individually.

Approach
Dynamic Programming: We will use a 2D array dp where dp[i][j] represents the minimum number of print operations required to print the substring from index i to j.

Recursive Function: The function Util(i, j) computes the minimum print operations for the substring s[i:j].

If i > j, it returns 0 (base case).
If dp[i][j] is already computed (not -1), it returns the stored value.
The first print operation is always counted, and then we check for any repeated characters in the substring to potentially reduce the number of operations.
Character Matching: For each character in the substring starting from i, if it matches the first character (s[i]), we can consider splitting the problem into two parts: printing from i to k-1 and from k+1 to j.

Memoization: Store the results of subproblems in dp to avoid redundant calculations.

Time and Space Complexity
Time Complexity: The time complexity is O(n 
3
 ), where n is the length of the string. This is due to the nested loops:
The outer function calls are O(n 
2
 ) (for each pair of indices).
The inner loop iterates through the substring, leading to an overall complexity of O(n 
3
 ).
Space Complexity: The space complexity is O(n 
2
 ) for the dp array that stores the results of subproblems.
Step-by-Step Explanation
For Input "aaabbb"

Let's trace the execution of the code with the input "aaabbb":

Initialization:

n = 6 (length of "aaabbb").
dp is initialized to a 6x6 matrix filled with -1.
First Call: strangePrinter("aaabbb") calls Util(0, 5).

Util(0, 5):

firstLetter = 'a'.
Initial answer = 1 + Util(1, 5) (printing 'a' once and considering the rest).
Util(1, 5):

firstLetter = 'a'.
Initial answer = 1 + Util(2, 5).
Util(2, 5):

firstLetter = 'a'.
Initial answer = 1 + Util(3, 5).
Util(3, 5):

firstLetter = 'b'.
Initial answer = 1 + Util(4, 5).
Util(4, 5):

firstLetter = 'b'.
Initial answer = 1 + Util(5, 5).
Util(5, 5):

firstLetter = 'b'.
Initial answer = 1 + Util(6, 5).
Util(6, 5) returns 0 (base case).
Backtrack to Util(4, 5):

answer = 1 + 1 = 2.
Check for repeated characters:
k = 5: s matches firstLetter ('b').
Calculate betterAnswer = Util(4, 4) + Util(6, 5) = 1 + 0 = 1.
Update answer = min(2, 1) = 1.
Backtrack to Util(3, 5):

answer = 1 + 1 = 2.
Check for repeated characters:
k = 4: s matches firstLetter ('b').
Calculate betterAnswer = Util(3, 3) + Util(5, 5) = 1 + 1 = 2.
Update `answer = min(2, 2) = 2.
Backtrack to Util(2, 5):

answer = 1 + 2 = 3.
Check for repeated characters:
k = 3: s matches firstLetter ('a').
Calculate betterAnswer = Util(2, 2) + Util(4, 5) = 1 + 1 = 2.
Update `answer = min(3, 2) = 2.
Backtrack to Util(1, 5):

answer = 1 + 2 = 3.
Check for repeated characters:
k = 2: s matches firstLetter ('a').
Calculate betterAnswer = Util(1, 1) + Util(3, 5) = 1 + 1 = 2.
Update `answer = min(3, 2) = 2.
Backtrack to Util(0, 5):

answer = 1 + 2 = 3.
Check for repeated characters:
k = 1: s matches firstLetter ('a').
Calculate betterAnswer = Util(0, 0) + Util(2, 5) = 1 + 2 = 3.
Update `answer = min(3, 3) = 3.
Final Result: The final value of dp is 3, which means a minimum of 3 print operations are needed to print "aaabbb".


 */
public class StrangePrinter664 {
     public int strangePrinter(String s) {
        int n = s.length();
        char[] sChar = s.toCharArray();
        int[][] dp = new int[n][n];
        for(int[] in : dp) Arrays.fill(in, -1);
        return Util(0, n - 1, sChar, dp);
    }
    public int Util(int i, int j, char[] sChar, int[][] dp) {
        if (i > j) {
            return 0;
        }

        if(dp[i][j] != -1) return dp[i][j];
        
        int firstLetter = sChar[i];
        // in case, current character is not repeated in the rest of the string
        int answer = 1 + Util(i + 1, j, sChar, dp);
        for (int k = i + 1; k <= j; k++) {
            // if repeated then update the answer
            if (sChar[k] == firstLetter) {   
                // splitting from i -> k - 1(remove the last character)
                // and from k + 1 -> j             
                int betterAnswer = Util(i, k - 1, sChar, dp) + Util(k + 1, j, sChar, dp);
                answer = Math.min(answer, betterAnswer);
            }
        }
        return dp[i][j] = answer;
    }
    /*
     * class Solution {
    public int strangePrinter(String s) {
        char[] sc = s.toCharArray();
        final int n = removeDuplicates(sc);
        if (n <= 1)  return n;
        return dfs(0, n - 1, sc, new int[n][n]);
    }
    
    
    private int dfs(int left, int right, char[] sc, int[][] memo) {
        if (left >= right)  return (left == right) ? 1 : 0;
        if (memo[left][right] != 0)  return memo[left][right];
        int letter = sc[left];
        int answer = 1 + dfs(left + 1, right, sc, memo);
        for (int k = left + 1; k <= right; k++) 
            if (sc[k] == letter) 
                answer = Math.min(answer, dfs(left+1, k - 1, sc, memo) + dfs(k, right, sc, memo));
        return memo[left][right] = answer;
    }
    
    
    private int removeDuplicates(char[] sc) {
        int scOutIdx = 0;
        char prev = 0;
        for (char c : sc) {
            if (c != prev) {
                sc[scOutIdx++] = c;
                prev = c;
            }
        }
        return scOutIdx;
    }
}
     */
}
