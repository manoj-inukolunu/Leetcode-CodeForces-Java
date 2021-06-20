package com.leetcode.random10.sixmonths.medium;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author manoji on 8/2/20.
 */
public class BoldTags {

  class Trie {

    HashMap<Character, Trie> map = new HashMap<>();
    boolean leaf;

    void insert(String word, int index) {
      if (index == word.length()) {
        leaf = true;
        return;
      }
      Character ch = word.charAt(index);
      Trie trie = map.get(ch);
      if (trie == null) {
        trie = new Trie();
        map.put(ch, trie);
      }
      trie.insert(word, index + 1);
    }

    public int[] search(String str, int start, int index, int len, int max) {
      if (index >= str.length()) {
        if (leaf) {
          max = Math.max(len, max);
          return new int[]{start, start + max};
        } else {
          return new int[]{-1, -1};
        }
      }
      if (leaf) {
        max = Math.max(len, max);
      }
      Character ch = str.charAt(index);
      if (map.containsKey(ch)) {
        return map.get(ch).search(str, start, index + 1, len + 1, max);
      } else if (max != -1) {
        return new int[]{start, start + max};
      }
      return new int[]{-1, -1};

    }

    public int[] search(String str, int start) {
      return search(str, start, start, 0, -1);
    }
  }


  public String addBoldTag(String s, String[] dict) {
    Trie trie = new Trie();
    for (String str : dict) {
      trie.insert(str, 0);
    }

    Stack<int[]> stack = new Stack<>();

    StringBuffer buffer = new StringBuffer(s);
    for (int i = 0; i < s.length(); i++) {
      int[] res = trie.search(s, i);
      if (res[0] != -1) {
        if (stack.isEmpty()) {
          stack.push(res);
        } else {
          int[] top = stack.peek();
          if (canMerge(top, res)) {
            top = stack.pop();
            int[] merged = merge(top, res);
            stack.push(merged);
          } else {
            stack.push(res);
          }
        }
      }
    }
    while (!stack.isEmpty()) {
      int[] curr = stack.pop();
      buffer.insert(curr[0], "<b>");
      buffer.insert(curr[1] + 3, "</b>");
    }
    return buffer.toString();
  }

  private int[] merge(int[] top, int[] res) {
    return new int[]{Math.min(top[0], res[0]), Math.max(top[1], res[1])};
  }

  private boolean canMerge(int[] top, int[] current) {
    return (current[1] == top[1] + 1 || (current[0] <= top[1] && current[1] >= top[1]) || (current[0] >= top[0] && top[1] >= current[1]));
  }

  public static void main(String args[]) {
    BoldTags b = new BoldTags();
    System.out.println(b.addBoldTag("aaabbccdef", new String[]{"abc", "123", "bcx"}));
  }

}
