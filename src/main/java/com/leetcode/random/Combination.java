package com.leetcode.random;

import java.util.*;

public class Combination {

  private HashMap<Integer, List<String>> getUntil(String chars, int len) {
    if (len == 1) {
      HashMap<Integer, List<String>> map = new HashMap();
      List<String> list = new ArrayList();
      for (int i = 0; i < chars.length(); i++) {
        list.add(chars.charAt(i) + "");
      }
      map.put(1, list);
      return map;
    }
    HashMap<Integer, List<String>> map = getUntil(chars, len - 1);
    List<String> curr = new ArrayList();
    List<String> prev = map.get(len - 1);
    for (int i = 0; i < prev.size(); i++) {
      String prevS = prev.get(i);
      StringBuffer buff = new StringBuffer(prevS);
      char last = prevS.charAt(prevS.length() - 1);
      for (int j = chars.indexOf(last); j < chars.length(); j++) {
        if (chars.charAt(j) != last) {
          buff.append(chars.charAt(j));
          curr.add(buff.toString());
          buff.deleteCharAt(buff.length() - 1);
        }

      }
    }
    map.remove(len - 1);
    map.put(len, curr);
    return map;
  }

  public static void main(String args[]) {
    Combination c = new Combination();
    System.out.println(c.getUntil("abc", 2));
  }

}
