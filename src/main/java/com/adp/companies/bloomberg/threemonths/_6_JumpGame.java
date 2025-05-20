package com.adp.companies.bloomberg.threemonths;

import java.util.HashMap;
import java.util.Map;

/*
55. Jump Game
Medium
Topics
Companies
You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.



Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.


Constraints:

1 <= nums.length <= 104
0 <= nums[i] <= 105

 */
public class _6_JumpGame {

    public boolean canJumpBruteForce(int[] nums) {
        return dfs(nums, 0);
    }

    private boolean dfs(int[] nums, int index) {
        if (index == nums.length - 1)  // ✅ Reached the last index
            return true;
        if (index >= nums.length || nums[index] == 0)  // ⛔ Out of bounds or stuck
            return false;
        int steps = nums[index];
        for (int i = 1; i <= steps; i++) {
            if (dfs(nums, i + index))
                return true;
        }
        return false;
    }

    private boolean dfs(int[] nums, int index, Boolean[] memo) {
        if (index >= nums.length - 1) return true;
        if (nums[index] == 0) return false;
        if (memo[index] != null) return memo[index];

        int steps = nums[index];
        for (int i = 1; i <= steps; i++) {
            if (dfs(nums, index + i, memo)) {
                memo[index] = true;
                return true;
            }
        }

        memo[index] = false;
        return false;
    }

    public boolean canJump(int[] nums) {
        return dfs(nums, 0, new Boolean[nums.length]);
    }

    public boolean canJumpGreedy(int[] nums) {
        int farthest = 0; // The farthest index we can currently reach
        for (int i = 0; i < nums.length; i++) {
            if (i > farthest) {
                return false; // We've hit a point we can't reach
            }
            farthest = Math.max(farthest, i + nums[i]);
        }
        return true; // If we finish the loop, we can reach the end
    }
}
