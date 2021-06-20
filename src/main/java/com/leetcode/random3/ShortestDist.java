package com.leetcode.random3;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestDist {


  public int shortestDistance(int[][] grid) {
    int[][] temp = new int[grid.length][grid[0].length];
    int[][] visited = new int[grid.length][grid[0].length];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == 1 || grid[i][j] == 2) {
          temp[i][j] = -1;
        }
      }
    }
    int totalHouses = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == 1) {
          totalHouses++;
          bfs(i, j, temp, visited);
        }
      }
    }
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < temp.length; i++) {
      for (int j = 0; j < temp[i].length; j++) {
        if (temp[i][j] != -1 && temp[i][j] != 0) {
          if (visited[i][j] != totalHouses) {
            return -1;
          }
          min = Math.min(min, temp[i][j]);
        }
      }
    }
    return min == Integer.MAX_VALUE ? -1 : min;
  }

  boolean inside(int row, int col, int[][] grid) {
    return row >= 0 && col >= 0 && row < grid.length && col < grid[0].length;
  }

  private void bfs(int row, int col, int[][] grid, int[][] times) {
    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visited = new boolean[grid.length][grid[0].length];
    queue.add(new int[]{row, col, 0});
    while (!queue.isEmpty()) {
      int[] curr = queue.poll();
      if (!visited[curr[0]][curr[1]]) {
        visited[curr[0]][curr[1]] = true;
        if (grid[curr[0]][curr[1]] != -1) {
          times[curr[0]][curr[1]]++;
          grid[curr[0]][curr[1]] += curr[2];
        }
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] dir : dirs) {
          int nextR = dir[0] + curr[0];
          int nextC = dir[1] + curr[1];
          if (inside(nextR, nextC, grid) && grid[nextR][nextC] != -1) {
            queue.add(new int[]{nextR, nextC, curr[2] + 1});
          }
        }
      }
    }
  }

}
