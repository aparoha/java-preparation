package com.adp.dsa.monotonicstack;

import java.util.*;

/*
next greater, greater equal, smaller, smaller equal
while[pop, assign current element to popped index] -> push

previous greater, greater equal, smaller, smaller equal
while[pop] -> [peak, assign peak element to current index] -> push

https://medium.com/@adparticles/monotonic-stack-patterns-ae8124dfe269
 */
public class AllProblems {

    //monotonic stack (weakly decreasing)
    public static int[] nextGreater(int[] arr) {
        if (arr == null || arr.length == 0)
            return new int[0];
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int current = arr[i];
            while (!stack.isEmpty() && arr[stack.peek()] < current) {
                int popped = stack.pop();
                ans[popped] = current;
            }
            stack.push(i);
        }
        return ans;
    }

    //monotonic stack (strictly decreasing)
    public static int[] nextGreaterEqual(int[] arr) {
        if (arr == null || arr.length == 0)
            return new int[0];
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int current = arr[i];
            while (!stack.isEmpty() && arr[stack.peek()] <= current) {
                int popped = stack.pop();
                ans[popped] = current;
            }
            stack.push(i);
        }
        return ans;
    }

    // monotonic stack (weakly increasing)
    public static int[] nextSmaller(int[] arr) {
        if (arr == null || arr.length == 0)
            return new int[0];
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int current = arr[i];
            while (!stack.isEmpty() && arr[stack.peek()] > current) {
                int popped = stack.pop();
                ans[popped] = current;
            }
            stack.push(i);
        }
        return ans;
    }

    //monotonic stack (strictly increasing)
    public static int[] nextSmallerEqual(int[] arr) {
        if (arr == null || arr.length == 0)
            return new int[0];
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int current = arr[i];
            while (!stack.isEmpty() && arr[stack.peek()] >= current) {
                int popped = stack.pop();
                ans[popped] = current;
            }
            stack.push(i);
        }
        return ans;
    }

    // monotonic stack (strictly decreasing)
    public static int[] previousGreater(int[] arr) {
        if (arr == null || arr.length == 0)
            return new int[0];
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int current = arr[i];
            while (!stack.isEmpty() && arr[stack.peek()] <= current) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                int peek = stack.peek();
                ans[i] = arr[peek];
            }
            stack.push(i);
        }

        return ans;
    }

    // monotonic stack (weakly decreasing)
    public static int[] previousGreaterEqual(int[] arr) {
        if (arr == null || arr.length == 0)
            return new int[0];
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int current = arr[i];
            while (!stack.isEmpty() && arr[stack.peek()] < current) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                int peek = stack.peek();
                ans[i] = arr[peek];
            }
            stack.push(i);
        }

        return ans;
    }

    // monotonic stack (strictly increasing)
    public static int[] previousSmaller(int[] arr) {
        if (arr == null || arr.length == 0)
            return new int[0];
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int current = arr[i];
            while (!stack.isEmpty() && arr[stack.peek()] >= current) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                int peek = stack.peek();
                ans[i] = arr[peek];
            }
            stack.push(i);
        }

        return ans;
    }

    // monotonic stack (weakly increasing)
    public static int[] previousSmallerEqual(int[] arr) {
        if (arr == null || arr.length == 0)
            return new int[0];
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int current = arr[i];
            while (!stack.isEmpty() && arr[stack.peek()] > current) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                int peek = stack.peek();
                ans[i] = arr[peek];
            }
            stack.push(i);
        }

        return ans;
    }

    /*
    You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the
    array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one
    position. Return an answer array that contains the minimum value for each sliding window of size k.

    For example, if the input array is nums = [11, 13, -11, -13, 15, 13, 16, 17] and k = 3,
    then the desired output or answer array would be [-11, -13, -13, -13, 13, 13]:

    Window position                         Min

------------------------               -----

[11  13  -11] -13  15  13  16  17       -11

 11 [13  -11  -13] 15  13  16  17       -13

 11  13 [-11  -13  15] 13  16  17       -13

 11  13  -11 [-13  15  13] 16  17       -13

 11  13  -11  -13 [15  13  16] 17        13

 11  13  -11  -13  15 [13  16  17]       13
     */

    public static List<Integer> slidingWindowMin(int[] arr, int k) {
        // Initialize empty deque for O(1) addition/removal
        Deque<Integer> deq = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            int current = arr[i];
            while (!deq.isEmpty() && arr[deq.peekLast()] > current) {
                deq.pollLast();
            }
            deq.addLast(i);
            // Remove the index that is out of the window (left side)
            if (deq.peekFirst() == i - k) {
                deq.pollFirst();
            }
            // Add the minimum element (which is at the front of the deque) to the answer list
            if (i >= k - 1) {
                ans.add(arr[deq.peekFirst()]);
            }
        }

        return ans;
    }

    public static List<Integer> slidingWindowMax(int[] arr, int k) {
        // Initialize empty deque for O(1) addition/removal
        Deque<Integer> deq = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            int current = arr[i];
            while (!deq.isEmpty() && arr[deq.peekLast()] < current) {
                deq.pollLast();
            }
            deq.addLast(i);
            // Remove the index that is out of the window (left side)
            if (deq.peekFirst() == i - k) {
                deq.pollFirst();
            }
            // Add the minimum element (which is at the front of the deque) to the answer list
            if (i >= k - 1) {
                ans.add(arr[deq.peekFirst()]);
            }
        }

        return ans;
    }

    // Function to create and maintain a monotonic increasing stack
    public static Stack<Integer> createMonotonicIncreasingStack(int[] nums) {
        Stack<Integer> stack = new Stack<>();

        // Iterate through the array of numbers
        for (int num : nums) {
            // While stack is not empty and the current number is smaller than the top of the stack
            while (!stack.isEmpty() && stack.peek() > num) {
                stack.pop();  // Remove elements that break the increasing property
            }
            stack.push(num);  // Push the current number onto the stack
        }

        return stack;  // Return the stack that is now monotonic increasing
    }
    public static void main(String[] args) {
//        System.out.println(Arrays.toString(nextGreater(new int[]{14, 17, 13, 14, 14, 19, 12, 14, 18}))); //[17, 19, 14, 19, 19, -1, 14, 18, -1]
//        System.out.println(Arrays.toString(nextGreaterEqual(new int[]{14, 17, 13, 14, 14, 19, 12, 14, 18}))); //[17, 19, 14, 14, 19, -1, 14, 18, -1]
//        System.out.println(Arrays.toString(nextSmaller(new int[]{14, 17, 13, 14, 14, 19, 12, 14, 18}))); //[13, 13, 12, 12, 12, 12, -1, -1, -1]
//        System.out.println(Arrays.toString(nextSmallerEqual(new int[]{14, 17, 13, 14, 14, 19, 12, 14, 18}))); //[13, 13, 12, 14, 12, 12, -1, -1, -1]
//        System.out.println(Arrays.toString(previousGreater(new int[]{14, 17, 13, 14, 14, 19, 12, 14, 18}))); //[-1, -1, 17, 17, 17, -1, 19, 19, 19]
//        System.out.println(Arrays.toString(previousGreaterEqual(new int[]{14, 17, 13, 14, 14, 19, 12, 14, 18}))); //[-1, -1, 17, 17, 14, -1, 19, 19, 19]
//        System.out.println(Arrays.toString(previousSmaller(new int[]{14, 17, 13, 14, 14, 19, 12, 14, 18}))); //[-1, 14, -1, 13, 13, 14, -1, 12, 14]
//        System.out.println(Arrays.toString(previousSmallerEqual(new int[]{14, 17, 13, 14, 14, 19, 12, 14, 18}))); //[-1, 14, -1, 13, 14, 14, -1, 12, 14]
        System.out.println(slidingWindowMin(new int[] {11, 13, -11, -13, 15, 13, 16, 17}, 3));
        System.out.println(slidingWindowMax(new int[] {1,3,-1,-3,5,3,6,7}, 3));
    }
}