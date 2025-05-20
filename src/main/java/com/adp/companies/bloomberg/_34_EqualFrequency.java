package com.adp.companies.bloomberg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
2423. Remove Letter To Equalize Frequency

You are given a 0-indexed string word, consisting of lowercase English letters. You need to select one index and remove the letter at that index from word so that the frequency of every letter present in word is equal.

Return true if it is possible to remove one letter so that the frequency of all letters in word are equal, and false otherwise.

Note:

The frequency of a letter x is the number of times it occurs in the string.
You must remove exactly one letter and cannot choose to do nothing.


Example 1:

Input: word = "abcc"
Output: true
Explanation: Select index 3 and delete it: word becomes "abc" and each character has a frequency of 1.
Example 2:

Input: word = "aazz"
Output: false
Explanation: We must delete a character, so either the frequency of "a" is 1 and the frequency of "z" is 2, or vice versa. It is impossible to make all present letters have equal frequency.


Constraints:

2 <= word.length <= 100
word consists of lowercase English letters only.
 */
public class _34_EqualFrequency {

    /*
    Count frequency of each character using a Map<Character, Integer>.

Try removing each character once (simulate removing it), and:

Decrease its count by 1.

Remove it from the map if its count becomes 0.

Check if all remaining frequencies are equal.

If any such removal results in equal frequencies → return true.
     */
    public boolean equalFrequency(String word) {
        // Create a map to count frequency of each character
        Map<Character, Integer> charFreqMap = new HashMap<>();

        // Step 1: Count frequency of each character
        for (char c : word.toCharArray())
            charFreqMap.merge(c, 1, Integer::sum);

        // Step 2: Try removing one character at each index
        for (char ch : charFreqMap.keySet()) {
            Map<Character, Integer> tempMap = new HashMap<>(charFreqMap);
            tempMap.put(ch, tempMap.get(ch) - 1);
            if (tempMap.get(ch) == 0) {
                tempMap.remove(ch);
            }

            // Check if all values in tempMap are equal
            Set<Integer> freqSet = new HashSet<>(tempMap.values());
            if (freqSet.size() == 1) {
                return true;
            }
        }

        return false;
    }
}
