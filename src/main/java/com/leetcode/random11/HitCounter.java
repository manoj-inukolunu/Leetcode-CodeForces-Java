package com.leetcode.random11;

import java.util.ArrayDeque;

public class HitCounter {


  ArrayDeque<Integer> queue = new ArrayDeque<>();

  public HitCounter() {

  }

  public void add(int timestamp) {
    queue.add(timestamp);
  }

  public int count(int timestamp) {
    while (!queue.isEmpty() && queue.peekFirst() < timestamp - 60) {
      queue.pollFirst();
    }
    return queue.size();
  }

  public static void main(String args[]) {
    HitCounter h = new HitCounter();
    h.count(0);
    h.add(60);
    System.out.println(h.count(60));
  }
}

