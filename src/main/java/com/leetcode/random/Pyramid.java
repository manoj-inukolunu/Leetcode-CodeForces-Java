package com.leetcode.random;

import com.google.common.collect.Lists;
import java.util.*;


public class Pyramid {


  String s = "ABCDEFG";
  HashSet<String> visited = new HashSet<>();
  Set<String> set = new HashSet<>();

  public boolean pyramidTransition(String str, List<String> allowed) {
    if (str.length() == 1) {
      return true;
    }
    if (set.isEmpty()) {
      set = new HashSet<>(allowed);
    }
    boolean found = false;
    for (String next : getNext(str, set)) {
      if (!visited.contains(next)) {
        found = found || pyramidTransition(next, allowed);
      }
    }
    return found;
  }

  private Set<String> getNext(String curr, Set<String> allowed) {
    HashMap<Integer, List<Character>> map = new HashMap<>();
    for (int i = 1; i < curr.length(); i++) {
      for (int j = 0; j < s.length(); j++) {
        String str = curr.charAt(i - 1) + "" + curr.charAt(i) + s.charAt(j);
        if (allowed.contains(str)) {
          List<Character> list = map.getOrDefault(i - 1, new ArrayList<>());
          list.add(s.charAt(j));
          map.put(i - 1, list);
        }
      }
    }
    HashSet<String> next = new HashSet<>();
    getNext(map, 0, new StringBuffer(), curr.length() - 1, next);
    return next;
  }

  private void getNext(HashMap<Integer, List<Character>> map, int curr, StringBuffer stringBuffer,
      int max, Set<String> next) {
    if (curr == max) {
      next.add(stringBuffer.toString());
      return;
    }
    List<Character> list = map.get(curr);
    if (list != null) {
      for (int i = 0; i < list.size(); i++) {
        stringBuffer.append(list.get(i));
        getNext(map, curr + 1, stringBuffer, max, next);
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
      }
    }
  }


  public static void main(String args[]) {
    Pyramid p = new Pyramid();
    List<String> list = Lists.newArrayList("AAA", "AAB", "ABA", "ABB", "BAC");
    String str = "AABA";
    System.out.println(p.pyramidTransition(str, list));
  }

}
