package com.adp.dsa.backtracking;

/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]

Constraints:

1 <= n <= 8
 */

import java.util.ArrayList;
import java.util.List;

public class _03_GenerateParentheses {

    //Approach-1 (Simple Recursion) - brute force
    //T.C : O(2n* (2^(2n)) -> Removing constant -> O(n * (2^n)) (length of result is 2*n and for each position there are 2 possibilities so for 2n positions there are 2^2n possibilities. For isValid check, we are checking whole string of length n, so 2n*2^2n)
    //S.C : O(2*n) -> Removing constant -> O(n) -> recursion stack space - Max depth of recursion tree

    /*
                              backtrack("", 2)
                             /               \
              backtrack("(", 2)         backtrack(")", 2)
               /         \                     \
   backtrack("((", 2)   backtrack("()", 2)     backtrack("())", 2)
        |              /          \
 backtrack("(((", 2)  backtrack("(()", 2) backtrack("()(", 2)


     */

    public static List<String> generateParenthesisApproach1(int n) {
        List<String> result = new ArrayList<>();
        backtrackApproach1(new StringBuilder(), n, result);
        return result;
    }

    private static void backtrackApproach1(StringBuilder current, int n, List<String> result) {

        if (current.length() == 2 * n) {
            if (isValid(current.toString()))
                result.add(current.toString());
            return;
        }

        current.append('(');
        backtrackApproach1(current, n, result);
        current.deleteCharAt(current.length() - 1);

        current.append(')');
        backtrackApproach1(current, n, result);
        current.deleteCharAt(current.length() - 1);
    }

    private static boolean isValid(String current) {
        int counter = 0;
        for (char c : current.toCharArray()) {
            if (c == '(') {
                counter++;
            } else if (c == ')') {
                counter--;
            }
            if (counter < 0) {
                return false;
            }
        }
        return counter == 0;
    }

    /*
     Approach-2 (Smart Recursion)
        1. Only add open parenthesis if open < n
        2. Only add a closing parenthesis if closed < open
        3. valid IIF open == closed == n (or current.length() == 2 * n)

     */

    public static List<String> generateParenthesisApproach2(int n) {
        List<String> result = new ArrayList<>();
        backtrackApproach2(new StringBuilder(), 0, 0, n, result);
        return result;
    }

    private static void backtrackApproach2(StringBuilder current, int open, int close, int n, List<String> result) {

        if (current.length() == 2 * n) {
            result.add(current.toString());
            return;
        }

        if (open < n) {
            current.append('(');
            backtrackApproach2(current,open + 1, close, n, result);
            current.deleteCharAt(current.length() - 1);
        }

        if (close < open) {
            current.append(')');
            backtrackApproach2(current, open, close + 1, n, result);
            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void main(String[] args) {
        List<String> result1 = generateParenthesisApproach1(2);
        System.out.println(result1);

        List<String> result2 = generateParenthesisApproach2(2);
        System.out.println(result2);
    }
}
