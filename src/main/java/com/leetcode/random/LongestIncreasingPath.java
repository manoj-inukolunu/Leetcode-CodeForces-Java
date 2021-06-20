package com.leetcode.random;

public class LongestIncreasingPath {


  int max = Integer.MIN_VALUE;

  private boolean inside(int row, int col, int[][] matrix) {
    return row >= 0 && col >= 0 && row < matrix.length && col < matrix[row].length;
  }

  public int longestIncreasingPath(int[][] matrix) {
    int[][] memo = new int[matrix.length][matrix[0].length];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        if (memo[i][j] == 0) {
          int val = dfs(matrix, memo, i, j);
          memo[i][j] = val;
          max = Math.max(max, memo[i][j]);
        }
      }
    }
    return max;
  }

  private int dfs(int[][] matrix, int[][] memo, int row, int col) {
    if (memo[row][col] != 0) {
      return memo[row][col];
    }
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int longest = 1;
    for (int[] dir : dirs) {
      int nextR = row + dir[0];
      int nextC = col + dir[1];
      if (inside(nextR, nextC, matrix) && matrix[nextR][nextC] > matrix[row][col]) {
        longest = Math.max(longest, 1 + dfs(matrix, memo, nextR, nextC));
      }
    }
    max = Math.max(max, longest);
    memo[row][col] = Math.max(memo[row][col], longest);
    return longest;
  }

  public static void main(String args[]) {
    int[][] nums = new int[][]{
        {3, 4, 5},
        {6, 6, 8},
        {2, 1, 1}
    };
    LongestIncreasingPath l = new LongestIncreasingPath();
    System.out.println(l.longestIncreasingPath(nums));
  }
}
