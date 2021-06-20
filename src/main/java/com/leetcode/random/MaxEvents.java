package com.leetcode.random;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

public class MaxEvents {

  public int maxEvents(int[][] events) {
    Arrays.sort(events, (o1, o2) -> Integer.compare(o1[0], o2[0]));
    int count = 0;
    PriorityQueue<Integer> p = new PriorityQueue<>();
    int minDay = events[0][0];
    int maxDay = -1;
    for (int i = 0; i < events.length; i++) {
      maxDay = Math.max(maxDay, events[i][1]);
    }
    int idx = 0;
    for (int i = minDay; i <= maxDay; i++) {
      while (idx < events.length && events[idx][0] <= i) {
        p.add(events[idx][1]);
        idx++;
      }
      while (!p.isEmpty() && p.peek() < i) {
        p.poll();
      }
      if (p.isEmpty()) {
        continue;
      }
      count++;
      p.poll();
    }
    return count;
  }

  private int getLeastAvailable(HashSet<Integer> set, int low, int high) {
    int ans = -1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (!set.contains(mid)) {
        ans = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return ans;
  }


  public static void main(String args[]) {
    int[][] events = new int[][]{
        {7, 11}, {7, 11}, {7, 11}, {9, 10}, {9, 11}
    };
    MaxEvents m = new MaxEvents();
    System.out.println(m.maxEvents(events));

  }

}
