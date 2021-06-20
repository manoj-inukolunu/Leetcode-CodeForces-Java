package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinesWiper {

  int mineRow = -1, mineCol = -1;

  public char[][] updateBoard(char[][] board, int[] click) {
    int row = click[0], col = click[1];
    dfs(row, col, board);
    return board;
  }

  private boolean inside(int row, int col, char[][] board) {
    return row >= 0 && col >= 0 && row < board.length && col < board[row].length;
  }

  private List<Integer[]> nextS(int row, int col) {
    List<Integer[]> list = new ArrayList();
    list.add(new Integer[]{row + 1, col});
    list.add(new Integer[]{row + 1, col + 1});
    list.add(new Integer[]{row + 1, col - 1});

    list.add(new Integer[]{row - 1, col});
    list.add(new Integer[]{row - 1, col + 1});
    list.add(new Integer[]{row - 1, col - 1});

    list.add(new Integer[]{row, col - 1});
    list.add(new Integer[]{row, col + 1});

    return list;
  }

  private void dfs(int row, int col, char[][] board) {
    if (board[row][col] == 'E') {
      boolean updated = false;
      int currM = 0;

      for (Integer[] next : nextS(row, col)) {
        if (inside(next[0], next[1], board) && board[next[0]][next[1]] == 'M') {
          updated = true;
          mineRow = next[0];
          mineCol = next[1];
          currM++;
        }
      }
      if (updated) {
        board[row][col] = Character.forDigit(currM, 10);
      } else {
        board[row][col] = 'B';
        for (Integer[] next : nextS(row, col)) {
          if (inside(next[0], next[1], board)) {
            dfs(next[0], next[1], board);
          }
        }
      }
    } else if (board[row][col] == 'M') {
      board[row][col] = 'X';
    }
  }

  public static void main(String args[]) {
    char[][] board = new char[][]{{'B', '1', 'E', '1', 'B'},
        {'B', '1', 'M', '1', 'B'},
        {'B', '1', '1', '1', 'B'},
        {'B', 'B', 'B', 'B', 'B'}};

    MinesWiper m = new MinesWiper();
    board = m.updateBoard(board, new int[]{1, 2});

    for (char[] row : board) {
      System.out.println(Arrays.toString(row));
    }

  }

}

