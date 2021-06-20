package com.leetcode.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

public class BlackList {


  class Interval {

    int start;
    int end;

    public Interval(int start, int end) {
      this.start = start;
      this.end = end;
    }

    public List<Interval> breakUp(Interval next) {
      List<Interval> list = new ArrayList<>();
      if (end == next.end && start != next.start && next.start - 1 >= 0) {
        list.add(new Interval(start, next.start - 1));
        return list;
      }

      if (next.start - 1 >= 0) {
        list.add(new Interval(start, next.start - 1));
      }
      if (end != next.start) {
        list.add(new Interval(next.end + 1, end));
      }
      return list;
    }

    @Override
    public String toString() {
      return "Interval{" +
          "start=" + start +
          ", end=" + end +
          '}';
    }
  }

  List<Interval> list = new ArrayList<>();

  Random random = new Random();

  public void splitTo(int N, int[] arr) {
    Arrays.sort(arr);
    System.out.println(Arrays.toString(arr));
    Stack<Interval> stack = new Stack<>();
    stack.push(new Interval(0, N - 1));
    int i = 0;
    while (i < arr.length) {
      Interval curr = new Interval(arr[i], -1);
      TreeSet<Integer> set = new TreeSet<>();
      while (i < arr.length && i + 1 < arr.length && arr[i + 1] == arr[i] + 1) {
        set.add(arr[i]);
        set.add(arr[i + 1]);
        i++;
      }
      if (set.isEmpty()) {
        curr.end = arr[i];
      } else {
        curr.end = set.last();
      }
      Interval top = stack.pop();
      List<Interval> next = top.breakUp(curr);
      stack.push(next.get(0));
      if (next.size() > 1) {
        stack.push(next.get(1));
      }
      i++;
    }

    while (!stack.isEmpty()) {
      list.add(stack.pop());
    }
  }

  public BlackList(int N, int[] blacklist) {
    splitTo(N, blacklist);
    System.out.println(list);
  }

  public int pick() {
    int idx = random.nextInt(list.size());
    Interval interval = list.get(idx);
    return ThreadLocalRandom.current().nextInt(interval.start, interval.end + 1);
  }

  public static void main(String args[]) {

    int[] arr = new int[]{3, 4, 1};
    BlackList b = new BlackList(5, arr);

    System.out.println(b.pick());
    System.out.println(b.pick());
    System.out.println(b.pick());
  }


}
