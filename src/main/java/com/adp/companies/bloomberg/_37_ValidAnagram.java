package com.adp.companies.bloomberg;

/*
242. Valid Anagram
Solved
Easy
Topics
Companies
Given two strings s and t, return true if t is an anagram of s, and false otherwise.



Example 1:

Input: s = "anagram", t = "nagaram"

Output: true

Example 2:

Input: s = "rat", t = "car"

Output: false



Constraints:

1 <= s.length, t.length <= 5 * 104
s and t consist of lowercase English letters.


Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?


 */

import java.util.HashMap;
import java.util.Map;

public class _37_ValidAnagram {
    public boolean isAnagram(String s, String t) {
        // Quick null/length check
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        int[] charCounts = new int[26];

        for (int i = 0; i < s.length(); i++) {
            charCounts[s.charAt(i) - 'a']++;
            charCounts[t.charAt(i) - 'a']--;
        }

        for (int count : charCounts) {
            if (count != 0) return false;
        }

        return true;
    }

//    // Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
//    public boolean isAnagram(String s, String t) {
//        if (s == null || t == null || s.length() != t.length()) {
//            return false;
//        }
//
//        Map<Character, Integer> charCount = new HashMap<>();
//
//        for (int i = 0; i < s.length(); i++) {
//            char sc = s.charAt(i);
//            char tc = t.charAt(i);
//
//            charCount.merge(sc, 1, Integer::sum);
//            charCount.merge(tc, -1, Integer::sum);
//        }
//
//        for (int count : charCount.values()) {
//            if (count != 0) return false;
//        }
//
//        return true;
//    }
}
