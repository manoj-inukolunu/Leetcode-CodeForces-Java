package com.leetcode.random;

import com.google.common.collect.Lists;
import java.util.HashMap;
import java.util.List;

public class MaxLengthUnique {


  int ans = 0;

  public int maxLength(List<String> arr) {
    dfs(arr, 0, new HashMap<>());
    return ans;
  }

  private void dfs(List<String> arr, int idx, HashMap<Character, Integer> map) {
    if (idx >= arr.size()) {
      int count = 0;
      for (char key : map.keySet()) {
        count += map.get(key);
      }
      ans = Math.max(ans, count);
      return;
    }

    dfs(arr, idx + 1, map);
    HashMap<Character, Integer> currMap = new HashMap<>();
    String str = arr.get(idx);
    boolean canInclude = true;
    for (int i = 0; i < str.length(); i++) {
      if (currMap.containsKey(str.charAt(i))) {
        canInclude = false;
        break;
      } else {
        currMap.put(str.charAt(i), currMap.getOrDefault(str.charAt(i), 0) + 1);
      }
    }
    if (canInclude) {
      boolean prev = true;
      for (char ch : currMap.keySet()) {
        if (map.containsKey(ch)) {
          prev = false;
          break;
        }
      }

      if (prev) {
        map.putAll(currMap);
        dfs(arr, idx + 1, map);
        for (char key : currMap.keySet()) {
          map.remove(key);
        }
      }
    }
  }

  public static void main(String args[]) {
    MaxLengthUnique m = new MaxLengthUnique();
    List<String> list = Lists.newArrayList("abcdefghijklmnopqrstuvwxyz");
    System.out.println(m.maxLength(list));
  }

}
