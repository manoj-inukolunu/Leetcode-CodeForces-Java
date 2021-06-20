package com.leetcode.random;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class PathWithMaxMin {

  public int maximumMinimumPath(int[][] A) {
    int low = 0;
    int high = Math.min(A[0][0], A[A.length - 1][A[0].length - 1]);
    int ans = 0;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (exists(A, mid)) {
        ans = Math.max(ans, mid);
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    int check = Math.min(A[0][0], A[A.length - 1][A[0].length - 1]);
    if (exists(A, check)) {
      ans = Math.max(ans, check);
    }
    return ans;
  }

  boolean inside(int row, int col, int[][] grid) {
    return row >= 0 && col >= 0 && row < grid.length && col < grid[0].length;
  }

  private boolean exists(int[][] arr, int mid) {
    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[]{0, 0});
    boolean[][] vis = new boolean[arr.length][arr[0].length];
    for (boolean[] row : vis) {
      Arrays.fill(row, false);
    }
    while (!queue.isEmpty()) {
      int[] curr = queue.poll();
      if (curr[0] == arr.length - 1 && curr[1] == arr[0].length - 1) {
        return true;
      }
      if (!vis[curr[0]][curr[1]]) {
        vis[curr[0]][curr[1]] = true;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] dir : dirs) {
          int nextR = dir[0] + curr[0];
          int nextC = dir[1] + curr[1];
          if (inside(nextR, nextC, arr) && arr[nextR][nextC] >= mid) {
            queue.add(new int[]{nextR, nextC});
          }
        }
      }
    }
    return false;
  }


  public static void main(String args[]) {
    int[][] arr = new int[][]{{3, 4, 6, 3, 4}, {0, 2, 1, 1, 7}, {8, 8, 3, 2, 7}, {3, 2, 4, 9, 8}, {4, 1, 2, 0, 0}, {4, 6, 5, 4, 3}};
    PathWithMaxMin p = new PathWithMaxMin();
    System.out.println(p.maximumMinimumPath(arr));
  }

}
