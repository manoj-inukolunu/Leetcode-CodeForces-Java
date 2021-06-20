package com.leetcode.random11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BraceExpansion {

  /*TreeMap<Integer, Integer> res = new TreeMap();

  private int getMax(int time, LinkedHashMap<Integer, Integer> lMap) {
    List<Entry<Integer, Integer>> list = new ArrayList();
    Integer max = lMap.values().stream().sorted(Collections.reverseOrder()).findFirst().get();
    for (Entry<Integer, Integer> entry : lMap.entrySet()) {
      if (entry.getValue() == max) {
        list.add(entry);
      }
    }
    for (int i = 0; i < list.size(); i++) {
      res.lowerKey(time);
    }
  }*/

  public String[] expand(String S) {
    List<String> str = expand(new StringBuffer(S));
    String[] strings = new String[str.size()];
    for (int i = 0; i < str.size(); i++) {
      strings[i] = str.get(i);
    }
    return strings;
  }

  private List<String> expand(StringBuffer buff) {
    int first = buff.indexOf("{", 0);
    List<String> list = new ArrayList();
    if (first != -1) {
      int second = buff.indexOf("}", first);
      String str = buff.substring(second+1);
      String[] curr = buff.substring(first+1, second).split(",");
      for (int i = 0; i < curr.length; i++) {
        list.addAll(expand(new StringBuffer(buff.substring(0,first)+curr[i] + str)));
      }
      return list;
    } else {
      list.add(buff.toString());
      return list;
    }
  }

  public static void main(String args[]) {
    BraceExpansion b = new BraceExpansion();
    System.out.println(Arrays.toString(b.expand("{a,b}c{d,e}f")));
  }

}
