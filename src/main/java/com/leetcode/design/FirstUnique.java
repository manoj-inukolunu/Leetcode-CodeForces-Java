package com.leetcode.design;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author manoji on 4/28/20.
 */
public class FirstUnique {

  private Queue<Integer> queue = new LinkedList<>();
  private LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();

  public FirstUnique(int[] nums) {
    for (int num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }
    for (int num : nums) {
      queue.add(num);
    }
  }

  public int showFirstUnique() {
    for (int key : map.keySet()) {
      if (map.get(key) == 1) {
        return key;
      }
    }
    return -1;
  }

  public void add(int value) {
    queue.add(value);
    map.put(value, map.getOrDefault(value, 0) + 1);
  }

  public static void main(String args[]) {
    FirstUnique firstUnique = new FirstUnique(new int[]{809});
    System.out.println(firstUnique.showFirstUnique()); // return 809
    firstUnique.add(809);          // the queue is now [809,809]
    System.out.println(firstUnique.showFirstUnique()); // return -1


  }
}
