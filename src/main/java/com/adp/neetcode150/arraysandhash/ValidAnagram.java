package com.adp.neetcode150.arraysandhash;

/*

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






Example Walkthrough
Checking if "anagram" and "nagaram" are anagrams:

Letter	s (anagram)	t (nagaram)	Change in count[]
'a'	    +1	        -1	        0
'n'	    +1	        -1	        0
'a'	+1	-1	0
'g'	+1	-1	0
'r'	+1	-1	0
'a'	+1	-1	0
'm'	+1	-1	0
Since the count for every letter is zero, "anagram" and "nagaram" are anagrams.

This function efficiently checks if two words are anagrams using an array instead of sorting, making it faster (O(n) time complexity instead of O(n log n) with sorting).

 */
public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        for (int val : count) {
            if (val != 0) {
                return false;
            }
        }
        return true;
    }
}
