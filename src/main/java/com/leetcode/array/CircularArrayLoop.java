package com.leetcode.array;

import java.util.HashSet;
import org.checkerframework.checker.units.qual.C;

public class CircularArrayLoop {

  HashSet<Integer> visited = new HashSet();

  public boolean circularArrayLoop(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      int next = (i + nums[i]);
      if (next < 0) {
        while (next < 0) {
          next = nums.length + next;
        }
      }
      if (nums[i] != 0 && dfs(i, next % nums.length, nums, nums[i] > 0 ? 1 : -1, 1)) {
        return true;
      }
      visited.clear();
    }
    return false;
  }

  private boolean dfs(int start, int curr, int[] nums, int dir, int len) {
    if (curr > nums.length) {
      return false;
    }
    if (nums[curr] == 0) {
      return false;
    }
    if (curr == start && len > 1) {
      return true;
    } else if (curr == start && len <= 1) {
      return false;
    }
    if (visited.contains(curr)) {
      return false;
    }
    visited.add(curr);
    int next = (curr + nums[curr]);
    if (dir == 1) {
      //moving forward
      if (next <= curr) {
        return false;
      } else {
        return dfs(start, next % nums.length, nums, dir, len + 1);
      }
    } else {
      if (next >= curr) {
        return false;
      } else {
        if (next < 0) {
          while (next < 0) {
            next = nums.length + next;
          }
        }
        return dfs(start, next % nums.length, nums, dir, len + 1);
      }
    }
  }


  public static void main(String args[]) {
    CircularArrayLoop c = new CircularArrayLoop();
    System.out.println(c.circularArrayLoop(new int[]{-2, -3, -9}));
  }
}
