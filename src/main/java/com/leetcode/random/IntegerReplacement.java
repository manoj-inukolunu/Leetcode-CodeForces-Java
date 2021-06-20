package com.leetcode.random;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class IntegerReplacement {

  class Pair {

    long num;
    int steps;

    public Pair(long num, int steps) {
      this.num = num;
      this.steps = steps;
    }
  }


  public int integerReplacement(int n) {

    Queue<Pair> queue = new LinkedList<>();
    queue.add(new Pair(n, 0));
    HashSet<Long> visited = new HashSet<>();
    while (!queue.isEmpty()) {
      Pair curr = queue.poll();
      if (curr.num == 1) {
        return curr.steps;
      }
      if (!visited.contains(curr.num)) {
        visited.add(curr.num);
        if (curr.num % 2 == 0) {
          queue.add(new Pair(curr.num / 2, curr.steps + 1));
        } else {
          queue.add(new Pair(curr.num + 1, curr.steps + 1));
          queue.add(new Pair(curr.num - 1, curr.steps + 1));
        }
      }
    }
    return -1;
  }

}
