package com.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author manoji on 4/4/20.
 */


public class SpiralMatrix {

  public Integer[][] generateMatrix(int n) {
    Integer[][] matrix = new Integer[n][n];
    String direction = "right";
    int startRow = 0, row = 0;
    int startColumn = 0, column = 0;
    int rows = matrix.length - 1;
    int columns = matrix[0].length - 1;
    int count = 0, total = (rows + 1) * (columns + 1);
    while (true) {
      if (count == total) {
        break;
      }
      if (matrix[row][column] == null) {
        matrix[row][column] = count + 1;
        //matrix[row][column] = Integer.MAX_VALUE;
        count++;
      }
      switch (direction) {
        case "right":
          if (column + 1 <= columns) {
            column++;
          } else {
            direction = "down";
            startRow++;
          }

          break;
        case "left":
          if (column - 1 >= startColumn) {
            column--;
          } else {
            direction = "up";
            rows--;
          }
          break;
        case "down":
          if (row + 1 <= rows) {
            row++;
          } else {
            direction = "left";
            columns--;
          }
          break;
        case "up":
          if (row - 1 >= startRow) {
            row--;
          } else {
            direction = "right";
            startColumn++;
          }
          break;
      }
    }
    return matrix;
  }

  public List<Integer> spiralOrder(int[][] matrix) {
    if (matrix.length == 0) {
      return new ArrayList();
    }
    List<Integer> integers = new ArrayList();
    String direction = "right";
    int startRow = 0, row = 0;
    int startColumn = 0, column = 0;
    int rows = matrix.length - 1;
    int columns = matrix[0].length - 1;
    int count = 0, total = (rows + 1) * (columns + 1);
    while (true) {
      if (count == total) {
        break;
      }
      if (matrix[row][column] != Integer.MAX_VALUE) {
        integers.add(matrix[row][column]);
        matrix[row][column] = Integer.MAX_VALUE;
        count++;
      }
      switch (direction) {
        case "right":
          if (column + 1 <= columns) {
            column++;
          } else {
            direction = "down";
            startRow++;
          }

          break;
        case "left":
          if (column - 1 >= startColumn) {
            column--;
          } else {
            direction = "up";
            rows--;
          }
          break;
        case "down":
          if (row + 1 <= rows) {
            row++;
          } else {
            direction = "left";
            columns--;
          }
          break;
        case "up":
          if (row - 1 >= startRow) {
            row--;
          } else {
            direction = "right";
            startColumn++;
          }
          break;
      }

    }
    return integers;
  }

  public static void main(String args[]) {
    int[][] arr = new int[][]{
        {1, 2}
    };

    SpiralMatrix spiralMatrix = new SpiralMatrix();
//		System.out.println(spiralMatrix.spiralOrder(arr));

    Integer[][] matrix = spiralMatrix.generateMatrix(5);

    for (Integer[] row : matrix) {
      System.out.println(Arrays.toString(row));
    }

  }
}
