package com.leetcode.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordSubSets {

  class Trie {

    HashMap<Character, Trie> map = new HashMap<>();
    boolean leaf = false;

    void insert(String str, int index) {
      if (index >= str.length()) {
        leaf = true;
        return;
      }
      Character ch = str.charAt(index);
      Trie trie = map.get(ch);
      if (trie == null) {
        trie = new Trie();
        map.put(ch, trie);
      }
      map.get(ch).insert(str, index + 1);
    }


  }

  boolean dfs(HashMap<Character, Integer> wMap, Trie trie) {
    boolean found = true;
    for (Character ch : trie.map.keySet()) {
      if (!wMap.containsKey(ch)) {
        return false;
      } else if (wMap.get(ch) == 0) {
        return false;
      } else {
        int count = wMap.get(ch);
        wMap.put(ch, count - 1);
        found = dfs(wMap, trie.map.get(ch)) && found;
        wMap.put(ch, count);
      }
    }
    return found;
  }

  public List<String> wordSubsets(String[] A, String[] B) {
    List<String> ans = new ArrayList<>();
   /* Trie trie = new Trie();
    for (String str : B) {
      trie.insert(str, 0);
    }

    for (int i = 0; i < A.length; i++) {
      HashMap<Character, Integer> map = getMap(A[i]);
      if (dfs(map, trie)) {
        ans.add(A[i]);
      }
    }*/
    HashMap<Character, Integer> bMap = new HashMap<>();
    for (String str : B) {
      HashMap<Character, Integer> curr = getMap(str);
      for (Character ch : curr.keySet()) {
        bMap.put(ch, Math.max(bMap.getOrDefault(ch, 0), curr.get(ch)));
      }
    }
    for (String str : A) {
      HashMap<Character, Integer> curr = getMap(str);
      boolean shouldAdd = true;
      for (Character ch : bMap.keySet()) {
        if (curr.containsKey(ch) && curr.get(ch) >= bMap.get(ch)) {
          continue;
        } else {
          shouldAdd = false;
          break;
        }
      }
      if (shouldAdd) {
        ans.add(str);
      }
    }
    return ans;
  }

  private HashMap<Character, Integer> getMap(String str) {
    HashMap<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < str.length(); i++) {
      map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
    }
    return map;
  }


  public static void main(String args[]) {
    String[] A = new String[]{"amazon", "apple", "facebook", "google", "leetcode"};
    String[] B = new String[]{"ec", "oc", "ceo"};

    WordSubSets w = new WordSubSets();
    System.out.println(w.wordSubsets(A, B));
  }

}
