package com.leetcode.random3;

import java.util.Arrays;

public class NQueens2 {

  int count = 0;

  public int totalNQueens(int n) {
    char[][] board = new char[n][n];
    for (char[] row : board) {
      Arrays.fill(row, '.');
    }
    dfs(board, 0);

    return count;
  }

  private void dfs(char[][] board, int row) {
    if (row >= board.length) {
      count++;
      return;
    }
    for (int col = 0; col < board[row].length; col++) {
      if (valid(board, row, col)) {
        board[row][col] = 'Q';
        dfs(board, row + 1);
        board[row][col] = '.';
      }
    }
  }

  private boolean valid(char[][] board, int i, int j) {
    for (int row = 0; row < i; row++) {
      if (board[row][j] == 'Q') {
        return false;
      }
    }
    for (int col = j, row = i; col >= 0 && row >= 0; col--, row--) {
      if (board[row][col] == 'Q') {
        return false;
      }
    }
    for (int col = j, row = i; row >= 0 && col < board[row].length; col++, row--) {
      if (board[row][col] == 'Q') {
        return false;
      }
    }
    return true;
  }

  public static void main(String args[]) {
    NQueens2 n = new NQueens2();
    System.out.println(n.totalNQueens(12));
  }

}
