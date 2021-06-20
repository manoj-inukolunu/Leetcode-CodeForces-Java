package com.leetcode.random10.sixmonths.medium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class ShortestBridge {

  class Pair {

    int row;
    int col;
    int dist;

    public Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }

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

  int[][] dirs = new int[][]{
      {1, 0}, {-1, 0}, {0, 1}, {0, -1}
  };

  public int shortestBridge(int[][] A) {
    int[][] temp = new int[A.length][A.length];
    copyArr(temp, A);
    Set<Pair> setA = new HashSet<>();
    Set<Pair> setB = new HashSet<>();
    int curr = 1;
    for (int i = 0; i < A.length; i++) {
      for (int j = 0; j < A.length; j++) {
        if (A[i][j] == 1 && curr == 1) {
          dfs(i, j, A, setA);
          curr++;
        } else if (A[i][j] == 1) {
          dfs(i, j, A, setB);
        }
      }
    }
    if (setA.size() < setB.size()) {
      return checkDist(setA, setB, temp);
    } else {
      return checkDist(setB, setA, temp);
    }
  }

  private void copyArr(int[][] temp, int[][] a) {
    for (int i = 0; i < temp.length; i++) {
      for (int j = 0; j < temp.length; j++) {
        temp[i][j] = a[i][j];
      }
    }
  }

  private int checkDist(Set<Pair> setA, Set<Pair> setB, int[][] arr) {
    Queue<Pair> queue = new LinkedList<>();
    int ret = Integer.MAX_VALUE;
    for (Pair pair : setA) {
      for (int[] dir : dirs) {
        int row = pair.row + dir[0];
        int col = pair.col + dir[1];
        if (inside(row, col, arr) && arr[row][col] == 0) {
          queue.add(new Pair(row, col, 1));
        }
      }
    }
    return bfs(queue, setB, arr);
  }

  private int bfs(Queue<Pair> queue, Set<Pair> toCheck, int[][] arr) {
    int dist = Integer.MAX_VALUE;
    HashSet<Pair> visited = new HashSet<>();
    while (!queue.isEmpty()) {
      Pair curr = queue.poll();
      if (!visited.contains(curr)) {
        visited.add(curr);
        for (int[] dir : dirs) {
          int row = curr.row + dir[0];
          int col = curr.col + dir[1];
          if (inside(row, col, arr)) {
            Pair next = new Pair(row, col, curr.dist + 1);
            if (toCheck.contains(next)) {
              dist = Math.min(dist, curr.dist);
            } else if (arr[row][col] != 1) {
              queue.add(next);
            }
          }
        }
      }
    }
    return dist;
  }

  private boolean inside(int row, int col, int[][] arr) {
    return row >= 0 && row < arr.length && col >= 0 && col < arr[row].length;
  }

  private void dfs(int row, int col, int[][] arr, Set<Pair> visited) {
    if (arr[row][col] == 1) {
      arr[row][col] = 0;
      visited.add(new Pair(row, col));
      for (int[] dir : dirs) {
        int nRow = row + dir[0];
        int nCol = col + dir[1];
        if (inside(nRow, nCol, arr) && arr[nRow][nCol] == 1) {
          dfs(nRow, nCol, arr, visited);
        }
      }
    }
  }

  public static void main(String args[]) {
    ShortestBridge s = new ShortestBridge();

    int[][] arr = new int[][]{
        {1, 1, 1, 1, 1},
        {1, 0, 0, 0, 1},
        {1, 0, 1, 0, 1},
        {1, 0, 0, 0, 1},
        {1, 1, 1, 1, 1},
    };

    System.out.println(s.shortestBridge(arr));
  }

}
