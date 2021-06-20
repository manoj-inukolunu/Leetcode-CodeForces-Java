package com.leetcode.random;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class LogSystem {

  class Node {

    TreeMap<Integer, Node> map = new TreeMap<>();
    Set<Integer> idsUnder = new HashSet<>();
    boolean leaf;

    public Set<Integer> insert(String timeStamp, int logId, int idx) {
      if (idx >= timeStamp.length() - 1) {
        leaf = true;
        idsUnder.add(logId);
        return idsUnder;
      }
      int next = timeStamp.indexOf(":", idx);
      next = next == -1 ? timeStamp.length() : next;
      Integer data = Integer.parseInt(timeStamp.substring(idx, next));
      Node node = map.get(data);
      if (node == null) {
        node = new Node();
        map.put(data, node);
      }
      Set<Integer> ids = node.insert(timeStamp, logId, next + 1);
      idsUnder.addAll(ids);
      return idsUnder;
    }

    public Set<Integer> collect(int level, int curr, String start, String end) {
      Set<Integer> ret = new HashSet<>();
      if (curr == level) {
        for (int val : getRange(start, end, level)) {
          if (map.containsKey(val)) {
            ret.addAll(map.get(val).idsUnder);
          }
        }
        return ret;
      }
      for (int node : map.keySet()) {
        ret.addAll(map.get(node).collect(level, curr + 1, start, end));
      }
      return ret;
    }

    private List<Integer> getRange(String start, String end, int level) {
      String[] starts = start.split(":");
      String[] ends = end.split(":");
      List<Integer> ret = new ArrayList<>();
      for (int i = Integer.parseInt(starts[level]); i <= Integer.parseInt(ends[level]); i++) {
        ret.add(i);
      }
      return ret;
    }
  }


  Node root = new Node();

  TreeMap<Long, HashSet<Integer>> map = new TreeMap<>();

  public LogSystem() {

  }

  private Long getTime(String timeStamp, int granularity) {
    String[] strings = timeStamp.split(":");
    Long time = 0L;
    int year = Integer.parseInt(strings[0]);
    List<Long> times = new ArrayList<>();
    for (int i = 2000; i <= year; i++) {
      if (year % 4 == 0) {
        time += (366 * 86400);
      } else {
        time += (365 * 86400);
      }
    }
    times.add(time);
    int month = Integer.parseInt(strings[1]);
    YearMonth yearMonthObject = YearMonth.of(year, month);
    int daysInMonth = yearMonthObject.lengthOfMonth();
    times.add((long) (daysInMonth * 86400));
    int days = Integer.parseInt(strings[2]);
    times.add((long) (days * 86400));
    int hours = Integer.parseInt(strings[3]);
    times.add((long) (hours * 3600));
    int minutes = Integer.parseInt(strings[4]);
    times.add((long) (minutes * 60));
    int seconds = Integer.parseInt(strings[5]);
    times.add((long) seconds);
    for (int i = 0; i < times.size(); i++) {
      if (i >= granularity) {
        time += times.get(i);
      }
    }
    return time;
  }

  TreeMap<String, HashSet<Integer>> sMap = new TreeMap<>();

  public void put(int id, String timestamp) {
    String[] splits = timestamp.split(":");
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < splits.length; i++) {
      buffer.append(splits[i]);
      HashSet<Integer> set = sMap.getOrDefault(buffer.toString(), new HashSet<>());
      set.add(id);
      sMap.put(buffer.toString(), set);
    }
  }

  public List<Integer> retrieve(String start, String end, String granularity) {
    Set<Integer> set = new HashSet<>();
    String startTimes[] = start.split(":");
    String endTmes[] = end.split(":");

    start = "";
    end = "";

    for (int i = 0; i <= getLevel(granularity); i++) {
      start += startTimes[i];
      end += endTmes[i];
    }
    int startLen = start.length();
    while (start != null && start.compareTo(end) <= 0) {
      if (sMap.containsKey(start) && start.length() >= startLen) {
        set.addAll(sMap.get(start));
      }
      start = sMap.higherKey(start);
    }
    return new ArrayList<>(set);
  }

  private int getLevel(String granularity) {
    switch (granularity) {
      case "Year":
        return 0;
      case "Month":
        return 1;
      case "Day":
        return 2;
      case "Hour":
        return 3;
      case "Minute":
        return 4;
      default:
        return 5;
    }
  }

  public static void main(String args[]) {

    LogSystem l = new LogSystem();
    l.put(1, "2017:01:01:23:59:59");
    l.put(2, "2017:01:02:23:59:59");
    l.put(3, "2016:01:01:00:00:00");
    System.out.println(l.retrieve("2017:01:01:23:59:58", "2017:01:02:23:59:58", "Second"));
    System.out.println(l.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year"));
    System.out.println(l.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour"));

  }
}
