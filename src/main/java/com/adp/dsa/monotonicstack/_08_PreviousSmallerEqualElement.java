package com.adp.dsa.monotonicstack;

import java.util.Arrays;
import java.util.Stack;

public class _08_PreviousSmallerEqualElement {
    public static int[] previousSmallerOrEqualElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Initialize result array with -1 (default for no smaller element)
        Arrays.fill(result, -1);

        // Iterate over the array from left to right
        for (int i = 0; i < n; i++) {
            // Pop elements from the stack until we find a smaller or equal element
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                stack.pop();
            }
            // If the stack is not empty, the top element is the previous smaller or equal element
            if (!stack.isEmpty()) {
                result[i] = nums[stack.peek()];
            }
            // Push current index to the stack
            stack.push(i);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 2, 10, 8};
        int[] result = previousSmallerOrEqualElement(nums);
        System.out.println(Arrays.toString(result));  // Output: [-1, 4, -1, 2, 2]
    }
}



