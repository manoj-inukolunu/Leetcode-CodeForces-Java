package com.leetcode.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KthHappy {


  public String getHappyString(int n, int k) {
    if (n == 1) {
      return k > 3 ? "" : String.valueOf("abc".charAt(k - 1));
    }
    HashMap<Integer, List<String>> res = get(n - 1);
    int curr = 1;
    for (int i = 0; i < res.get(n - 1).size(); i++) {
      StringBuffer buffer = new StringBuffer(res.get(n - 1).get(i));
      for (char next : getNext(buffer.charAt(buffer.length() - 1))) {
        if (curr == k) {
          return buffer.append(next).toString();
        }
        curr++;
      }
    }

    return "";

  }

  private HashMap<Integer, List<String>> get(int n) {
    if (n == 1) {
      List<String> list = new ArrayList<>();
      list.add("a");
      list.add("b");
      list.add("c");
      HashMap<Integer, List<String>> map = new HashMap<>();
      map.put(1, list);
      return map;
    }
    HashMap<Integer, List<String>> res = get(n - 1);
    List<String> ans = new ArrayList<>();
    for (int i = 0; i < res.get(n - 1).size(); i++) {
      StringBuffer buffer = new StringBuffer(res.get(n - 1).get(i));
      for (char next : getNext(buffer.charAt(buffer.length() - 1))) {
        ans.add(buffer.append(next).toString());
        buffer.deleteCharAt(buffer.length() - 1);
      }
    }
    res.put(n, ans);
    res.remove(n - 1);
    return res;
  }

  private List<Character> getNext(char charAt) {
    List<Character> list = new ArrayList<>();
    if (charAt == 'a') {
      list.add('b');
      list.add('c');
      return list;
    } else if (charAt == 'b') {
      list.add('a');
      list.add('c');
      return list;
    } else if (charAt == 'c') {
      list.add('a');
      list.add('b');
      return list;
    }
    return list;
  }

  public static void main(String args[]) {
    KthHappy k = new KthHappy();
    System.out.println(k.getHappyString(1, 1));
  }


}
