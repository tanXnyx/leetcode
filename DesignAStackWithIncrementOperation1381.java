public class DesignAStackWithIncrementOperation1381 {
    /*
     *Design a stack that supports increment operations on its elements.

Implement the CustomStack class:

CustomStack(int maxSize) Initializes the object with maxSize which is the maximum number of elements in the stack.
void push(int x) Adds x to the top of the stack if the stack has not reached the maxSize.
int pop() Pops and returns the top of the stack or -1 if the stack is empty.
void inc(int k, int val) Increments the bottom k elements of the stack by val. If there are less than k elements in the stack, increment all the elements in the stack.
 

Example 1:

Input
["CustomStack","push","push","pop","push","push","push","increment","increment","pop","pop","pop","pop"]
[[3],[1],[2],[],[2],[3],[4],[5,100],[2,100],[],[],[],[]]
Output
[null,null,null,2,null,null,null,null,null,103,202,201,-1]
Explanation
CustomStack stk = new CustomStack(3); // Stack is Empty []
stk.push(1);                          // stack becomes [1]
stk.push(2);                          // stack becomes [1, 2]
stk.pop();                            // return 2 --> Return top of the stack 2, stack becomes [1]
stk.push(2);                          // stack becomes [1, 2]
stk.push(3);                          // stack becomes [1, 2, 3]
stk.push(4);                          // stack still [1, 2, 3], Do not add another elements as size is 4
stk.increment(5, 100);                // stack becomes [101, 102, 103]
stk.increment(2, 100);                // stack becomes [201, 202, 103]
stk.pop();                            // return 103 --> Return top of the stack 103, stack becomes [201, 202]
stk.pop();                            // return 202 --> Return top of the stack 202, stack becomes [201]
stk.pop();                            // return 201 --> Return top of the stack 201, stack becomes []
stk.pop();                            // return -1 --> Stack is empty return -1.
 

Constraints:

1 <= maxSize, x, k <= 1000
0 <= val <= 100
At most 1000 calls will be made to each method of increment, push and pop each separately.
 Intuition
The problem requires implementing a custom stack with three main operations: push, pop, and increment. The stack should have a fixed size, meaning we can't add more than a certain number of elements, and the increment operation should add a specified value to the bottom k elements of the stack. A simple array can be used to simulate the stack behavior.

Approach
Stack representation: Use an integer array to simulate the stack with a fixed size.
Push operation: Add an element to the top of the stack if it hasn't exceeded the stack's size limit.
Pop operation: Remove the top element of the stack, or return -1 if the stack is empty.
Increment operation: Iterate through the first k elements and increment their value by val, but ensure k does not exceed the current stack size.
Complexity
Time complexity:

push: O(1), as we just add an element to the top of the stack.
pop: O(1), as we remove the element from the top of the stack.
increment: O(k) where k is the number of elements to increment (bounded by the minimum of k and the stack's size).
Space complexity:

O(n), where n is the maximum stack size, as we are storing elements in an array of fixed size.

     */
    int [] stack;
    int top = -1;

    public DesignAStackWithIncrementOperation1381(int maxSize) {
        this.stack = new int [maxSize];
    }
    
    public void push(int x) {
        if (top < this.stack.length - 1) {
            top ++;
            this.stack[top] = x;
        }
    }
    
    public int pop() {
        if (top != -1) {
            return this.stack[top --];
        }
        return -1;
    }
    
    public void increment(int k, int val) {
        for (int i = 0; i < Math.min(k, top + 1); i ++) {
            this.stack[i] += val;
        }
    }
}
/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */
