package com.leetcode.sort;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class LongestWord {

  public String findLongestWord(String s, List<String> d) {
    HashMap<Character, TreeMap<Integer, Integer>> map = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      TreeMap<Integer, Integer> indexMap = map.getOrDefault(s.charAt(i), new TreeMap<>());
      indexMap.put(i, i);
      map.put(s.charAt(i), indexMap);
    }
    Collections.sort(d, new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        if (o1.length() == o2.length()) {
          return o1.compareTo(o2);
        } else {
          return Integer.compare(o1.length(), o2.length());
        }
      }
    });

    for (int i = d.size() - 1; i >= 0; i--) {
      if (isSubSeq(d.get(i), map)) {
        return d.get(i);
      }
    }
    return "";
  }

  private boolean isSubSeq(String word, HashMap<Character, TreeMap<Integer, Integer>> map) {
    int i = 0, curr = -1;
    while (i < word.length()) {
      Character ch = word.charAt(i++);
      if (!map.containsKey(ch)) {
        return false;
      }
      if (curr == -1) {
        curr = map.get(ch).firstKey();
      } else {
        Integer higher = map.get(ch).higherKey(curr);
        if (higher == null) {
          return false;
        }
        curr = higher;
      }
    }
    return true;
  }

  public static void main(String args[]) {
    String str = "aewfafwafjlwajflwajflwafj";
    LongestWord l = new LongestWord();
    str = l.findLongestWord(str, Lists.newArrayList("apple", "ewaf", "awefawfwaf", "awef", "awefe", "ewafeffewafewf"));
    System.out.println(str);
  }
}
