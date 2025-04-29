package com.adp.neetcode150.arraysandhash;

import java.util.*;

/*
Given an array of strings strs, group the anagrams together. You can return the answer in any order.



Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]

Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Explanation:

There is no string in strs that can be rearranged to form "bat".
The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
Example 2:

Input: strs = [""]

Output: [[""]]

Example 3:

Input: strs = ["a"]

Output: [["a"]]



Constraints:

1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.

-----------------------------------------------------------------------------------------------------

The function groups words that are anagrams.

It creates a unique key for each word based on letter frequency.

Words with the same key are stored in the same group.

It returns a list of lists, where each sublist contains anagrams.

Example Walkthrough
Input:
java
Copy
Edit
String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
Processing:
"eat" → Key: "[1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0]"

Stored under this key: ["eat"]

"tea" → Same key as "eat" → Grouped with ["eat", "tea"]

"tan" → New key → Stored separately ["tan"]

"ate" → Same key as "eat" → Grouped with ["eat", "tea", "ate"]

"nat" → Same key as "tan" → Grouped with ["tan", "nat"]

"bat" → New key → Stored separately ["bat"]

 */
public class _03_GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> res = new HashMap<>();
        for (String s : strs) {
            /*
            We create an array count of size 26 (for letters 'a' to 'z'). For each letter in the word, we increase the corresponding index in the count array.
            Example: If s = "cat", the count array will store:
            [1, 0, 1, 0, ..., 1, 0, 0]  (1 'c', 1 'a', 1 't')

             */
            int[] count = new int[26];
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }
            //This converts the count array into a string, such as "[1, 0, 1, 0, ..., 1, 0, 0]", so it can be used as a key in the map.
            //Grouping words of same frequency array
            String key = Arrays.toString(count);
            res.putIfAbsent(key, new ArrayList<>());
            res.get(key).add(s);
        }
        return new ArrayList<>(res.values());
    }
}
