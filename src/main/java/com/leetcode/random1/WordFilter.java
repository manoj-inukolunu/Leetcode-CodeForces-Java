package com.leetcode.random1;

import java.util.HashMap;
import java.util.TreeSet;

public class WordFilter {

  class Trie {

    HashMap<Character, Trie> map = new HashMap<>();
    boolean leaf;
    TreeSet<Integer> set = new TreeSet<>();

    void insert(String str, int wordIndex, int start) {
      this.set.add(wordIndex);
      if (start >= str.length()) {
        this.leaf = true;
        return;
      }
      char ch = str.charAt(start);
      Trie t = map.get(ch);
      if (t == null) {
        t = new Trie();
        map.put(ch, t);
      }
      map.get(ch).insert(str, wordIndex, start + 1);
    }


    int search(String word, int idx) {
      if (idx >= word.length()) {
        return set.last();
      }
      char ch = word.charAt(idx);
      if (map.containsKey(ch)) {
        return map.get(ch).search(word, idx + 1);
      }
      return -1;
    }
  }


  Trie trie = new Trie();

  public WordFilter(String[] words) {
    for (int i = 0; i < words.length; i++) {
      String str = words[i];
      for (int j = 0; j < str.length(); j++) {
        String word = str.substring(j) + "#" + str;
        trie.insert(word, i, 0);
      }
    }
  }

  public int f(String prefix, String suffix) {
    String word = suffix + "#" + prefix;
    return trie.search(word, 0);
  }

  public static void main(String[] args) {
    WordFilter w = new WordFilter(
        new String[]{"abcaccbcaa"});
    System.out.println(w.f("ab", "abcaccbcaa"));

  }
}
