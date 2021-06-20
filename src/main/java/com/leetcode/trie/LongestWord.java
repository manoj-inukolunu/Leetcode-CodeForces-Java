package com.leetcode.trie;

import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @author manoji on 5/14/20.
 */
public class LongestWord {


  class TrieNode {

    HashMap<Character, TrieNode> map = new HashMap<>();
    private boolean isLeaf = false;

    private boolean prefixExists(String prefix, int index) {
      if (index >= prefix.length()) {
        return isLeaf;
      }
      Character ch = prefix.charAt(index);

      if (map.containsKey(ch)) {
        return map.get(ch).prefixExists(prefix, index + 1);
      } else {
        return false;
      }
    }

    private void insert(String word, int index) {
      if (index >= word.length()) {
        isLeaf = true;
        return;
      }
      Character ch = word.charAt(index);
      TrieNode l = map.get(ch);
      if (l == null) {
        l = new TrieNode();
        map.put(ch, l);
      }
      l.insert(word, index + 1);
    }
  }


  public String longestWord(String[] words) {
    TrieNode node = new TrieNode();
    for (String str : words) {
      node.insert(str, 0);
    }
    boolean found;
    PriorityQueue<String> pr = new PriorityQueue<>((o1, o2) -> {
      if (o1.length() > o2.length()) {
        return -1;
      } else if (o1.length() == o2.length()) {
        return o1.compareTo(o2);
      } else {
        return 1;
      }
    });
    for (String word : words) {
      found = true;
      for (int i = 0; i <= word.length(); i++) {
        String str = word.substring(0, i);
        if (!str.equalsIgnoreCase("") && !node.prefixExists(str, 0)) {
          found = false;
          break;
        }
      }

      if (found) {
        pr.add(word);
      }
    }
    return pr.poll();
  }

  public static void main(String args[]) {
    LongestWord l = new LongestWord();

    System.out.println(l.longestWord(new String[]{"w", "wo", "wor", "worl", "world"}));
  }

}
