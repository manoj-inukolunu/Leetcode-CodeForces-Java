package com.leetcode.biweekly.context46;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MaxPeak {


  public int[][] highestPeak(int[][] arr) {
    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visited = new boolean[arr.length][arr[0].length];
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr.length; j++) {
        if (arr[i][j] == 1) {
          queue.add(new int[]{i, j, 0});
        }
      }
    }
    while (!queue.isEmpty()) {
      int[] curr = queue.poll();
      int row = curr[0];
      int col = curr[1];
      int height = curr[2];
      if (!visited[row][col]) {
        arr[row][col] = height;
        visited[row][col] = true;
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : dirs) {
          int nextR = dir[0] + row;
          int nextC = dir[1] + col;
          if (inside(nextR, nextC, arr)) {
            queue.add(new int[]{nextR, nextC, height + 1});
          }
        }
      }
    }
    return arr;
  }

  private boolean inside(int row, int col, int[][] arr) {
    return row >= 0 && col >= 0 && row < arr.length && col < arr[row].length;
  }

  public static void main(String args[]) {
    int[][] arr = new int[][]{
        {0, 1}, {0, 0}
    };
    MaxPeak m = new MaxPeak();
    int[][] res = m.highestPeak(arr);
    for (int[] row : res) {
      System.out.println(Arrays.toString(row));
    }
  }
}
