package com.adp.companies.bloomberg;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
TreeMap keeps scores sorted, allowing efficient descending traversal.

top(K) runs in O(K log S) where S is the number of distinct scores (often smaller than number of players).

addScore and reset maintain the integrity of both maps in O(log S) time.

The intuition behind using TreeMap is that it takes advantage of the fact that scores need to be sorted for the top(K) method.
Although it incurs some cost when adding and resetting scores due to re-sorting,
it is balanced out by the efficient retrieval of the top K scores, which can be expected to be a frequent operation in this context.
 */
public class _03_Leaderboard_TreeMap {

    //provides default values for missing keys and is used here to store player scores. It maps player IDs to their scores.
    private Map<Integer, Integer> playerScores = new HashMap<>();
    //maintains a sorted list of scores. This allows efficient insertion, removal, and access to the largest scores in order to compute the sum of the top K scores.
    private TreeMap<Integer, Integer> sortedScores;

    public _03_Leaderboard_TreeMap() {
        playerScores = new HashMap<>();
        // A TreeMap to maintain sorted scores (in descending order) along with their frequency.
        sortedScores = new TreeMap<>((a, b) -> b - a);
    }

    /*
    For the addScore method, we first check if the player ID is already in the dictionary. If it isn't, we add the player ID and the score to the dictionary
    and the score to the TreeMap. If the player ID exists, we remove the old score, update the score in the dictionary, and re-insert the new score into the
    TreeMap.
     */
    public void addScore(int playerId, int score) {
        // Merge the new score into the existing one or put if absent, then get the updated score
        playerScores.merge(playerId, score, Integer::sum);
        int updatedScore = playerScores.get(playerId);

        // If the score is an update (not the first score), reduce the frequency of the old score
        if (updatedScore != score) {
            sortedScores.merge(updatedScore - score, -1, Integer::sum);
        }

        // Add or update the frequency of the new score
        sortedScores.merge(updatedScore, 1, Integer::sum);
    }

    /*
    we can directly access the last K elements (which are the largest due to the sorted property of the list) and return their sum.
     */
    public int top(int K) {
        int sum = 0;

        // Iterate over the scores in descending order
        for (var entry : sortedScores.entrySet()) {
            int score = entry.getKey();
            int count = entry.getValue();

            // Take only as many scores as needed to fulfill the quantity K
            count = Math.min(count, K);
            sum += score * count;
            K -= count;

            // If the top K scores are accumulated, we can break early
            if (K == 0) {
                break;
            }
        }
        return sum;
    }

    //removes the player's score by popping the player ID from the dictionary and also removing the associated score from the TreeMap.
    public void reset(int playerId) {
        // Remove the player's score
        int score = playerScores.remove(playerId);

        // Decrement the frequency of the player's score and if it reaches zero, remove it from sortedScores
        if (sortedScores.merge(score, -1, Integer::sum) == 0) {
            sortedScores.remove(score);
        }
    }
}

/*
Example Scenario
We'll simulate this sequence of method calls:

java
Copy
Edit
Leaderboard.addScore(1, 50);
Leaderboard.addScore(2, 80);
Leaderboard.addScore(3, 50);
Leaderboard.addScore(2, 20);  // Player 2 now has 100
Leaderboard.reset(3);
We'll track both playerScores and sortedScores.

📌 Initial State:
java
Copy
Edit
playerScores = {}
sortedScores = {}
1. addScore(1, 50)
playerScores: {1 → 50}

sortedScores: {50 → 1}

2. addScore(2, 80)
playerScores: {1 → 50, 2 → 80}

sortedScores: {80 → 1, 50 → 1} (TreeMap keeps descending order)

3. addScore(3, 50)
Player 3 also scores 50.

playerScores: {1 → 50, 2 → 80, 3 → 50}

sortedScores: {80 → 1, 50 → 2}

4. addScore(2, 20)
(Player 2's score becomes 80 + 20 = 100)

First, decrease frequency of old score (80 → 0), so remove it.

Then add 100.

java
Copy
Edit
playerScores = {1 → 50, 2 → 100, 3 → 50}
sortedScores = {100 → 1, 50 → 2}
5. reset(3)
Remove player 3 (score 50).

Decrease 50’s frequency from 2 → 1.

java
Copy
Edit
playerScores = {1 → 50, 2 → 100}
sortedScores = {100 → 1, 50 → 1}
🧠 Final State Recap
playerScores (Map of player → score):
java
Copy
Edit
{
  1 → 50,
  2 → 100
}
sortedScores (TreeMap of score → number of players with that score):
java
Copy
Edit
{
  100 → 1,
   50 → 1
}
(In descending order thanks to the custom comparator)


Assume sortedScores looks like this:

java
Copy
Edit
{
  100 → 2,
   90 → 1,
   80 → 3
}
That means:

Two players have 100

One player has 90

Three players have 80

Now let's say you call top(3). Here's what happens:

1. First entry: 100 → 2
You need 3 scores; 2 of them are 100.

So: sum += 100 * 2 = 200

Remaining K = 3 - 2 = 1

2. Second entry: 90 → 1
You need 1 more score; one player has 90.

So: sum += 90 * 1 = 90

Now K = 0 → done!

Final Sum: 200 + 90 = 290
💡 Why This Is Efficient
Thanks to the TreeMap being in descending order, the method always starts with the highest scores.

It stops early once it reaches K scores — no need to iterate the whole map.

Time complexity is O(K) in the best case, and O(S) in the worst case, where S is the number of distinct scores.
 */
