package com.adp.companies.bloomberg.threemonths;

import java.lang.reflect.Array;
import java.util.Arrays;

/*
475. Heaters
Medium
Topics
Companies
Winter is coming! During the contest, your first job is to design a standard heater with a fixed warm radius to warm all the houses.

Every house can be warmed, as long as the house is within the heater's warm radius range.

Given the positions of houses and heaters on a horizontal line, return the minimum radius standard of heaters so that those heaters could cover all houses.

Notice that all the heaters follow your radius standard, and the warm radius will the same.



Example 1:

Input: houses = [1,2,3], heaters = [2]
Output: 1
Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
Example 2:

Input: houses = [1,2,3,4], heaters = [1,4]
Output: 1
Explanation: The two heaters were placed at positions 1 and 4. We need to use a radius 1 standard, then all the houses can be warmed.
Example 3:

Input: houses = [1,5], heaters = [2]
Output: 3
 */
public class _1_Heaters {

    /*
    1. For each house, calculate the minimum distance to any heater.
    2. Keep track of the maximum of these minimum distances — that's the required radius.
    Time: O(M × N), where M = houses.length and N = heaters.length

    Space: O(1), excluding input
     */
    public int findRadiusBruteForce(int[] houses, int[] heaters) {

        /*
        The minimum radius is the maximum distance between a house and it's closest heater
         */
        int radius = 0;

        for (int house : houses) {
            int minDist = Integer.MAX_VALUE;

            for (int heater : heaters) {
                int dist = Math.abs(house - heater);
                minDist = Math.min(minDist, dist);
            }

            // Update the radius needed for this house
            radius = Math.max(radius, minDist);
        }

        return radius;
    }

    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int radius = 0;
        for (int house : houses)
            radius = Math.max(radius, closestDistance(heaters, house));
        return radius;
    }

    // Search for the closest heater
    private int closestDistance(int[] heaters, int house) {
        int left = 0, right = heaters.length - 1;
        int minDistance = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            minDistance = Math.min(minDistance, Math.abs(heaters[mid] - house));
            if (heaters[mid] < house)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return minDistance;
    }
}
