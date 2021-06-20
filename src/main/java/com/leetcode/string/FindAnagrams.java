package com.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.lang.model.type.ArrayType;

/**
 * @author manoji on 5/17/20.
 */
public class FindAnagrams {

  public List<Integer> findAnagrams(String s, String p) {
    List<Integer> ans = new ArrayList<>();
    if (p.length() > s.length()) {
      return ans;
    }
    char[] parr = p.toCharArray();
    HashMap<Character, Integer> pMap = new HashMap<>();
    for (int i = 0; i < parr.length; i++) {
      pMap.put(parr[i], pMap.getOrDefault(parr[i], 0) + 1);
    }
    char[] sArr = s.toCharArray();
    HashMap<Character, Integer> sMap = new HashMap<>();
    for (int i = 0; i < p.length(); i++) {
      sMap.put(sArr[i], sMap.getOrDefault(s.charAt(i), 0) + 1);
    }
    if (sMap.equals(pMap)) {
      ans.add(0);
    }
    for (int i = 1; i + parr.length <= sArr.length; i++) {
      int val = sMap.get(sArr[i - 1]);
      if (val > 1) {
        sMap.put(sArr[i - 1], sMap.get(sArr[i - 1]) - 1);
      } else {
        sMap.remove(sArr[i - 1]);
      }
      char key = sArr[i + p.length() - 1];
      sMap.put(key, sMap.getOrDefault(key, 0) + 1);
      if (check(sMap, pMap)) {
        ans.add(i);
      }
    }
    return ans;
  }

  private boolean check(HashMap<Character, Integer> sMap, HashMap<Character, Integer> pMap) {
    return sMap.equals(pMap);
  }


  public static void main(String args[]) {
    FindAnagrams f = new FindAnagrams();
    System.out.println(f.findAnagrams("abab", "ab"));
  }

}
