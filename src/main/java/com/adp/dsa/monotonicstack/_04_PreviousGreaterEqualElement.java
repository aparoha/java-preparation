package com.adp.dsa.monotonicstack;

import java.util.Stack;

// Strictly decreasing - every element of the stack is strictly smaller than the previous element - [9, 8, 5, 4, 1]

public class _04_PreviousGreaterEqualElement {

    public static int[] previousGreaterOrEqualElement(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[nums.length];

        // Initialize result array with -1, which represents no greater element
        for (int i = 0; i < nums.length; i++) {
            result[i] = -1;
        }

        // Iterate through the array from left to right (0th index onwards)
        for (int i = 0; i < nums.length; i++) {
            // Maintain the stack in non-increasing order
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                result[i] = nums[stack.peek()];
            }
            stack.push(i);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 3, 2, 5};
        int[] result = previousGreaterOrEqualElement(nums);

        // Print the result array
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}

