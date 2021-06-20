package com.leetcode.common;

public class Interval {

  int start;
  int end;

  public Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }

  public boolean overlaps(Interval next) {
    return Math.max(this.start, next.start) < Math.min(this.end, next.end);
  }

  public Interval getOverLapped(Interval next) {
    return new Interval(Math.max(this.start, next.start), start + Math.min(this.end, next.end) - Math.max(this.start, next.start));
  }

  @Override
  public String toString() {
    return "Interval{" +
        "start=" + start +
        ", end=" + end +
        '}';
  }

  public Interval intersect(Interval next) {
    return new Interval(Math.max(start, next.start), Math.min(end, next.end));
  }

  public static void main(String args[]) {
    Interval intervalA = new Interval(10, 20);
    Interval intervalB = new Interval(15, 30);

    System.out.println(intervalA.getOverLapped(intervalB));

    System.out.println(intervalB.intersect(intervalA));

  }

}
