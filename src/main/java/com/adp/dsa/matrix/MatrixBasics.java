package com.adp.dsa.matrix;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MatrixBasics {

    private static final int[] dx = {-1, 1, 0, 0};  // Up, Down, Left, Right
    private static final int[] dy = {0, 0, -1, 1};  // Up, Down, Left, Right

    /*
    A common problem is searching for an element in a matrix.
    If the matrix is sorted row-wise and column-wise (like a 2D binary search matrix), you can use an efficient approach:
     */

    private static boolean searchInMatrix(int[][] matrix, int target) {
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                row++;  // Move down
            } else {
                col--;  // Move left
            }
        }
        return false;
    }

    /*
    If you want to get the sum of the diagonal elements in a square matrix, here’s a simple approach:
     */

    private static int sumDiagonal(int[][] matrix) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i][i];  // Primary diagonal
            if (i != matrix.length - i - 1) { // Avoid double-counting the center in odd-sized matrix
                sum += matrix[i][matrix.length - i - 1];  // Secondary diagonal
            }
        }
        return sum;
    }

    private static void dfs(int[][] grid, int x, int y, boolean[][] visited) {
        // Check if the current cell is out of bounds or already visited
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 1 || visited[x][y]) {
            return;
        }

        // Mark the current cell as visited
        visited[x][y] = true;
        System.out.println("Visiting cell: (" + x + ", " + y + ")");

        // Explore neighbors (Up, Down, Left, Right)
        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            dfs(grid, newX, newY, visited);  // Recurse for each neighbor
        }
    }

    public static void bfs(int[][] grid, int startX, int startY) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Create a queue for BFS and a visited array
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];

        // Start from the initial cell (startX, startY)
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            // Print the current cell being visited
            System.out.println("Visiting cell: (" + x + ", " + y + ")");

            // Explore neighbors (Up, Down, Left, Right)
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                // Check if the new cell is within bounds, not visited, and not an obstacle
                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && grid[newX][newY] == 0 && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    queue.add(new int[]{newX, newY});
                }
            }
        }
    }

    // Function to print all paths from (startX, startY) to (endX, endY)
    public static void findAllPaths(
            int[][] grid,
            int startX, int startY,
            int endX, int endY,
            boolean[][] visited,
            List<String> path
    ) {
        // Base case: If the current cell is out of bounds, an obstacle, or already visited, return
        if (startX < 0 || startX >= grid.length || startY < 0 || startY >= grid[0].length || grid[startX][startY] == 1 || visited[startX][startY]) {
            return;
        }

        // If the destination is reached, print the path
        if (startX == endX && startY == endY) {
            path.add("(" + startX + "," + startY + ")");
            System.out.println(path);
            path.remove(path.size() - 1);  // Backtrack
            return;
        }

        // Mark the current cell as visited and add it to the path
        visited[startX][startY] = true;
        path.add("(" + startX + "," + startY + ")");

        // Explore all four directions (up, down, left, right)
        for (int i = 0; i < 4; i++) {
            int newX = startX + dx[i];
            int newY = startY + dy[i];
            findAllPaths(grid, newX, newY, endX, endY, visited, path);
        }

        // Backtrack: Remove the current cell from the path and mark it as unvisited
        visited[startX][startY] = false;
        path.remove(path.size() - 1);
    }
}
