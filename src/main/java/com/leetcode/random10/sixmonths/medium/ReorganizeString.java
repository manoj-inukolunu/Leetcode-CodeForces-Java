package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class ReorganizeString {

  class Pair {

    Character ch;
    int count;

    public Pair(Character ch, int count) {
      this.ch = ch;
      this.count = count;
    }
  }

  public String reorganizeString(String S) {

    PriorityQueue<Pair> pr = new PriorityQueue<>((o1, o2) -> -Integer.compare(o1.count, o2.count));

    HashMap<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < S.length(); i++) {
      map.put(S.charAt(i), map.getOrDefault(S.charAt(i), 0) + 1);
    }

    for (Character ch : map.keySet()) {
      pr.add(new Pair(ch, map.get(ch)));
    }

    StringBuffer buffer = new StringBuffer();
    Character prev = null;
    while (!pr.isEmpty()) {
      List<Pair> hold = new ArrayList<>();
      while (prev != null && !pr.isEmpty() && pr.peek().ch == prev) {
        hold.add(pr.poll());
      }
      if (pr.isEmpty()) {
        return "";
      }
      Pair top = pr.poll();
      buffer.append(top.ch);
      top = new Pair(top.ch, top.count - 1);
      if (top.count != 0) {
        pr.add(top);
      }
      prev = top.ch;
      pr.addAll(hold);
    }
    return buffer.toString();
  }

  public static void main(String args[]) {
    ReorganizeString r = new ReorganizeString();

    System.out.println(r.reorganizeString("aaab"));
  }

}
