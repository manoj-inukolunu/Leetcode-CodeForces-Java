package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {


  class Data {

    int row;
    int col;

    int dist;

    public Data(int row, int col, int dist) {
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
      Data data = (Data) o;
      return row == data.row &&
          col == data.col;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, col);
    }
  }


  public int shortestPathBinaryMatrix(int[][] grid) {
    if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) {
      return -1;
    }
    Data data = new Data(0, 0, 1);
    Queue<Data> queue = new LinkedList<>();
    queue.add(data);
    HashSet<Data> visited = new HashSet<>();
    int min = Integer.MAX_VALUE;
    while (!queue.isEmpty()) {
      Data curr = queue.poll();
      if (curr.row == grid.length - 1 && curr.col == grid[0].length - 1) {
        min = Math.min(min, curr.dist);
      }
      if (!visited.contains(curr)) {
        visited.add(curr);
        for (Data next : getNext(curr)) {
          if (canVisit(next, grid) && !visited.contains(next)) {
            queue.add(new Data(next.row, next.col, curr.dist + 1));
          }
        }
      }
    }
    return min;
  }

  private List<Data> getNext(Data curr) {
    List<Data> next = new ArrayList();
    int row = curr.row;
    int col = curr.col;
    int dist = curr.dist;
    next.add(new Data(row - 1, col, dist));
    next.add(new Data(row, col - 1, dist));
    next.add(new Data(row - 1, col - 1, dist));
    next.add(new Data(row + 1, col, dist));
    next.add(new Data(row + 1, col - 1, dist));
    next.add(new Data(row - 1, col + 1, dist));
    next.add(new Data(row, col + 1, dist));
    next.add(new Data(row + 1, col + 1, dist));
    return next;
  }

  private boolean canVisit(Data next, int[][] grid) {
    return next.row >= 0 && next.row < grid.length && next.col >= 0 && next.col < grid[0].length && grid[next.row][next.col] != 1;
  }


  public static void main(String args[]) {
    ShortestPathInBinaryMatrix s = new ShortestPathInBinaryMatrix();

    int[][] arr = new int[][]{
        {0, 0, 0},
        {1, 0, 0},
        {1, 1, 0}
    };

    System.out.println(s.shortestPathBinaryMatrix(arr));
  }

}
