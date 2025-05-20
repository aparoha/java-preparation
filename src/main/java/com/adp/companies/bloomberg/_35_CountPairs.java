package com.adp.companies.bloomberg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
2176. Count Equal and Divisible Pairs in an Array

Given a 0-indexed integer array nums of length n and an integer k, return the number of pairs (i, j) where 0 <= i < j < n, such that nums[i] == nums[j] and (i * j) is divisible by k.


Example 1:

Input: nums = [3,1,2,2,2,1,3], k = 2
Output: 4
Explanation:
There are 4 pairs that meet all the requirements:
- nums[0] == nums[6], and 0 * 6 == 0, which is divisible by 2.
- nums[2] == nums[3], and 2 * 3 == 6, which is divisible by 2.
- nums[2] == nums[4], and 2 * 4 == 8, which is divisible by 2.
- nums[3] == nums[4], and 3 * 4 == 12, which is divisible by 2.
Example 2:

Input: nums = [1,2,3,4], k = 1
Output: 0
Explanation: Since no value in nums is repeated, there are no pairs (i,j) that meet all the requirements.


Constraints:

1 <= nums.length <= 100
1 <= nums[i], k <= 100
 */
public class _35_CountPairs {

    // O(n^2)
    /*
    Complexity
Time complexity: O(n^2)
Because we examine all pairs (i, j) such that 0 <= i < j < n. For each such pair, we do constant time checks.
Space complexity: O(1)
We use a fixed number of variables. No extra space is used apart from loop counters and the count variable.
     */
    public int countPairsBruteForce(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] == nums[j] && (i * j) % k == 0)
                    count++;
            }
        }
        return count;
    }

    /*
    Instead of checking every possible pair in the array, we observe that the condition nums[i] == nums[j] significantly
    reduces the number of valid pairs. So we can group all indices of equal elements together and only check index pairs
    within those groups for the divisibility condition (i * j) % k == 0.
    This optimization helps reduce the number of unnecessary comparisons, especially when the list has many duplicates.

    Approach
Group Indices by Value:

Traverse the array and build a map where keys are the elements and values are lists of indices where that element occurs.
Process Each Group:

For each list of indices corresponding to the same element:
Iterate over all index pairs (i, j) such that i < j.
Check if (i * j) % k == 0, and if so, increment the count.
This is much faster when there are many repeating values because it skips checking across different values.

Complexity
Time complexity: O(n^2) in the worst case, where all n elements are the same and we compare all pairs.
However, in practice, the time is significantly reduced due to grouping and skipping unmatched values.

Space complexity: O(n)

We store a map with lists of indices. In the worst case (all values unique), we store all indices.
     */
    public int countPairs(int[] nums, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], x -> new ArrayList<>()).add(i);
        }

        for (List<Integer> indices : map.values()) {
            for (int i = 0; i < indices.size(); i++) {
                for (int j = i + 1; j < indices.size(); j++) {
                    if (((long) indices.get(i) * indices.get(j)) % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
