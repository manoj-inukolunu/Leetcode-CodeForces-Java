package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MyCalendar {

  class Interval {

    int start;
    int end;

    public Interval(int start, int end) {
      this.start = start;
      this.end = end;
    }

    public boolean overlaps(Interval next) {
      return Math.max(this.start, next.start) < Math.min(this.end, next.end);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Interval interval = (Interval) o;
      return start == interval.start &&
          end == interval.end;
    }

    @Override
    public int hashCode() {
      return Objects.hash(start, end);
    }
  }


  List<Interval> list = new ArrayList<>();

  HashMap<Interval, Integer> map = new HashMap<>();

  public MyCalendar() {

  }

  public boolean book(int start, int end) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).overlaps(new Interval(start, end))) {
        return false;
      }
    }
    list.add(new Interval(start, end));
    return true;
  }


  public static void main(String args[]) {
    MyCalendar m = new MyCalendar();
    System.out.println(m.book(10, 20));
    System.out.println(m.book(20, 30));
    System.out.println(m.book(10, 15));
    System.out.println(m.book(5, 10));
  }
}
