package com.leetcode.random7;

import java.util.TreeMap;

public class MaxGoodSubArray {

  public int maximumScore(int[] nums, int k) {
    int[] leftMin = new int[k + 1];
    leftMin[k] = nums[k];
    for (int i = k - 1; i >= 0; i--) {
      leftMin[i] = Math.min(leftMin[i + 1], nums[i]);
    }
    int[] rightMin = new int[nums.length - k - 1];
    rightMin[0] = nums[k + 1];
    for (int i = 1; i < rightMin.length; i++) {
      rightMin[i] = Math.min(rightMin[i - 1], nums[k + i + 1]);
    }
    TreeMap<Integer, Integer[]> rightMap = new TreeMap<>();
    for (int i = 0; i < rightMin.length; i++) {
      if (rightMap.containsKey(rightMin[i])) {
        Integer[] curr = rightMap.get(rightMin[i]);
        rightMap.put(rightMin[i], new Integer[]{curr[0], i});
      } else {
        rightMap.put(rightMin[i], new Integer[]{i, i});
      }

    }
    TreeMap<Integer, Integer[]> leftMap = new TreeMap<>();
    for (int i = leftMin.length - 1; i >= 0; i--) {
      if (leftMap.containsKey(leftMin[i])) {
        Integer[] curr = leftMap.get(leftMin[i]);
        leftMap.put(leftMin[i], new Integer[]{curr[0], i});
      } else {
        leftMap.put(leftMin[i], new Integer[]{i, i});
      }
    }
    int max = nums[k];
    for (int key : leftMap.keySet()) {
      int leftLen = leftMin.length - leftMap.get(key)[1];
      int rightLen = getIncludeUpto(rightMap, key, rightMin.length);
      max = Math.max((leftLen + rightLen) * key, max);
    }
    for (int key : rightMap.keySet()) {
      int rightLen = rightMap.get(key)[1] + 1;
      int leftLen = getLeftIncludeUpto(leftMap, key, leftMin.length);
      max = Math.max((rightLen + leftLen) * key, max);
    }
    return max;
  }

  private int getLeftIncludeUpto(TreeMap<Integer, Integer[]> map, int key, int maxUpto) {
    Integer lower = map.lowerKey(key);
    if (lower == null && !map.containsKey(key)) {
      return maxUpto;
    } else if (lower == null && map.containsKey(key)) {
      return map.get(key)[1];
    } else {
      return maxUpto - map.get(lower)[0] - 1;
    }
  }

  private Integer getIncludeUpto(TreeMap<Integer, Integer[]> map, int key, int maxUpto) {
    Integer lower = map.lowerKey(key);
    if (lower == null && !map.containsKey(key)) {
      return maxUpto;
    } else if (lower == null && map.containsKey(key)) {
      return map.get(key)[1];
    } else {
      return Math.max(map.get(lower)[0] - 1, 0);
    }
  }

  public static void main(String args[]) {
    MaxGoodSubArray m = new MaxGoodSubArray();
    System.out.println(m.maximumScore(new int[]{5, 5, 4, 5, 4, 1, 1, 1}, 0));
  }

}
