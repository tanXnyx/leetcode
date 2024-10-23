import javax.swing.tree.TreeNode;
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 * Given the root of a binary tree, replace the value of each node in the tree with the sum of all its cousins' values.

Two nodes of a binary tree are cousins if they have the same depth with different parents.

Return the root of the modified tree.

Note that the depth of a node is the number of edges in the path from the root node to it.

 

Example 1:


Input: root = [5,4,9,1,10,null,7]
Output: [0,0,0,7,7,null,11]
Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
- Node with value 5 does not have any cousins so its sum is 0.
- Node with value 4 does not have any cousins so its sum is 0.
- Node with value 9 does not have any cousins so its sum is 0.
- Node with value 1 has a cousin with value 7 so its sum is 7.
- Node with value 10 has a cousin with value 7 so its sum is 7.
- Node with value 7 has cousins with values 1 and 10 so its sum is 11.
Example 2:


Input: root = [3,1,2]
Output: [0,0,0]
Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
- Node with value 3 does not have any cousins so its sum is 0.
- Node with value 1 does not have any cousins so its sum is 0.
- Node with value 2 does not have any cousins so its sum is 0.
 

Constraints:

The number of nodes in the tree is in the range [1, 105].
1 <= Node.val <= 104
class Solution {
    static final TreeNode[] nodes = new TreeNode[50_000];
    
    public TreeNode replaceValueInTree(TreeNode root) {
        nodes[0] = root;
        int sumL = root.val;
        int sumR = 0;
        final int startL = 0;
        final int startR = nodes.length - 1;
        int lastL = startL + 1;
        int lastR = startR;
        TreeNode node = null;
        
        while (lastL != 0) {
            sumR = 0;
            while (lastL > 0) {
                node = nodes[--lastL];
                node.val = sumL - node.val;
                if (node.left != null) {
                    if (node.right != null) {    // If two children.
                        sumR += node.left.val = node.right.val = 
                                    node.left.val + node.right.val;
                        nodes[lastR--] = node.left;
                        nodes[lastR--] = node.right;
                    } else {                     // If left child only.
                        sumR += node.left.val;
                        nodes[lastR--] = node.left;
                    }
                } else if (node.right != null) { // If right child only.
                    sumR += node.right.val;
                    nodes[lastR--] = node.right;
                }
            }
            if (lastR == startR)  break;    // If no more levels.
            // Process the list of nodes on the right side of 
            // nodes[] while building the list of the next level's 
            // nodes on the left side of nodes[].
            sumL = 0;
            while (lastR < startR) {
                node = nodes[++lastR];
                node.val = sumR - node.val;
                if (node.left != null) {
                    if (node.right != null) {    // If two children.
                        sumL += node.left.val = node.right.val = 
                                    node.left.val + node.right.val;
                        nodes[lastL++] = node.left;
                        nodes[lastL++] = node.right;
                    } else {                     // If left child only.
                        sumL += node.left.val;
                        nodes[lastL++] = node.left;
                    }
                } else if (node.right != null) { // If right child only.
                    sumL += node.right.val;
                    nodes[lastL++] = node.right;
                }
            }
        }
        return root;
    }
}
 */
public class CousinsInBinaryTreeII2641 {
      public TreeNode replaceValueInTree(TreeNode root) {
        dfs(new TreeNode[] {root});
        root.val = 0;
        return root;
    }

    private void dfs(TreeNode[] arr) {
        if (arr.length == 0) return;

        int sum = 0;
        for (TreeNode node : arr) {
            if (node == null) continue;
            if (node.left != null) sum += node.left.val;
            if (node.right != null) sum += node.right.val;
        }

        TreeNode[] childArr = new TreeNode[arr.length * 2];
        int index = 0;

        for (TreeNode node : arr) {
            int curSum = 0;
            if (node.left != null) curSum += node.left.val;
            if (node.right != null) curSum += node.right.val;

            if (node.left != null) {
                node.left.val = sum - curSum;
                childArr[index++] = node.left;
            }
            if (node.right != null) {
                node.right.val = sum - curSum;
                childArr[index++] = node.right;
            }
        }

        dfs(java.util.Arrays.copyOf(childArr, index));
    }
}
