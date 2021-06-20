package com.leetcode.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindAndReplaceInAPattern {

  public List<String> findAndReplacePattern(String[] words, String pattern) {
    List<String> ans = new ArrayList<>();
    for (int i = 0; i < words.length; i++) {
      String curr = words[i];
      if (matches(curr, pattern)) {
        ans.add(curr);
      }
    }
    return ans;
  }

  private boolean matches(String str, String pattern) {
    HashMap<Character, Character> map = new HashMap<>();
    //HashMap<Character, Character> rMap = new HashMap<>();
    for (int i = 0; i < str.length(); i++) {
      if (i >= pattern.length()) {
        break;
      }
      char curr = str.charAt(i);
      if (!map.containsKey(curr)) {
        map.put(curr, pattern.charAt(i));
       // rMap.put(pattern.charAt(i), curr);
      } else {
        Character mapped = map.get(curr);
        if (mapped != pattern.charAt(i)) {
          return false;
        }
      }
    }
    return true;
  }

}
