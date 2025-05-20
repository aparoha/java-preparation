package com.adp.companies.bloomberg;

import java.util.HashMap;
import java.util.Map;

/*
1399. Count Largest Group

You are given an integer n.

We need to group the numbers from 1 to n according to the sum of its digits. For example, the numbers 14 and 5 belong to the same group, whereas 13 and 3 belong to different groups.

Return the number of groups that have the largest size, i.e. the maximum number of elements.



Example 1:

Input: n = 13
Output: 4
Explanation: There are 9 groups in total, they are grouped according sum of its digits of numbers from 1 to 13:
[1,10], [2,11], [3,12], [4,13], [5], [6], [7], [8], [9].
There are 4 groups with largest size.
Example 2:

Input: n = 2
Output: 2
Explanation: There are 2 groups [1], [2] of size 1.


Constraints:

1 <= n <= 104
 */
public class _33_CountLargestGroup {

    // Method to find the number of groups with the largest size
    public int maxGroupSize(int n) {
        // Map to store the count of numbers for each sum of digits
        Map<Integer, Integer> sumGroupCount = new HashMap<>();

        // Grouping numbers from 1 to n based on sum of digits
        for (int i = 1; i <= n; i++) {
            int digitSum = digitalSum(i);   // Calculate the sum of digits of i
            sumGroupCount.put(digitSum, sumGroupCount.getOrDefault(digitSum, 0) + 1);
        }

        // Find the largest group size
        int maxSize = 0;
        for (int count : sumGroupCount.values()) {
            maxSize = Math.max(maxSize, count);
        }

        // Count how many groups have the largest size
        int result = 0;
        for (int count : sumGroupCount.values()) {
            if (count == maxSize) {
                result++;
            }
        }

        return result;
    }

    // Method to calculate the digital sum (just sum of digits)
    private int digitalSum(int num) {
        int sum = 0;

        // Loop through each digit of the number and add it to sum
        while (num > 0) {
            sum += num % 10; // Add the last digit
            num /= 10;       // Remove the last digit
        }

        return sum;
    }
}
