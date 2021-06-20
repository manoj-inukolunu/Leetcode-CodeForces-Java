package com.leetcode.random;

import java.util.TreeMap;

public class BoatsToSavePeople {

  public int numRescueBoats(int[] people, int limit) {
    TreeMap<Integer, Integer> map = new TreeMap<>();
    for (int num : people) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }
    int boats = 0;
    while (!map.isEmpty()) {
      Integer first = map.lastKey();
      int free = limit;
      free -= first;
      map.put(first, map.get(first) - 1);
      if (map.get(first) == 0) {
        map.remove(first);
      }
      if (map.containsKey(free)) {
        map.put(free, map.get(free) - 1);
        if (map.get(free) == 0) {
          map.remove(free);
        }
      } else {
        Integer second = map.lowerKey(free);
        if (second != null) {
          map.put(second, map.get(second) - 1);
          if (map.get(second) == 0) {
            map.remove(second);
          }
        }
      }
      boats++;
    }
    return boats;
  }

  public static void main(String args[]) {
    BoatsToSavePeople b = new BoatsToSavePeople();
    System.out.println(b.numRescueBoats(new int[]{2, 2}, 6));

  }

}
