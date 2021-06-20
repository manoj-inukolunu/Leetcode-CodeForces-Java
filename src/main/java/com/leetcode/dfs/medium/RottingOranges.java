package com.leetcode.dfs.medium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author manoji on 5/6/20.
 */
public class RottingOranges {

  class Pair {

    int row;
    int col;

    public Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }

  public int orangesRotting(int[][] grid) {
    Queue<Pair> queue = new LinkedList<>();

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == 2) {
          queue.add(new Pair(i, j));
        }
      }
    }

    int minutes = 0;

    int count = queue.size();
    while (!queue.isEmpty()) {
      if (count == 0) {
        count = queue.size();
        minutes++;
      } else {
        Pair pair = queue.poll();
        //grid[pair.row][pair.col] = 2;
        int currRow = pair.row;
        int currCol = pair.col;
        count--;
        if (currRow - 1 >= 0 && !(grid[currRow - 1][currCol] == 0 || grid[currRow - 1][currCol] == 2)) {
          grid[currRow - 1][currCol] = 2;
          queue.add(new Pair(currRow - 1, currCol));
        }
        if (currRow + 1 < grid.length && !(grid[currRow + 1][currCol] == 0 || grid[currRow + 1][currCol] == 2)) {
          grid[currRow + 1][currCol] = 2;
          queue.add(new Pair(currRow + 1, currCol));
        }

        if (currCol - 1 >= 0 && !(grid[currRow][currCol - 1] == 0 || grid[currRow][currCol - 1] == 2)) {
          grid[currRow][currCol - 1] = 2;
          queue.add(new Pair(currRow, currCol - 1));
        }

        if (currCol + 1 < grid[0].length && !(grid[currRow][currCol + 1] == 0 || grid[currRow][currCol + 1] == 2)) {
          grid[currRow][currCol + 1] = 2;
          queue.add(new Pair(currRow, currCol + 1));
        }
      }
    }

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == 1) {
          return -1;
        }
      }
    }

    return minutes;

  }

  public static void main(String args[]) {
    int[][] grid = new int[][]{
        {2, 1, 1},
        {1, 1, 0},
        {0, 1, 2}
    };

    RottingOranges r = new RottingOranges();
    System.out.println(r.orangesRotting(grid));
  }

}
