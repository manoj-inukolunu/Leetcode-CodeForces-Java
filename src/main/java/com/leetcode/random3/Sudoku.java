package com.leetcode.random3;

public class Sudoku {


  boolean solved = false;

  public void solveSudoku(char[][] board) {
    int curr = 0;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (Character.isDigit(board[i][j])) {
          curr++;
        }
      }
    }
    solve(board, curr, 0);

  }

  private void solve(char[][] board, int total, int row) {
    if (total == 81) {
      solved = true;
      return;
    }
    if (!solved) {
      for (int col = 0; col < board[row].length; col++) {
        if (board[row][col] == '.') {
          for (int i = 1; i <= 9; i++) {
            if (canAdd(i, board, row, col)) {
              board[row][col] = Character.forDigit(i, 10);
              if (filled(row, board)) {
                solve(board, total + 1, row + 1);
              } else {
                solve(board, total + 1, row);
              }
              if (!solved) {
                board[row][col] = '.';
              }
            } else {
              return;
            }
          }
        }
      }
    }
  }

  private boolean filled(int row, char[][] board) {
    for (int i = 0; i < board[row].length; i++) {
      if (board[row][i] == '.') {
        return false;
      }
    }
    return true;
  }

  private boolean canAdd(int num, char[][] board, int row, int col) {
    //cols
    for (int i = 0; i < board[row].length; i++) {
      if (Character.isDigit(board[row][i]) && Character.getNumericValue(board[row][i]) == num) {
        return false;
      }
    }
    //rows
    for (int i = 0; i < board.length; i++) {
      if (Character.isDigit(board[i][col]) && Character.getNumericValue(board[i][col]) == num) {
        return false;
      }
    }
    //center
    for (int i = 3 * (row / 3); i < 3 * (row / 3) + 3; i++) {
      for (int j = 3 * (col / 3); j < 3 * (col / 3) + 3; j++) {
        if (Character.isDigit(board[i][col]) && Character.getNumericValue(board[i][col]) == num) {
          return false;
        }
      }
    }
    return true;
  }


  public static void main(String args[]) {
    char[][] board = new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
        {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
        {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
    Sudoku s = new Sudoku();
    s.solveSudoku(board);

  }

}
