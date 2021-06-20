package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.List;

class MyCalendarTwo {

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

    public boolean overlapsFull(Interval next) {
      return next.start <= this.start && next.end >= this.end;
    }

    public Interval intersect(Interval next) {
      return new Interval(Math.max(start, next.start), Math.min(end, next.end));
    }
  }


  private List<Interval> doubleBooked = new ArrayList<>();
  private List<Interval> singleBooked = new ArrayList<>();

  public MyCalendarTwo() {

  }

  public boolean book(int start, int end) {
    Interval interval = new Interval(start, end);
    for (int i = 0; i < doubleBooked.size(); i++) {
      if (doubleBooked.get(i).overlaps(interval)) {
        return false;
      }
    }
    List<Interval> temp = new ArrayList<>();
    for (int i = 0; i < singleBooked.size(); i++) {
      Interval curr = singleBooked.get(i);
      if (interval.overlaps(curr)) {
        temp.add(curr);
      }
    }
    if (temp.size() == 0) {
      singleBooked.add(interval);
      return true;
    } else {
      for (int i = 0; i < temp.size(); i++) {
        Interval curr = temp.get(i);
        Interval intersect = curr.intersect(interval);
        doubleBooked.add(intersect);
        singleBooked.remove(temp.get(i));
        if (overlapsFull(interval, curr) && intersect.start > curr.start && intersect.end < curr.end) {
          singleBooked.add(new Interval(curr.start, intersect.end));
          singleBooked.add(new Interval(intersect.end, curr.end));
        } else if (overlapsFull(interval, curr) && curr.start < intersect.start && intersect.end > curr.end) {
          singleBooked.remove(temp.get(i));
        } else if (intersect.start > curr.start) {
          singleBooked.add(new Interval(curr.start, intersect.start));
        } else if (intersect.end < curr.end) {
          singleBooked.add(new Interval(intersect.end, curr.end));
        }
      }
      return true;
    }
  }

  private boolean overlapsFull(Interval interval1, Interval interval2) {
    return interval1.overlapsFull(interval2) || interval2.overlapsFull(interval1);
  }

  public static void main(String args[]) {
    MyCalendarTwo m = new MyCalendarTwo();
    System.out.println(m.book(10, 20));
    System.out.println(m.book(50, 60));
    System.out.println(m.book(10, 40));
    System.out.println(m.book(5, 15));
    System.out.println(m.book(5, 10));
    System.out.println(m.book(25, 55));
  }
}
