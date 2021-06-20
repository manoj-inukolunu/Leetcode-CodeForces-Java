package com.leetcode.queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author manoji on 5/4/20.
 */
public class MonotonicQueue {

  public static void main(String args[]) {
    Deque<Integer> queue = new LinkedList<>();
    int[] arr = new int[]{3, 1, 2, 4, 3};
    for (int i = 0; i < arr.length; i++) {
      while (!queue.isEmpty() && queue.getLast() > arr[i]) {
        queue.pollLast();
      }
      queue.push(arr[i]);
      System.out.println(queue);
    }

    System.out.println(queue);
  }

}
