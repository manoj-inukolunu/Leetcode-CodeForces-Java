package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MissingRanges {


  public List<String> findMissingRanges(int[] nums, int lower, int upper) {

    List<String> ans = new ArrayList<>();
    long low = lower;
    long up = upper;
    if (nums.length == 0) {
      if (Math.abs(Math.abs(up) - Math.abs(low)) > 0) {
        ans.add(low + "->" + up);
      } else {
        ans.add(up + "");
      }
      return ans;
    }
    Set<Long> set = new HashSet<>();
    long min = nums[0], max = nums[0];
    for (int i = 0; i < nums.length; i++) {
      min = Math.min(nums[i], min);
      max = Math.max(nums[i], max);
      set.add(Long.valueOf(nums[i]));
    }

    long i = min;
    if (min != low) {
      if (Math.abs(Math.abs(min) - Math.abs(low)) > 1) {
        ans.add(low + "->" + (min - 1));
      } else {
        ans.add(low + "");
      }
    }
    while (i <= max) {
      if (!set.contains(i)) {
        StringBuffer buffer = new StringBuffer();
        int count = 0;
        buffer.append(i);
        while (!set.contains(i) && i <= max) {
          i++;
          count++;
        }
        if (count > 1) {
          buffer.append("->");
          buffer.append(i - 1);
        }
        ans.add(buffer.toString());
      }
      i++;
    }
    if (max != up) {
      if (Math.abs(Math.abs(max) - Math.abs(up)) > 1) {
        ans.add(max + 1 + "->" + up);
      } else {
        ans.add(up + "");
      }
    }
    return ans;
  }

  private List<String> findMissingRanges1(int[] nums, int lower, int upper) {
    List<String> ans = new ArrayList<>();
    long low = lower;
    long up = upper;
    if (nums.length == 0) {
      if (Math.abs(Math.abs(up) - Math.abs(low)) > 0) {
        ans.add(low + "->" + up);
      } else {
        ans.add(up + "");
      }
      return ans;
    }
    long min = nums[0], max = nums[nums.length - 1];
    for (int i = 0; i + 1 < nums.length; i++) {
      if (nums[i + 1] - nums[i] != 1 && (nums[i] != nums[i + 1])) {
        if (nums[i] + 1 != nums[i + 1] - 1) {
          ans.add((nums[i] + 1) + "->" + (nums[i + 1] - 1));
        } else {
          ans.add((nums[i] + 1) + "");
        }
      }
    }

    if (min != low && low < min) {
      if (min - low > 1) {
        ans.add(0, low + "->" + (min - 1));
      } else {
        ans.add(0, low + "");
      }
    }
    if (max != up && up > max) {
      if (up - max > 1) {
        ans.add(max + 1 + "->" + up);
      } else {
        ans.add(up + "");
      }
    }
    return ans;
  }


  public static void main(String args[]) {
    MissingRanges m = new MissingRanges();
    System.out.println(m.findMissingRanges1(new int[]{Integer.MAX_VALUE}, Integer.MIN_VALUE, Integer.MAX_VALUE));
  }

}
