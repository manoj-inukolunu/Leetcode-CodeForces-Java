package com.leetcode.random;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class MinJumps {

  class Pos {

    @Override
    public String toString() {
      return "Pos{" +
          "pos=" + pos +
          ", back=" + back +
          ", steps=" + steps +
          '}';
    }

    int pos;
    boolean back;
    int steps;

    public Pos(int pos, boolean back, int steps) {
      this.pos = pos;
      this.back = back;
      this.steps = steps;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Pos pos1 = (Pos) o;
      return pos == pos1.pos &&
          back == pos1.back;
    }

    @Override
    public int hashCode() {
      return Objects.hash(pos, back);
    }
  }


  public int minimumJumps(int[] forbidden, int a, int b, int x) {
    HashSet<Integer> set = new HashSet<>();
    for (int num : forbidden) {
      set.add(num);
    }
    Queue<Pos> queue = new LinkedList<>();
    queue.add(new Pos(0, false, 0));
    HashSet<Pos> visited = new HashSet<>();
    while (!queue.isEmpty()) {
      Pos curr = queue.poll();
      if (set.contains(curr.pos) || curr.pos < 0) {
        continue;
      }
      if (curr.pos == x) {
        return curr.steps;
      }
      if (!visited.contains(curr)) {
        System.out.println(curr);
        visited.add(curr);
        if (!curr.back) {
          queue.add(new Pos(curr.pos - b, true, curr.steps + 1));
        }
        queue.add(new Pos(curr.pos + a, false, curr.steps + 1));
      }
    }
    return -1;
  }


  public int minJ(int[] forbidden, int a, int b, int x) {
    HashSet<Integer> set = new HashSet<>();
    for (int num : forbidden) {
      set.add(num);
    }

    return dfs(set, a, b, false, x);
  }

  private int dfs(HashSet<Integer> set, int a, int b, boolean back, int x) {
    int steps = Integer.MAX_VALUE;
    if (back) {
      steps = dfs(set, a, b, true, x + b);
    }
    return Math.min(steps, dfs(set, a, b, false, x - a));
  }

  public static void main(String args[]) {
    MinJumps m = new MinJumps();

    System.out.println(m.minimumJumps(new int[]{8, 3, 16, 6, 12, 20}, 15, 13, 11));
  }
}
