package com.adp.neetcode150;

/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the MinStack class:

MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.
You must implement a solution with O(1) time complexity for each function.



Example 1:

Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2


Constraints:

-231 <= val <= 231 - 1
Methods pop, top and getMin operations will always be called on non-empty stacks.
At most 3 * 104 calls will be made to push, pop, top, and getMin.
 */

import java.util.Stack;

/*
Explanation:
Stack Initialization: We use a Stack<StackEntry> where the first element is the value, and the second element is the minimum value up to that point.

Push: When pushing a value, we calculate the current minimum by comparing the value with the minimum value in the stack (the second element of the record).
We push a new record object containing the value and the new minimum.

Pop: We simply pop the stack.

Top: We return the value (the first element of the record object).

GetMin: We return the minimum value (the second element of the record object).
 */

public class MinimumStack {

    private record StackEntry(int value, int minValueUpToThisPoint) {}

    private final Stack<StackEntry> minStack;

    public MinimumStack() {
        minStack = new Stack<>();
    }

    public int[] finalPrices(int[] prices) {
        int pricesLength = prices.length;
        int[] result = new int[pricesLength];
        for(int i = 0; i < pricesLength; i++) {
            int discount = 0;
            for(int j = i + 1; j < pricesLength; j++) {
                if(prices[j] <= prices[i]) {
                    discount = prices[j];
                    break;
                }
            }
            result[i] = prices[i] - discount;
        }
        return result;
    }

    public void push(int val) {
        if (minStack.isEmpty()) {
            minStack.push(new StackEntry(val, val));
        } else {
            int min = Math.min(val, minStack.peek().minValueUpToThisPoint());
            minStack.push(new StackEntry(val, min));
        }
    }

    public void pop() {
        minStack.pop();
    }

    public int top() {
        return minStack.peek().value();
    }

    public int getMin() {
        return minStack.peek().minValueUpToThisPoint();
    }
}
