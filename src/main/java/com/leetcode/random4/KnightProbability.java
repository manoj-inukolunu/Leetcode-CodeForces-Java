package com.leetcode.random4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KnightProbability {

  public double knightProbability(int N, int K, int r, int c) {
    double[][] curr = new double[N][N];
    double[][] next = new double[N][N];
    curr[r][c] = 1;
    int[][] dirs = new int[][]{
        {-1, 2}, {-2, 1}, {1, 2}, {2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}
    };
    for (int moves = 1; moves <= K; moves++) {
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (curr[i][j] != 0) {
            for (int[] dir : dirs) {
              int nextR = dir[0] + i;
              int nextC = dir[1] + j;
              if (inside(nextR, nextC, curr)) {
                next[nextR][nextC] += (curr[i][j] / 8);
              }
            }
          }
        }
      }
      curr = next;
      next = new double[N][N];
    }
    double ans = 0;
    for (int i = 0; i < curr.length; i++) {
      for (int j = 0; j < curr[i].length; j++) {
        ans += curr[i][j];
      }
    }
    return ans;
  }

  private boolean inside(int row, int col, double[][] arr) {
    return row >= 0 && col >= 0 && row < arr.length && col < arr[row].length;
  }

  private void dfs(double[][] arr, int k, int row, int col) {
    if (k == 0) {
      return;
    }
    for (double[] r : arr) {
      System.out.println(Arrays.toString(r));
    }
    System.out.println();
    int[][] dirs = new int[][]{
        {-1, 2}, {-2, 1}, {1, 2}, {2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}
    };
    List<int[]> list = new ArrayList<>();
    for (int[] dir : dirs) {
      int nextR = dir[0] + row;
      int nextC = dir[1] + col;
      if (inside(nextR, nextC, arr)) {
        list.add(new int[]{nextR, nextC});
        if (arr[row][col] == 0) {
          arr[nextR][nextC] = arr[nextR][nextC] + ((double) 1 / 8);
        } else {
          arr[nextR][nextC] = arr[nextR][nextC] + (arr[row][col] / 8);
        }
      }
    }
    for (int[] r : list) {
      dfs(arr, k - 1, r[0], r[1]);
    }
  }


  public static void main(String args[]) {
    KnightProbability k = new KnightProbability();
    System.out.println(k.knightProbability(3, 2, 0, 0));
  }

}
