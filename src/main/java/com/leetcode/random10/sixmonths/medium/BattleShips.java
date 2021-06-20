package com.leetcode.random10.sixmonths.medium;

/**
 * @author manoji on 7/16/20.
 */
public class BattleShips {

  public int countBattleships(char[][] board) {
    int count = 0;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (i - 1 >= 0 && board[i - 1][j] != 'X' && board[i][j] == 'X' && j - 1 >= 0 && board[i][j - 1] != 'X' && board[i][j] == 'X') {
          count++;
        }
        if (i == 0 && j == 0 && board[i][j] == 'X') {
          count++;
        }

        if (i == 0) {
          if (j - 1 >= 0 && board[i][j - 1] != 'X' && board[i][j] == 'X') {
            count++;
          }
        }

        if (j == 0) {
          if (i - 1 >= 0 && board[i - 1][j] != 'X' && board[i][j] == 'X') {
            count++;
          }
        }
      }
    }
    return count;
  }

  public static void main(String args[]) {
    BattleShips b = new BattleShips();
    char[][] board = new char[][]{
        {'X', '.', 'X'},
        {'.', '.', 'X'},
        {'.', '.', 'X'}
    };

    System.out.println(b.countBattleships(board));
  }

}
