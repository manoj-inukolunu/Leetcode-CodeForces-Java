package com.leetcode.trie;

import com.google.common.collect.Lists;
import java.util.HashMap;
import java.util.List;

/**
 * @author manoji on 5/14/20.
 */
public class ReplaceWord {


  class Pair {

    String root;
    boolean found;

    Pair(String root, boolean isLeaf) {
      this.root = root;
      this.found = isLeaf;
    }
  }

  class TrieNode {

    HashMap<Character, TrieNode> map = new HashMap();
    boolean isLeaf;


    public Pair getRoot(String word, int index) {
      if (index >= word.length()) {
        return new Pair("", isLeaf);
      }
      Character ch = word.charAt(index);
      if (map.containsKey(ch)) {
        TrieNode node = map.get(ch);
        if (node.isLeaf) {
          return new Pair(ch + "", true);
        }
        Pair pair = map.get(ch).getRoot(word, index + 1);
        return new Pair(ch + pair.root, pair.found);
      } else if (isLeaf) {
        return new Pair("", isLeaf);
      } else {
        return new Pair("", false);
      }
    }


    public void insert(String word, int index) {
      if (index >= word.length()) {
        isLeaf = true;
        return;
      }
      Character ch = word.charAt(index);
      TrieNode trieNode = map.get(ch);
      if (trieNode == null) {
        trieNode = new TrieNode();
        map.put(ch, trieNode);
      }
      trieNode.insert(word, index + 1);
    }

  }

  public String replaceWords(List<String> dict, String sentence) {
    StringBuffer buffer = new StringBuffer();

    TrieNode node = new TrieNode();

    for (String str : dict) {
      node.insert(str, 0);
    }

    String[] strings = sentence.split(" ");
    for (String str : strings) {
      buffer.append(" ");
      Pair root = node.getRoot(str, 0);
      if (root.found && !root.root.equalsIgnoreCase("")) {
        buffer.append(root.root);
      } else {
        buffer.append(str);
      }
    }
    return buffer.toString();
  }


  public static void main(String args[]) {
    ReplaceWord r = new ReplaceWord();

    List<String> dict = Lists.newArrayList("a", "aa", "aaa", "aaaa");

    System.out.println(r.replaceWords(dict, "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa"));
  }

}
