public class mOSTsTONESrEMOVEDwITHsAMErOWoRcOLUMN {
    /*
     * On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.

A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.

Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.

 

Example 1:

Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Explanation: One way to remove 5 stones is as follows:
1. Remove stone [2,2] because it shares the same row as [2,1].
2. Remove stone [2,1] because it shares the same column as [0,1].
3. Remove stone [1,2] because it shares the same row as [1,0].
4. Remove stone [1,0] because it shares the same column as [0,0].
5. Remove stone [0,1] because it shares the same row as [0,0].
Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
Example 2:

Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3
Explanation: One way to make 3 moves is as follows:
1. Remove stone [2,2] because it shares the same row as [2,0].
2. Remove stone [2,0] because it shares the same column as [0,0].
3. Remove stone [0,2] because it shares the same row as [0,0].
Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
Example 3:

Input: stones = [[0,0]]
Output: 0
Explanation: [0,0] is the only stone on the plane, so you cannot remove it.
 

Constraints:

1 <= stones.length <= 1000
0 <= xi, yi <= 104
No two stones are at the same coordinate point.
Intuition
Imagine as if you are playing a game on a really big chessboard. You have scattered the stones across this board where each is occupying its own square now we can remove a stone if and only if there is a other stone in the same row or same column. Our job is to Remove as many stones as we can. At first, this might look very straightforward just keep removing the stones which share rows or columns until you can not any longer But what about the order in which we remove stones, it matters. Make a wrong choice in early stages and there is a chance that we leave stones stranded, unable to be removed.

We are dealing with up to 1000 stones here which are spread across a board that is 10,000 squares on each side. That's a lot of possibilities to consider. How do we ensure we're making the optimal choices?

I wanted to try all possible removal sequences! I thought, We could start with any stone, if possible we can remove it and after that we can try to remove the rest recursively. By searching all paths, we'd surely find the solution which is optimal. But With up to 1000 stones the number of removal sequences which are possible is very large. We're talking about a factorial growth in possibilities. Even for a modest number of stones this approach would take very long.

Try to visualize the stones and their relationships, lines connecting stones in the same rows and columns, forming a network across the board. This visualization is important tell me does it reminds you of graphs??

What if, instead of thinking about the individual stones, we considered these connections I mean after all the ability to remove a stone depends on its connections to other stones.

With this new perspective I hope you can see that our chessboard has transformed into a graph where Each stone became a node and the shared rows and columns became edges that are connecting these nodes. Now our problem looks a lot like finding connected components in a graph. Think about it in a connected group of stones, we can remove all but one of them. The last one has to stay because it no longer shares a row or column with any other stone all its "friends" have been removed. Instead of focusing on the sequence of removing them, we can instead focus on finding out these connected groups. The maximum number of removable stones would be the total number of stones minus the number of these groups (since we have to leave one stone per group).

With the help of this graph thing, my next thought was to use depth-first search (DFS). We could start at any stone, use DFS to find all connected stones mark them for removal, and repeat until we've covered the entire board. It would correctly identify the connected components and give us the right answer. But think about it how would we efficiently find all stones in the same row or column,
We could maintain lists of stones for each row and column, but that would require a lot of space and time to set up. Or we could scan through all stones for each DFS step, but the thing is that would be slow for a large number of stones.You can use DSU it is better at grouping elements and finding out if two elements belong to the same group. But you might question how could we apply DSU to this problem Well try to shift your perspective once again. Instead of thinking about stones as the primary elements what if we considered rows and columns as the elements to be grouped? Every stone connects a row with a column, by its very position. If we treat rows and columns as the elements in our DSU, then placing a stone is same as performing a union (merging) of a row group with a column group.

With this new approach in mind, try to think how do we represent rows and columns in a way that doesn't confuse them I mean We can't use the same numbering system for both as that would muddle our groups. Its simple: offset the column values by a large number. By adding 10,001 (just beyond the maximum row value) to each column number, we create two separate ranges where one is for rows and one is for columns. This will then allow our DSU structure to treat them as distinct elements while still maintaining their relationships.
Also we needed to keep careful track of the number of unique components. Each new row or column encountered would start as its own component. These components would merge as we process the stones. The final number of components would be improtant in determining how many stones we could remove.

This tracking part solves our problem We start with zero components. and Each time we encounter a new row or column, we increment our count also Each time we merge two components (by placing a stone), we decrement the count. In the end, the count gives us the number of connected components in our graph.

What if all stones are in the same row In this case, we'd have two components the row and the column and we could remove all but one stone. What if no stones share any rows or columns Then each stone would be its own component, and we couldn't remove any. Our DSU approach handles these cases automatically. Whether we have one large group or many small ones, the component count will accurately reflect the situation.

Mathematical Intuition for the DSU Approach:

Let's consider the problem as a graph, where each stone represents a node, and two nodes are connected if they share the same row or column. Mathematically, we can define the stone positions as a set S={(x 
1
​
 ,y 
1
​
 ),(x 
2
​
 ,y 
2
​
 ),…,(x 
n
​
 ,y 
n
​
 )}.

To analyze the problem, imagine two functions: f 
r
​
 (x,y)=x and f 
c
​
 (x,y)=y+k, where k is a large constant (in our case, 10001). These functions map the 2D coordinates of stones into a 1D space, separating rows and columns. Applying these functions to the set S, we get two new sets: R={f 
r
​
 (x,y)∣(x,y)∈S} and C={f 
c
​
 (x,y)∣(x,y)∈S}, representing all unique rows and columns.

Now, the union of these sets U=R∪C captures all unique rows and columns as if they were part of a larger "super set." The DSU structure operates on this set U, merging elements as we process each stone. The key insight here is that the number of connected components in this graph is given by ∣U∣−(∣R∪C∣−∣S∣), where ∣R∪C∣ represents the total number of unique rows and columns.

This brings us to a critical realization: the number of stones we can remove is ∣S∣−(∣R∣+∣C∣−(∣R∪C∣−∣S∣))=2∣S∣−∣R∪C∣. In simpler terms, ∣S∣−∣U∣+1 gives the number of stones that can be removed, because ∣U∣ represents the number of connected components, and in each component, we can remove all but one stone. This formulation elegantly shows how we've transformed a 2D problem into a 1D set operation, allowing us to efficiently count the number of removable stones.

This final approach, using DSU with rows and columns as elements, is superior to our earlier attempts in several ways: We process each stone exactly once, performing constant-time DSU operations for each. This scales well even for the maximum input size of 1000 stones.

We've eliminated the need to explicitly track connections between stones. The row-column unions implicitly capture all necessary relationships.

This approach handles all possible configurations of stones, from highly connected to completely disconnected, without any special cases.

Approach
Let's break it down in detail:

Data Structure Initialization:

We create an array setRepresentatives of size 20003. Why this specific size?
Rows are represented by indices 1 to 10001 (adding 1 to avoid using index 0)
Columns are represented by indices 10002 to 20002 (adding 10002 to differentiate from rows)
This allows us to handle the maximum possible coordinate value of 10000 for both rows and columns
We initialize a connectedComponentCount to 0. This will keep track of the number of unique components in our DSU structure.
Stone Processing:
For each stone, we perform the following steps:

Convert the row coordinate to a unique identifier: rowElement = stonePosition[0] + 1
Adding 1 ensures we don't use index 0, which we reserve to indicate an uninitialized state
Convert the column coordinate to a unique identifier: columnElement = stonePosition[1] + 10002
Adding 10002 shifts all column identifiers above the range used for rows, preventing any overlap
Call mergeComponents(rowElement, columnElement, setRepresentatives)
This step connects the row and column of the current stone
The findRepresentative Method:
This method is the core of our DSU structure. It does three crucial things:
a) Initializes new elements:

If setRepresentatives[element] == 0, it means we've encountered this element for the first time
We set it as its own representative: setRepresentatives[element] = element
We increment connectedComponentCount, as we've found a new unique component
b) Finds the representative of a set:
If an element is its own representative (setRepresentatives[element] == element), we return it
Otherwise, we recursively find the representative of its parent
c) Implements path compression:
After finding the true representative, we update setRepresentatives[element] to point directly to it
This flattens the tree structure, optimizing future lookups
The mergeComponents Method:
This method connects two elements (in our case, a row and a column). Here's what it does:

Find the representatives of both elements using findRepresentative
If the representatives are different (i.e., the elements are in different sets):
We merge the sets by making one representative point to the other: setRepresentatives[repB] = repA
We decrement connectedComponentCount, as two components have become one
Result Calculation:

The total number of stones that can be removed is the difference between the total number of stones and the number of connected components
This works because in each connected component, we can remove all stones except one
By representing rows and columns as separate elements in our DSU structure, we automatically connect all stones that share a row or column. When we merge a row and column, we're effectively saying "all stones in this row are connected to all stones in this column."

This approach solves the problem without need for explicit graph construction or DFS traversal, making it both time and space efficient.

Complexity
Time complexity: O(n∗α(n))
where n is the number of stones and α is the inverse Ackermann function.

We iterate through each stone once, performing two find operations and potentially one union operation for each stone.
The find and union operations have an amortized time complexity of O(α(n)), which is effectively constant for all practical values of n.
Therefore, the overall time complexity is nearly linear in the number of stones.
Space complexity: O(m)
where m is the maximum possible coordinate value (10000 in this case).

We use an array of size 20003 to represent all possible row and column values.
The space used is constant relative to the number of stones, but depends on the range of coordinate values.
     */
    public int numOfIslands = 0;
    public int removeStones(int[][] stones) {
        int[] parent = new int[20003];
        for(int[] stone:stones) {
            unionSets(stone[0]+1, stone[1] + 10002, parent);
        }
        return stones.length - numOfIslands;
    }
    public void unionSets(int a, int b, int[] parent) {
        int parA = findParent(a, parent), parB = findParent(b, parent);
        if(parA != parB) {
            parent[parB] = parA;
            numOfIslands--;
        }
        return;
    }
    public int findParent(int node, int[] parent) {
        if(parent[node] == 0) {
            parent[node] = node;
            numOfIslands++;
        }
        if(parent[node] == node) {
            return node;
        }
        int par = findParent(parent[node], parent);
        parent[node] = par;
        return par;
    }
}
