package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class TimeMap {

  class Pair {

    String value;
    int timestamp;

    public Pair(String value, int timestamp) {
      this.value = value;
      this.timestamp = timestamp;
    }
  }

  HashMap<String, PriorityQueue<Pair>> map = new HashMap<>();

  /**
   * Initialize your data structure here.
   */
  public TimeMap() {

  }

  public void set(String key, String value, int timestamp) {
    PriorityQueue<Pair> pairs = new PriorityQueue<>(new Comparator<Pair>() {
      @Override
      public int compare(Pair o1, Pair o2) {
        return -Integer.compare(o1.timestamp, o2.timestamp);
      }
    });
    if (map.containsKey(key)) {
      pairs = map.get(key);
      pairs.add(new Pair(value, timestamp));
      map.put(key, pairs);
    } else {
      pairs.add(new Pair(value, timestamp));
      map.put(key, pairs);
    }
  }

  public String get(String key, int timestamp) {
    if (map.containsKey(key)) {
      PriorityQueue<Pair> pr = map.get(key);
      List<Pair> hold = new ArrayList<>();
      String ret = "";
      while (!pr.isEmpty()) {
        Pair curr = pr.poll();
        hold.add(curr);
        if (curr.timestamp == timestamp) {
          ret = curr.value;
          break;
        } else if (curr.timestamp < timestamp) {
          ret = curr.value;
          break;
        }
      }
      pr.addAll(hold);
      return ret;
    } else {
      return "";
    }
  }

  public static void main(String args[]) {
    TimeMap timeMap = new TimeMap();
    timeMap.set("foo", "bar", 1);
    timeMap.set("foo", "bar2", 4);
    timeMap.set("gszaw", "ztpearaw", 3);
    timeMap.set("ctondw", "gszaw", 4);
    System.out.println(timeMap.get("foo", 1));
    System.out.println(timeMap.get("foo", 3));
    System.out.println(timeMap.get("foo", 4));
    System.out.println(timeMap.get("foo", 3));
//    System.out.println(timeMap.get("foo", 3));
  }
}
