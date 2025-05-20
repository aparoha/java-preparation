package com.adp.dsa.slidingwindow;

/*
1423. Maximum Points You Can Obtain from Cards
Medium
Topics
Companies
Hint
There are several cards arranged in a row, and each card has an associated number of points. The points are given in the integer array cardPoints.

In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.

Your score is the sum of the points of the cards you have taken.

Given the integer array cardPoints and the integer k, return the maximum score you can obtain.



Example 1:

Input: cardPoints = [1,2,3,4,5,6,1], k = 3
Output: 12
Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
Example 2:

Input: cardPoints = [2,2,2], k = 2
Output: 4
Explanation: Regardless of which two cards you take, your score will always be 4.
Example 3:

Input: cardPoints = [9,7,7,9,7,7,9], k = 7
Output: 55
Explanation: You have to take all the cards. Your score is the sum of points of all cards.


Constraints:

1 <= cardPoints.length <= 105
1 <= cardPoints[i] <= 104
1 <= k <= cardPoints.length
 */
public class _01_MaxScores {

    /*
    Algorithm / Intuition
To select cards from the beginning or end of the array containing value of the cards, we can use a sliding window approach with two pointers:

A left pointer that initially covers the first k elements from the left.
A right pointer that initially covers no elements from the right (is present at the end and moves to the left).

Now, we execute the sliding window approach from the left to the right one at a time moving elements from the right to the left.

Algorithm
Step 1: Initialise variables lsum, rsum and maxSum that will store the sum of elements from the left, from the right and the maximum sum.

Step 2: Iterate through the first k elements of the cards array and calculate their sum. Store this in lsum.

Step 3: Set the maximum sum to the sum calculated in the previous step. This initialises the maximum sum to the sum of the first k element from the left.

Step 4: Iterate from k-1 to 0 and at each iteration:

Subtract the element at the current index from the left sum moving from left window to right window.
Add the element at the right index to the right sum hence moving from the right to the left.
Update the maxSum to the maximum of the current maxSum and the sum of lsum and rSum.

Step 5: After the k iterations, maxSum will hole the maximum sum achievable by selecting k elements from the left and right array to find the maximum sum possible.


     */

    public int maxScore(int[] cardPoints, int k) {
        // Initialize left sum (lsum) and right sum (rsum) to 0.
        int lsum = 0, rsum = 0;

        // Initialize the maximum sum (maxSum) to 0.
        int maxSum = 0;

        // Set rIndex to the index of
        // the last element in cardPoints.
        int rIndex = cardPoints.length - 1;

        // Calculate the sum of the first k elements from the left side of the array.
        for(int i = 0; i < k; i++) {
            lsum += cardPoints[i];
        }

        // Initialize maxSum to the sum of the first k elements from the left.
        maxSum = lsum;

        // Move one element from the left to the right at a time.
        for(int i = k - 1; i >= 0; i--) {
            // Subtract the current element from lsum as it's moved to the right.
            lsum = lsum - cardPoints[i];

            // Add the current right element to rsum.
            rsum = rsum + cardPoints[rIndex];

            // Decrease the right index to point to the next element on the left.
            rIndex = rIndex - 1;

            // Update maxSum to the maximum value
            // between the current maxSum and
            // the sum of lsum and rsum.
            maxSum = Math.max(maxSum, lsum + rsum);
        }

        // Return the maximum sum obtained.
        return maxSum;

    }
}
