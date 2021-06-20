package com.leetcode.random5;

import java.util.TreeMap;

public class LongestSubstrMaxDiff {

  public int longestSubarray(int[] nums, int limit) {
    TreeMap<Integer,Integer> map = new TreeMap<>();
    int left = 0;
    int right = 0;
    int res = 0;
    while(right < nums.length) {
      map.put(nums[right], map.getOrDefault(nums[right],0)+1);

      while(map.lastKey() - map.firstKey() > limit) {
        map.replace(nums[left], map.get(nums[left])-1);
        if(map.get(nums[left]) == 0) {
          map.remove(nums[left]);
        }
        left++;
      }
      res = Math.max(res, right - left + 1);
      right++;
    }
    return res;
  }


  private boolean exists(int len, int[] nums, int limit) {
    TreeMap<Integer, Integer> map = new TreeMap<>();
    for (int i = 0; i < len; i++) {
      map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
    }
    if (Math.abs(map.firstKey() - map.lastKey()) <= limit) {
      return true;
    }
    for (int i = 1; i + len <= nums.length; i++) {
      map.put(nums[i - 1], map.get(nums[i - 1]) - 1);
      if (map.get(nums[i - 1]) <= 0) {
        map.remove(nums[i - 1]);
      }
      map.put(nums[i + len - 1], map.getOrDefault(nums[i + len - 1], 0) + 1);
      int min = map.firstEntry().getKey();
      int max = map.lastEntry().getKey();
      if (Math.abs(max - min) <= limit) {
        return true;
      }
    }
    return false;
  }

  public static void main(String args[]) {
    LongestSubstrMaxDiff l = new LongestSubstrMaxDiff();
    System.out.println(l.longestSubarray(new int[]{8}, 10));
  }

}
