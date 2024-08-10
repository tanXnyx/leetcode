public class RegionsCutBySlashes959 {
    /*
     * An n x n grid is composed of 1 x 1 squares where each 1 x 1 square consists of a '/', '\', or blank space ' '. These characters divide the square into contiguous regions.

Given the grid grid represented as a string array, return the number of regions.

Note that backslash characters are escaped, so a '\' is represented as '\\'.

 

Example 1:


Input: grid = [" /","/ "]
Output: 2
Example 2:


Input: grid = [" /","  "]
Output: 1
Example 3:


Input: grid = ["/\\","\\/"]
Output: 5
Explanation: Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.
 

Constraints:

n == grid.length == grid[i].length
1 <= n <= 30
grid[i][j] is either '/', '\', or ' '.
     */
    int[] parent;
    int[] rank;
    int count;
    public int regionsBySlashes(String[] grid) {
        int rows = grid.length;
        int dots = rows+1;
        parent = new int[dots*dots];
        rank = new int[dots*dots];
        for (int i=0; i<parent.length; i++){
            parent[i] = i;
            rank[i] = 1;
        }
        
        for (int i=0; i<dots; i++){
            for (int j=0; j<dots; j++){
                if (i==0 || j==0 || i==rows || j==rows){
                    int cells = i * dots + j;
                    union(0, cells);
                }
            }
        }

        for (int i=0; i<rows; i++){
            char[] ch = grid[i].toCharArray();
            for (int j=0; j<ch.length; j++){
                if (ch[j] == '\\'){
                    int cell1 = i* dots+ j;
                    int cell2 = (i+1)*dots + (j+1);
                    union(cell1, cell2);
                } else if (ch[j] == '/'){
                    int cell1 = (i+1)*dots + j;
                    int cell2 = i*dots + (j+1);
                    union(cell1, cell2);
                }
            }
        }
        return count;
    }

    public void union(int a, int b){
        int parentA = find(a);
        int parentB = find(b);
        if (parentA == parentB){
            count++;
        } else {
            if (rank[parentA] > rank[parentB]){
                parent[parentB] = parentA;
            } else if (rank[parentA] < rank[parentB]){
                parent[parentA] = parentB;
            } else {
                parent[parentB] = parentA;
                rank[parentA]++;
            }
        }
    }

    public int find(int a){
        if(parent[a]==a)
            return a;
        int temp = find(parent[a]);
        parent[a] = temp;
        return temp;
    }
    /*
     * class Solution {
    int[] parent;
    int[] rank;
    int count;
    public int regionsBySlashes(String[] grid) {
        int rows = grid.length;
        int dots = rows+1;
        parent = new int[dots*dots];
        rank = new int[dots*dots];
        for (int i=0; i<parent.length; i++){
            parent[i] = i;
            rank[i] = 1;
        }
        
        for (int i=0; i<dots; i++){
            for (int j=0; j<dots; j++){
                if (i==0 || j==0 || i==rows || j==rows){
                    int cells = i * dots + j;
                    union(0, cells);
                }
            }
        }

        for (int i=0; i<rows; i++){
            char[] ch = grid[i].toCharArray();
            for (int j=0; j<ch.length; j++){
                if (ch[j] == '\\'){
                    int cell1 = i* dots+ j;
                    int cell2 = (i+1)*dots + (j+1);
                    union(cell1, cell2);
                } else if (ch[j] == '/'){
                    int cell1 = (i+1)*dots + j;
                    int cell2 = i*dots + (j+1);
                    union(cell1, cell2);
                }
            }
        }
        return count;
    }

    public void union(int a, int b){
        int parentA = find(a);
        int parentB = find(b);
        if (parentA == parentB){
            count++;
        } else {
            if (rank[parentA] > rank[parentB]){
                parent[parentB] = parentA;
            } else if (rank[parentA] < rank[parentB]){
                parent[parentA] = parentB;
            } else {
                parent[parentB] = parentA;
                rank[parentA]++;
            }
        }
    }

    public int find(int a){
        if(parent[a]==a)
            return a;
        int temp = find(parent[a]);
        parent[a] = temp;
        return temp;
    }

}
     */

}
