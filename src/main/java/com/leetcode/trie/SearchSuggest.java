package com.leetcode.trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableMap;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author manoji on 5/26/20.
 */
public class SearchSuggest {

  private PriorityQueue<String> list = new PriorityQueue<>();

  class Trie {

    private boolean isLeaf = false;

    NavigableMap<Character, Trie> map = new TreeMap<>();


    public void insert(String word, int index) {
      if (index >= word.length()) {
        isLeaf = true;
        return;
      }
      Character ch = word.charAt(index);
      Trie node = map.get(ch);
      if (node == null) {
        node = new Trie();
        map.put(ch, node);
      }
      node.insert(word, index + 1);
    }

    private void dfs(Trie node, StringBuffer collector, HashSet<Trie> visited) {
      if (node.isLeaf && !list.contains(collector.toString())) {
        list.add(collector.toString());
      }
      if (node.map.isEmpty() && !list.contains(collector.toString())) {
        list.add(collector.toString());
        return;
      }
      visited.add(node);
      int i = 0;
      for (Character c : node.map.navigableKeySet()) {
        if (i < 3 && list.size() < 3) {
          i++;
          collector.append(c);
          if (!visited.contains(node.map.get(c))) {
            node.dfs(node.map.get(c), collector, visited);
          }
          collector.deleteCharAt(collector.length() - 1);
        }
      }
    }

    public void suggest(String prefix, int index, StringBuffer linkedList) {
      if (index >= prefix.length()) {
        return;
      }
      Character ch = prefix.charAt(index);
      linkedList.append(ch);
      Trie trie = map.get(ch);
      if (trie != null) {
        if (index == prefix.length() - 1) {
          dfs(trie, linkedList, new HashSet<>());
        } else {
          trie.suggest(prefix, index + 1, linkedList);
        }
      }
    }
  }

  public List<List<String>> suggestedProducts(String[] products, String searchWord) {
    Trie trie = new Trie();
    for (String str : products) {
      trie.insert(str, 0);
    }
    List<List<String>> ans = new ArrayList<>();
    for (int i = 1; i <= searchWord.length(); i++) {
      String s = searchWord.substring(0, i);
      trie.suggest(s, 0, new StringBuffer());
      ans.add(new ArrayList<>(list));
      list.clear();
    }
    return ans;
  }

  public static void main(String args[]) {
    SearchSuggest s = new SearchSuggest();

    String str[] = new String[]{"mobile", "mouse", "moneypot", "monitor", "mousepad"};
    String str2[] = new String[]{"havana"};
    String str3[] = new String[]{"bags", "baggage", "banner", "box", "cloths"};

    System.out.println(s.suggestedProducts(str, "mouse"));

  }

}
