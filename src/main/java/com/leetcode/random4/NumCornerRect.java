package com.leetcode.random4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class NumCornerRect {

  class Pair {

    int row;
    int col;

    public Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }

    @Override
    public String toString() {
      return row + ":" + col;
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
  }

  class Rect {

    Pair first;
    Pair second;
    Pair third;
    Pair fourth;
    Set<Pair> set = new HashSet();

    public Rect(Pair one, Pair two, Pair three, Pair four) {
      this.first = one;
      this.second = two;
      this.third = three;
      this.fourth = four;
      set.add(one);
      set.add(two);
      set.add(three);
      set.add(four);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Rect rect = (Rect) o;
      return Objects.equals(set, rect.set);
    }

    @Override
    public int hashCode() {
      return Objects.hash(set);
    }
  }

  public int countCornerRectangles(int[][] grid) {
    List<Pair> ones = new ArrayList();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == 1) {
          ones.add(new Pair(i, j));
        }
      }
    }
    HashSet<Rect> set = new HashSet();
    for (int i = 0; i < ones.size(); i++) {
      for (int j = 0; j < ones.size(); j++) {
        if (i != j) {
          Pair first = ones.get(i);
          Pair second = ones.get(j);
          if (first.row != second.row && first.col != second.col && grid[first.row][second.col] == 1 && grid[second.row][first.col] == 1) {
            set.add(new Rect(first, second, new Pair(first.row, second.col), new Pair(second.row, first.col)));
          }
        }
      }
    }

    return set.size();
  }

}
