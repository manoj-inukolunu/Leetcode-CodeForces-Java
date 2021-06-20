package com.leetcode.string;

import java.util.HashMap;

public class LargestTime {

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

  HashMap<Character, Integer> map = new HashMap();

  private boolean digits(String str) {
    HashMap<Character, Integer> temp = (HashMap<Character, Integer>) map.clone();
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == ':') {
        continue;
      }
      if (!temp.containsKey(str.charAt(i))) {
        return false;
      } else {
        int val = temp.get(str.charAt(i)) - 1;
        if (val <= 0) {
          temp.remove(str.charAt(i));
        } else {
          temp.put(str.charAt(i), temp.get(str.charAt(i)) - 1);
        }
      }
    }
    return temp.isEmpty();
  }

  public String largestTimeFromDigits(int[] arr) {
    String start = "23:59";
    int count = 1440;
    for (int i = 0; i < arr.length; i++) {
      map.put((char) (arr[i] + '0'), map.getOrDefault((char) (arr[i] + '0'), 0) + 1);
    }
    while (count-- > 0) {
      if (digits(start)) {
        return start;
      }
      start = prev(start);
    }
    return "";
  }

  public static void main(String args[]) {
    LargestTime l = new LargestTime();
    System.out.println(l.largestTimeFromDigits(new int[]{1, 2, 3, 4}));
  }

}
