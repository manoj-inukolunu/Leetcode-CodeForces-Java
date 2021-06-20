package com.leetcode.random;

import java.util.*;

public class BeforeAndAfterPuzzle {

  public List<String> beforeAndAfterPuzzles(String[] phrases) {
    Set<String> set = new HashSet();
    List<String[]> list = new ArrayList<>();
    for (int i = 0; i < phrases.length; i++) {
      list.add(phrases[i].split(" "));
    }
    for (int i = 0; i < list.size(); i++) {
      String[] a = list.get(i);
      for (int j = 0; j < list.size(); j++) {
        if (i != j && list.get(j)[0].trim().equalsIgnoreCase(a[a.length - 1])) {
          merge(set, a, list.get(j));
        }
      }
    }
    List<String> ans = new ArrayList<>(set);
    Collections.sort(ans);
    return ans;
  }

  private void merge(Set<String> set, String[] a, String[] strings) {
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < a.length - 1; i++) {
      buffer.append(a[i]);
    }
    for (int j = 0; j < strings.length; j++) {
      buffer.append(strings[j]);
    }
    set.add(buffer.toString());
  }

  public static void main(String args[]) {
    BeforeAndAfterPuzzle b = new BeforeAndAfterPuzzle();
    System.out.println(b.beforeAndAfterPuzzles(new String[]{"a", "b", "a"}));
  }

}
