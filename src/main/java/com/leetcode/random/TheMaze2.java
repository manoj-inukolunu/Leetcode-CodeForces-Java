package com.leetcode.random;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TheMaze2 {

  boolean inside(int row, int col, int[][] maze) {
    return row >= 0 && col >= 0 && row < maze.length && col < maze[row].length;
  }

  public int shortestDistance(int[][] maze, int[] start, int[] destination) {
    Queue<Integer[]> queue = new LinkedList<>();
    queue.add(new Integer[]{start[0], start[1]});
    int[][] dist = new int[maze.length][maze[0].length];
    for (int[] row : dist) {
      Arrays.fill(row, Integer.MAX_VALUE);
    }
    dist[start[0]][start[1]] = 0;
    while (!queue.isEmpty()) {
      Integer[] curr = queue.poll();
      int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
      for (int[] dir : dirs) {
        int nextR = dir[0] + curr[0];
        int nextC = dir[1] + curr[1];
        int cDist = 0;
        while (inside(nextR, nextC, maze) && maze[nextR][nextC] == 0) {
          nextR += dir[0];
          nextC += dir[1];
          cDist++;
        }
        nextR = nextR - dir[0];
        nextC = nextC - dir[1];
        if (dist[curr[0]][curr[1]] + cDist < dist[nextR][nextC]) {
          dist[nextR][nextC] = dist[curr[0]][curr[1]] + cDist;
          queue.add(new Integer[]{nextR, nextC});
        }
      }

    }
    return dist[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dist[destination[0]][destination[1]];
  }

  public static void main(String args[]) {
    TheMaze2 t = new TheMaze2();
    int[][] maze = new int[][]{
        {0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 1, 0},
        {1, 1, 0, 1, 1},
        {0, 0, 0, 0, 0}
    };
    System.out.println(t.shortestDistance(maze, new int[]{0, 4}, new int[]{4, 4}));
  }
}


