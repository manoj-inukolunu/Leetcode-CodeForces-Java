package com.leetcode.random;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TeemoAttacking {

  class Interval {

    int start;
    int end;
    int duration;

    public Interval(int start, int end) {
      this.start = start;
      this.end = end;
      this.duration = end - start + 1;
    }

    public boolean overlaps(Interval next) {
      return Math.max(this.start, next.start) <= Math.min(this.end, next.end);
    }

    public Interval merge(Interval next) {
      return new Interval(Math.min(start, next.start), Math.max(end, next.end));
    }
  }

  public int findPoisonedDuration(int[] timeSeries, int duration) {

    List<Interval> list = new ArrayList<>();
    for (int i = 0; i < timeSeries.length; i++) {
      list.add(new Interval(timeSeries[i], timeSeries[i] + duration - 1));
    }
    Stack<Interval> stack = new Stack<>();
    for (int i = 0; i < list.size(); i++) {
      if (stack.isEmpty()) {
        stack.push(list.get(i));
      } else {
        Interval top = stack.peek();
        if (list.get(i).overlaps(top)) {
          stack.pop();
          stack.push(list.get(i).merge(top));
        } else {
          stack.push(list.get(i));
        }
      }
    }
    int total = 0;
    while (!stack.isEmpty()) {
      total += (stack.pop().duration);
    }
    return total;
  }

  public static void main(String args[]) {
    TeemoAttacking t = new TeemoAttacking();
    System.out.println(t.findPoisonedDuration(new int[]{1, 2}, 2));
  }
}
