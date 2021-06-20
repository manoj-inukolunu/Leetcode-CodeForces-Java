package com.leetcode.s60day;

import java.util.Arrays;

public class ZigZag {

  public String convert(String s, int numRows) {
    char[][] arr = new char[4][s.length()];
    for (char[] row : arr) {
      Arrays.fill(row, ' ');
    }
    int dir = 0, row = 0, col = 0, idx = 0;
    while (idx < s.length()) {
      if (dir == 0) {
        arr[row][col] = s.charAt(idx);
        row++;
      } else {
        arr[row][col] = s.charAt(idx);
        row--;
        col++;
      }
      if (row == numRows - 1) {
        dir = 1;
        row--;
        col++;
      } else if (row == 0) {
        dir = 0;
      }
      idx++;
    }
    StringBuffer buff = new StringBuffer();
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        if (arr[i][j] != ' ') {
          buff.append(arr[i][j]);
        }
      }
    }
    return buff.toString().trim();
  }

}
