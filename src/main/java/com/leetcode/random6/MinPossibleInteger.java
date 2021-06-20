/*

package com.leetcode.random6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class MinPossibleInteger {

  class Pair {

    int idx;
    char ch;

    public Pair(int idx, char ch) {
      this.idx = idx;
      this.ch = ch;
    }
  }

  class Node {

    char ch;
    Node next;

    public Node(char ch, Node next) {
      this.next = next;
      this.ch = ch;
    }
  }

  public String _minInteger(String num, int k) {

    HashMap<Character, TreeSet<Integer>> map = new HashMap<>();
    for (int i = 0; i < num.length(); i++) {
      TreeSet<Integer> set = map.getOrDefault(num.charAt(i), new TreeSet<>());
      set.add(i);
      map.put(num.charAt(i), set);
    }
    StringBuffer b = new StringBuffer();
    for (int i = 0; i < num.length(); i++) {
      boolean appended = false;
      for (char next = '0'; next <= '9'; next++) {
        TreeSet<Integer> set = map.get(next);
        if (set != null) {
          Integer higher = set.higher(i);
          if (set.contains(i)) {
            higher = i;
          }
          if (higher != null && higher - i <= k) {
            // window i - higher
            b.append(next);
            map.get(next).remove(higher);
            if (map.get(next).isEmpty()) {
              map.remove(next);
            }
            for (int j = i; j <= higher && j < num.length(); j++) {
              char ch = num.charAt(j);
              if (map.get(ch) != null) {
                map.get(ch).remove(j);
                map.get(ch).add(j + 1);
              }
            }
            k -= (higher - i);
            appended = true;
            break;
          }
        }
      }
      if (!appended) {
        break;
      }
    }
    List<Pair> list = new ArrayList<>();
    for (char key : map.keySet()) {
      list.addAll(map.get(key).stream().map(integer -> new Pair(integer, key)).collect(Collectors.toSet()));
    }
    Collections.sort(list, Comparator.comparingInt(o -> o.idx));
    for (Pair p : list) {
      b.append(p.ch);
    }
    return b.toString();
  }


  public static void main(String args[]) {
    MinPossibleInteger m = new MinPossibleInteger();
    System.out.println(m.minInteger("9438957234785635408", 23));
  }

  private String minInteger(String s, int k) {
    Node head = new Node(s.charAt(0), null);
    Node temp = head;
    for (int i = 1; i < s.length(); i++) {
      head.next = new Node(s.charAt(i), null);
      head = head.next;
    }

  }

}

*/
