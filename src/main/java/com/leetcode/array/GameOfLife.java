package com.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author manoji on 4/23/20.
 */
public class GameOfLife {

  public void gameOfLife(int[][] board) {

    List<Integer[]> die = new ArrayList();
    List<Integer[]> live = new ArrayList();

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == 1) {
          int count = neighbors(board, i, j);
          if (count < 2) {
            die.add(new Integer[]{i, j});
          } else if (count == 2 || count == 3) {
            live.add(new Integer[]{i, j});
          } else if (count > 3) {
            die.add(new Integer[]{i, j});
          }
        } else {
          int count = neighbors(board, i, j);
          if (count == 3) {
            live.add(new Integer[]{i, j});
          }
        }
      }
    }

    for (Integer[] ints : die) {
      board[ints[0]][ints[1]] = 0;
    }

    for (Integer[] ints : live) {
      board[ints[0]][ints[1]] = 0;
    }

  }

  private int neighbors(int[][] board, int i, int j) {
    int rows = board.length;
    int columns = board[i].length;

    int count = 0;
    if (j + 1 < columns && board[i][j + 1] == 1) {
      count++;
    }

    if (j - 1 >= 0 && board[i][j - 1] == 1) {
      count++;
    }

    if (i + 1 < rows && board[i + 1][j] == 1) {
      count++;
    }

    if (i - 1 >= 0 && board[i - 1][j - 1] == 1) {
      count++;
    }

    if (i - 1 >= 0 && j - 1 >= 0 && board[i - 1][j - 1] == 1) {
      count++;
    }

    if (i - 1 >= 0 && j + 1 < columns && board[i - 1][j + 1] == 1) {
      count++;
    }

    if (i + 1 < rows && j + 1 < columns && board[i + 1][j + 1] == 1) {
      count++;
    }

    if (i + 1 < rows && j - 1 >= 0 && board[i + 1][j + 1] == 1) {
      count++;
    }

    return count;
  }

}
