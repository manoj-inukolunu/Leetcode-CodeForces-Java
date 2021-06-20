package com.leetcode.random3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class AlienDictionary {

  public String alienOrder(String[] words) {
    HashMap<Character, Set<Character>> map = new HashMap<>();
    for (int i = 0; i + 1 < words.length; i++) {
      String first = words[i];
      String second = words[i + 1];
      if (second.length() < first.length() && first.startsWith(second)) {
        return "";
      }
      for (char ch : first.toCharArray()) {
        if (!map.containsKey(ch)) {
          map.put(ch, new HashSet<>());
        }
      }
      for (char ch : second.toCharArray()) {
        if (!map.containsKey(ch)) {
          map.put(ch, new HashSet<>());
        }
      }
      for (int j = 0; j < first.length(); j++) {
        if (j < second.length() && first.charAt(j) != second.charAt(j)) {
          Set<Character> set = map.getOrDefault(first.charAt(j), new HashSet<>());
          set.add(second.charAt(j));
          map.put(first.charAt(j), set);
          break;
        } else if (j < second.length() && !map.containsKey(first.charAt(j))) {
          map.put(first.charAt(j), new HashSet<>());
        }
      }
    }
    HashMap<Character, Queue<Character>> graph = new HashMap<>();
    HashMap<Character, Integer> incoming = new HashMap<>();
    for (char ch : map.keySet()) {
      for (char c : map.get(ch)) {
        incoming.put(c, incoming.getOrDefault(c, 0) + 1);
      }
      graph.put(ch, new LinkedList<>(map.get(ch)));
    }
    Set<Character> out = new HashSet<>();
    for (char ch : map.keySet()) {
      if (!incoming.containsKey(ch)) {
        out.add(ch);
      }
    }
    return khans(graph, out, incoming);
  }

  private String khans(HashMap<Character, Queue<Character>> graph, Set<Character> out,
      HashMap<Character, Integer> incoming) {
    LinkedHashSet<Character> set = new LinkedHashSet();
    while (!out.isEmpty()) {
      char next = out.iterator().next();
      out.remove(next);
      set.add(next);
      if (graph.containsKey(next)) {
        for (char ch : graph.get(next)) {
          if (incoming.containsKey(ch)) {
            incoming.put(ch, incoming.get(ch) - 1);
            if (incoming.get(ch) == 0) {
              incoming.remove(ch);
              out.add(ch);
              set.add(ch);
            }
          }
        }
      }
    }
    if (incoming.isEmpty()) {
      StringBuffer buffer = new StringBuffer();
      set.stream().forEach(character -> buffer.append(character));
      return buffer.toString();
    }
    return "";
  }

  public static void main(String args[]) {
    AlienDictionary a = new AlienDictionary();
    String[] words = new String[]{"wnlb"};
    System.out.println(a.alienOrder(words));
  }

}
