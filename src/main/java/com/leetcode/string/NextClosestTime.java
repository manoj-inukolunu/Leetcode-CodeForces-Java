package com.leetcode.string;

import java.util.HashMap;
import java.util.HashSet;

public class NextClosestTime {

  private String next(String curr) {
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


  public String nextClosestTime(String time) {
    HashSet<Character> set = new HashSet<>();
    for (int i = 0; i < time.length(); i++) {
      set.add(time.charAt(i));
    }
    String nextTime = next(time);
    while (true) {
      boolean found = true;
      for (int i = 0; i < nextTime.length(); i++) {
        if (!set.contains(nextTime.charAt(i))) {
          found = false;
          break;
        }
      }
      if (found) {
        return nextTime;
      }
      nextTime = next(nextTime);
    }
  }

}
