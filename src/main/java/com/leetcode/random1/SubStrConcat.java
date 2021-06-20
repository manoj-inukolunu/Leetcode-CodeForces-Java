package com.leetcode.random1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubStrConcat {

  public List<Integer> findSubstring(String s, String[] words) {

    HashMap<String, Integer> map = new HashMap<>();
    int len = words[0].length() * words.length;
    for (String str : words) {
      map.put(str, map.getOrDefault(str, 0) + 1);
    }
    List<Integer> ans = new ArrayList<>();
    for (int i = 0; i + len <= s.length(); i++) {
      String str = s.substring(i, i + len);
      if (found(str, map, words[0].length())) {
        ans.add(i);
      }
    }
    return ans;
  }

  private boolean found(String str, HashMap<String, Integer> map, int len) {
    HashMap<String, Integer> curr = new HashMap<>();
    int i = 0;
    while (i < str.length() && i + len <= str.length()) {
      String s = str.substring(i, i + len);
      if (map.containsKey(s) && curr.getOrDefault(s, 0) < map.get(s)) {
        curr.put(s, curr.getOrDefault(s, 0) + 1);
      } else {
        return false;
      }
      i = i + len;
    }
    return true;
  }

  public static void main(String args[]) {
    SubStrConcat s = new SubStrConcat();
    System.out.println(s.findSubstring("barfoofoobarthefoobarman", new String[]{"bar", "foo", "the"}));
  }

}
