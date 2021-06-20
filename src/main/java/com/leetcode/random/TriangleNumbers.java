package com.leetcode.random;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class TriangleNumbers {


  public int triangleNumber(int[] nums) {
    Arrays.sort(nums);
    TreeMap<Integer, Integer> map = new TreeMap<>();

    for (int i = 0; i < nums.length; i++) {
      map.put(nums[i], i);
    }

    Set<Set<Integer>> set = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        for (int k = j + 1; k < nums.length; k++) {
          if (nums[i] + nums[j] > nums[k] && nums[i] + nums[k] > nums[j] && nums[j] + nums[k] > nums[i]) {
            System.out.println(nums[i] + " " + nums[j] + " " + nums[k]);
          }
        }
      }
    }

    int count = 0;
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        int max = nums[i] + nums[j] - 1;
        int end;
        if (map.containsKey(max)) {
          end = map.get(max);
          if (end > j) {
            count += (end - j);
          }
        } else if (map.lowerKey(max) != null) {
          end = map.get(map.lowerKey(max));
          if (end > j) {
            count += (end - j);
          }
        }
      }
    }
    return count;
  }


  public static void main(String args[]) {
    TriangleNumbers t = new TriangleNumbers();
    System.out.println(t.triangleNumber(new int[]{82, 15, 23, 82, 67, 0, 3, 92, 11}));
  }
}
