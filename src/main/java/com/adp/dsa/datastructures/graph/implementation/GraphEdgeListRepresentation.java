package com.adp.dsa.datastructures.graph.implementation;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a graph using an edge list.
 * This class supports both directed and undirected graphs, as well as weighted edges.
 */
public class GraphEdgeListRepresentation {

    private List<Edge> edges;
    private boolean isDirected;
    private boolean isWeighted;

    /**
     * Constructor to initialize the graph.
     *
     * @param isDirected Flag to indicate if the graph is directed
     * @param isWeighted Flag to indicate if the graph uses weighted edges
     */
    public GraphEdgeListRepresentation(boolean isDirected, boolean isWeighted) {
        this.isDirected = isDirected;
        this.isWeighted = isWeighted;
        this.edges = new ArrayList<>();
    }

    /**
     * Adds an edge to the graph. This method is used for weighted graphs.
     *
     * @param start  The starting vertex of the edge
     * @param end    The ending vertex of the edge
     * @param weight The weight of the edge
     */
    public void addEdge(int start, int end, double weight) {
        edges.add(new Edge(start, end, weight));
        if (!isDirected) {
            edges.add(new Edge(end, start, weight)); // For undirected graph, add the reverse edge
        }
    }

    /**
     * Adds an edge to the graph. This method is used for unweighted graphs.
     * The edge is simply marked with a value of 1.
     *
     * @param start The starting vertex of the edge
     * @param end   The ending vertex of the edge
     */
    public void addEdge(int start, int end) {
        addEdge(start, end, 1.0); // For unweighted graphs, the weight is implicitly 1
    }

    /**
     * Removes an edge from the graph.
     *
     * @param start The starting vertex of the edge
     * @param end   The ending vertex of the edge
     */
    public void removeEdge(int start, int end) {
        edges.removeIf(edge -> edge.start() == start && edge.end() == end);
        if (!isDirected) {
            edges.removeIf(edge -> edge.start() == end && edge.end() == start); // Undirected: remove reverse edge
        }
    }

    /**
     * Prints the graph's edge list.
     */
    public void printGraph() {
        System.out.println("Edge List:");
        for (Edge edge : edges) {
            System.out.print("(" + edge.start() + ", " + edge.end());
            if (isWeighted) {
                System.out.print(", " + edge.weight());
            }
            System.out.println(")");
        }
    }

    /**
     * Checks if an edge exists between two vertices.
     *
     * @param start The starting vertex of the edge
     * @param end   The ending vertex of the edge
     * @return true if an edge exists, false otherwise
     */
    public boolean hasEdge(int start, int end) {
        return edges
                .stream()
                .anyMatch(edge -> edge.start() == start && edge.end() == end);
    }

    /**
     * Gets the weight of the edge between two vertices (for weighted graphs).
     *
     * @param start The starting vertex
     * @param end   The ending vertex
     * @return The weight of the edge (or -1 if no edge exists)
     */
    public double getEdgeWeight(int start, int end) {
        for (Edge edge : edges) {
            if (edge.start() == start && edge.end() == end) {
                return edge.weight();
            }
        }
        return -1; // If no edge exists, return -1
    }

    /**
     * Inner class to represent an edge.
     */
    private record Edge(int start, int end, double weight) {}
}
