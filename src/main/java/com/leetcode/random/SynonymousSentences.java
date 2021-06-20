package com.leetcode.random;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SynonymousSentences {

  TreeSet<String> ans = new TreeSet<>();
  Set<Set<String>> components = new HashSet<>();

  public List<String> generateSentences(List<List<String>> synonyms, String text) {

    String[] data = text.split(" ");
    HashMap<String, Set<String>> map = new HashMap<>();
    for (int i = 0; i < synonyms.size(); i++) {
      String s1 = synonyms.get(i).get(0);
      String s2 = synonyms.get(i).get(1);
      Set<String> set = map.getOrDefault(s1, new HashSet<>());
      set.add(s2);
      map.put(s1, set);
      set = map.getOrDefault(s2, new HashSet<>());
      set.add(s1);
      map.put(s2, set);
    }
    HashSet<String> visited = new HashSet<>();
    for (String key : map.keySet()) {
      if (!visited.contains(key)) {
        Set<String> curr = new HashSet<>();
        dfs(key, map, visited, curr);
        components.add(curr);
      }
    }

    backtrack(data, 0, new ArrayList<>());
    return new ArrayList<>(ans);
  }

  private void dfs(String node, HashMap<String, Set<String>> map, HashSet<String> visited, Set<String> curr) {
    visited.add(node);
    curr.add(node);
    if (map.containsKey(node) && !visited.contains(map.get(node))) {
      for (String str : map.get(node)) {
        if (!visited.contains(str)) {
          dfs(str, map, visited, curr);
        }
      }
    }
  }

  private void backtrack(String[] data, int idx, ArrayList<String> list) {
    if (idx >= data.length) {
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < list.size(); i++) {
        builder.append(list.get(i));
        if (i != list.size() - 1) {
          builder.append(" ");
        }
      }
      ans.add(builder.toString());
      return;
    }
    String str = data[idx];
    boolean done = false;
    for (Set<String> set : components) {
      if (set.contains(str)) {
        done = true;
        for (String s : set) {
          list.add(s);
          backtrack(data, idx + 1, list);
          list.remove(list.size() - 1);
        }
      }
    }
    if (!done) {
      list.add(str);
      backtrack(data, idx + 1, list);
      list.remove(list.size() - 1);
    }
  }

  public static void main(String args[]) {

    List<List<String>> syn = Lists.newArrayList(Lists.newArrayList("happy", "joy"), Lists.newArrayList("sad", "sorrow"), Lists.newArrayList("joy",
        "cheerful"));
    String text = "I am happy today but was sad yesterday";
    SynonymousSentences s = new SynonymousSentences();
    s.generateSentences(syn, text).stream().forEach(System.out::println);
    System.out.println(3 / 2);
  }

}
