package com.adp.companies.bloomberg;

import java.util.HashSet;
import java.util.Set;

public class _02_WordSearch {

    record Cell(int row, int column) {}
    private final int[][] directions = new int[][] {
            {-1, 0}, {1, 0}, {0, 1}, {0, -1}
    };

    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int columns = board[0].length;

        Set<Cell> visited = new HashSet<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (board[r][c] == word.charAt(0) && dfs(r, c, board, 0, visited, word))
                    return true;
            }
        }

        return false;
    }

    private boolean dfs(int row, int column, char[][] board, int index, Set<Cell> visited, String word) {
        if (index == word.length())
            return true;
        Cell currentCell = new Cell(row, column);
        if (row < 0 || row >= board.length || column < 0 || column >= board[0].length || visited.contains(currentCell))
            return false;
        if (board[row][column] != word.charAt(index))
            return false;
        visited.add(currentCell);
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = column + dir[1];
            if (dfs(newRow, newCol, board, index + 1, visited, word)) {
                return true;
            }
        }
        visited.remove(currentCell);
        return false;
    }
}
