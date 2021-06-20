package com.leetcode.random;

import java.util.HashMap;

public class MapSum {

  class Trie {

    int val = 0;
    HashMap<Character, Trie> map = new HashMap<>();
    HashMap<String, Integer> valMap = new HashMap<>();

    private void insert(String str, int idx, int val) {
      if (idx >= str.length()) {
        valMap.put(str, val);
        return;
      }
      Character ch = str.charAt(idx);
      Trie trie = map.get(ch);
      if (trie == null) {
        trie = new Trie();
        trie.val = val;
        map.put(ch, trie);
      } else if (valMap.containsKey(str)) {
        trie.val = trie.val - valMap.get(str);
        trie.val = trie.val + val;
        map.put(ch, trie);
      } else {
        trie.val = trie.val + val;
        map.put(ch, trie);
      }
      valMap.put(str, val);
      map.get(ch).insert(str, idx + 1, val);
    }

    private int sum(String str, int idx) {
      if (idx == str.length() - 1) {
        return map.containsKey(str.charAt(idx)) ? map.get(str.charAt(idx)).val : 0;
      }
      return map.containsKey(str.charAt(idx)) ? map.get(str.charAt(idx)).sum(str, idx + 1) : 0;
    }
  }


  /**
   * Initialize your data structure here.
   */
  Trie trie = new Trie();

  public MapSum() {

  }

  public void insert(String key, int val) {
    trie.insert(key, 0, val);
  }

  public int sum(String prefix) {
    return trie.sum(prefix, 0);
  }

  public static void main(String args[]) {
    MapSum m = new MapSum();
    m.insert("apple", 3);
    System.out.println(m.sum("ap"));
    m.insert("app", 2);
    m.insert("apple", 2);
    System.out.println(m.sum("ap"));
  }
}
