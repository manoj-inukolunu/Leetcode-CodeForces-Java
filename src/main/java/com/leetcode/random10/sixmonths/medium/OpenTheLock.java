package com.leetcode.random10.sixmonths.medium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class OpenTheLock {

  class Pair {

    String str;
    int dist;

    public Pair(String str, int dist) {
      this.str = str;
      this.dist = dist;
    }
  }

  public int openLock(String[] deadends, String target) {
    Set<String> dead = new HashSet<>();
    String start = "0000";
    for (String str : deadends) {
      dead.add(str);
    }
    if (dead.contains(start)) {
      return -1;
    }
    HashSet<String> visited = new HashSet<>();
    Queue<Pair> queue = new LinkedList<>();
    queue.add(new Pair(start, 0));
    int ans = Integer.MAX_VALUE;
    while (!queue.isEmpty()) {
      Pair curr = queue.poll();
      if (curr.str.equals(target)) {
        ans = Math.min(ans, curr.dist);
      }
      if (!visited.contains(curr.str)) {
        visited.add(curr.str);
        for (Pair next : getNext(curr)) {
          if (!dead.contains(next.str)) {
            queue.add(next);
          }
        }
      }
    }
    return ans == Integer.MAX_VALUE ? -1 : ans;
  }

  private Set<Pair> getNext(Pair curr) {
    Set<Pair> set = new HashSet<>();
    StringBuffer buffer = new StringBuffer(curr.str);
    for (int i = 0; i < buffer.length(); i++) {
      int charAtI = Character.getNumericValue(buffer.charAt(i));
      char prev = buffer.charAt(i);
      if (charAtI + 1 >= 10) {
        charAtI = 0;
      } else {
        charAtI = charAtI + 1;
      }
      buffer.setCharAt(i, Character.forDigit(charAtI, 10));
      set.add(new Pair(buffer.toString(), curr.dist + 1));
      buffer.setCharAt(i, prev);
      charAtI = Character.getNumericValue(prev);
      if (charAtI - 1 < 0) {
        charAtI = 9;
      } else {
        charAtI = charAtI - 1;
      }
      buffer.setCharAt(i, Character.forDigit(charAtI, 10));
      set.add(new Pair(buffer.toString(), curr.dist + 1));
      buffer.setCharAt(i, prev);

    }
    return set;
  }

  public static void main(String args[]) {
    OpenTheLock o = new OpenTheLock();

    System.out.println(o.openLock(new String[]{"0000"}, "8888"));
  }

}
