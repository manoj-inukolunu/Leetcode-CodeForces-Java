package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author manoji on 5/24/20.
 */
public class LongestConsecutiveSequence {

  public int longestConsecutive(int[] nums) {
    HashSet<Integer> set = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
      set.add(nums[i]);
    }

    HashMap<String, Integer> map = new HashMap<>();

    int max = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      int count = 1;
      int curr = nums[i];
      while (set.contains(curr + 1)) {
        curr++;
        count++;
      }
      if (count > max) {
        max = count;
      }
    }

    return max;
  }

  public static void main(String args[]) {
    LongestConsecutiveSequence l = new LongestConsecutiveSequence();
    System.out.println(l.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
  }

}
