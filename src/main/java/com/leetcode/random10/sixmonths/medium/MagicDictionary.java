package com.leetcode.random10.sixmonths.medium;

import java.util.HashMap;

/**
 * @author manoji on 8/2/20.
 */
public class MagicDictionary {

  class Trie {

    HashMap<Character, Trie> map = new HashMap<>();

    private boolean leaf = false;

    void insert(String str, int index) {
      if (index == str.length()) {
        leaf = true;
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

    private boolean search(String word, int index, int count) {
      if (index >= word.length() && count == 1 && this.leaf) {
        return true;
      }
      if (index >= word.length()) {
        return false;
      }
      Character ch = word.charAt(index);
      boolean val = false;
      for (Character c : map.keySet()) {
        val = val || map.get(c).search(word, index + 1, ch == c ? count : count + 1);
      }
      return val;
    }
  }

  private Trie trie;

  /**
   * Initialize your data structure here.
   */
  public MagicDictionary() {
    trie = new Trie();

  }

  /**
   * Build a dictionary through a list of words
   */
  public void buildDict(String[] dict) {
    for (String str : dict) {
      trie.insert(str, 0);
    }
  }


  /**
   * Returns if there is any word in the trie that equals to the given word after modifying exactly one character
   */
  public boolean search(String word) {
    return trie.search(word, 0, 0);
  }

  public static void main(String args[]) {
    MagicDictionary m = new MagicDictionary();

    m.buildDict(new String[]{"a", "b", "ab"});

    System.out.println(m.search("a"));
    System.out.println(m.search("b"));
    System.out.println(m.search("c"));
    System.out.println(m.search("d"));
    System.out.println(m.search("e"));
    System.out.println(m.search("f"));
    System.out.println(m.search("ab"));
    System.out.println(m.search("ba"));
    System.out.println(m.search("abc"));
  }
}
