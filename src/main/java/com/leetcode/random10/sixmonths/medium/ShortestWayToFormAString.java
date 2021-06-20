package com.leetcode.random10.sixmonths.medium;

import java.util.HashMap;
import java.util.TreeMap;

public class ShortestWayToFormAString {

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

  public int shortestWay(String source, String target) {
    HashMap<Character, TreeMap<Integer, Integer>> map = new HashMap<>();
    for (int i = 0; i < source.length(); i++) {
      TreeMap<Integer, Integer> indexMap = map.getOrDefault(source.charAt(i), new TreeMap<>());
      indexMap.put(i, i);
      map.put(source.charAt(i), indexMap);
    }
    int count = 0, i = 0;
    StringBuffer buffer = new StringBuffer();
    while (i < target.length()) {
      while (isSubSeq(map, buffer.toString()) && i < target.length()) {
        buffer.append(target.charAt(i));
        i++;
      }
      if (i == target.length() && isSubSeq(map, buffer.toString())) {
        count++;
        break;
      }
      buffer.deleteCharAt(buffer.length() - 1);
      i--;
      if (buffer.length() == 0) {
        return -1;
      }
      count++;
      buffer.setLength(0);
    }

    return count;
  }


  public static void main(String args[]) {
    ShortestWayToFormAString s = new ShortestWayToFormAString();
    System.out.println(s.shortestWay("xyz", "xzyxz"));

//    System.out.println(s.shortestWay("xyz", "xzyxz"));
  }

}
