package com.leetcode.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RandomPick {


  class Interval {

    int start;
    int end;

    @Override
    public String toString() {
      return "Interval{" +
          "start=" + start +
          ", end=" + end +
          '}';
    }
  }

  List<Interval> list = new ArrayList<>();

  public List<Interval> getList(int[] blacklist) {
    Arrays.sort(blacklist);
    int i = 0;
    while (i < blacklist.length) {
      Interval interval = new Interval();
      interval.start = blacklist[i];
      while (i + 1 < blacklist.length && blacklist[i] + 1 == blacklist[i + 1]) {
        i++;
      }
      interval.end = blacklist[i - 1];
      list.add(interval);
      i++;
    }
    return list;
  }

  public RandomPick(int N, int[] blacklist) {

  }

  public int pick() {
    return 1;
  }

  public static void main(String args[]) {
    int[] arr = new int[]{1, 2, 3, 5, 7, 2, 4, 6};

    RandomPick r = new RandomPick(7, arr);
    System.out.println(r.getList(arr));
  }

}
