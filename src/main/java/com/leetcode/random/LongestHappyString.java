package com.leetcode.random;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class LongestHappyString {

  public class Pair {

    int count;
    char ch;

    public Pair(int count, char ch) {
      this.count = count;
      this.ch = ch;
    }
  }

  public String longestDiverseString(int a, int b, int c) {

    PriorityQueue<Pair> p = new PriorityQueue<>((o1, o2) -> -Integer.compare(o1.count, o2.count));

    p.add(new Pair(a, 'a'));
    p.add(new Pair(b, 'b'));
    p.add(new Pair(c, 'c'));

    StringBuffer buffer = new StringBuffer();
    while (true) {
      Pair curr = p.poll();
      List<Pair> hold = new ArrayList<>();
      if (curr == null) {
        break;
      }
      if (canAdd(buffer, curr)) {
        update(p, buffer, curr, 2);
      } else {
        hold.add(curr);
        Pair next = p.poll();
        if (next != null) {
          if (canAdd(buffer, next)) {
            update(p, buffer, next, 1);
          } else {
            hold.add(next);
            Pair last = p.poll();
            if (last == null) {
              break;
            } else if (canAdd(buffer, last)) {
              update(p, buffer, last, 1);
            } else {
              break;
            }
          }
        } else {
          break;
        }
      }
      if (!hold.isEmpty()) {
        p.addAll(hold);
      }
    }

    return buffer.toString();

  }

  private void update(PriorityQueue<Pair> p, StringBuffer buffer, Pair curr, int count) {
    buffer.append(curr.ch);
    curr.count--;
    if (count == 2 && curr.count > 0) {
      buffer.append(curr.ch);
      curr.count--;
    }
    if (curr.count > 0) {
      p.add(curr);
    }
  }

  private boolean canAdd(StringBuffer buffer, Pair curr) {
    if (curr.count <= 0) {
      return false;
    }
    if (buffer.length() == 0 && curr.count > 0) {
      return true;
    }
    int len = buffer.length();
    if (len < 2) {
      return true;
    }
    return !(buffer.charAt(len - 1) == curr.ch && buffer.charAt(len - 2) == curr.ch);
  }


  public static void main(String args[]) {
    LongestHappyString l = new LongestHappyString();
    System.out.println(l.longestDiverseString(7, 1, 0));
  }
}
