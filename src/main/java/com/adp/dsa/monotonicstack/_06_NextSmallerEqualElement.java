package com.adp.dsa.monotonicstack;

import java.util.Arrays;
import java.util.Stack;

public class _06_NextSmallerEqualElement {
    public static int[] nextSmallerOrEqualElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Initialize result array with -1 (default for no smaller element)
        Arrays.fill(result, -1);

        // Iterate over the array from left to right
        for (int i = 0; i < n; i++) {
            // Pop elements from the stack until we find a smaller or equal element
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                result[stack.pop()] = nums[i];
            }
            // Push current index to the stack
            stack.push(i);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 2, 10, 8};
        int[] result = nextSmallerOrEqualElement(nums);
        System.out.println(Arrays.toString(result));  // Output: [2, 2, -1, 8, -1]
    }
}



