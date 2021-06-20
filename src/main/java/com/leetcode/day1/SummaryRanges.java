package com.leetcode.day1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author manoji on 6/2/20.
 */
public class SummaryRanges {

  public List<String> summaryRanges(int[] nums) {
    Set<Long> set = new HashSet<>();
    for (int num : nums) {
      set.add((long) num);
    }

    List<String> ans = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      int begin = i;
      Long curr = Long.valueOf(nums[i]);
      StringBuffer buff = new StringBuffer();
      buff.append(curr);
      while (set.contains(curr + 1)) {
        curr = curr + 1;
        i++;
      }
      if (begin != i) {
        buff.append("->");
        buff.append(nums[i]);
      }
      ans.add(buff.toString());
    }
    return ans;
  }

  public static void main(String args[]) {
    SummaryRanges summaryRanges = new SummaryRanges();
    System.out.println(summaryRanges.summaryRanges(new int[]{0, 1, 2}));
  }

}
