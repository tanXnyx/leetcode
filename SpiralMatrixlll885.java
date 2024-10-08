import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpiralMatrixlll885 {
    /*
     * You start at the cell (rStart, cStart) of an rows x cols grid facing east. The northwest corner is at the first row and column in the grid, and the southeast corner is at the last row and column.

You will walk in a clockwise spiral shape to visit every position in this grid. Whenever you move outside the grid's boundary, we continue our walk outside the grid (but may return to the grid boundary later.). Eventually, we reach all rows * cols spaces of the grid.

Return an array of coordinates representing the positions of the grid in the order you visited them.

 

Example 1:


Input: rows = 1, cols = 4, rStart = 0, cStart = 0
Output: [[0,0],[0,1],[0,2],[0,3]]
Example 2:


Input: rows = 5, cols = 6, rStart = 1, cStart = 4
Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]
 

Constraints:

1 <= rows, cols <= 100
0 <= rStart < rows
0 <= cStart < cols
     */
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int numSteps = 1;
        int totalCells = rows * cols;
        List<int[]> result = new ArrayList<>();
        int r = rStart, c = cStart;
        int d = 0;

        while (result.size() < totalCells) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < numSteps; j++) {
                    if (0 <= r && r < rows && 0 <= c && c < cols) {
                        result.add(new int[]{r, c});
                    }
                    if (result.size() == totalCells) {
                        return convertListToArray(result);
                    }
                    r += directions[d][0];
                    c += directions[d][1];
                }
                d = (d + 1) % 4;
            }
            numSteps++;
        }

        return convertListToArray(result);
    }

    private int[][] convertListToArray(List<int[]> list) {
        int[][] array = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static void main(String[] args) {
        SpiralMatrixlll885 SpiralMatrixlll885 = new SpiralMatrixlll885();
        int[][] result = SpiralMatrixlll885.spiralMatrixIII(5, 6, 1, 4);
        for (int[] coords : result) {
            System.out.println(Arrays.toString(coords));
        }
    }
    /*
     * class Solution {
int minX, minY, maxX, maxY, index;

    int[][] result;

    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        
        int n = cols * rows;
        result = new int[n][];

        minX = cStart;
        maxX = cStart + 1;
        minY = rStart;
        maxY = rStart;
        index = 0;
        result[index++] = new int[]{rStart, cStart};

        while (true) {
            if (minY >= 0){
                right(Math.max(0, minX + 1), Math.min(cols - 1, maxX));
            } 
            maxY++;
            if (index >= n) break;

            if (maxX < cols) down(Math.max(0, minY + 1), Math.min(rows - 1, maxY));
            minX--;
            if (index >= n) break;

            if (maxY < rows) left(Math.min(cols - 1, maxX - 1), Math.max(0, minX));
            minY--;
            if (index >= n) break;

            if (minX >= 0) up(Math.min(rows - 1, maxY - 1), Math.max(0, minY));
            maxX++;
            if (index >= n) break;
        }
        return result;
    }

    public void right(int start, int end) {
        for (int i = start; i <= end; i++) 
            result[index++] = new int[]{minY, i};
    }

    public void left(int start, int end) {
        for (int i = start; i >= end; i--) 
           result[index++] = new int[]{maxY, i};
    }

    public void down(int start, int end) {
        for (int i = start; i <= end; i++) 
            result[index++] = new int[]{i, maxX};
    }

    public void up(int start, int end) {
        for (int i = start; i >= end; i--) 
           result[index++] = new int[]{i, minX};
    }
}
     */

}
