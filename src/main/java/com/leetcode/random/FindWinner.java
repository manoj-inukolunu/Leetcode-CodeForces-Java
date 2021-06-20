package com.leetcode.random;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public class FindWinner {

  public int getWinner(int[] arr, int k) {
    int max = Integer.MIN_VALUE;
    for (int num : arr) {
      max = Math.max(num, max);
    }
    if (k >= arr.length) {
      return max;
    }
    Deque<Integer> queue = new LinkedList<>();
    for (int num : arr) {
      queue.add(num);
    }
    HashMap<Integer, Integer> count = new HashMap<>();
    while (true) {
      int first = queue.poll();
      int second = queue.poll();
      if (first == max || second == max) {
        return max;
      }
      if (first > second) {
        queue.addFirst(first);
        queue.add(second);
        count.put(first, count.getOrDefault(first, 0) + 1);
        if (count.get(first) == k) {
          return first;
        }
      } else {
        queue.addFirst(second);
        queue.add(first);
        count.put(second, count.getOrDefault(second, 0) + 1);
        if (count.get(second) == k) {
          return second;
        }
      }
    }
  }

  public static void main(String args[]) {
    int[] arr = new int[]{1, 11, 22, 33, 44, 55, 66, 77, 88, 99};
    FindWinner f = new FindWinner();
    System.out.println(f.getWinner(arr, 1000000000));
  }

}
