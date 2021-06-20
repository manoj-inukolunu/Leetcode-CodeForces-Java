package com.leetcode.random6;

import java.util.LinkedList;

public class MRUQueue {

  LinkedList<Integer> list = new LinkedList<>();

  public MRUQueue(int n) {
    for (int i = 1; i <= n; i++) {
      list.addLast(n);
    }
  }

  public int fetch(int k) {
    int val = list.get(k - 1);
    System.out.println(val);
    list.remove(k - 1);
    list.add(val);
    return val;
  }

  public static void main(String args[]) {

  }
}