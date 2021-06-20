package com.leetcode.random10.sixmonths.medium;

import java.util.Arrays;

public class CandyCrush {

  private boolean inside(int row, int col, int[][] board) {
    return row >= 0 && col >= 0 && row < board.length && col < board[row].length;
  }

  public int[][] candyCrush(int[][] board) {
    int[][] temp = copy(board);
    while (true) {
      if (!crush(board, temp)) {
        break;
      }
      printArr(temp);
      System.out.println();
      drop(temp);
      printArr(temp);
      System.out.println();
      board = copy(temp);
    }
    return temp;
  }

  private void printArr(int[][] temp) {
    for (int[] row : temp) {
      System.out.println(Arrays.toString(row));
    }
  }

  private int[][] copy(int[][] board) {
    int[][] temp = new int[board.length][board[0].length];

    for (int i = 0; i < board.length; i++) {
      System.arraycopy(board[i], 0, temp[i], 0, board[i].length);
    }
    return temp;
  }

  private void drop(int[][] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        if (arr[i][j] == -1) {
          int row = i, col = j;
          int count = 0;
          while (arr[row][col] == -1) {
            count++;
            if (inside(row + 1, col, arr) && arr[row + 1][col] == -1) {
              row++;
            } else {
              break;
            }
          }

          if (row - count < 0) {
            while (row >= 0) {
              arr[row--][col] = 0;
            }
          } else {
            while (row >= 0 && row - count >= 0) {
              arr[row][col] = arr[row - count][col];
              arr[row - count][col] = 0;
              row--;
            }
          }

        }
      }
    }
  }

  private boolean crush(int[][] board, int[][] temp) {
    boolean shouldBreak = false;
    int rows = board.length, cols = board[0].length;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        int row = i, col = j;
        if (board[i][j] != 0 && j + 1 < cols && j + 2 < cols && board[i][j] == board[i][j + 1] && board[i][j + 1] == board[i][j + 2]) {
          while (inside(row, col, board) && board[row][col] == board[i][j]) {
            shouldBreak = true;
            temp[row][col++] = -1;
          }
        }
        row = i;
        col = j;
        if (board[i][j] != 0 && i + 1 < rows && i + 2 < rows && board[i][j] == board[i + 1][j] && board[i + 1][j] == board[i + 2][j]) {
          while (inside(row, col, board) && board[row][col] == board[i][j]) {
            shouldBreak = true;
            temp[row++][col] = -1;
          }
        }
      }
    }
    return shouldBreak;
  }

  //{{2,5,5,3,5},{5,4,5,2,3},{2,2,4,5,4},{2,4,4,4,5},{5,2,3,3,5}}
  public static void main(String args[]) {

    int[][] arr = new int[][]{{2, 5, 5, 3, 5}, {5, 4, 5, 2, 3}, {2, 2, 4, 5, 4}, {2, 4, 4, 4, 5}, {5, 2, 3, 3, 5}};


   /* for (int[] row : arr) {
      System.out.println(Arrays.toString(row));
    }

    System.out.println();
    arr = new int[][]{
        {0, 0, 0, 0, 0}, {110, 0, 0, 0, 114}, {210, 0, 112, 113, 214}, {310, 0, 5, 213, 314}, {410, 0, 3, 313, 414}, {5, 5, 412, 5, 3},
        {610, 211, 512, 3, 614}, {710, 311, 1, 613, 714}, {810, 411, 5, 713, 1}, {1, 1, 2, 1, 1014}
    };

    for (int[] row : arr) {
      System.out.println(Arrays.toString(row));

    }*/

    CandyCrush c = new CandyCrush();
    c.printArr(arr);
    System.out.println();
    arr = c.candyCrush(arr);
    c.printArr(arr);
  }
}
