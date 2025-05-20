package com.adp.companies.bloomberg;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
217. Contains Duplicate
Solved
Easy
Topics
Companies
Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.



Example 1:

Input: nums = [1,2,3,1]

Output: true

Explanation:

The element 1 occurs at the indices 0 and 3.

Example 2:

Input: nums = [1,2,3,4]

Output: false

Explanation:

All elements are distinct.

Example 3:

Input: nums = [1,1,1,3,3,4,3,2,4,2]

Output: true



Constraints:

1 <= nums.length <= 105
-109 <= nums[i] <= 109
 */
public class _32_ContainsDuplicates {

//    // Brute Force (O(n²) Time, O(1) Space)
//    public boolean containsDuplicate(int[] nums) {
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                if (nums[i] == nums[j]) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    // Sort the Array (O(n log n) Time, O(1) Extra Space if In-place)
//    public boolean containsDuplicate(int[] nums) {
//        Arrays.sort(nums); // Sort the array
//        for (int i = 1; i < nums.length; i++) {
//            if (nums[i] == nums[i - 1]) {
//                return true; // Found duplicate
//            }
//        }
//        return false;
//    }

    // O(n) time complexity and O(n) space complexity
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (!seen.add(num)) {
                return true;
            }
        }
        return false;
    }
}
