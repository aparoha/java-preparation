package com.adp.dsa.datastructures.graph.implementation;

/**
 * A class representing a graph using an adjacency matrix.
 * This class supports both directed and undirected graphs, as well as weighted edges.
 */
public class GraphAdjMatrixRepresentation {
    private int[][] adjMatrix;
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
    public GraphAdjMatrixRepresentation(int vertices, boolean isDirected, boolean isWeighted) {
        this.numVertices = vertices;
        this.isDirected = isDirected;
        this.isWeighted = isWeighted;
        this.adjMatrix = new int[vertices][vertices]; // Initialize adjacency matrix
    }

    /**
     * Adds an edge to the graph.
     * If the graph is weighted, the weight is specified.
     *
     * @param start  The starting vertex of the edge
     * @param end    The ending vertex of the edge
     * @param weight The weight of the edge (ignored if isWeighted is false)
     */
    public void addEdge(int start, int end, int weight) {
        validateVertex(start);
        validateVertex(end);

        if (isWeighted) {
            adjMatrix[start][end] = weight;
        } else {
            adjMatrix[start][end] = 1; // Mark the edge with a value of 1
        }

        if (!isDirected) {
            adjMatrix[end][start] = isWeighted ? weight : 1; // Undirected: add reverse edge
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

        adjMatrix[start][end] = 0;
        if (!isDirected) {
            adjMatrix[end][start] = 0; // Remove reverse edge in an undirected graph
        }
    }

    /**
     * Prints the graph's adjacency matrix.
     */
    public void printGraph() {
        System.out.println("Adjacency Matrix:");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(adjMatrix[i][j] + " ");
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
        return adjMatrix[start][end] != 0;
    }

    /**
     * Gets the weight of the edge between two vertices (for weighted graphs).
     *
     * @param start The starting vertex
     * @param end   The ending vertex
     * @return The weight of the edge (or 0 if no edge exists)
     */
    public int getEdgeWeight(int start, int end) {
        validateVertex(start);
        validateVertex(end);
        return adjMatrix[start][end];
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
}
