package com.leetcode.queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class MinDel {

  public int minDeletions(String s) {
    HashMap<Character, Integer> map = new HashMap();
    for (int i = 0; i < s.length(); i++) {
      map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
    }
    int ans = 0;
    List<Integer> list = new ArrayList(map.values());
    TreeMap<Integer, Integer> tMap = new TreeMap<>(Collections.reverseOrder());
    for (int i = 0; i < list.size(); i++) {
      tMap.put(list.get(i), tMap.getOrDefault(list.get(i), 0) + 1);
    }
    Integer start = tMap.firstKey();
    while (start != null) {
      int toDec = tMap.get(start) - 1;
      ans += (toDec);
      tMap.put(start, 1);
      if (toDec != 0 && start - 1 != 0) {
        tMap.put(start - 1, tMap.getOrDefault(start - 1, 0) + toDec);
      }
      start = tMap.higherKey(start);
    }

    return ans;
  }


  public static void main(String args[]) {
    MinDel m = new MinDel();
    System.out.println(m.minDeletions("bbcebab"));
  }

}
