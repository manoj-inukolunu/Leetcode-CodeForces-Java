package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author manoji on 7/18/20.
 */
public class DiagonalTraverse {

	/*

			if (corner(row, col, matrix)) {
				if (topLeft(row, col)) {
					col++;
					direction = !direction;
				} else if (topRight(row, col, matrix)) {
					row++;
					direction = !direction;
				} else if (bottomRight(row, col, matrix)) {

				}
			}
	 */

  //[1,2,4,7,5,3,4,6,8,9,7,10]
  public int[] findDiagonalOrder(int[][] matrix) {
    boolean direction = true;
    int row = 0, col = 0, rows = matrix.length, cols = matrix[0].length;
    List<Integer> list = new ArrayList<>();
    while (true) {
      list.add(matrix[row][col]);
      if (row == rows - 1 && col == cols - 1) {
        break;
      }
      if (direction) {
        if (up(row) && right(col, cols)) {
          row--;
          col++;
        } else if (!up(row) && right(col, cols)) {
          col++;
          direction = !direction;
        } else if (!up(row) && !right(col, cols)) {
          row++;
          direction = !direction;
        } else if (up(row) && !right(col, cols)) {
          row++;
          direction = !direction;
        }
      } else {
        if (down(row, rows) && left(col)) {
          row++;
          col--;
        } else if (!left(col) && down(row, rows)) {
          row++;
          direction = !direction;
        } else if (!down(row, rows) && right(col, cols)) {
          col++;
          direction = !direction;
        } else if (!down(row, rows) && !right(col, cols)) {
          direction = !direction;

        }
      }
    }
    return list.stream().mapToInt(value -> value).toArray();
  }

  private boolean up(int row) {
    return row - 1 >= 0;
  }

  private boolean down(int row, int rows) {
    return row + 1 < rows;
  }

  private boolean right(int col, int cols) {
    return col + 1 < cols;
  }

  private boolean left(int col) {
    return col - 1 >= 0;
  }

  public static void main(String args[]) {
    DiagonalTraverse d = new DiagonalTraverse();

    int[][] arr = new int[][]{
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };

    System.out.println(Arrays.toString(d.findDiagonalOrder(arr)));
  }

}
