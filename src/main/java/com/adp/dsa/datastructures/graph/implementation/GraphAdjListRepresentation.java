package com.adp.dsa.datastructures.graph.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class representing a graph using an adjacency list.
 * This class supports both directed and undirected graphs, as well as weighted edges.
 */
public class GraphAdjListRepresentation {

    private Map<Integer, List<Edge>> adjList;
    private int numVertices;
    private boolean isDirected;
    private boolean isWeighted;

    /**
     * Constructor to initialize the graph.
     *
     * @param vertices   Number of vertices in the graph
     * @param isDirected Flag to indicate if the graph is directed
     * @param isWeighted Flag to indicate if the graph uses weighted edges
     */
    public GraphAdjListRepresentation(int vertices, boolean isDirected, boolean isWeighted) {
        this.numVertices = vertices;
        this.isDirected = isDirected;
        this.isWeighted = isWeighted;
        this.adjList = new HashMap<>();

        // Initialize the adjacency list with empty lists for each vertex
        for (int i = 0; i < vertices; i++) {
            adjList.put(i, new ArrayList<>());
        }
    }

    /**
     * Adds an edge to the graph. This method is used for weighted graphs.
     *
     * @param start  The starting vertex of the edge
     * @param end    The ending vertex of the edge
     * @param weight The weight of the edge
     */
    public void addEdge(int start, int end, int weight) {
        validateVertex(start);
        validateVertex(end);

        if (isWeighted) {
            adjList.get(start).add(new Edge(end, weight)); // For weighted graph, add Edge with weight
        } else {
            adjList.get(start).add(new Edge(end, 1)); // For unweighted graph, edge weight is 1
        }

        if (!isDirected) {
            adjList.get(end).add(new Edge(start, isWeighted ? weight : 1)); // Undirected: add reverse edge
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
        addEdge(start, end, 1); // For unweighted graphs, the weight is implicitly 1
    }

    /**
     * Removes an edge from the graph.
     *
     * @param start The starting vertex of the edge
     * @param end   The ending vertex of the edge
     */
    public void removeEdge(int start, int end) {
        validateVertex(start);
        validateVertex(end);

        adjList.get(start).removeIf(edge -> edge.vertex() == end);
        if (!isDirected) {
            adjList.get(end).removeIf(edge -> edge.vertex() == start); // Undirected: remove reverse edge
        }
    }

    /**
     * Prints the graph's adjacency list.
     */
    public void printGraph() {
        System.out.println("Adjacency List:");
        for (Map.Entry<Integer, List<Edge>> entry : adjList.entrySet()) {
            int vertex = entry.getKey();
            List<Edge> edges = entry.getValue();
            System.out.print(vertex + ": ");
            for (Edge edge : edges) {
                System.out.print(edge.vertex() + (isWeighted ? "(" + edge.weight() + ")" : "") + " ");
            }
            System.out.println();
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
        validateVertex(start);
        validateVertex(end);

        return adjList.get(start).stream().anyMatch(edge -> edge.vertex() == end);
    }

    /**
     * Gets the weight of the edge between two vertices (for weighted graphs).
     *
     * @param start The starting vertex
     * @param end   The ending vertex
     * @return The weight of the edge (or -1 if no edge exists)
     */
    public int getEdgeWeight(int start, int end) {
        validateVertex(start);
        validateVertex(end);

        for (Edge edge : adjList.get(start)) {
            if (edge.vertex() == end) {
                return edge.weight();
            }
        }
        return -1; // If no edge exists
    }

    /**
     * Validates that a vertex index is within the bounds of the graph.
     *
     * @param vertex The vertex index to validate
     * @throws IllegalArgumentException if the vertex is out of bounds
     */
    private void validateVertex(int vertex) {
        if (vertex < 0 || vertex >= numVertices) {
            throw new IllegalArgumentException("Vertex " + vertex + " is out of bounds.");
        }
    }

    /**
     * Returns the number of vertices in the graph.
     *
     * @return The number of vertices
     */
    public int getNumVertices() {
        return numVertices;
    }

    public List<Integer> getNeighbors(int vertex) {
        validateVertex(vertex);

        List<Integer> neighbors = new ArrayList<>();
        if (adjList.containsKey(vertex)) {
            for (Edge edge : adjList.get(vertex)) {
                neighbors.add(edge.vertex()); // Get the destination vertex of each edge
            }
        }
        return neighbors;
    }

    // Inner class to represent an edge
    private record Edge(int vertex, int weight) {}
}
