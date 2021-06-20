package com.leetcode.array;

import com.leetcode.linkedlist.Merge;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * @author manoji on 5/8/20.
 */
public class MergeIntervals {

  class Pair {

    int start;
    int end;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Pair pair = (Pair) o;
      return start == pair.start &&
          end == pair.end;
    }

    @Override
    public int hashCode() {
      return Objects.hash(start, end);
    }

    public Pair(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }

  public int[][] insert(int[][] intervals, int[] newInterval) {
    Pair pair = new Pair(newInterval[0], newInterval[1]);
    List<Pair> list = new ArrayList<>();
    for (int[] row : intervals) {
      list.add(new Pair(row[0], row[1]));
    }
    list.add(pair);

    if (list.isEmpty()) {
      return new int[0][];
    }

    Collections.sort(list, Comparator.comparingInt(value -> value.end));

    Stack<Pair> stack = new Stack<>();
    stack.push(list.get(0));
    for (int i = 1; i < list.size(); i++) {
      if (!stack.isEmpty()) {
        Pair top = stack.peek();
        Pair current = list.get(i);
        while (!stack.isEmpty() && canMerge(top, current)) {
          Pair merged = merge(top, current);
          stack.pop();
          if (stack.isEmpty()) {
            current = merged;
            top = null;
          } else {
            top = stack.peek();
            current = merged;
          }
        }
        stack.push(current);
      } else {
        stack.push(list.get(i));
      }
    }
    int[][] arr = new int[stack.size()][2];
    int i = arr.length - 1;
    while (!stack.isEmpty()) {
      Pair p = stack.pop();
      arr[i][0] = p.start;
      arr[i--][1] = p.end;
    }
    return arr;
  }


  private Pair merge(Pair first, Pair second) {
    int start = first.start <= second.start ? first.start : second.start;
    int end = first.end >= second.end ? first.end : second.end;
    return new Pair(start, end);
  }

  private boolean canMerge(Pair first, Pair second) {
    if (first == null) {
      return false;
    }
    if ((second.start <= first.end && second.start >= first.start) || (first.end >= second.start && first.end <= second.end)) {
      return true;
    }
    return false;
  }

  public int[][] merge(int[][] intervals) {
    List<Pair> list = new ArrayList<>();
    for (int[] row : intervals) {
      list.add(new Pair(row[0], row[1]));
    }

    if (list.isEmpty()) {
      return new int[0][];
    }

    Collections.sort(list, Comparator.comparingInt(value -> value.end));

    Stack<Pair> stack = new Stack<>();
    stack.push(list.get(0));
    for (int i = 1; i < list.size(); i++) {
      if (!stack.isEmpty()) {
        Pair top = stack.peek();
        Pair current = list.get(i);
        while (!stack.isEmpty() && canMerge(top, current)) {
          Pair merged = merge(top, current);
          stack.pop();
          if (stack.isEmpty()) {
            current = merged;
            top = null;
          } else {
            top = stack.peek();
            current = merged;
          }
        }
        stack.push(current);
      } else {
        stack.push(list.get(i));
      }
    }
    int[][] arr = new int[stack.size()][2];
    int i = arr.length - 1;
    while (!stack.isEmpty()) {
      Pair p = stack.pop();
      arr[i][0] = p.start;
      arr[i--][1] = p.end;
    }
    return arr;
  }


  public static void main(String args[]) {
    int[][] arr = new int[][]{
        {1, 3},
        {4, 6},
        {5, 7}

    };

    MergeIntervals m = new MergeIntervals();
    int[][] res = m.merge(arr);

    for (int[] row : res) {
      System.out.println(Arrays.toString(row));
    }

  }

}
