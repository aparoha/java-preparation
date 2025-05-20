package com.adp.companies.bloomberg;

/*
209. Minimum Size Subarray Sum

Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray
whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.

Example 1:

Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.
Example 2:

Input: target = 4, nums = [1,4,4]
Output: 1
Example 3:

Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0


Constraints:

1 <= target <= 109
1 <= nums.length <= 105
1 <= nums[i] <= 104


Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).
 */
public class _31_MinSizeSubarraySum {

    /*
    Approach:
Sliding Window: Start with two pointers (left and right) to represent the window. Initially, both pointers will point to the start of the array.

Expand the window by moving the right pointer and adding elements to the sum until the sum is greater than or equal to the target.

Once the sum meets or exceeds the target, try to shrink the window from the left side by moving the left pointer to minimize the window size while maintaining the sum greater than or equal to the target.

Track the minimum length of any valid subarray found.

If no such subarray is found by the end, return 0.
     */
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int minLength = Integer.MAX_VALUE;  // Start with a large value
        int sum = 0;  // Sum of the current window
        int left = 0;  // Left pointer of the sliding window

        // Iterate through the array with the right pointer
        for (int right = 0; right < n; right++) {
            sum += nums[right];  // Add current element to the window sum

            // Shrink the window from the left as long as the sum >= target
            while (sum >= target) {
                minLength = Math.min(minLength, right - left + 1);  // Update the minimum length
                sum -= nums[left];  // Remove the leftmost element from the sum
                left++;  // Move the left pointer to the right
            }
        }

        // If no valid subarray is found, return 0
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    /*
    If you're looking for a solution with a time complexity of O(n log n), we can use a binary search approach, leveraging prefix sums.

Approach:
Prefix Sum Array: Create an array of prefix sums where each element at index i represents the sum of all elements from nums[0] to nums[i-1]. The prefix sum array helps us quickly calculate the sum of any subarray.

Binary Search: Once we have the prefix sums, we can use binary search to find the minimal subarray length. For each prefix sum at index i, we check if there's a previous prefix sum that results in a subarray whose sum is greater than or equal to the target.
     */

    public int minSubArrayLenPrefixSum(int target, int[] nums) {
        int n = nums.length;
        int[] prefixSums = new int[n + 1];  // Prefix sum array, prefixSums[i] is sum of nums[0..i-1]

        // Compute prefix sums
        for (int i = 1; i <= n; i++) {
            prefixSums[i] = prefixSums[i - 1] + nums[i - 1];
        }

        int minLength = Integer.MAX_VALUE;

        // Iterate through each element in the prefix sum array
        for (int i = 1; i <= n; i++) {
            // Perform binary search to find the smallest index j where prefixSums[i] - prefixSums[j] >= target
            int left = 0, right = i - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (prefixSums[i] - prefixSums[mid] >= target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            // If a valid subarray is found
            if (prefixSums[i] - prefixSums[left] >= target) {
                minLength = Math.min(minLength, i - left);
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

}
