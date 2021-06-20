package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MeetingRooms2 {

  class Interval {

    int start;
    int end;

    public Interval(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }

  public int minMeetingRooms(int[][] intervals) {
    List<Interval> intervalList = new ArrayList<>();
    for (int i = 0; i < intervals.length; i++) {
      Interval interval = new Interval(intervals[i][0], intervals[i][1]);
      intervalList.add(interval);
    }
    if (intervalList.isEmpty()) {
      return 0;
    }
    Collections.sort(intervalList, Comparator.comparingInt(o -> o.start));
    PriorityQueue<Interval> priorityQueue = new PriorityQueue(new Comparator<Interval>() {
      @Override
      public int compare(Interval o1, Interval o2) {
        return Integer.compare(o1.end, o2.end);
      }
    });
    priorityQueue.add(intervalList.get(0));
    for (int i = 1; i < intervalList.size(); i++) {
      Interval intervalTop = priorityQueue.peek();
      Interval curr = intervalList.get(i);
      if (curr.start >= intervalTop.end) {
        priorityQueue.poll();
      }
      priorityQueue.add(curr);
    }
    return priorityQueue.size();
  }

  public static void main(String args[]) {
    //[[1,8],[6,20],[9,16],[13,17]]
    int[][] arr = new int[][]{
        {1, 8}, {6, 20}, {9, 16}, {13, 17}
    };
    MeetingRooms2 m = new MeetingRooms2();
    System.out.println(m.minMeetingRooms(arr));
  }


}
