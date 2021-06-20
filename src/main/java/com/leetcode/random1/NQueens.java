package com.leetcode.random1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class NQueens {


  List<List<String>> ans = new ArrayList<>();

  public List<List<String>> solveNQueens(int n) {
    char[][] arr = new char[n][n];
    for (char[] row : arr) {
      Arrays.fill(row, '.');
    }
    dfs(arr, 0, new HashSet<>(), new HashSet<>());
    return ans;
  }

  private void dfs(char[][] arr, int row, HashSet<Integer> rows, HashSet<Integer> cols) {
    if (row == arr.length) {
      ans.add(build(arr));
      return;
    }
    for (int col = 0; col < arr[row].length; col++) {
      if (!rows.contains(row) && !cols.contains(col) && valid(arr, row, col)) {
        arr[row][col] = 'Q';
        dfs(arr, row + 1, rows, cols);
        arr[row][col] = '.';
      }
    }
  }

  private boolean valid(char[][] arr, int row, int col) {
    for (int i = 0; i < row; i++) {
      if (arr[i][col] == 'Q') {
        return false;
      }
    }
    for (int i = row - 1, j = col + 1; i >= 0 && j < arr.length; i--, j++) {
      if (arr[i][j] == 'Q') {
        return false;
      }
    }
    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
      if (arr[i][j] == 'Q') {
        return false;
      }
    }
    return true;
  }

  private List<String> build(char[][] arr) {
    List<String> res = new ArrayList<>();
    for (char[] ch : arr) {
      res.add(new String(ch));
    }
    return res;
  }

  public static void main(String args[]) {
    NQueens n = new NQueens();
    System.out.println(n.solveNQueens(4));
  }

}
