package com.leetcode.random;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class SmallestEquivalentString {

  public String smallestEquivalentString(String A, String B, String S) {

    HashMap<Character, TreeSet<Character>> graph = new HashMap<>();
    for (int i = 0; i < A.length(); i++) {
      Character c1 = A.charAt(i);
      Character c2 = B.charAt(i);
      TreeSet<Character> set = graph.getOrDefault(c1, new TreeSet<>());
      set.add(c2);
      set.add(c1);
      graph.put(c1, set);
      set = graph.getOrDefault(c2, new TreeSet<>());
      set.add(c1);
      set.add(c2);
      graph.put(c2, set);
    }

    HashMap<Character, Character> minMap = new HashMap<>();
    StringBuffer buffer = new StringBuffer("abcdefghijklmnopqrstuvwxyz");
    for (int i = 0; i < buffer.length(); i++) {
      dfs(buffer.charAt(i), buffer.charAt(i), graph, minMap, new HashSet<Character>());
    }
    buffer = new StringBuffer();
    for (int i = 0; i < S.length(); i++) {
      if (minMap.containsKey(S.charAt(i))) {
        buffer.append(minMap.get(S.charAt(i)));
      } else {
        buffer.append(S.charAt(i));
      }
    }
    return buffer.toString();
  }

  private void dfs(char start, char curr, HashMap<Character, TreeSet<Character>> graph, HashMap<Character, Character> minMap,
      HashSet<Character> visited) {
    if (minMap.containsKey(start) && start != curr) {
      if (curr < minMap.get(start)) {
        minMap.put(start, curr);
      }
    } else {
      if (graph.containsKey(start)) {
        minMap.put(start, graph.get(start).first());
      }
    }
    if (!visited.contains(curr)) {
      visited.add(curr);

      if (graph.containsKey(curr)) {
        for (char next : graph.get(curr)) {
          if (!visited.contains(next)) {
            dfs(start, next, graph, minMap, visited);
          }
        }
      }
    }
  }

  public static void main(String args[]) {
    SmallestEquivalentString s = new SmallestEquivalentString();
    System.out.println(s.smallestEquivalentString("leetcode", "programs", "sourcecode"));
  }


}
