package com.leetcode.random3;

import java.util.HashMap;

public class MInStickers {

  public int minStickers(String[] stickers, String target) {
    HashMap<Character, Integer>[] s = new HashMap[stickers.length];
    for (int i = 0; i < stickers.length; i++) {
      s[i] = getMap(stickers[i]);
    }
    int val = knapsack(s, 0, getMap(target), new HashMap<>());
    return val == Integer.MAX_VALUE ? -1 : val;
  }

  private int knapsack(HashMap<Character, Integer>[] arr, int idx, HashMap<Character, Integer> target,
      HashMap<String, Integer> dp) {
    if (idx >= arr.length) {
      if (target.isEmpty()) {
        return 0;
      }
      return Integer.MAX_VALUE;
    }

    if (target.isEmpty()) {
      return 0;
    }
    String key = getKey(idx, target);
    if (dp.containsKey(key)) {
      return dp.get(key);
    }
    int incl = Integer.MAX_VALUE;
    if (contains(arr[idx], target)) {
      HashMap<Character, Integer> map = remTarget(target, arr[idx]);
      //incl
      int val = knapsack(arr, idx, target, dp);
      if (val != Integer.MAX_VALUE) {
        incl = 1 + val;
      }
      add(target, map);
    }

    int excl = knapsack(arr, idx + 1, target, dp);
    //excl
    dp.put(key, Math.min(incl, excl));
    return Math.min(incl, excl);
  }

  private String getKey(int idx, HashMap<Character, Integer> target) {
    return idx + "|" + target.toString();
  }

  private boolean contains(HashMap<Character, Integer> map, HashMap<Character, Integer> target) {
    for (char ch : target.keySet()) {
      if (map.containsKey(ch)) {
        return true;
      }
    }
    return false;
  }

  private HashMap<Character, Integer> remTarget(HashMap<Character, Integer> target, HashMap<Character, Integer> map) {
    HashMap<Character, Integer> ret = new HashMap<>();
    for (char ch : map.keySet()) {
      if (target.containsKey(ch)) {
        ret.put(ch, target.get(ch) >= map.get(ch) ? map.get(ch) : target.get(ch));
        target.put(ch, target.get(ch) - map.get(ch));
        if (target.get(ch) <= 0) {
          target.remove(ch);
        }
      }
    }
    return ret;
  }

  private void add(HashMap<Character, Integer> target, HashMap<Character, Integer> map) {
    for (char ch : map.keySet()) {
      target.put(ch, target.getOrDefault(ch, 0) + map.get(ch));
    }
  }

  private HashMap<Character, Integer> getMap(String sticker) {
    HashMap<Character, Integer> map = new HashMap<>();
    for (char ch : sticker.toCharArray()) {
      map.put(ch, map.getOrDefault(ch, 0) + 1);
    }
    return map;
  }

  public static void main(String args[]) {
    MInStickers m = new MInStickers();
    String[] str = new String[]{"notice", "possible"};
    System.out.println(m.minStickers(str, "basicbasic"));
  }

}
