package com.leetcode.random10.sixmonths.medium;

/**
 * @author manoji on 7/29/20.
 */
public class TheMaze {

  private boolean reachable = false;

  public boolean hasPath(int[][] maze, int[] start, int[] destination) {

    boolean bounded = isBounded(destination, maze);
    dfs(start[0], start[1], destination, maze);
    return reachable && bounded;
  }

  private boolean isBounded(int[] destination, int[][] maze) {
    int row = destination[0], col = destination[1], count = 0;
    if (row + 1 < maze.length) {
      if (maze[row + 1][col] == 1) {
        count++;
      }
    } else {
      count++;
    }
    if (row - 1 >= 0) {
      if (maze[row - 1][col] == 1) {
        count++;
      }
    } else {
      count++;
    }
    if (col + 1 < maze[row].length) {
      if (maze[row][col + 1] == 1) {
        count++;
      }
    } else {
      count++;
    }
    if (col - 1 >= 0) {
      if (maze[row][col - 1] == 1) {
        count++;
      }
    } else {
      count++;
    }
    return count == 3;
  }

  private boolean inside(int row, int col, int[][] maze) {
    return row >= 0 && col >= 0 && row < maze.length && col < maze[row].length;
  }

  private void dfs(int row, int col, int[] dest, int[][] maze) {
    if (row == dest[0] && col == dest[1]) {
      reachable = true;
      return;
    }
    maze[row][col] = 1;
    if (!reachable && inside(row + 1, col, maze) && maze[row + 1][col] != 1) {
      dfs(row + 1, col, dest, maze);
    }
    if (!reachable && inside(row - 1, col, maze) && maze[row - 1][col] != 1) {
      dfs(row - 1, col, dest, maze);
    }
    if (!reachable && inside(row, col + 1, maze) && maze[row][col + 1] != 1) {
      dfs(row, col + 1, dest, maze);
    }
    if (!reachable && inside(row, col - 1, maze) && maze[row][col - 1] != 1) {
      dfs(row, col - 1, dest, maze);
    }

  }

  public static void main(String args[]) {
    TheMaze t = new TheMaze();
    int[][] maze = new int[][]{
        {0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 1, 0},
        {1, 1, 0, 1, 1},
        {0, 0, 0, 0, 0}
    };
    System.out.println(t.hasPath(maze, new int[]{0, 4}, new int[]{1, 2}));
  }


}
