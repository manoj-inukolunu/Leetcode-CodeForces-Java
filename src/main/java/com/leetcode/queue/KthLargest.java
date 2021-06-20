package com.leetcode.queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author manoji on 6/27/20.
 */
public class KthLargest {

  PriorityQueue<Integer> priorityQueue = new PriorityQueue(Collections.reverseOrder());
  int valK;

  int curr;

  public KthLargest(int k, int[] nums) {
    valK = k;
    for (int i = 0; i < nums.length; i++) {
      priorityQueue.add(nums[i]);
    }
    curr = getKthLargest();
  }

  public int add(int val) {
    priorityQueue.add(val);
    if (val < curr && valK < priorityQueue.size()) {
      return curr;
    } else {
      return getKthLargest();
    }
  }

  private int getKthLargest() {
    if (!priorityQueue.isEmpty()) {
      List<Integer> ints = new ArrayList();
      for (int i = 0; i < valK; i++) {
        if (!priorityQueue.isEmpty()) {
          Integer integer = priorityQueue.poll();
          ints.add(integer);
        }
      }
      for (Integer integer : ints) {
        if (integer != null) {
          priorityQueue.add(integer);
        }
      }
      return ints.get(ints.size() - 1);
    }
    return Integer.MIN_VALUE;
  }

  public static void main(String args[]) {
    KthLargest k = new KthLargest(2, new int[]{0});
    System.out.println(k.add(-1));
    System.out.println(k.add(1));
    System.out.println(k.add(-2));
    System.out.println(k.add(-4));
    System.out.println(k.add(3));

  }
}
