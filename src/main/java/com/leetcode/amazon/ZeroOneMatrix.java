package com.leetcode.amazon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class ZeroOneMatrix {

  class Pair {

    int row;
    int col;
    int dist;

    public Pair(int row, int col, int dist) {
      this.row = row;
      this.col = col;
      this.dist = dist;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Pair pair = (Pair) o;
      return row == pair.row &&
          col == pair.col;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, col);
    }
  }

  private boolean inside(int row, int col, int[][] arr) {
    return row >= 0 && col >= 0 && row < arr.length && col < arr[row].length;
  }

  public int[][] updateMatrix(int[][] matrix) {
    Queue<Pair> queue = new LinkedList<>();
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        if (matrix[i][j] == 0) {
          queue.add(new Pair(i, j, 0));
        }
      }
    }

    HashSet<Pair> visited = new HashSet<>();
    while (!queue.isEmpty()) {
      Pair curr = queue.poll();
      if (matrix[curr.row][curr.col] == 1 && !visited.contains(curr)) {
        matrix[curr.row][curr.col] = curr.dist;
      }
      if (!visited.contains(curr)) {
        visited.add(curr);
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        for (int[] dir : dirs) {
          int row = curr.row + dir[0];
          int col = curr.col + dir[1];
          if (inside(row, col, matrix)) {
            queue.add(new Pair(row, col, curr.dist + 1));
          }
        }
      }
    }
    return matrix;
  }

  public static void main(String args[]) {
    ZeroOneMatrix z = new ZeroOneMatrix();
    int[][] arr = new int[][]{
        {0, 0, 0},
        {0, 1, 0},
        {1, 1, 1}
    };
    arr = z.updateMatrix(arr);
    for (int[] row : arr) {
      System.out.println(Arrays.toString(row));
    }
  }

}
