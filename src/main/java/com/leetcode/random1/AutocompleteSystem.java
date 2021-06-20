package com.leetcode.random1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class AutocompleteSystem {


  HashMap<String, Integer> countMap = new HashMap<>();

  class Trie {

    HashMap<Character, Trie> map = new HashMap<>();
    boolean isLeaf;

    public void insert(String str, int idx) {
      if (idx >= str.length()) {
        this.isLeaf = true;
        return;
      }
      char ch = str.charAt(idx);
      Trie child = map.get(ch);
      if (child == null) {
        child = new Trie();
        map.put(ch, child);
      }
      child.insert(str, idx + 1);
    }

    public List<String> getStringsStartingWith(String prefix, int idx) {
      if (idx >= prefix.length()) {
        dfs(map.get(prefix.charAt(idx - 1)), new StringBuffer());
        for (int i = 0; i < list.size(); i++) {
          list.set(i, prefix.substring(0, prefix.length() - 2) + list.get(i));
        }
        return list;
      }
      char ch = prefix.charAt(idx);
      if (map.containsKey(ch)) {
        return map.get(ch).getStringsStartingWith(prefix, idx + 1);
      } else {
        return new ArrayList<>();
      }
    }

    List<String> list = new ArrayList<>();

    private void dfs(Trie trie, StringBuffer buffer) {
      if (trie.isLeaf) {
        list.add(buffer.toString());

      }
      for (char ch : trie.map.keySet()) {
        buffer.append(ch);
        dfs(map.get(ch), buffer);
        buffer.deleteCharAt(buffer.length() - 1);
      }
    }
  }

  Trie trie = new Trie();

  public AutocompleteSystem(String[] sentences, int[] times) {

    for (int i = 0; i < sentences.length; i++) {
      countMap.put(sentences[i], times[i]);
      trie.insert(sentences[i], 0);
      set.add(sentences[i]);
    }
  }

  StringBuffer buffer = new StringBuffer();

  class Pair {

    String str;
    int hotIndex;

    public Pair(String str, int idx) {
      this.str = str;
      this.hotIndex = idx;
    }
  }

  HashSet<String> set = new HashSet<>();

  public List<String> input(char c) {
    if (c == '#') {
      countMap.put(buffer.toString(), countMap.getOrDefault(buffer.toString(), 0) + 1);
      // trie.insert(buffer.toString(), 0);
      set.add(buffer.toString());
      buffer.setLength(0);

      return new ArrayList<>();
    }
    buffer.append(c);
    List<String> list = new ArrayList<>();
    for (String s : set) {
      if (s.startsWith(buffer.toString())) {
        list.add(s);
      }
    }
    List<Pair> pairList = list.stream().map(s -> new Pair(s, countMap.get(s))).collect(Collectors.toList());
    Collections.sort(pairList, (o1, o2) -> {
      if (o1.hotIndex == o2.hotIndex) {
        return o1.str.compareTo(o2.str);
      }
      return Integer.compare(o1.hotIndex, o2.hotIndex);
    });

    List<String> ans = new ArrayList<>();
    for (int i = 0; i < 3 && i < pairList.size(); i++) {
      ans.add(pairList.get(i).str);
    }
    return ans;
  }
}
