package com.binarysearch;

import java.util.HashSet;
import java.util.Set;

public class NumSubL {

  int mod = (int) Math.pow(10, 9) + 7;

  public int solve(int[] nums, int[] target) {
    HashSet<Integer> set = new HashSet();
    for (int num : target) {
      set.add(num);
    }
    if (set.isEmpty()) {
      return 0;
    }
    int count = 0;
    Set<Integer> curr = new HashSet<>();
    int i = 0;
    while (i < nums.length) {
      int start = i;
      curr.clear();
      while (start < nums.length) {
        if (set.contains(nums[start])) {
          curr.add(nums[start]);
          if (curr.size() == set.size()) {
            start--;
            break;
          }
        }
        start++;
      }
      if (start < 0) {
        i++;
        continue;
      }
      if (start >= nums.length) {
        start--;
      }
      if (start <= nums.length && start >= 0) {
        int len = (start - i + 1);
        int num = len * (len + 1) / 2;
        count = (count % mod + num % mod) % mod;
      }
      if (start == i) {
        i++;
      } else if (start > i) {
        i = start + 1;
      } else {
        i++;
      }
    }
    return count;
  }

  public static void main(String args[]) {
    int[] arr = new int[]{2, 1, 0};
    int[] target = new int[]{2, 0};
    NumSubL n = new NumSubL();
    System.out.println(n.solve(arr, target));
  }

}
