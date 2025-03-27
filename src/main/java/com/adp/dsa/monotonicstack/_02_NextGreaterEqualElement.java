package com.adp.dsa.monotonicstack;

import java.util.Arrays;
import java.util.Stack;

// Monotonic strictly decreasing stack
// every element of the stack is strictly smaller than the previous element
public class _02_NextGreaterEqualElement {
    public static int[] findNextGreaterEqual(int[] arr) {

        // Initialize an empty stack
        Stack<Integer> stack = new Stack<>();
        int[] nextGreater = new int[arr.length];

        // Initialize nextGreater array with -1 (indicating no next greater element)
        Arrays.fill(nextGreater, -1);

        // Iterate through the array
        for (int i = 0; i < arr.length; i++) {
            // While stack is not empty and the element at the stack's top is smaller or equal than the current element
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                // Pop the index from the stack
                int stackTop = stack.pop();
                // The next greater element for stackTop is the current element at index i
                nextGreater[stackTop] = arr[i];
            }
            // Push the current index onto the stack
            stack.push(i);
        }

        return nextGreater;
    }

    // Main function to test the program
    public static void main(String[] args) {
        int[] arr = {4, 5, 5, 2, 2, 10, 8}; // Example array
        int[] result = findNextGreaterEqual(arr);

        System.out.println(Arrays.toString(result));
    }
}
