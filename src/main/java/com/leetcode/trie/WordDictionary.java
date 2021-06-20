package com.leetcode.trie;

import java.util.HashMap;
import jdk.nashorn.internal.runtime.WithObject;

/**
 * @author manoji on 5/14/20.
 */
public class WordDictionary {


  HashMap<Character, WordDictionary> map = new HashMap<>();

  private boolean isLeaf;

  /**
   * Initialize your data structure here.
   */
  public WordDictionary() {

  }

  /**
   * Adds a word into the data structure.
   */
  public void addWord(String word) {
    addWord(word, 0);
  }

  private void addWord(String word, int index) {
    if (index >= word.length()) {
      isLeaf = true;
      return;
    }
    Character ch = word.charAt(index);
    WordDictionary w = map.get(ch);
    if (w == null) {
      w = new WordDictionary();
      map.put(ch, w);
    }
    w.addWord(word, index + 1);

  }

  /**
   * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
   */
  public boolean search(String word) {
    return search(word, 0);
  }


  private boolean search(String word, int index) {
    if (index >= word.length()) {
      return isLeaf;
    }
    Character ch = word.charAt(index);

    if (ch == '.') {
      for (Character key : map.keySet()) {
        if (map.get(key).search(word, index + 1)) {
          return true;
        }
      }
    } else if (map.containsKey(ch)) {
      return map.get(ch).search(word, index + 1);
    }
    return false;
  }

  public static void main(String args[]) {
    WordDictionary w = new WordDictionary();

    w.addWord("bad");
    w.addWord("dad");
    w.addWord("mad");

    System.out.println(w.search("pad"));
    System.out.println(w.search("bad"));
    System.out.println(w.search(".ad"));
    System.out.println(w.search("b.."));
  }


}
