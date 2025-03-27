package com.adp.dsa.datastructures.graph.traversal;

import com.adp.dsa.datastructures.graph.implementation.GraphAdjListRepresentation;

import java.util.*;

public class BFS {

    public static List<Integer> performBfs(GraphAdjListRepresentation graph, int start) {
        // BFS order
        List<Integer> result = new ArrayList<>();
        // Visited hashset
        Set<Integer> visited = new HashSet<>();
        // Create a queue to explore vertices level by level
        Queue<Integer> queue = new LinkedList<>();
        // Start with the source vertex
        visited.add(start);
        queue.add(start);
        // Traverse the graph
        while (!queue.isEmpty()) {
            // Dequeue vertex from the queue
            int current = queue.poll();
            result.add(current);

            // Get all adjacent vertices
            List<Integer> neighbors = graph.getNeighbors(current);

            // Enqueue all unvisited adjacent vertices
            for (int neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return result;
    }

    // Main method for testing BFS
    public static void main(String[] args) {
        // Create a graph using Adjacency List representation
        GraphAdjListRepresentation graph = new GraphAdjListRepresentation(7, false, false);

        // Add edges to the graph (undirected for this example)
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 6);

        // Perform BFS starting from vertex 1
        System.out.println(performBfs(graph, 0));
    }
}
