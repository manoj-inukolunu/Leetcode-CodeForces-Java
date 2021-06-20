package com.leetcode.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class HasValidPath {

  HashMap<Integer, Set<Integer>> left = new HashMap<>();
  HashMap<Integer, Set<Integer>> top = new HashMap<>();
  HashMap<Integer, Set<Integer>> bottom = new HashMap<>();
  HashMap<Integer, Set<Integer>> right = new HashMap<>();

  Set<HashMap<Integer, Set<Integer>>> next = new HashSet<>();

  void populate() {
    for (int i = 1; i <= 6; i++) {
      left.put(i, new HashSet<>());
      right.put(i, new HashSet<>());
      top.put(i, new HashSet<>());
      bottom.put(i, new HashSet<>());
    }
    left.get(1).add(4);
    left.get(1).add(6);
    left.get(1).add(1);
    right.get(1).add(3);
    right.get(1).add(1);
    right.get(1).add(5);
    top.get(2).add(3);
    top.get(2).add(2);
    top.get(2).add(4);
    bottom.get(2).add(5);
    bottom.get(2).add(2);
    bottom.get(2).add(6);
    left.get(3).add(1);
    left.get(3).add(4);
    left.get(3).add(6);
    bottom.get(3).add(5);
    bottom.get(3).add(2);
    bottom.get(3).add(6);
    top.get(5).add(3);
    top.get(5).add(2);
    top.get(5).add(4);
    left.get(5).add(4);
    left.get(5).add(6);
    left.get(5).add(1);
    right.get(4).add(3);
    right.get(4).add(5);
    right.get(4).add(1);
    bottom.get(4).add(6);
    bottom.get(4).add(5);
    bottom.get(4).add(2);
    top.get(6).add(3);
    top.get(6).add(2);
    top.get(6).add(4);
    right.get(6).add(3);
    right.get(6).add(5);
    right.get(6).add(1);

    next.add(left);
    next.add(right);
    next.add(bottom);
    next.add(top);
  }


  class Pair {

    int row;
    int col;

    public Pair(int row, int col) {
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
      return row == pair.row &&
          col == pair.col;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, col);
    }
  }

  public boolean hasValidPath(int[][] grid) {
    populate();
    Queue<Pair> queue = new LinkedList();
    queue.add(new Pair(0, 0));
    HashSet<Pair> visited = new HashSet<>();
    while (!queue.isEmpty()) {
      Pair curr = queue.poll();
      if (curr.row == grid.length - 1 && curr.col == grid[0].length - 1) {
        return true;
      }
      if (!visited.contains(curr)) {
        visited.add(curr);
        List<Pair> nextList = getNext(curr, grid);
        for (Pair next : nextList) {
          queue.add(next);
        }
      }
    }

    return false;
  }

  private boolean inside(int row, int col, int[][] grid) {
    return row >= 0 && col >= 0 && row < grid.length && col < grid[row].length;
  }

  private List<Pair> getNext(Pair curr, int[][] grid) {
    List<Pair> list = new ArrayList<>();
    if (canGoLeft(curr, grid)) {
      list.add(new Pair(curr.row, curr.col - 1));
    }
    if (canGoRight(curr, grid)) {
      list.add(new Pair(curr.row, curr.col + 1));
    }
    if (canGoTop(curr, grid)) {
      list.add(new Pair(curr.row - 1, curr.col));
    }
    if (canGoBottom(curr, grid)) {
      list.add(new Pair(curr.row + 1, curr.col));
    }
    return list;
  }

  private boolean canGoBottom(Pair curr, int[][] grid) {
    return inside(curr.row + 1, curr.col, grid) && left.containsKey(grid[curr.row][curr.col]) && left.get(grid[curr.row][curr.col])
        .contains(grid[curr.row + 1][curr.col]);
  }

  private boolean canGoTop(Pair curr, int[][] grid) {
    return inside(curr.row - 1, curr.col, grid) && left.containsKey(grid[curr.row][curr.col]) && left.get(grid[curr.row][curr.col])
        .contains(grid[curr.row - 1][curr.col]);
  }

  private boolean canGoRight(Pair curr, int[][] grid) {
    return inside(curr.row, curr.col + 1, grid) && left.containsKey(grid[curr.row][curr.col]) && left.get(grid[curr.row][curr.col])
        .contains(grid[curr.row][curr.col + 1]);
  }

  private boolean canGoLeft(Pair curr, int[][] grid) {
    return inside(curr.row, curr.col - 1, grid) && left.containsKey(grid[curr.row][curr.col]) && left.get(grid[curr.row][curr.col])
        .contains(grid[curr.row][curr.col - 1]);
  }

  public static void main(String args[]) {
    HasValidPath h = new HasValidPath();
    int[][] grid = new int[][]{
        {1, 1, 1, 1, 1, 1, 3}
    };
    System.out.println(h.hasValidPath(grid));
  }

}
