package com.leetcode.random3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class RearrangeKDist {

  public String rearrangeString(String s, int k) {
    HashMap<Character, Integer> map = new HashMap<>();
    for (char ch : s.toCharArray()) {
      map.put(ch, map.getOrDefault(ch, 0) + 1);
    }
    PriorityQueue<Entry<Character, Integer>> p = new PriorityQueue<>((o1, o2) -> -Integer.compare(o1.getValue(), o2.getValue()));
    p.addAll(map.entrySet());
    StringBuffer buffer = new StringBuffer();
    HashMap<Character, Integer> last = new HashMap<>();
    int idx = 0;
    List<Entry<Character, Integer>> hold = new ArrayList<>();
    while (!p.isEmpty()) {
      Entry<Character, Integer> curr = p.poll();
      if (!last.containsKey(curr.getKey())) {
        buffer.append(curr.getKey());
        last.put(curr.getKey(), idx);
        curr.setValue(curr.getValue() - 1);
        if (curr.getValue() != 0) {
          p.add(curr);
        }
        p.addAll(hold);
        idx++;
        hold.clear();
      } else if (idx - last.get(curr.getKey()) >= k) {
        buffer.append(curr.getKey());
        last.put(curr.getKey(), idx);
        curr.setValue(curr.getValue() - 1);
        if (curr.getValue() != 0) {
          p.add(curr);
        }
        p.addAll(hold);
        hold.clear();
        idx++;
      } else {
        hold.add(curr);
      }
    }
    if (!hold.isEmpty()) {
      return "";
    }
    return buffer.toString();
  }

  public static void main(String args[]) {
    RearrangeKDist r = new RearrangeKDist();
    System.out.println(r.rearrangeString("aaadbbcc", 2));
  }

}
