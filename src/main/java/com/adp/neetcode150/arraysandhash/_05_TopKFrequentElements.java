package com.adp.neetcode150.arraysandhash;

import java.util.*;
import java.util.stream.Collectors;

/*
Given an integer array nums and an integer k, return the k most frequent elements within the array.

The test cases are generated such that the answer is always unique.

You may return the output in any order.

Example 1:

Input: nums = [1,2,2,3,3,3], k = 2

Output: [2,3]
Example 2:

Input: nums = [7,7], k = 1

Output: [7]
 */
public class _05_TopKFrequentElements {

    // O(nlogn)
    public int[] topKFrequentMapSort(int[] nums, int k) {
        // Step 1: Build the frequency map
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.merge(num, 1, Integer::sum); // Efficient frequency count frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        //Sort frequency map by value to get top k key value pairs first
        var sortedMapEntries = frequencyMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());
        int[] result = new int[k];
        for (var kv : sortedMapEntries) {
            if (k != 0) {
                result[--k] = kv.getKey();
            }
        }
        return result;
    }

    // O(nlogn)
    /*
    O(n) to count frequencies
    O(n log n) to build full heap
    O(k log n) to extract top k

    Total: O(n log n)
     */
    public int[] topKFrequentMaxHeap(int[] nums, int k) {
        // Step 1: Frequency map
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.merge(num, 1, Integer::sum);
        }

        // Step 2: Max-heap (full size)
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        maxHeap.addAll(freqMap.entrySet());

        // Step 3: Get top k
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll().getKey();
        }

        return result;
    }

    /*
    O(n log k)

    O(n) to count frequencies
    O(n log k) to maintain heap
    O(k) to build result

    Total: O(n log k)
     */
    public int[] topKFrequentMinHeap(int[] nums, int k) {
        // Step 1: Build frequency map
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.merge(num, 1, Integer::sum);
        }

        // Step 2: Min-heap (size k)
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll(); // remove least frequent
            }
        }

        // Step 3: Extract result
        int[] result = new int[k];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : minHeap) {
            result[i++] = entry.getKey();
        }

        return result;
    }


}
