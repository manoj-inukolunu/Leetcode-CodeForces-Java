package com.leetcode.random10.sixmonths.medium;

import java.util.Arrays;

public class TicTacToe {

  int[][] arr;

  /**
   * Initialize your data structure here.
   */
  public TicTacToe(int n) {
    arr = new int[n][n];
    for (int[] row : arr) {
      Arrays.fill(row, -1);
    }
  }

  /**
   * Player {player} makes a move at ({row}, {col}).
   *
   * @param row The row of the board.
   * @param col The column of the board.
   * @param player The player, can be either 1 or 2.
   * @return The current winning condition, can be either: 0: No one wins. 1: Player 1 wins. 2: Player 2 wins.
   */
  public int move(int row, int col, int player) {
    arr[row][col] = player;
    int won = check(arr);
    if (won == 0) {
      int[] ret = checkDiag(arr);
      if (ret[0] != -1) {
        return ret[1];
      }
    }
    return won;
  }


  private int check(int[][] arr) {
    int found = 0;
    boolean won;
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr.length; j++) {
        if (arr[i][j] == 1) {
          won = dfs(arr, i, j, 1);
          if (won) {
            found = 1;
            break;
          }
        } else if (arr[i][j] == 2) {
          won = dfs(arr, i, j, 2);
          if (won) {
            found = 2;
            break;
          }
        }
      }
    }
    return found;
  }

  private boolean dfs(int[][] arr, int row, int col, int player) {
    boolean wonRow = true, wonCol = true;
    for (int i = 0; i < arr[row].length; i++) {
      if (arr[row][i] != player) {
        wonRow = false;
        break;
      }
    }
    for (int i = 0; i < arr.length; i++) {
      if (arr[i][col] != player) {
        wonCol = false;
        break;
      }
    }

    return wonRow || wonCol;
  }

  private int[] checkDiag(int[][] arr) {
    int col = 0;
    int player = arr[0][0];
    int[] ret = new int[]{-1, -1};
    boolean diag1 = true, diag2 = true;
    if (player != -1) {
      for (int i = 0; i < arr.length; i++) {
        if (arr[i][col++] != player) {
          diag1 = false;
          break;
        }
      }
      if (diag1) {
        ret[0] = 1;
        ret[1] = player;
        return ret;
      }
    }
    col = 0;
    player = arr[arr.length - 1][0];
    if (player != -1) {
      for (int i = arr.length - 1; i >= 0; i--) {
        if (arr[i][col++] != player) {
          diag2 = false;
          break;
        }
      }
      if (diag2) {
        ret[0] = 1;
        ret[1] = player;
        return ret;
      }
    }
    return ret;
  }

  public static void main(String args[]) {
    TicTacToe t = new TicTacToe(3);

   /* ["TicTacToe", "move", "move", "move", "move", "move", "move", "move", "move"]
    [[3],[2, 0, 1],[0, 0, 2],[0, 1, 1],[0, 2, 2],[2, 1, 1],[1, 1, 2],[1, 2, 1],[2, 2, 2]]*/
    System.out.println(t.move(1, 2, 2));
    System.out.println(t.move(0, 2, 1));

    System.out.println(t.move(0, 0, 2));
    System.out.println(t.move(2, 0, 1));

    System.out.println(t.move(0, 1, 2));

    System.out.println(t.move(1, 1, 1));

    for (int[] row : t.arr) {
      System.out.println(Arrays.toString(row));
    }
  }
}

