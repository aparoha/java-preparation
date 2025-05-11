package com.adp.companies.bloomberg;

import java.util.*;

/*
A transaction is possibly invalid if:

the amount exceeds $1000, or;
if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.
You are given an array of strings transaction where transactions[i] consists of comma-separated values representing the name, time (in minutes), amount, and city of the transaction.

Return a list of transactions that are possibly invalid. You may return the answer in any order.



Example 1:

Input: transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
Output: ["alice,20,800,mtv","alice,50,100,beijing"]
Explanation: The first transaction is invalid because the second transaction occurs within a difference of 60 minutes, have the same name and is in a different city. Similarly the second one is invalid too.
Example 2:

Input: transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
Output: ["alice,50,1200,mtv"]
Example 3:

Input: transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
Output: ["bob,50,1200,mtv"]


Constraints:

transactions.length <= 1000
Each transactions[i] takes the form "{name},{time},{amount},{city}"
Each {name} and {city} consist of lowercase English letters, and have lengths between 1 and 10.
Each {time} consist of digits, and represent an integer between 0 and 1000.
Each {amount} consist of digits, and represent an integer between 0 and 2000.
 */
public class _04_InvalidTransactions {

    public List<String> invalidTransactions(String[] transactions) {
        List<String> result = new ArrayList<>();
        int n = transactions.length;
        String[] names = new String[n];
        int[] times = new int[n];
        int[] amounts = new int[n];
        String[] cities = new String[n];

        for (int i = 0; i < n; i++) {
            String[] parts = transactions[i].split(",");
            names[i] = parts[0];
            times[i] = Integer.parseInt(parts[1]);
            amounts[i] = Integer.parseInt(parts[2]);
            cities[i] = parts[3];
        }

        Set<Integer> invalidIndices = new HashSet<>();

        for (int i = 0; i < n; i++) {
            if (amounts[i] > 1000) {
                invalidIndices.add(i);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j &&
                        names[i].equals(names[j]) &&
                        !cities[i].equals(cities[j]) &&
                        Math.abs(times[i] - times[j]) <= 60) {
                    invalidIndices.add(i);
                    invalidIndices.add(j);
                }
            }
        }

        for (int i : invalidIndices) {
            result.add(transactions[i]);
        }

        return result;
    }


    public List<String> invalidTransactionsOptimized(String[] transactions) {
        record TransactionItem(int time, String city, int index){};
        // A map to maintain transaction items per user
        Map<String, List<TransactionItem>> transactionMap = new HashMap<>();
        // A set to keep track of invalid transaction indices
        Set<Integer> invalidIndices = new HashSet<>();

        // Iterate through all transactions
        for (int i = 0; i < transactions.length; i++) {
            // Split the transaction string into individual pieces of data
            String[] transactionDetails = transactions[i].split(",");

            String name = transactionDetails[0];
            int time = Integer.valueOf(transactionDetails[1]);
            int amount = Integer.valueOf(transactionDetails[2]);
            String city = transactionDetails[3];

            // Add the transaction item under the user's name in the map
            transactionMap.computeIfAbsent(name, k -> new ArrayList<>())
                    .add(new TransactionItem(time, city, i));

            // If the amount exceeds $1000, mark as invalid
            if (amount > 1000) {
                invalidIndices.add(i);
            }

            // Check the transaction against other transaction items of the same user
            for (TransactionItem item : transactionMap.get(name)) {
                // If a transaction item with a different city within 60 minutes is found, mark as invalid
                if (!city.equals(item.city) && Math.abs(time - item.time) <= 60) {
                    invalidIndices.add(i);
                    invalidIndices.add(item.index);
                }
            }
        }

        // Prepare the list of invalid transaction strings to return
        List<String> answer = new ArrayList<>();
        for (int index : invalidIndices) {
            answer.add(transactions[index]);
        }
        return answer;
    }
}
