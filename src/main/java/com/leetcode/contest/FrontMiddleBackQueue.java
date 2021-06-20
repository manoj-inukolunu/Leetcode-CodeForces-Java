package com.leetcode.contest;

import java.util.ArrayList;
import java.util.List;

public class FrontMiddleBackQueue {

  List<Integer> queue = new ArrayList<>();

  public FrontMiddleBackQueue() {

  }

  public void pushFront(int val) {
    queue.add(0, val);
  }

  public void pushMiddle(int val) {
    if (queue.size() % 2 != 0) {
      int idx = (queue.size() + 1) / 2;
      queue.add(idx - 1, val);
    } else {
      queue.add(queue.size() / 2, val);
    }
  }

  public void pushBack(int val) {
    queue.add(val);
  }

  public int popFront() {
    if (!queue.isEmpty()) {
      return queue.remove(0);
    } else {
      return -1;
    }
  }

  public int popMiddle() {
    if (queue.size() % 2 != 0) {
      int idx = (queue.size() + 1) / 2;
      return queue.remove(idx);
    } else {
      return queue.remove(queue.size() / 2);
    }
  }

  public int popBack() {
    if (!queue.isEmpty()) {
      return queue.remove(queue.size() - 1);
    } else {
      return -1;
    }
  }
}

