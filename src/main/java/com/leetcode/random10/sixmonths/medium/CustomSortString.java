package com.leetcode.random10.sixmonths.medium;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author manoji on 7/25/20.
 */
public class CustomSortString {

  public String customSortString(String S, String T) {
    char sArr[] = S.toCharArray();
    HashMap<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < sArr.length; i++) {
      map.put(sArr[i], i);
    }
    char tArr[] = T.toCharArray();
    Character[] tArrCh = new Character[tArr.length];
    for (int i = 0; i < tArr.length; i++) {
      tArrCh[i] = tArr[i];
    }
    Arrays.sort(tArrCh, (o1, o2) -> {
      if (map.containsKey(o1) && map.containsKey(o2)) {
        if (map.get(o1).equals(map.get(o2))) {
          return 0;
        } else if (map.get(o1) > map.get(o2)) {
          return 1;
        } else {
          return -1;
        }
      } else if (map.containsKey(o1) && !map.containsKey(o2)) {
        return 1;
      } else if (!map.containsKey(o1) && map.containsKey(o2)) {
        return -1;
      } else {
        return 1;
      }
    });
    for (int i = 0; i < tArrCh.length; i++) {
      tArr[i] = tArrCh[i];
    }
    return new String(tArr);
  }

  public static void main(String args[]) {
    CustomSortString c = new CustomSortString();
    System.out.println(c.customSortString("exv", "xwvee"));
  }


}
