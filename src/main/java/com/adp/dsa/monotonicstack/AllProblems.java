package com.adp.dsa.monotonicstack;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

/*
next greater, greater equal, smaller, smaller equal
while[pop, assign current element to popped index] -> push

previous greater, greater equal, smaller, smaller equal
while[pop] -> [peak, assign peak element to current index] -> push
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

    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextGreater(new int[]{14, 17, 13, 14, 14, 19, 12, 14, 18}))); //[17, 19, 14, 19, 19, -1, 14, 18, -1]
        System.out.println(Arrays.toString(nextGreaterEqual(new int[]{14, 17, 13, 14, 14, 19, 12, 14, 18}))); //[17, 19, 14, 14, 19, -1, 14, 18, -1]
        System.out.println(Arrays.toString(nextSmaller(new int[]{14, 17, 13, 14, 14, 19, 12, 14, 18}))); //[13, 13, 12, 12, 12, 12, -1, -1, -1]
        System.out.println(Arrays.toString(nextSmallerEqual(new int[]{14, 17, 13, 14, 14, 19, 12, 14, 18}))); //[13, 13, 12, 14, 12, 12, -1, -1, -1]
        System.out.println(Arrays.toString(previousGreater(new int[]{14, 17, 13, 14, 14, 19, 12, 14, 18}))); //[-1, -1, 17, 17, 17, -1, 19, 19, 19]
        System.out.println(Arrays.toString(previousGreaterEqual(new int[]{14, 17, 13, 14, 14, 19, 12, 14, 18}))); //[-1, -1, 17, 17, 14, -1, 19, 19, 19]
        System.out.println(Arrays.toString(previousSmaller(new int[]{14, 17, 13, 14, 14, 19, 12, 14, 18}))); //[-1, 14, -1, 13, 13, 14, -1, 12, 14]
        System.out.println(Arrays.toString(previousSmallerEqual(new int[]{14, 17, 13, 14, 14, 19, 12, 14, 18}))); //[-1, 14, -1, 13, 14, 14, -1, 12, 14]
    }
}