package com.adp.dsa.monotonicstack;

import java.util.Arrays;
import java.util.Stack;

/*
1. Create a stack and an array for results:
-> We create an empty stack to help us keep track of array indices.
-> We also create an array called nextGreater, where we’ll store the next greater element for each position in the input array. Initially, all values are set to -1
to indicate that there is no greater element found yet.

2. Iterate through the array:
-> We loop through the input array, arr, starting from the first element to the last.

3. Check for next greater element:
-> For each element in the array, we check if there is an element on top of the stack (i.e., at the index stored in the stack) that is smaller than the current
element in the array.
-> If it is smaller, that means the current element is the next greater element for the element at the stack’s top. We update the nextGreater array to store the
current element in the position of the popped index.

4. Pop indices from the stack:
-> For every element that is greater than the element at the stack's top, we pop the index off the stack (because we have found the next greater element for that
index).
-> We then update the nextGreater array for that popped index.

5. Push current index onto the stack:
-> After checking for the next greater element, we push the current index onto the stack. This index is now a potential candidate for which we will find the next
greater element in future iterations.

6.Return the result:
-> After we finish the loop, the nextGreater array contains the next greater element for each index in the input array, or -1 if no greater element exists.

For arr = [4, 5, 2, 10], the steps would look like this:

Start with nextGreater = [-1, -1, -1, -1] and an empty stack.

At index 0 (value 4), nothing is popped (stack is empty), so push index 0.

At index 1 (value 5), 4 (at index 0) is smaller, so pop index 0 and set nextGreater[0] = 5. Then, push index 1.

At index 2 (value 2), nothing is popped, so push index 2.

At index 3 (value 10), 2 (at index 2) and 5 (at index 1) are smaller, so pop both. Set nextGreater[2] = 10 and nextGreater[1] = 10. Then, push index 3.

Result: nextGreater = [5, 10, 10, -1].

Monotonic non-increasing stack [9, 9, 8, 5, 5, 4, 1]
every element of the stack is smaller than or equal to the previous element
 */

public class _01_NextGreaterElement {
    public static int[] findNextGreater(int[] arr) {

        // Initialize an empty stack
        Stack<Integer> stack = new Stack<>();
        int[] nextGreater = new int[arr.length];

        // Initialize nextGreater array with -1 (indicating no next greater element)
        Arrays.fill(nextGreater, -1);

        // Iterate through the array
        for (int i = 0; i < arr.length; i++) {
            // While stack is not empty and the element at the stack's top is smaller than the current element
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                // Pop the index from the stack
                int prevIndex = stack.pop();
                // The next greater element for stackTop is the current element at index i
                nextGreater[prevIndex] = arr[i];
            }
            // Push the current index onto the stack
            stack.push(i);
        }

        return nextGreater;
    }

    // Main function to test the program
    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 10, 8}; // Example array
        int[] result = findNextGreater(arr);

        System.out.println(Arrays.toString(result));

        // Print the result
//        for (int i = 0; i < result.length; i++) {
//            System.out.println("Next greater element for " + arr[i] + " is at index " + (result[i] == -1 ? "None" : result[i]));
//        }

        /*
        Next greater element for 4 is at index 1
        Next greater element for 5 is at index 3
        Next greater element for 2 is at index 3
        Next greater element for 10 is at index None
        Next greater element for 8 is at index None
         */
    }
}
