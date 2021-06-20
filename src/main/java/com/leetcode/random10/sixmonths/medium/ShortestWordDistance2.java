package com.leetcode.random10.sixmonths.medium;

import java.util.HashMap;
import java.util.TreeSet;

public class ShortestWordDistance2 {

  static class WordDistance {

    HashMap<String, TreeSet<Integer>> map = new HashMap();


    public WordDistance(String[] words) {
      int i = 0;
      for (String str : words) {
        TreeSet<Integer> queue = map.getOrDefault(str, new TreeSet<Integer>());
        queue.add(i++);
        map.put(str, queue);
      }
    }

    public int shortest(String word1, String word2) {
      TreeSet<Integer> set = map.get(word1);
      TreeSet<Integer> next = map.get(word2);
      int diff = Integer.MAX_VALUE;
      for (int val : set) {
        Integer l = next.lower(val);
        Integer r = next.higher(val);
        if (l == null) {
          diff = Math.min(Math.abs(val - r), diff);
        } else if (r == null) {
          diff = Math.min(diff, Math.abs(val - l));
        } else {
          int l1 = Math.abs(val - l);
          int r1 = Math.abs(val - r);
          diff = Math.min(Math.min(l1, r1), diff);
        }
      }
      return diff;
    }

    public static void main(String args[]) {
      WordDistance w = new WordDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"});
      System.out.println(w.shortest("coding", "practice"));
      System.out.println(w.shortest("makes", "coding"));
    }
  }

}

