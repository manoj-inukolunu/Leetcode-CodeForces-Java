package com.leetcode.random;

import java.util.TreeMap;

public class FindAndReplaceInAString {

  private boolean matches(String str, int curr, String[] strings) {
    for (int i = curr, j = 0; i < str.length() && j < strings[0].length(); i++, j++) {
      if (str.charAt(i) != strings[0].charAt(j)) {
        return false;
      }
    }
    return true;
  }

  public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
    StringBuffer buffer = new StringBuffer();
    TreeMap<Integer, String[]> map = new TreeMap();
    for (int i = 0; i < indexes.length; i++) {
      map.put(indexes[i], new String[]{sources[i], targets[i]});
    }
    Integer curr = map.firstKey();
    int sPtr = 0;
    while (sPtr < S.length()) {
      if (curr != null && sPtr == curr) {
        if (matches(S, sPtr, map.get(curr))) {
          buffer.append(map.get(curr)[1]);
          sPtr += map.get(curr)[0].length();
        } else {
          buffer.append(S.charAt(sPtr++));
        }
        curr = map.higherKey(curr);
      } else {
        buffer.append(S.charAt(sPtr++));
      }
    }
    return buffer.toString();
  }
  /*
  "vmokgggqzp"
[3,5,1]
["kg","ggq","mo"]
["s","so","bfr"]
   */

  public static void main(String args[]) {
    FindAndReplaceInAString f = new FindAndReplaceInAString();
    int[] indexes = new int[]{3, 5, 1};
    String[] sources = new String[]{"kg", "ggq", "mo"};
    String[] targets = new String[]{"s", "so", "bfr"};
    System.out.println(f.findReplaceString("vmokgggqzp", indexes, sources, targets));
  }

}
