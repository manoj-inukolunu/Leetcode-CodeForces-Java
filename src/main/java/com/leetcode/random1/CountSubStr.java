package com.leetcode.random1;

import java.util.HashMap;

public class CountSubStr {

  public int countSubstrings(String s, String t) {

    HashMap<String, Integer> map = new HashMap<>();
    for (int i = 0; i < t.length(); i++) {
      for (int j = i + 1; j <= t.length(); j++) {
        String str = t.substring(i, j);
        map.put(str, map.getOrDefault(str, 0) + 1);
      }
    }

    HashMap<String, Integer> sMap = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      for (int j = i + 1; j <= s.length(); j++) {
        String str = s.substring(i, j);
        sMap.put(str, sMap.getOrDefault(str, 0) + 1);
      }
    }

    int count = 0;
    for (String str : sMap.keySet()) {
      char[] arr = str.toCharArray();
      for (int i = 0; i < arr.length; i++) {
        char temp = arr[i];
        for (int k = 0; k < 26; k++) {
          char curr = (char) ('z' - k);
          if (curr != temp) {
            arr[i] = curr;
            String check = new String(arr);
            if (map.containsKey(check)) {
              count += (map.get(check) * sMap.get(str));
            }
          }
          arr[i] = temp;
        }
      }
    }

    return count;
  }


  public static void main(String args[]) {
    CountSubStr c = new CountSubStr();
    System.out.println(c.countSubstrings("abe", "bbc"));
  }
}
