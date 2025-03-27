package com.adp.leetcode.stack;

import java.util.Stack;

/*
1475. Final Prices With a Special Discount in a Shop
Solved
Easy
Topics
Companies
Hint
You are given an integer array prices where prices[i] is the price of the ith item in a shop.

There is a special discount for items in the shop. If you buy the ith item, then you will receive a discount equivalent to prices[j] where j is the minimum index such that j > i and prices[j] <= prices[i]. Otherwise, you will not receive any discount at all.

Return an integer array answer where answer[i] is the final price you will pay for the ith item of the shop, considering the special discount.



Example 1:

Input: prices = [8,4,6,2,3]
Output: [4,2,4,2,3]
Explanation:
For item 0 with price[0]=8 you will receive a discount equivalent to prices[1]=4, therefore, the final price you will pay is 8 - 4 = 4.
For item 1 with price[1]=4 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 4 - 2 = 2.
For item 2 with price[2]=6 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 6 - 2 = 4.
For items 3 and 4 you will not receive any discount at all.
Example 2:

Input: prices = [1,2,3,4,5]
Output: [1,2,3,4,5]
Explanation: In this case, for all items, you will not receive any discount at all.
Example 3:

Input: prices = [10,1,1,6]
Output: [9,0,1,6]
 */
public class SpecialDiscount1475 {

    //Aproach 1 - brute force
    public int[] finalPrices(int[] prices) {
        int pricesLength = prices.length;
        int[] result = new int[pricesLength];
        for(int i = 0; i < pricesLength; i++) {
            int discount = 0;
            // For each item, find the first item after it that gives a discount
            for(int j = i + 1; j < pricesLength; j++) {
                if(prices[j] <= prices[i]) {
                    discount = prices[j];
                    break; // Stop once the discount is found
                }
            }
            // The final price for the current item is the original price minus the discount
            result[i] = prices[i] - discount;
        }
        return result;
    }

    //Approach 2 - variation of next smaller element

    /*
    1. Key part of the problem: for any given item, we need to find the first price that is smaller or equal to it and comes after it. This is similar to a classic problem known as finding the "next smaller element,"
    2. we are processing prices from left to right. At each step, we need to determine if the current price can serve as a discount for any previous prices. The stack helps us keep track of those previous prices that haven't found their discount yet.
    3. The key intuition is that when we find a price that is smaller than some earlier prices, it must be the discount for those earlier prices that are larger than it. We only care about the most recent of these prices because we want the first available discount
    4. So, for each element, our stack must contain all the most recent prices before that element that are greater than it. This implies that each element present in the stack must be in increasing order of value. This is called a monotonic stack.
    5. When we encounter an element that is smaller than the top of the stack, this means a discount can be applied to the stack element. We continue popping prices from the stack and applying the discount until the stack is empty or the top price is less than the current price. Then, we push the current price to the top of the stack, to wait for a discount which may come further down. This way, we can both apply discounts and also maintain the monotonic property of the stack.
    6. To implement this idea, we'll maintain a stack of indices (not prices, since we need the positions to apply discounts). We iterate over the prices array and check if the current price is less than or equal to the price at the top of the stack. If it is, the current element can be used as a discount to the elements waiting in the stack. We remove each larger price from the stack and apply the discount, then add the current price to the stack. Any prices left on the stack at the end of the main loop had no discount available.
     */
    public static int[] finalPricesOptimized(int[] prices) {
        //Next smaller element, monotonic stack
        int n = prices.length;
        int[] answer = new int[n];
        System.arraycopy(prices, 0, answer, 0, prices.length);
        Stack<Integer> nextSmaller = new Stack<>();
        for(int i = 0; i < n; i++) {
            while(!nextSmaller.isEmpty() && prices[nextSmaller.peek()] >= prices[i]) {
                int nextSmallerTop = nextSmaller.pop();
                answer[nextSmallerTop] = prices[nextSmallerTop] - prices[i];
            }
            nextSmaller.push(i);
        }
        return answer;
    }
}
