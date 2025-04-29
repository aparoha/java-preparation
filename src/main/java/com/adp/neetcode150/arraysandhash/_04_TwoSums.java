package com.adp.neetcode150.arraysandhash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.



Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]
Example 3:
 */
public class _04_TwoSums {

    public int[] twoSumApproach1(int[] nums, int target) {
        int[] result = new int[2];
        int n = nums.length;
        for (int  i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
        }
        return result;
    }

    // Optimal for unsorted arrays
    public int[] twoSumApproach2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                result[0] = map.get(complement);
                result[1] = i;
                break;
            }
            map.put(nums[i], i);
        }

        return result;
    }

    // When array is sorted
    public int[] twoSumApproach3(int[] nums, int target) {
        int[] result = new int[2];
        Arrays.sort(nums); // If original indices needed, use a value-index pair array
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                result[0] = left;
                result[1] = right;
                return result;
            }
            else if (sum < target)
                left++;
            else
                right--;
        }

        return result;
    }
}
