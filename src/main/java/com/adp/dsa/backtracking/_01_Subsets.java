package com.adp.dsa.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class _01_Subsets {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> currentState = new ArrayList<>();
        backtrack(subsets, currentState, 0, nums);
        return subsets;
    }

    private static void backtrack(List<List<Integer>> subsets, List<Integer> currentState, int startIndex, int[] nums){
        subsets.add(new ArrayList<>(currentState));
        for (int index = startIndex; index < nums.length; index++) {
            currentState.add(nums[index]);
            backtrack(subsets, currentState, index+1, nums);
            currentState.remove(currentState.size() - 1);
        }
    }

    private static void logIt(String msg, List<List<Integer>> subsets, List<Integer> currentState, int startIndex) {
        System.out.println(String.format("%s, subsets: %s, currentState: %s, startIndex %d",
                        msg,
                        subsets.stream().map(String::valueOf)
                                .collect(Collectors.joining(",")),
                        currentState.stream().map(String::valueOf)
                                .collect(Collectors.joining(",")),
                        startIndex
                )
        );
    }


    public static void main(String[] args) {
        List<List<Integer>> subsets = subsets(new int[] {1, 2});
        System.out.println(subsets);
    }
}
