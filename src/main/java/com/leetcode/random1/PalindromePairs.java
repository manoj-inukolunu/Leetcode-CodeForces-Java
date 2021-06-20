package com.leetcode.random1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PalindromePairs {

  List<Pair> hold = new ArrayList<>();

  class Pair {

    int idx;
    String str;
    String word;

    public Pair(int idx, String str, String word) {
      this.idx = idx;
      this.str = str;
      this.word = word;
    }
  }

  class Trie {

    int idx;
    HashMap<Character, Trie> map = new HashMap<>();
    boolean leaf;
    String word;

    void insert(String str, int i, int idx) {
      if (i < 0) {
        this.idx = idx;
        this.leaf = true;
        this.word = str;
        return;
      }
      char ch = str.charAt(i);
      Trie t = map.get(ch);
      if (t == null) {
        t = new Trie();
        map.put(ch, t);
      }
      map.get(ch).insert(str, i - 1, idx);
    }

    void startsWith(String str, int idx, StringBuffer buffer) {
      if (leaf) {
        hold.add(new Pair(this.idx, buffer.toString(), word));
      }
      if (idx >= str.length()) {
        dfs(this, buffer);
        return;
      }
      char ch = str.charAt(idx);
      if (map.containsKey(ch)) {
        buffer.append(ch);
        map.get(ch).startsWith(str, idx + 1, buffer);
        buffer.deleteCharAt(buffer.length() - 1);
      } else {
        dfs(this, buffer);
      }
    }


    private void dfs(Trie trie, StringBuffer buffer) {
      if (trie.leaf) {
        hold.add(new Pair(trie.idx, buffer.toString(), trie.word));

      }
      for (char ch : trie.map.keySet()) {
        buffer.append(ch);
        trie.map.get(ch).dfs(trie.map.get(ch), buffer);
        buffer.deleteCharAt(buffer.length() - 1);
      }
    }

  }

  public List<List<Integer>> palindromePairs(String[] words) {
    Set<List<Integer>> ans = new HashSet<>();
    Trie trie = new Trie();
    int emptyIdx = -1;
    for (int i = 0; i < words.length; i++) {
      if (!words[i].isEmpty()) {
        trie.insert(words[i], words[i].length() - 1, i);
      } else {
        emptyIdx = i;
      }
    }
    for (int i = 0; i < words.length; i++) {
      if (words[i].isEmpty()) {
        continue;
      }
      trie.startsWith(words[i], 0, new StringBuffer());
      for (int j = 0; j < hold.size(); j++) {
        if (hold.get(j).idx != i && isPalin(words[i] + hold.get(j).word)) {
          List<Integer> l = new ArrayList<>();
          l.add(i);
          l.add(hold.get(j).idx);
          ans.add(l);
        }
      }
      hold.clear();
    }
    addEmpty(words, ans, emptyIdx);
    return new ArrayList<>(ans);
  }

  private void addEmpty(String[] words, Set<List<Integer>> ans, int emptyIdx) {
    if (emptyIdx != -1) {
      for (int i = 0; i < words.length; i++) {
        if (i != emptyIdx && isPalin(words[i])) {
          List<Integer> l = new ArrayList();
          l.add(i);
          l.add(emptyIdx);
          ans.add(new ArrayList(l));
          l.clear();
          l.add(emptyIdx);
          l.add(i);
          ans.add(new ArrayList(l));
          l.clear();
        }
      }
    }
  }

  public static boolean isPalin(String str) {
    for (int i = 0; i < str.length() / 2; i++) {
      if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
        return false;
      }
    }
    return true;
  }

  public static void main(String args[]) {
    PalindromePairs p = new PalindromePairs();
    String[] arr = new String[]{"a", "b", "c", "ab", "ac", "aa"};
    System.out.println(p.palindromePairs(arr));
  }

}
