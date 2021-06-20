package com.leetcode.contest;

import java.util.ArrayList;
import java.util.List;

public class OrderedStream {

  class Pair {

    int id;
    String value;

    public Pair(int id, String value) {
      this.id = id;
      this.value = value;
    }
  }

  List<Pair> list = new ArrayList();

  int ptr = 1;

  public OrderedStream(int n) {
    for (int i = 0; i <= n; i++) {
      list.add(new Pair(i, null));
    }
  }

  public List<String> insert(int id, String value) {
    List<String> ans = new ArrayList<>();

    Pair pair = list.get(id);
    pair.value = value;
    list.set(id, pair);
    if (list.get(ptr).value != null) {
      while (ptr < list.size()) {
        if (list.get(ptr).value == null) {
          break;
        }
        ans.add(list.get(ptr).value);
        ptr++;
      }
    }
    return ans;
  }

  public static void main(String args[]) {
    OrderedStream o = new OrderedStream(5);
    System.out.println(o.insert(3, "ccccc"));
    System.out.println(o.insert(1, "aaaaa"));
    System.out.println(o.insert(2, "bbbbb"));
    System.out.println(o.insert(5, "eeeee"));
    System.out.println(o.insert(4, "ddddd"));
  }
}
