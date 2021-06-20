package com.leetcode.array;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortMatrixDiag {

  private boolean inside(int row, int col, int[][] mat) {
    return row >= 0 && col >= 0 && row < mat.length && col < mat[row].length;
  }

  public int[][] diagonalSort(int[][] mat) {
    for (int col = 0; col < mat[0].length; col++) {
      List<Integer> list = new ArrayList<>();
      int row = 0, j = col, idx = 0;
      while (inside(row, j, mat)) {
        list.add(mat[row][j]);
        j++;
        row++;
      }
      Collections.sort(list);
      row = 0;
      j = col;
      while (inside(row, j, mat)) {
        mat[row][j] = list.get(idx++);
        row++;
        j++;
      }
    }
    for (int row = 1; row < mat.length; row++) {
      List<Integer> list = new ArrayList<>();
      int col = 0, j = row, idx = 0;
      while (inside(j, col, mat)) {
        list.add(mat[j][col]);
        j++;
        col++;
      }
      Collections.sort(list);
      col = 0;
      j = row;
      while (inside(j, col, mat)) {
        mat[j][col] = list.get(idx++);
        col++;
        j++;
      }
    }
    return mat;
  }

  public static void main(String args[]) {
    SortMatrixDiag s = new SortMatrixDiag();
    int[][] arr = new int[][]{
        {3, 3, 1, 1},
        {2, 2, 1, 2},
        {1, 1, 1, 2}
    };
    arr = s.diagonalSort(arr);
    for (int[] row : arr) {
      System.out.println(Arrays.toString(row));
    }
  }

}
