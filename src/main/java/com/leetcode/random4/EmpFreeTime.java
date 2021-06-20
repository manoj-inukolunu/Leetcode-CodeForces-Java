package com.leetcode.random4;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class EmpFreeTime {


  static class Int {

    int start;
    int end;

    public Int(int start, int end) {
      this.start = start;
      this.end = end;
    }

    public boolean overlaps(Int next) {
      return Math.max(this.start, next.start) < Math.min(this.end, next.end);
    }

    public Int getOverLapped(Int next) {
      return new Int(Math.min(this.start, next.start), Math.max(this.end, next.end));
    }

    @Override
    public String toString() {
      return "Interval{" +
          "start=" + start +
          ", end=" + end +
          '}';
    }
  }

  public List<Int> employeeFreeTime(List<List<Int>> schedule) {
    List<Int> list = new ArrayList<>();
    for (int i = 0; i < schedule.size(); i++) {
      for (int j = 0; j < schedule.get(i).size(); j++) {
        list.add(schedule.get(i).get(j));
      }
    }
    Collections.sort(list, new Comparator<Int>() {
      @Override
      public int compare(Int o1, Int o2) {
        if (o1.end == o2.end) {
          return Integer.compare(o1.start, o2.start);
        }
        return Integer.compare(o1.end, o2.end);
      }
    });

    list = merge(list);
    List<Int> ans = new ArrayList<>();
    for (int i = 1; i < list.size(); i++) {
      Int prev = list.get(i - 1);
      Int curr = list.get(i);
      ans.add(new Int(prev.end, curr.start));
    }
    return ans;
  }

  private List<Int> merge(List<Int> list) {
    Stack<Int> stack = new Stack<>();
    int i = 0;
    while (i < list.size()) {
      if (stack.isEmpty()) {
        stack.push(list.get(i));
      } else if (stack.peek().overlaps(list.get(i))) {
        Int pop = stack.pop();
        stack.push(pop.getOverLapped(list.get(i)));
      } else {
        stack.push(list.get(i));
      }
      i++;
    }
    List<Int> ans = new ArrayList<>();
    while (!stack.isEmpty()) {
      Int interval = stack.pop();
      if (interval.start != Integer.MIN_VALUE && interval.end != Integer.MAX_VALUE) {
        ans.add(0, interval);
      }
    }
    return ans;
  }

  public static void main(String args[]) {
    EmpFreeTime e = new EmpFreeTime();
    List<List<Int>> times = Lists
        .newArrayList(Lists.newArrayList(new Int(1, 2), new Int(5, 6)), Lists.newArrayList(new Int(1, 3)),
            Lists.newArrayList(new Int(4, 10)));
    System.out.println(e.employeeFreeTime(times));
  }

}
