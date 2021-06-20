package com.leetcode.random10.sixmonths.medium;

import java.util.Arrays;

/**
 * @author manoji on 7/16/20.
 */
public class ZigZagConversion {

  public String convert(String s, int numRows) {
    if (numRows == 1) {
      return s;
    }
    char[][] arr = new char[numRows][100];
    for (char[] row : arr) {
      Arrays.fill(row, ' ');
    }

    int row = 0, col = 0;

    int direction = 1;
    for (int i = 0; i < s.length(); i++) {
      if (direction == 1) {
        arr[row++][col] = s.charAt(i);
      } else if (direction == 0) {
        arr[row--][col++] = s.charAt(i);
      }

      if (row == numRows - 1 && direction == 1) {
        direction = 0;
      }
      if (row == 0 && direction == 0) {
        direction = 1;
      }
    }

    StringBuffer buffer = new StringBuffer();

    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        if (arr[i][j] != ' ') {
          buffer.append(arr[i][j]);
        }
      }
    }

    return buffer.toString();
  }

  private void printArr(char[][] arr) {
    for (char[] row : arr) {
      System.out.println(Arrays.toString(row));
    }
    System.out.println();
  }

  public static void main(String args[]) {
    ZigZagConversion z = new ZigZagConversion();
    System.out.println(z.convert("AB", 1));
  }

}
