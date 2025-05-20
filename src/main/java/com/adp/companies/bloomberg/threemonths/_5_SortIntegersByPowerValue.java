package com.adp.companies.bloomberg.threemonths;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
1387. Sort Integers by The Power Value
Medium
Topics
Companies
Hint
The power of an integer x is defined as the number of steps needed to transform x into 1 using the following steps:

if x is even then x = x / 2
if x is odd then x = 3 * x + 1
For example, the power of x = 3 is 7 because 3 needs 7 steps to become 1 (3 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1).

Given three integers lo, hi and k. The task is to sort all integers in the interval [lo, hi] by the power value in ascending order, if two or more integers have the same power value sort them by ascending order.

Return the kth integer in the range [lo, hi] sorted by the power value.

Notice that for any integer x (lo <= x <= hi) it is guaranteed that x will transform into 1 using these steps and that the power of x is will fit in a 32-bit signed integer.



Example 1:

Input: lo = 12, hi = 15, k = 2
Output: 13
Explanation: The power of 12 is 9 (12 --> 6 --> 3 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1)
The power of 13 is 9
The power of 14 is 17
The power of 15 is 17
The interval sorted by the power value [12,13,14,15]. For k = 2 answer is the second element which is 13.
Notice that 12 and 13 have the same power value and we sorted them in ascending order. Same for 14 and 15.
Example 2:

Input: lo = 7, hi = 11, k = 4
Output: 7
Explanation: The power array corresponding to the interval [7, 8, 9, 10, 11] is [16, 3, 19, 6, 14].
The interval sorted by power is [8, 10, 11, 7, 9].
The fourth number in the sorted array is 7.


Constraints:

1 <= lo <= hi <= 1000
1 <= k <= hi - lo + 1
 */
public class _5_SortIntegersByPowerValue {

    // Method to compute power of x using Collatz sequence
    private static int getPower(int x) {
        int power = 0;
        while (x != 1) {
            if (x % 2 == 0) {
                x /= 2;
            } else {
                x = 3 * x + 1;
            }
            power++;
        }
        return power;
    }

    public static int getKthBruteForce(int lo, int hi, int k) {
        record valpower(int val, int power) {}
        // List to store pairs of [number, power]
        List<valpower> powerList = new ArrayList<>();

        for (int i = lo; i <= hi; i++) {
            powerList.add(new valpower(i, getPower(i)));
        }

        // Sort by power, then by value
        Collections.sort(powerList, (a, b) -> {
            if (a.power() == b.power()) {
                return Integer.compare(a.val(), b.val());
            }
            return Integer.compare(a.power(), b.power());
        });

        return powerList.get(k - 1).val(); // k is 1-indexed
    }

    /*
        // Memoization cache
    private static Map<Integer, Integer> powerCache = new HashMap<>();

    // Compute power with memoization
    private static int getPower(int x) {
        if (x == 1) return 0;
        if (powerCache.containsKey(x)) return powerCache.get(x);

        int next = (x % 2 == 0) ? x / 2 : 3 * x + 1;
        int power = 1 + getPower(next);

        powerCache.put(x, power);
        return power;
    }
     */
}
