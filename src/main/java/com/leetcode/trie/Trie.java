package com.leetcode.trie;

import java.util.HashMap;

/**
 * @author manoji on 5/14/20.
 */
public class Trie {

  HashMap<Character, Trie> map = new HashMap<>();

  public boolean isLeaf() {
    return isLeaf;
  }

  public void setLeaf(boolean leaf) {
    isLeaf = leaf;
  }

  private boolean isLeaf = false;

  /**
   * Initialize your data structure here.
   */
  public Trie() {

  }


  /**
   * Inserts a word into the trie.
   */
  public void insert(String word) {
    insert(word, 0);
  }

  private void insert(String str, int index) {
    if (index == str.length()) {
      setLeaf(true);
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

  /**
   * Returns if the word is in the trie.
   */
  public boolean search(String word) {
    return search(word, 0);
  }

  private boolean search(String word, int index) {
    if (index >= word.length()) {
      return isLeaf();
    }
    Character ch = word.charAt(index);
    if (map.containsKey(ch)) {
      return map.get(ch).search(word, index + 1);
    } else {
      return false;
    }
  }

  /**
   * Returns if there is any word in the trie that starts with the given prefix.
   */
  public boolean startsWith(String prefix) {
    return startsWith(prefix, 0);
  }

  private boolean startsWith(String word, int index) {
    if (index >= word.length()) {
      return true;
    }
    Character ch = word.charAt(index);
    if (map.containsKey(ch)) {
      return map.get(ch).startsWith(word, index + 1);
    } else {
      return false;
    }
  }


  public static void main(String args[]) {
    Trie trie = new Trie();

    trie.insert("apple");
    System.out.println(trie.search("apple"));
    System.out.println(trie.search("app"));
    System.out.println(trie.startsWith("app"));

    trie.insert("app");

    System.out.println(trie.search("app"));


  }

}
