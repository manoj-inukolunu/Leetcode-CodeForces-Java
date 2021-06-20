package com.leetcode.random10.sixmonths.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class WallsAndGates {

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


  public void wallsAndGates(int[][] rooms) {
    for (int i = 0; i < rooms.length; i++) {
      for (int j = 0; j < rooms[i].length; j++) {
        if (rooms[i][j] == 0) {
          bfs(i, j, rooms);
        }
      }
    }
  }

  private boolean inside(int row, int col, int[][] rooms) {
    return row >= 0 && col >= 0 && row < rooms.length && col < rooms[row].length;
  }

  private void bfs(int row, int col, int[][] rooms) {
    Queue<Pair> queue = new LinkedList<>();
    queue.add(new Pair(row, col, 0));
    HashSet<Pair> visited = new HashSet();
    while (!queue.isEmpty()) {
      Pair curr = queue.poll();
      row = curr.row;
      col = curr.col;
      rooms[curr.row][curr.col] = Math.min(curr.dist, rooms[curr.row][curr.col]);
      if (!visited.contains(curr)) {
        visited.add(curr);
        if (inside(row - 1, col, rooms) && (rooms[row - 1][col] != 0 && rooms[row - 1][col] != -1)) {
          queue.add(new Pair(row - 1, col, curr.dist + 1));
        }
        if (inside(row + 1, col, rooms) && (rooms[row + 1][col] != 0 && rooms[row + 1][col] != -1)) {
          queue.add(new Pair(row + 1, col, curr.dist + 1));
        }
        if (inside(row, col - 1, rooms) && (rooms[row][col - 1] != 0 && rooms[row][col - 1] != -1)) {
          queue.add(new Pair(row, col - 1, curr.dist + 1));
        }
        if (inside(row, col + 1, rooms) && (rooms[row][col + 1] != 0 && rooms[row][col + 1] != -1)) {
          queue.add(new Pair(row, col + 1, curr.dist + 1));
        }
      }
    }
  }

  public static void main(String args[]) {
    WallsAndGates w = new WallsAndGates();
    /*int[][] arr = new int[][]{
        {0, 2147483647, -1, 2147483647, 2147483647, -1, -1, 0, 0, -1, 2147483647, 2147483647, 0, -1, 2147483647, 2147483647, 2147483647, 2147483647,
            0, 2147483647, 0, -1, -1, -1, -1, 2147483647, -1, -1, 2147483647, 2147483647, -1, -1, 0, 0, -1, 0, 0, 0, 2147483647, 0, 2147483647, -1,
            -1, 0, -1, 0, 0, 0, 2147483647},
        {2147483647, 0, -1, 2147483647, 0, -1, -1, -1, -1, 0, 0, 2147483647, 2147483647, -1, -1, 2147483647, -1, -1, 2147483647, 2147483647, -1, 0,
            -1, 2147483647, 0, 2147483647, -1, 2147483647, 0, 2147483647, 0, 2147483647, -1, 2147483647, 0, 2147483647, -1, 2147483647, 0, 2147483647,
            2147483647, 0, -1, 2147483647, -1, -1, -1, 0, 2147483647}};
int[][] arr = new int[][]{{2147483647, 0, 2147483647, 2147483647, 0, 2147483647, -1, 2147483647}};
    */

    int[][] arr = new int[][]{
        {Integer.MAX_VALUE, -1, 0, Integer.MAX_VALUE},
        {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1},
        {Integer.MAX_VALUE, -1, Integer.MAX_VALUE, -1},
        {0, -1, Integer.MAX_VALUE, Integer.MAX_VALUE}
    };

    for (int[] row : arr) {
      System.out.println(Arrays.toString(row));
    }
    w.wallsAndGates(arr);

    System.out.println();

    for (int[] row : arr) {
      System.out.println(Arrays.toString(row));
    }


  }


}
