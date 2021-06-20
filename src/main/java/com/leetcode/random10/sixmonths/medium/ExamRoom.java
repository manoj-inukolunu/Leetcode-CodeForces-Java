package com.leetcode.random10.sixmonths.medium;

import java.util.TreeMap;

public class ExamRoom {

  TreeMap<Integer, Boolean> treeMap = new TreeMap<>();
  int size = 0;

  public ExamRoom(int N) {
    this.size = N - 1;
  }

  int getLeft(int idx) {
    Integer integer = treeMap.lowerKey(idx);
    if (integer == null) {
      return idx;
    } else {
      return (idx - integer) / 2;
    }
  }

  int getRight(int idx) {
    Integer integer = treeMap.higherKey(idx);
    if (integer == null) {
      return size - idx;
    } else {
      return (integer - idx) / 2;
    }
  }

  public int seat() {
    if (treeMap.isEmpty()) {
      treeMap.put(0, true);
      return 0;
    }
    int max = Integer.MIN_VALUE;
    int ret = -1;
    for (int key : treeMap.keySet()) {
      int left = getLeft(key);
      int right = getRight(key);
      if (left == right) {
        if (left > max) {
          max = left;
          ret = key - left;
        }
      } else if (left > right) {
        if (left > max) {
          max = left;
          ret = key - left;
        }
      } else if (left < right) {
        if (right > max) {
          max = right;
          ret = key + right;
        }
      }
    }
    treeMap.put(ret, true);
    return ret;
  }

  public void leave(int p) {
    treeMap.remove(p);
  }

  public static void main(String args[]) {
    ExamRoom e = new ExamRoom(10);
    System.out.println(e.seat());
    System.out.println(e.seat());
    System.out.println(e.seat());
    System.out.println(e.seat());
    e.leave(4);
    System.out.println(e.seat());
  }

}
