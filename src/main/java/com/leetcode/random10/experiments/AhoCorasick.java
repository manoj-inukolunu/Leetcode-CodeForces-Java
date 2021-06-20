package com.leetcode.random10.experiments;

import java.util.HashMap;

public class AhoCorasick {

  static class Trie {

    HashMap<Character, Trie> map = new HashMap<>();
    boolean isLeaf = false;
    HashMap<Trie, Trie> failureLink = new HashMap();
    HashMap<Trie, Trie> dictionaryLink = new HashMap<>();


    private Trie search(String pref, int index) {
      if (index >= pref.length()) {
        return null;
      }
      Character ch = pref.charAt(index);
      Trie trie = map.get(ch);
      if (index == pref.length() - 1 && trie != null) {
        return trie;
      }
      if (trie != null) {
        return trie.search(pref, index + 1);
      }
      return null;
    }

    private void insert(String str, int index) {
      if (str != null) {
        if (index >= str.length()) {
          isLeaf = true;
          return;
        }
        Character ch = str.charAt(index);
        Trie trie = map.get(ch);
        if (trie == null) {
          trie = new Trie();
          map.put(ch, trie);
        }
        trie.insert(str, index + 1);
      }
    }
  }


  public static void main(String args[]) {
    Trie trie = new Trie();
    for (String str : new String[]{"ACC", "ATC", "CAT", "GCG"}) {
      trie.insert(str, 0);
    }

    System.out.println(trie.search("AT", 0).map);


  }

}
