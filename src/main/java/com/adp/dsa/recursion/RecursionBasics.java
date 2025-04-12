package com.adp.dsa.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
1. Subsequence
2. Keypad combination
3. Stairs path
4. Maze paths
5. Maze path with jumps
6. Permutations
7. Encodings
8. Flood fill
9. Target sum subsets
10. N queens
11. Knights tour
 */

public class RecursionBasics {

    public static void main(String[] args) {
        //towerOfHanoi(3, 1, 2, 3);
        //displayArrayReverse(new int[] {1,2,3,4,5}, 0);
//        System.out.println(maxOfArray(new int[] {22, 3, 19, 4, 7}, 0));
//        System.out.println(minOfArray(new int[] {22, 3, 19, 4, 7}, 0));
//        System.out.println(findFirstIndexOf(new int[] {22, 3, 19, 4, 7, 3}, 0, 3));
//        System.out.println(findLastIndexOf(new int[] {22, 3, 19, 4, 7, 3}, 0, 3));
//        System.out.println(Arrays.toString(allIndices(new int[] {22, 3, 19, 4, 7, 3}, 0, 3, 0)));
        System.out.println(mazePaths(0, 0, 2, 2));
    }

    private static void printZigZag(int n) {
        if (n == 0)
            return;
        System.out.println("Pre " + n);
        printZigZag(n -1);
        System.out.println("In " + n);
        printZigZag(n -1);
        System.out.println("Post " + n);
    }

    private static void towerOfHanoi(int n, int t1, int t2, int t3) {
        if (n == 0)
            return;
        towerOfHanoi(n - 1, t1, t3, t2); // move from t1 to t3 using t2
        System.out.println(n + "[" + t1 + "->" + t2 + "]");
        towerOfHanoi(n - 1, t3, t2, t1);
    }

    private static void displayArray(int[] arr, int index) {
        if (index == arr.length)
            return;
        System.out.println(arr[index]);
        displayArray(arr, index + 1);
    }

    private static void displayArrayReverse(int[] arr, int index) {
        if (index == arr.length)
            return;
        displayArrayReverse(arr, index + 1);
        System.out.println(arr[index]);
    }

    private static int maxOfArray(int[] arr, int index) {
        if (index == arr.length - 1)
            return arr[index]; // return current index value when we reach last index of array
        int currentMax = Math.max(arr[index], maxOfArray(arr, index + 1));
        return currentMax;
    }

    private static int minOfArray(int[] arr, int index) {
        if (index == arr.length - 1)
            return arr[index]; // return current index value when we reach last index of array
        int currentMin = Math.min(arr[index], minOfArray(arr, index + 1));
        return currentMin;
    }

    private static int findFirstIndexOf(int[] arr, int index, int x) {
        if (index == arr.length)
            return -1;
        if (arr[index] == x)
            return index;
        return findFirstIndexOf(arr, index + 1, x);
    }
    private static int findLastIndexOf(int[] arr, int index, int x) {
        if (index == arr.length)
            return -1;
        int lastIndexInRemainingArray = findLastIndexOf(arr, index + 1, x);
        //if we don't find last index in remaining array
        if (lastIndexInRemainingArray == -1) {
            //That means, we need to check if the current index itself is the last index
            //If index is not found in remaining array and current index is also not matching with value then index not found in whole array
            return arr[index] == x ? index : -1;
        } else
            return lastIndexInRemainingArray; // last index found in remaining array so no need to check current index
    }

    private static int[] allIndices(int[] arr, int index, int x, int foundSoFar) {
        if (index == arr.length)
            return new int[foundSoFar];
        // The tricky part is we don't know what is going to be size of returned array (as we don't know how many times x present in the array), that's why we need foundSoFar
        if (arr[index] == x) {
            int[] iarr = allIndices(arr, index + 1, x, foundSoFar + 1);
            iarr[foundSoFar] = index;
            return iarr;
        } else {
            return allIndices(arr, index + 1, x, foundSoFar); // not found so no increment in foundSoFar
        }
    }

    /*
        2^n for the string of length n
        A subsequence is derived from another sequence by deleting some or no elements without changing the order of the
        remaining elements.

        Original sequence: [1, 2, 3]
        Subsequences: [1], [2], [3], [1, 2], [1, 3], [2, 3], [1, 2, 3], []

        “All subsequences are subsets, but not all subsets are subsequences.”
        Subsequences always maintains their relative Order.

        Property	Subsequence	                                                Subset
        Order	    Order must be preserved	                                    Order does not matter
        Deletion	You can delete elements, but order must remain the same.	You can select elements in any order.
        Example	    From "abc", subsequences include: "a", "ab", "abc".	        From {a, b, c}, subsets include: {a, b}, {a, c}, {b, c}.
        Empty Set	The empty subsequence is always a valid subsequence.	    The empty set is a valid subset.
     */

    // Method to generate all subsequences of a given string
    private static List<String> getAllSubsequences(String str) {
        List<String> subsequences = new ArrayList<>();
        generateSubsequencesHelper(str, 0, new StringBuilder(), subsequences);
        return subsequences;
    }

    // Helper method to recursively generate subsequences using backtracking
    private static void generateSubsequencesHelper(
            String str,
            int index,
            StringBuilder current,
            List<String> subsequences
    ) {
        // If we've considered all characters, add the current subsequence
        if (index == str.length()) {
            subsequences.add(current.toString());
            return;
        }

        current.append(str.charAt(index));  // Include the character
        generateSubsequencesHelper(str, index + 1, current, subsequences);

        // Backtrack by excluding the current character
        current.deleteCharAt(current.length() - 1);  // Remove the character (backtrack)
        generateSubsequencesHelper(str, index + 1, current, subsequences);
    }

    private static List<String> getAllSubsequencesApproach2(String str) {
        if (str.isEmpty())
            return List.of("");
        char firstChar = str.charAt(0);//a
        String remainingStr = str.substring(1);//bc
        List<String> remainingResult = getAllSubsequencesApproach2(remainingStr);//[--,-c,b-,bc]
        List<String> finalResult = new ArrayList<>();
        for (String rr : remainingResult)
            finalResult.add(firstChar + rr);
        for (String rr : remainingResult)
            finalResult.add("" + rr);
        return finalResult;
    }

    /*
    Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

    A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

        Example 1:

        Input: digits = "23"
        Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
        Example 2:

        Input: digits = ""
        Output: []
        Example 3:

        Input: digits = "2"
        Output: ["a","b","c"]


        Constraints:

        0 <= digits.length <= 4
        digits[i] is a digit in the range ['2', '9'].
     */

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty())
            return new ArrayList<>();

        List<String> ans = new ArrayList<>();
        String[] digitToLetters = {"", "", "abc",  "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        dfs(digits, 0, new StringBuilder(), digitToLetters, ans);
        return ans;
    }

    private void dfs(String digits, int i, StringBuilder sb, String[] digitToLetters, List<String> ans) {
        if (i == digits.length()) {
            ans.add(sb.toString());
            return;
        }
        for (char c : digitToLetters[digits.charAt(i) - '0'].toCharArray()) {
            sb.append(c);
            dfs(digits, i + 1, sb, digitToLetters, ans);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static List<String> letterCombinationsApproach2(String digits) {
        if (digits.isEmpty()) {
            return List.of("");
        }
        String[] digitToLetters = {".;", "abc",  "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz"};
        // 573
        char ch = digits.charAt(0); // 5
        String remainingDigits = digits.substring(1); // 73
        List<String> remainingDigitsResult = letterCombinationsApproach2(remainingDigits); // 6 words of 73
        List<String> finalResult = new ArrayList<>(); // 24 words of 573

        String codeForCh = digitToLetters[ch - '0']; // change character to number
        for (int i = 0; i < codeForCh.length(); i++) {
            char chCode = codeForCh.charAt(i);
            for (String rdr : remainingDigitsResult)
                finalResult.add(chCode + rdr);
        }
        return finalResult;
    }

    private static List<String> mazePaths(int startRow, int startCol, int finalRow, int finalCol){
        if (startRow == finalRow && startCol == finalCol) {
            List<String> baseResult = new ArrayList<>();
            baseResult.add("");
            return baseResult;
        }

        List<String> horizontalPaths = new ArrayList<>();
        List<String> verticalPaths = new ArrayList<>();

        // Move horizontally if possible
        if (startCol < finalCol)
            horizontalPaths = mazePaths(startRow, startCol + 1, finalRow, finalCol);
        // Move vertically if possible
        if (startRow < finalRow)
            verticalPaths = mazePaths(startRow + 1, startCol, finalRow, finalCol);

        List<String> result = new ArrayList<>();
        for (String hp : horizontalPaths)
            result.add("H" + hp);
        for (String vp : verticalPaths)
            result.add("V" + vp);

        return result;
    }

}
