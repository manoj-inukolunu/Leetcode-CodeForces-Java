package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class StreamChecker {

  Deque<Character> deque = new ArrayDeque<>();

  class Trie {

    HashMap<Character, Trie> map = new HashMap();
    boolean leaf = false;

    public void insert(String str, int index) {
      if (index >= str.length()) {
        return;
      }
      if (index == str.length() - 1) {
        leaf = true;
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

  Trie trie;

  public StreamChecker(String[] words) {
    trie = new Trie();
    for (String str : words) {
      trie.insert(new StringBuffer(str).reverse().toString(), 0);
    }
  }

  public boolean query(char letter) {
    deque.addFirst(letter);
    Trie curr = trie;
    for (char ch : deque) {
      if (curr.leaf) {
        return true;
      }
      if (curr.map.containsKey(ch)) {
        curr = curr.map.get(ch);
      } else {
        return false;
      }
    }
    return curr.leaf;
  }

  public static void main(String args[]) {
    StreamChecker streamChecker = new StreamChecker(new String[]{"ab", "ba", "aaab", "abab", "baa"});
    streamChecker.query('a');          // return false
    streamChecker.query('a');          // return false
    streamChecker.query('a');          // return false
    streamChecker.query('a');          // return true, because 'cd' is in the wordlist
    streamChecker.query('a');          // return false
    streamChecker.query('b');          // return true, because 'f' is in the wordlist
    streamChecker.query('g');          // return false
    streamChecker.query('h');          // return false
    streamChecker.query('i');          // return false
    streamChecker.query('j');          // return false
    streamChecker.query('k');          // return false
    streamChecker.query('l');          // return true, because 'kl' is in the wordlist

  }
}
