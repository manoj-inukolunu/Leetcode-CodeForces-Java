package com.leetcode.random10.sixmonths.medium;

import java.util.HashMap;

public class AllOne {

  HashMap<String, Integer> map = new HashMap<>();
  int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
  String maxStr = "", minStr = "";
  HashMap<Integer, String> rMap = new HashMap<>();


  /**
   * Initialize your data structure here.
   */
  public AllOne() {

  }

  /**
   * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
   */
  public void inc(String key) {
    int count = map.getOrDefault(key, 0);
    map.put(key, count + 1);
    rMap.put(count + 1, key);
    count++;
    if (count > max) {
      max = count;
      maxStr = key;
    }
    if (key.equalsIgnoreCase(minStr)) {
      String nextMin = rMap.get(count - 2);
      minStr = nextMin;
      min = count - 2;
    }
    if (count < min) {
      min = count;
      minStr = key;
    }
  }

  /**
   * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
   */
  public void dec(String key) {
    if (map.containsKey(key)) {
      int count = map.get(key);
      if (count == 1) {
        map.remove(key);
        rMap.remove(count - 1);
      } else {
        map.put(key, count - 1);
        rMap.put(count - 1, key);
      }
      if (count == max) {
        max = count - 1;
        maxStr = key;
      }
      if (count == min) {
        if (rMap.containsKey(count + 1)) {
          min = count + 1;
          minStr = rMap.get(count + 1);
        } else {
          min = 0;
          minStr = "";
        }
      }
      count--;
      if (count < min && count != 0) {
        min = count;
        minStr = key;
      }
    }
  }

  /**
   * Returns one of the keys with maximal value.
   */
  public String getMaxKey() {
    return maxStr;
  }

  /**
   * Returns one of the keys with Minimal value.
   */
  public String getMinKey() {
    return minStr;
  }

  public static void main(String args[]) {
    AllOne a = new AllOne();
    a.inc("a");
    a.inc("b");
    a.inc("c");
    a.inc("d");
    a.inc("a");
    a.inc("b");
    a.inc("c");
    a.inc("d");
    a.inc("c");
    a.inc("d");
    a.inc("d");
    a.inc("a");

//    System.out.println(a.getMaxKey());
    System.out.println(a.getMinKey());

  }
}
