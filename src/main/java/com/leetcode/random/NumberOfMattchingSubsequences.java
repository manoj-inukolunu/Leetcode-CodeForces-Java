package com.leetcode.random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class NumberOfMattchingSubsequences {

  public int numMatchingSubseq(String S, String[] words) {

    HashMap<Character, TreeMap<Integer, Integer>> map = new HashMap<>();
    for (int i = 0; i < S.length(); i++) {
      TreeMap<Integer, Integer> indexMap = map.getOrDefault(S.charAt(i), new TreeMap<>());
      indexMap.put(i, i);
      map.put(S.charAt(i), indexMap);
    }
    int ans = 0;

    for (int i = 0; i < words.length; i++) {
      if (isSubSeq(map, words[i])) {
        ans++;
      }
    }
    return ans;
  }

  private boolean isSubSeq(HashMap<Character, TreeMap<Integer, Integer>> map, String word) {
    int i = 0, curr = -1;
    while (i < word.length()) {
      Character ch = word.charAt(i++);
      if (!map.containsKey(ch)) {
        return false;
      }
      if (curr == -1) {
        curr = map.get(ch).firstKey();
      } else {
        Integer higher = map.get(ch).higherKey(curr);
        if (higher == null) {
          return false;
        }
        curr = higher;
      }
    }
    return true;
  }

  public boolean isSubsequence(String s, String t) {
    List<Integer>[] idx = new List[256]; // Just for clarity
    for (int i = 0; i < t.length(); i++) {
      if (idx[t.charAt(i)] == null) {
        idx[t.charAt(i)] = new ArrayList<>();
      }
      idx[t.charAt(i)].add(i);
    }

    int prev = 0;
    for (int i = 0; i < s.length(); i++) {
      if (idx[s.charAt(i)] == null) {
        return false; // Note: char of S does NOT exist in T causing NPE
      }
      int j = Collections.binarySearch(idx[s.charAt(i)], prev);
      if (j < 0) {
        j = -j - 1;
      }
      if (j == idx[s.charAt(i)].size()) {
        return false;
      }
      prev = idx[s.charAt(i)].get(j) + 1;
    }
    return true;
  }

  public static void main(String args[]) {
    NumberOfMattchingSubsequences n = new NumberOfMattchingSubsequences();
    /*
    "rwpddkvbnnuglnagtvamxkqtwhqgwbqgfbvgkwyuqkdwhzudsxvjubjgloeofnpjqlkdsqvruvabjrikfwronbrdyyjnakstqjac"
["wpddkvbnn","lnagtva","kvbnnuglnagtvamxkqtwhqgwbqgfbvgkwyuqkdwhzudsxvju","rwpddkvbnnugln","gloeofnpjqlkdsqvruvabjrikfwronbrdyyj",
"vbgeinupkvgmgxeaaiuiyojmoqkahwvbpwugdainxciedbdkos","mspuhbykmmumtveoighlcgpcapzczomshiblnvhjzqjlfkpina",
"rgmliajkiknongrofpugfgajedxicdhxinzjakwnifvxwlokip","fhepktaipapyrbylskxddypwmuuxyoivcewzrdwwlrlhqwzikq",
"qatithxifaaiwyszlkgoljzkkweqkjjzvymedvclfxwcezqebx"]
     */
    String[] words = new String[]{"wpddkvbnn", "lnagtva", "kvbnnuglnagtvamxkqtwhqgwbqgfbvgkwyuqkdwhzudsxvju", "rwpddkvbnnugln",
        "gloeofnpjqlkdsqvruvabjrikfwronbrdyyj",
        "vbgeinupkvgmgxeaaiuiyojmoqkahwvbpwugdainxciedbdkos", "mspuhbykmmumtveoighlcgpcapzczomshiblnvhjzqjlfkpina",
        "rgmliajkiknongrofpugfgajedxicdhxinzjakwnifvxwlokip", "fhepktaipapyrbylskxddypwmuuxyoivcewzrdwwlrlhqwzikq",
        "qatithxifaaiwyszlkgoljzkkweqkjjzvymedvclfxwcezqebx"};
    System.out
        .println(n.numMatchingSubseq("rwpddkvbnnuglnagtvamxkqtwhqgwbqgfbvgkwyuqkdwhzudsxvjubjgloeofnpjqlkdsqvruvabjrikfwronbrdyyjnakstqjac", words));
  }

}
