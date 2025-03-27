/*
Backtracking is a technique for solving problems by exploring all possible solutions.

Backtracking problems ask us to find combinations or permutations. These Ps & Cs are then matched against certain conditions or smallest/greatest logic. Here are a couple of examples:

-> Given an array of numbers, find all the possible combinations of numbers that add up to a given number.
-> Given a collection of distinct integers, return all possible permutations of them.

In short, we can drill down backtracking to the below steps:

1. Start from a state.
2. If the current state is a solution, add the current state to the result.
3. If not, try each of the possible moves from the current state.
4. Once we have tried all the possible moves, backtrack to the previous state.
5. Repeat the above steps until a solution is found or all possibilities have been exhausted.

 */

package com.adp.dsa.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _02_CombinationSum {

    /*
    Problem 1

    Given a list of distinct positive numbers, find all combinations that add up to a given number.

    Input: [1, 2, 3, 4], target = 5
    Output: [[2, 3], [1, 4]]

     */

    private static List<List<Integer>> combinationSum(int[] nums, int target) {

        /*

        Step 1 - Initial State - The initial state is an empty list. We are going to start from this state and try to add numbers to it.

        We will keep track of:

        1. The current state or list of numbers used.
        2. The sum of the numbers in the list.

        */

        // will store the final results
        List<List<Integer>> result = new ArrayList<>();

        // keeps track of the current state
        List<Integer> currentState = new ArrayList<>();

        // call the helper function which does the processing
        // first 0 is the sum of the numbers in the current state
        // second 0 is the index to start from
        backtrack(nums, target, 0, 0, currentState, result);

        return result;
    }

    static void backtrack(int[] nums, int target, int currentStateSum, int startIndex, List<Integer> currentState, List<List<Integer>> result) {

        /*
                Step 2 - Check if the current state is a solution
                We need to check if the current state is a solution. To do so, we need to check if the sum of the numbers in the current state is equal to the target.

                If yes, we will add the current state to the final result.

         */

        // if the sum of the numbers in the current state is equal to the target,
        // then we have found a solution
        if (target == currentStateSum) {
            // copy the content of the current state to a new list and save to result
            result.add(new ArrayList<>(currentState));
            return;
        } else if(currentStateSum > target) {
            // short circuit, if the sum of the numbers in the current state is greater than the target,
            return;
        }

        /*

            Step 3 - Try each of the possible moves and backtrack
            If the current state is not a solution, we need to try each of the possible moves.

            The possible moves are all the numbers in the nums array that comes on or after the index.

            We can divide this into 3 steps:

            1. Add a number to the current list to get to the next state.
            2. Call the backtrack function to check if the next state is a solution. Update the index and sum being sent to the backtrack function.
            3. Remove the number from the current list to get back to the previous state. The next iteration will try the next number.

         */


        for (int i = startIndex; i < nums.length; i++) {
            // add the current number to the current state
            currentState.add(nums[i]);

            // call the helper function to try the next state - Current sum is updated and index is increased to the next number
            backtrack(nums, target, currentStateSum + nums[i], i + 1, currentState, result);

            // remove the current number from the current state
            currentState.remove(currentState.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> result = combinationSum(new int[] {1,2,3,4}, 5);
        System.out.println(result);
    }


}
