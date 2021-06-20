package com.leetcode.random;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import org.jetbrains.annotations.NotNull;

public class VowelSpellChecker {

  class Trie {

    boolean leaf;
    String trieWord;
    LinkedHashMap<Character, Trie> map = new LinkedHashMap<>();

    void insert(String str, int index) {
      if (index >= str.length()) {
        leaf = true;
        trieWord = str;
        return;
      }
      Character ch = str.charAt(index);
      Trie node = map.get(ch);
      if (node == null) {
        node = new Trie();
        map.put(ch, node);
      }
      node.insert(str, index + 1);
    }

    String matches(String word, int index) {
      if (index >= word.length()) {
        if (leaf) {
          return trieWord;
        }
        return "";
      }
      Character ch = word.charAt(index);
      if (map.containsKey(ch)) {
        String res = map.get(ch).matches(word, index + 1);
        if (res.isEmpty() && vowels.indexOf(ch) != -1) {
          return getString(word, index);
        } else {
          return res;
        }
      } else if (vowels.indexOf(ch) != -1) {
        return getString(word, index);
      } else {
        return "";
      }
    }

    @NotNull
    private String getString(String word, int index) {
      for (Character key : map.keySet()) {
        if (vowels.indexOf(key) != -1) {
          String res = map.get(key).matches(word, index + 1);
          if (!res.isEmpty()) {
            return res;
          }
        }
      }
      return "";
    }
  }

  String vowels = "aeiou";

  public String[] spellchecker(String[] wordlist, String[] queries) {
    HashMap<String, String> caseI = new HashMap();
    HashSet<String> caseS = new HashSet();
    HashMap<String, LinkedHashSet<String>> vMap = new HashMap<>();
    Trie trie = new Trie();
    int j = 0;
    for (int i = wordlist.length - 1; i >= 0; i--) {
      String str = wordlist[i];
      caseI.put(str.toLowerCase(), str);
      caseS.add(str);
      //trie.insert(wordlist[j++].toLowerCase(), 0);
      String vString = replaceAll(wordlist[j].toLowerCase());
      LinkedHashSet set = vMap.getOrDefault(vString, new LinkedHashSet<>());
      set.add(wordlist[j++]);
      vMap.put(vString, set);
    }

    String[] ans = new String[queries.length];
    for (int i = 0; i < queries.length; i++) {
      String word = queries[i];
      if (caseS.contains(word)) {
        ans[i] = word;
      } else if (caseI.containsKey(word.toLowerCase())) {
        ans[i] = caseI.get(word.toLowerCase());
      } else {
        String vRep = replaceAll(word);
        if (vMap.containsKey(vRep)) {
          ans[i] = vMap.get(vRep).iterator().next();
        } else {
          ans[i] = "";
        }
      }
    }
    return ans;
  }

  private String replaceAll(String str) {
    StringBuffer buffer = new StringBuffer(str);
    for (int i = 0; i < buffer.length(); i++) {
      if (vowels.indexOf(buffer.charAt(i)) != -1) {
        buffer.setCharAt(i, 'a');
      }
    }
    return buffer.toString();
  }

  public static void main(String args[]) {
    VowelSpellChecker v = new VowelSpellChecker();
    String[] wordList = new String[]{"KiTe", "kite", "hare", "Hare"};
    String[] queries = new String[]{"kite", "Kite", "KiTe", "Hare", "HARE", "Hear", "hear", "keti", "keet", "keto"};

    System.out.println(Arrays.toString(v.spellchecker(wordList, queries)));
  }

}
