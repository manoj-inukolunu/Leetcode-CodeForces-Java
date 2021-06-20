package com.leetcode.random7;

import java.util.ArrayList;
import java.util.List;

public class MyCalendarTwo {

  List<Integer[]> list = new ArrayList<>();

  public MyCalendarTwo() {

  }

  public boolean book(int start, int end) {
    int count = 0;
    for (int i = 0; i < list.size(); i++) {
      Integer[] curr = list.get(i);
      if (Math.max(start, curr[0]) < Math.min(end, curr[1])) {
        count++;
      }
    }
    if (count >= 2) {
      return false;
    }
    list.add(new Integer[]{start, end});
    return true;
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
