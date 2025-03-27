package com.adp.neetcode150;

/*
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

-Open brackets must be closed by the same type of brackets.
-Open brackets must be closed in the correct order.
-Every close bracket has a corresponding open bracket of the same type.


Example 1:

Input: s = "()"
Output: true

Example 2:

Input: s = "()[]{}"
Output: true

Example 3:

Input: s = "(]"
Output: false

Example 4:

Input: s = "([])"
Output: true


Constraints:

1 <= s.length <= 104
s consists of parentheses only '()[]{}'.


Intuition
----------

Matching brackets requires ensuring that the different types of brackets are nested properly. E.g.

([])

is correctly balanced, but

(])[

is not.

-> So you can't just use counters for the opening and closing brackets, you need to keep track of the relative order.
-> Closing brackets have to be in the reverse order of the corresponding opening brackets.

->The last-in/first-out nature of stacks makes them a natural fit for processing most kinds of nested or hierarchical data.
->An opening bracket corresponds to a push operation, while a closing bracket corresponds to a pop, and
->you can check that you're popping the opening bracket that matches the closing bracket that you're processing during that iteration.


To build intuition around the idea that the Valid Parentheses problem on LeetCode requires a stack, think about how parentheses (or brackets) need to be matched and
balanced in the input string. The key insight is understanding that you need to "remember" the last opened parenthesis until it is properly closed. This is where the
stack comes in, as it provides a natural way to handle this type of problem.

Here’s how you can break it down:

1. Last Opened Parenthesis Needs to Be Closed First:
-> Parentheses must be matched in a specific order — for example, if you open a parenthesis (, you need to close it with ) later.
-> If you're looking at a string like "()", you see that the last opened parenthesis needs to be the first one to be closed. This behavior is called LIFO (Last In, First Out),
which is exactly how a stack works.

2. Opening Parentheses Are Pushed to the Stack:
-> Every time you encounter an opening parenthesis (, {, or [, you push it onto the stack. You're essentially "remembering" that you opened a parenthesis and need to close
it later.

3. Closing Parentheses Are Popped from the Stack:
-> When you encounter a closing parenthesis ), }, or ], you need to check if it matches the most recently opened one. You do this by popping the top of the stack.
-> If the stack is empty (i.e., no unmatched opening parenthesis exists), the parentheses are not balanced — and the string is invalid.

4. Balance Check at the End:
-> At the end of the string, if there's any unmatched opening parenthesis left in the stack, then the parentheses are not balanced.

Example Walkthrough:
Let's take an example of the string "{[()]}":

The first character is {, so we push it onto the stack: Stack: ["{"].

The next character is [, so we push it onto the stack: Stack: ["{", "["].

The next character is (, so we push it onto the stack: Stack: ["{", "[", "("].

The next character is ). We check the top of the stack — it's (, so we pop it: Stack: ["{", "["].

The next character is ]. We check the top of the stack — it's [, so we pop it: Stack: ["{"].

The next character is }. We check the top of the stack — it's {, so we pop it: Stack: [].

At the end, the stack is empty, indicating all parentheses were properly matched and closed.
 */


import java.util.Map;
import java.util.Stack;

public class ValidParentheses {

    /*
        Algorithm
        -------------

        1. Initial Check for Odd-Length String:
            This checks if the length of the input string s is odd.
            If the length is odd, we know right away that it's impossible to have balanced parentheses because parentheses come in pairs (one opening and one closing).
            If the string length is odd, we immediately return false.
        2. Mapping Opening Parentheses to Closing Parentheses:
            We use a Map (which is like a dictionary) to link each opening parenthesis ('(', '{', '[') to its corresponding closing parenthesis (')', '}', ']').
            This helps us easily check whether a closing parenthesis matches the last opened one by looking it up in the map.
        3. Using a Stack to Keep Track of Open Parentheses:
            We use a Stack to keep track of the opening parentheses we encounter.
            A stack works in a "Last In, First Out" (LIFO) manner, meaning the last opened parenthesis is the first one we want to check when a closing parenthesis comes up.
        4. Looping Through Each Character in the String:
            We loop through each character in the string s (the string of parentheses) one by one.
                -> If the current character is an opening parenthesis (like '(', '{', or '['), we push it onto the stack.This means we're now "keeping track" of this parenthesis
                because it needs to be matched with a closing parenthesis later on.
                -> If the current character is a closing parenthesis (like ')', '}', or ']'), we need to check if it matches the last opened parenthesis.
                   We pop the top element from the stack (the last opened parenthesis) to see if it matches the current closing parenthesis.
                   If it doesn't match, we return false, meaning the string is not valid because the parentheses are not correctly paired.
                   If the stack is empty (i.e., no opening parenthesis to match), we also return false, meaning there is no opening parenthesis for the closing one, which makes the string invalid.
        5. After looping through all the characters in the string, we check if the stack is empty.
            If the stack is empty, it means that every opening parenthesis has been correctly matched and closed with a corresponding closing parenthesis.
            If the stack is not empty, it means there are still unmatched opening parentheses left, so we return false.

     */
    public boolean isValid(String s) {

        // If the string length is odd, it cannot be valid
        if (s.length() % 2 != 0) {
            return false;
        }

        // Map for matching closing parentheses
        Map<Character, Character> match = Map.of('(', ')', '{', '}', '[', ']');
        Stack<Character> stack = new Stack<>();

        // Loop through each character in the string
        for (char c : s.toCharArray()) {
            // If it's an opening parenthesis, push it to the stack
            if (match.containsKey(c)) {
                stack.push(c);
            } else {
                // If it's a closing parenthesis, check if it matches the last opened parenthesis
                if (!stack.isEmpty()) {
                    char popped = stack.pop();
                    if (match.get(popped) != c)
                        return false; // Mismatched parentheses
                } else
                    return false; // No opening parenthesis for the closing one
            }
        }

        // If the stack is empty, all parentheses were matched and closed properly
        return stack.isEmpty();
    }


    /*
        Without Map needs a series of if condition to find matching pairs
     */
//    public boolean isValid(String s) {
//        // If the string length is odd, it cannot be valid
//        if (s.length() % 2 != 0) {
//            return false;
//        }
//
//        Stack<Character> stack = new Stack<>();
//
//        // Loop through each character in the string
//        for (char c : s.toCharArray()) {
//            // If it's an opening parenthesis, push it to the stack
//            if (c == '(' || c == '{' || c == '[') {
//                stack.push(c);
//            }
//            // If it's a closing parenthesis, check if it matches the last opened one
//            else {
//                if (stack.isEmpty()) {
//                    return false; // No opening parenthesis to match with
//                }
//
//                char popped = stack.pop();
//                if (c == ')' && popped != '(' ||
//                        c == '}' && popped != '{' ||
//                        c == ']' && popped != '[') {
//                    return false; // Mismatched parentheses
//                }
//            }
//        }
//
//        // If the stack is empty, all parentheses were matched and closed properly
//        return stack.isEmpty();
//    }

    public static void main(String[] args) {
        ValidParentheses validator = new ValidParentheses();

        // Test cases
        System.out.println(validator.isValid("()"));     // true
        System.out.println(validator.isValid("()[]{}")); // true
        System.out.println(validator.isValid("(]"));     // false
        System.out.println(validator.isValid("([)]"));   // false
        System.out.println(validator.isValid("{[]}"));   // true
    }
}
