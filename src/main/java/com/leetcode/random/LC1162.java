package com.leetcode.random;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class LC1162 {

  class Hold {

    int row;
    int col;
    int dist;

    public Hold(int row, int col, int dist) {
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
      Hold hold = (Hold) o;
      return row == hold.row &&
          col == hold.col;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, col);
    }
  }

  private boolean inside(int row, int col, int[][] arr) {
    return row >= 0 && col >= 0 && row < arr.length && col < arr[row].length;
  }

  public int maxDistance(int[][] grid) {

    int[][] temp = new int[grid.length][grid[0].length];
    int ans = Integer.MIN_VALUE;
    Queue<Hold> queue = new LinkedList<>();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        temp[i][j] = grid[i][j];
        if (grid[i][j] == 1) {
          queue.add(new Hold(i, j, 0));
        }
      }
    }
    HashSet<Hold> visited = new HashSet<>();
    while (!queue.isEmpty()) {
      Hold curr = queue.poll();
      if (grid[curr.row][curr.col] == 0) {
        if (temp[curr.row][curr.col] == 0) {
          temp[curr.row][curr.col] = curr.dist;
        } else {
          temp[curr.row][curr.col] = Math.min(temp[curr.row][curr.col], curr.dist);
        }
        ans = Math.max(ans, temp[curr.row][curr.col]);
      }
      if (!visited.contains(curr)) {
        visited.add(curr);
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        for (int[] dir : dirs) {
          int row = curr.row + dir[0];
          int col = curr.col + dir[1];
          if (inside(row, col, grid)) {
            queue.add(new Hold(row, col, curr.dist + 1));
          }
        }
      }
    }
    /*List<Integer[]> list = new ArrayList<>();
    for (int i = 0; i < temp.length; i++) {
      for (int j = 0; j < temp[j].length; j++) {
        if (temp[i][j] == ans) {
          for(int k=)
        }
      }
    }*/
    return ans;
  }

  public static void main(String args[]) {
    LC1162 l = new LC1162();
    int[][] arr = new int[][]{
        {1, 0, 0}, {0, 0, 0}, {0, 0, 0}
    };
    System.out.println(l.maxDistance(arr));
  }

}
