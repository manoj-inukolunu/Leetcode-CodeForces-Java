package com.leetcode.random;

import com.google.common.collect.Lists;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SentenceSimilarity2 {

  public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
    if (words1.length != words2.length) {
      return false;
    }

    HashMap<String, Set<String>> map = new HashMap<>();
    for (int i = 0; i < pairs.size(); i++) {
      List<String> curr = pairs.get(i);
      Set<String> set = map.getOrDefault(curr.get(0), new HashSet<>());
      set.add(curr.get(1));
      set.add(curr.get(0));
      map.put(curr.get(0), set);
      set = map.getOrDefault(curr.get(1), new HashSet<>());
      set.add(curr.get(0));
      set.add(curr.get(1));
      map.put(curr.get(1), set);
    }
    HashSet<String> visited = new HashSet<>();
    Set<Set<String>> collector = new HashSet<>();
    for (String str : map.keySet()) {
      if (!visited.contains(str)) {
        dfs(str, map, visited);
        collector.add(new HashSet<>(visited));
      }
      visited.clear();
    }
    for (int i = 0; i < words1.length; i++) {
      boolean found = false;
      for (Set<String> set : collector) {
        if (set.contains(words1[i]) && set.contains(words2[i])) {
          found = true;
          break;
        }
      }
      if (!found) {
        return false;
      }
    }

    return true;
  }

  private void dfs(String curr, HashMap<String, Set<String>> map, HashSet<String> visited) {
    if (!visited.contains(curr)) {
      visited.add(curr);
      for (String next : map.get(curr)) {
        if (!visited.contains(next) && !curr.equalsIgnoreCase(next)) {
          dfs(next, map, visited);
        }
      }
    }
  }

  public static void main(String args[]) {
    SentenceSimilarity2 s = new SentenceSimilarity2();
    String[] words1 = new String[]{"great", "acting", "skills"};
    String[] words = new String[]{"fine", "drama", "talent"};
    List<List<String>> pairs = Lists.newArrayList(Lists.newArrayList("great", "good"), Lists.newArrayList("fine", "good"),
        Lists.newArrayList("acting", "drama"), Lists.newArrayList("skills", "talent"));
    System.out.println(s.areSentencesSimilarTwo(words1, words, pairs));
  }

}
