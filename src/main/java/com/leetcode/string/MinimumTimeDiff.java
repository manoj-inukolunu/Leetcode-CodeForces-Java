package com.leetcode.string;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class MinimumTimeDiff {

  int getMin(String t) {
    String[] split = t.split(":");
    return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
  }

  int getDiff(String t1, String t2) {
    int straight = Math.abs(getMin(t1) - getMin(t2));
    int reverse = 1440 - straight;
    return Math.min(straight, reverse);
  }

  String next(String curr) {
    String[] splits = curr.split(":");
    int hour = Integer.parseInt(splits[0]);
    int minutes = Integer.parseInt(splits[1]) + 1;
    if (minutes >= 60 && hour == 23) {
      hour = 0;
      minutes = minutes - 60;
    } else if (minutes >= 60) {
      hour++;
      if (minutes == 60) {
        minutes = 0;
      }
    }
    return (hour < 10 ? "0" + hour : hour) + ":" + (minutes < 10 ? "0" + minutes : minutes);
  }

  String prev(String curr) {
    String[] splits = curr.split(":");
    int hour = Integer.parseInt(splits[0]);
    int minutes = Integer.parseInt(splits[1]) - 1;
    if (minutes < 0 && hour == 0) {
      hour = 23;
      minutes = 60 + minutes;
    } else if (minutes < 0) {
      hour--;
      minutes = 60 + minutes;
    }
    return (hour < 10 ? "0" + hour : hour) + ":" + (minutes < 10 ? "0" + minutes : minutes);
  }

  public int findMinDifference(List<String> timePoints) {
    HashMap<String, Integer> map = new HashMap<>();
    for (String str : timePoints) {
      map.put(str, map.getOrDefault(str, 0) + 1);
    }

    int min = Integer.MAX_VALUE;
    for (int i = 0; i < timePoints.size(); i++) {
      String str = timePoints.get(i);
      if (map.get(str) > 1) {
        return 0;
      }
      int count = 1440;
      while (count != 0) {
        String n = next(str);
        if (map.containsKey(n) && !n.equalsIgnoreCase(timePoints.get(i))) {
          min = Math.min(min, getDiff(timePoints.get(i), n));
        }
        str = n;
        count--;
      }
    }
    return min;
  }

  public static void main(String args[]) {
    MinimumTimeDiff m = new MinimumTimeDiff();
    System.out.println(m.findMinDifference(Lists.newArrayList("01:01", "02:01")));
    System.out.println(m.prev("01:11"));
    System.out.println(m.next("05:59"));
  }

}
