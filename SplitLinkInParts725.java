public class SplitLinkInParts725 {
    /*
     * Given the head of a singly linked list and an integer k, split the linked list into k consecutive linked list parts.

The length of each part should be as equal as possible: no two parts should have a size differing by more than one. This may lead to some parts being null.

The parts should be in the order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal to parts occurring later.

Return an array of the k parts.

 

Example 1:


Input: head = [1,2,3], k = 5
Output: [[1],[2],[3],[],[]]
Explanation:
The first element output[0] has output[0].val = 1, output[0].next = null.
The last element output[4] is null, but its string representation as a ListNode is [].
Example 2:


Input: head = [1,2,3,4,5,6,7,8,9,10], k = 3
Output: [[1,2,3,4],[5,6,7],[8,9,10]]
Explanation:
The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.
 

Constraints:

The number of nodes in the list is in the range [0, 1000].
0 <= Node.val <= 1000
1 <= k <= 50
     */
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] ans = new ListNode[k];

        // get total size of linked list
        int size = 0;
        ListNode current = head;
        while (current != null) {
            size++;
            current = current.next;
        }

        // minimum size for the k parts
        int splitSize = size / k;

        // Remaining nodes after splitting the k parts evenly.
        // These will be distributed to the first (size % k) nodes
        int numRemainingParts = size % k;

        current = head;
        ListNode prev = current;
        for (int i = 0; i < k; i++) {
            // create the i-th part
            ListNode newPart = current;
            // calculate size of i-th part
            int currentSize = splitSize;
            if (numRemainingParts > 0) {
                numRemainingParts--;
                currentSize++;
            }

            // traverse to end of new part
            int j = 0;
            while (j < currentSize) {
                prev = current;
                current = current.next;
                j++;
            }
            // cut off the rest of linked list
            if (prev != null) {
                prev.next = null;
            }

            ans[i] = newPart;
        }

        return ans;
    }
}
