public class DeleteNodeFromLinkListPresentInArray3217 {
    /**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 * You are given an array of integers nums and the head of a linked list. Return the head of the modified linked list after removing all nodes from the linked list that have a value that exists in nums.

 

Example 1:

Input: nums = [1,2,3], head = [1,2,3,4,5]

Output: [4,5]

Explanation:



Remove the nodes with values 1, 2, and 3.

Example 2:

Input: nums = [1], head = [1,2,1,2,1,2]

Output: [2,2,2]

Explanation:



Remove the nodes with value 1.

Example 3:

Input: nums = [5], head = [1,2,3,4]

Output: [1,2,3,4]

Explanation:



No node has value 5.

 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 105
All elements in nums are unique.
The number of nodes in the given list is in the range [1, 105].
1 <= Node.val <= 105
The input is generated such that there is at least one node in the linked list that has a value not present in nums.
Intuition
Imagine you're running a party, but only some guests are invited (based on a list of numbers). If a guest shows up and isn't on the list, you need to remove them. That's what we're doing here with linked lists. We want to keep only the nodes whose values match the numbers from the array.

Approach
First, we find the largest number in the list to know how big our frequency array needs to be.
We create a frequency array (like a guest list) where True means the number is invited, and False means they aren't.
Then we walk through the linked list. If a node’s value is on the guest list (i.e., the frequency array says True), we skip it. If not, we add it to our new, modified linked list.
Finally, we return the new linked list that only contains the uninvited guests (nodes with values not in the array).
Time Complexity
Time complexity:
We first loop through the array to find the max value and then build a frequency array based on that. After that, we loop through both the nums array and the linked list. So, overall it's O(n+m) where n is the length of nums and m is the length of the linked list.
Space Complexity
Space complexity:
We need extra space for the frequency array, which depends on the largest number in the input array. Therefore, the space complexity is O(k), where k is the largest number in nums.
 */
    public ListNode modifiedList(int[] nums, ListNode head) {
        int max = -1;
        for(int num : nums ){
            max = num > max ? num : max;
        }
        boolean[] freq = new boolean[max+1];

        for(int num : nums) freq[num] = true;

        ListNode temp = new ListNode();
        ListNode current = temp;

        while(head != null){
            if( head.val >= freq.length || freq[head.val] == false){
                current.next = head;
                current = current.next;
            }
            head = head.next;
        }

        current.next = null;
        return temp.next;
    }
}
