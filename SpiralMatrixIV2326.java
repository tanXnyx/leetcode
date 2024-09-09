import java.util.Arrays;
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 * You are given two integers m and n, which represent the dimensions of a matrix.

You are also given the head of a linked list of integers.

Generate an m x n matrix that contains the integers in the linked list presented in spiral order (clockwise), starting from the top-left of the matrix. If there are remaining empty spaces, fill them with -1.

Return the generated matrix.

 

Example 1:


Input: m = 3, n = 5, head = [3,0,2,6,8,1,7,9,4,2,5,5,0]
Output: [[3,0,2,6,8],[5,0,-1,-1,1],[5,2,4,9,7]]
Explanation: The diagram above shows how the values are printed in the matrix.
Note that the remaining spaces in the matrix are filled with -1.
Example 2:


Input: m = 1, n = 4, head = [0,1,2]
Output: [[0,1,2,-1]]
Explanation: The diagram above shows how the values are printed from left to right in the matrix.
The last space in the matrix is set to -1.
 

Constraints:

1 <= m, n <= 105
1 <= m * n <= 105
The number of nodes in the list is in the range [1, m * n].
0 <= Node.val <= 1000
Intuition
To solve the problem of filling an m x n matrix with values from a linked list in a spiral order, we need to simulate the process of filling the matrix in a spiral manner starting from the top-left corner and moving clockwise. The remaining cells that are not filled with values from the linked list should be filled with -1.

Approach
Matrix Initialization:

Create a 2D matrix of size m x n and initialize all elements to -1, which will be used to fill any remaining spaces after placing values from the linked list.
Spiral Filling Logic:

Define boundaries for the spiral traversal: topRow, bottomRow, leftColumn, and rightColumn.
Use a while loop to traverse and fill the matrix in a spiral order as long as there are still values left in the linked list.
Within the loop:
Fill Top Row: Traverse from leftColumn to rightColumn in the current topRow and place the linked list values.
Fill Right Column: Traverse from topRow to bottomRow in the current rightColumn and place the linked list values.
Fill Bottom Row: Traverse from rightColumn to leftColumn in the current bottomRow and place the linked list values.
Fill Left Column: Traverse from bottomRow to topRow in the current leftColumn and place the linked list values.
Update the boundaries after each traversal: increment topRow and leftColumn, and decrement bottomRow and rightColumn.
Edge Handling:

Continue this process until either the matrix is completely filled or there are no more values left in the linked list.
Complexity
Time complexity:

The time complexity is (O(m x n)). This is because every cell in the matrix is processed exactly once, either by filling it with a value from the linked list or leaving it as -1 if the linked list runs out of values.
Space complexity:

The space complexity is also (O(m x n)). This includes the space required for the output matrix, which is of size m x n.
Step By STep Explanation
Example
Let's say we have a linked list with the following values: [1, 2, 3, 4, 5, 6, 7, 8, 9], and we want to fill a 3 x 3 matrix in a spiral order.

Initial Setup
Matrix Size: 3 rows x 3 columns
Linked List Values: [1, 2, 3, 4, 5, 6, 7, 8, 9]
Step-by-Step Spiral Filling
Initialize the Matrix:

-1 -1 -1
-1 -1 -1
-1 -1 -1
Boundaries: topRow = 0, bottomRow = 2, leftColumn = 0, rightColumn = 2
Fill Top Row:

Traverse from leftColumn to rightColumn in the topRow.
1 2 3
-1 -1 -1
-1 -1 -1
Update Boundaries: topRow = 1
Fill Right Column:

Traverse from topRow to bottomRow in the rightColumn.
1 2 3
-1 -1 4
-1 -1 5
Update Boundaries: rightColumn = 1
Fill Bottom Row:

Traverse from rightColumn to leftColumn in the bottomRow.
1 2 3
-1 -1 4
7 8 9
Update Boundaries: bottomRow = 1
Fill Left Column:

Traverse from bottomRow to topRow in the leftColumn.
1 2 3
6 -1 4
7 8 9
Update Boundaries: leftColumn = 1
Next Iteration:

Top Row: Only one position (matrix[1][1]) left to fill.
1 2 3
6 5 4
7 8 9
Boundaries: topRow = 2, bottomRow = 1, leftColumn = 2, rightColumn = 1
Final Matrix
After the above steps, the matrix filled in spiral order looks like this:

1 2 3
6 5 4
7 8 9
Explanation
Initialize the Matrix: Start by creating a matrix filled with a default value (e.g., -1).

Define Boundaries: Keep track of the boundaries for the spiral filling:

topRow: the current top boundary
bottomRow: the current bottom boundary
leftColumn: the current left boundary
rightColumn: the current right boundary
Fill the Matrix in Spiral Order:

Top Row: Fill from left to right along the top boundary.
Right Column: Fill from top to bottom along the right boundary.
Bottom Row: Fill from right to left along the bottom boundary.
Left Column: Fill from bottom to top along the left boundary.
Update Boundaries: After filling each part of the matrix, adjust the boundaries to move inward.

Repeat: Continue filling in the spiral order until all values from the linked list are used or the matrix is completely filled.

This approach ensures that all values are placed in the matrix in a spiral order, and any remaining cells are filled with the default value (-1).
 */
public class SpiralMatrixIV2326 {
    public int[][] spiralMatrix(int rows, int columns, ListNode head) {
        int[][] matrix = new int[rows][];
        for (int i = 0; i < rows; i++) {
            matrix[i] = new int [columns];
            Arrays.fill(matrix[i], -1);
        }

        int topRow = 0, bottomRow = rows - 1, leftColumn = 0, rightColumn = columns - 1;
        while (head != null) {
        
            for (int col = leftColumn; col <= rightColumn && head != null; col++) {
                matrix[topRow][col] = head.val;
                head = head.next;
            }
            topRow++;

        
            for (int row = topRow; row <= bottomRow && head != null; row++) {
                matrix[row][rightColumn] = head.val;
                head = head.next;
            }
            rightColumn--;

 
            for (int col = rightColumn; col >= leftColumn && head != null; col--) {
                matrix[bottomRow][col] = head.val;
                head = head.next;
            }
            bottomRow--;

       
            for (int row = bottomRow; row >= topRow && head != null; row--) {
                matrix[row][leftColumn] = head.val;
                head = head.next;
            }
            leftColumn++;
        }

        return matrix;
    }
}
