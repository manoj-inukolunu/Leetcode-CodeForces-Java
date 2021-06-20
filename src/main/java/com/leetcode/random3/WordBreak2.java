package com.leetcode.random3;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak2 {


  List<String> ans = new ArrayList<>();

  public List<String> wordBreak(String s, List<String> wordDict) {
    Set<String> words = new HashSet<>(wordDict);
    HashMap<String, List<String>> map = new HashMap<>();
    return dfs(s, words, 0, s.length(), map);
  }

  private List<String> dfs(String s, Set<String> words, int start, int end, HashMap<String, List<String>> map) {
    String str = s.substring(start, end);
    if (map.containsKey(str)) {
      return map.get(str);
    }
    if (start >= end) {
      List<String> list = new ArrayList<>();
      list.add("");
      return list;
    }
    List<String> list = new ArrayList<>();
    for (int i = start; i <= end; i++) {
      String left = s.substring(start, i);
      if (!left.isEmpty() && words.contains(left)) {
        List<String> ret = dfs(s, words, i, end, map);
        for (int j = 0; j < ret.size(); j++) {
          StringBuffer buffer = new StringBuffer(left);
          buffer.append(" ").append(ret.get(j));
          list.add(buffer.toString().trim());
        }
      }
    }
    map.put(str, list);
    return list;
  }

  public static void main(String args[]) {
    WordBreak2 w = new WordBreak2();
    System.out.println(w.wordBreak("pineapplepenapple", Lists.newArrayList("apple", "pen", "applepen", "pine", "pineapple")));
  }


}
