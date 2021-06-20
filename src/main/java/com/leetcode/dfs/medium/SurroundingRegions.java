package com.leetcode.dfs.medium;

import java.util.Arrays;

/**
 * @author manoji on 5/2/20.
 */
public class SurroundingRegions {

  public void solve(char[][] board) {

    int[] rows = new int[]{0, board.length - 1};
    int[] cols = new int[]{0, board[0].length - 1};

    for (int i : rows) {
      for (int col = 0; col < board[i].length; col++) {
        if (board[i][col] == 'O') {
          visit(board, i, col);
        }
      }
    }

    for (int i : cols) {
      for (int row = 0; row < board.length; row++) {
        if (board[row][i] == 'O') {
          visit(board, row, i);
        }
      }
    }

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == 'O') {
          board[i][j] = 'X';
        }
      }
    }

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == '1') {
          board[i][j] = 'O';
        }
      }
    }
  }

  private void visit(char[][] board, int row, int col) {
    if (row < 0 || row >= board.length || col < 0 || col >= board[row].length) {
      return;
    }

    board[row][col] = '1';
    if (row + 1 < board.length && board[row + 1][col] == 'O') {
      visit(board, row + 1, col);
    }

    if (row - 1 >= 0 && board[row - 1][col] == 'O') {
      visit(board, row - 1, col);
    }

    if (col + 1 < board[row].length && board[row][col + 1] == 'O') {
      visit(board, row, col + 1);
    }

    if (col - 1 >= 0 && board[row][col - 1] == 'O') {
      visit(board, row, col - 1);
    }
  }


  public static void main(String args[]) {
    char[][] board = new char[][]{
        {'X', 'X', 'X', 'X'},
        {'X', 'O', 'O', 'X'},
        {'X', 'X', 'O', 'X'},
        {'X', 'O', 'X', 'X'},

    };

    SurroundingRegions s = new SurroundingRegions();

    s.solve(board);

    for (char[] character : board) {
      System.out.println(Arrays.toString(character));
    }
  }
}
