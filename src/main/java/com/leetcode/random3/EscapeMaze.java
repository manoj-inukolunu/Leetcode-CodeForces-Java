package com.leetcode.random3;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class EscapeMaze {

  class Pair {

    long row, col;
    long steps = 0;

    public Pair(long row, long col) {
      this.row = row;
      this.col = col;
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
      return row == pair.row && col == pair.col;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, col);
    }

    public Pair(long row, long col, long steps) {
      this.row = row;
      this.col = col;
      this.steps = steps;
    }
  }

  public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
    HashSet<Pair> block = new HashSet<>();
    for (int i = 0; i < blocked.length; i++) {
      block.add(new Pair(blocked[i][0], blocked[i][1]));
    }
    return bfs(source, block, target) && bfs(target, block, source);
  }

  private boolean bfs(int[] source, HashSet<Pair> blocked, int[] target) {
    Queue<Pair> queue = new LinkedList<>();
    queue.add(new Pair(source[0], source[1], 0));
    HashSet<Pair> visited = new HashSet<>();
    while (!queue.isEmpty()) {
      Pair curr = queue.poll();
      if (blocked.contains(curr)) {
        continue;
      }
      if (curr.steps > 200) {
        return true;
      }
      if (curr.row == target[0] && curr.col == target[1]) {
        return true;
      }
      if (!visited.contains(curr)) {
        visited.add(curr);
        long[][] dirs = new long[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (long[] dir : dirs) {
          long nextR = dir[0] + curr.row;
          long nextC = dir[1] + curr.col;
          if (nextR >= 0 && nextC >= 0 && !blocked.contains(new Pair(nextR, nextC))) {
            queue.add(new Pair(nextR, nextC, curr.steps + 1));
          }
        }
      }
    }
    return false;
  }

  public static void main(String args[]) {
    int[][] blocked = new int[][]{{0, 1}, {1, 0}};
    EscapeMaze e = new EscapeMaze();
    System.out.println(e.isEscapePossible(blocked, new int[]{0, 0}, new int[]{0, 2}));
  }

}
