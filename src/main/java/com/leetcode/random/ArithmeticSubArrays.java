package com.leetcode.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArithmeticSubArrays {

  public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
    List<Boolean> list = new ArrayList<>();
    for (int i = 0; i < l.length; i++) {
      int start = l[i];
      int end = r[i];
      int diff = nums[start + 1] - nums[start];
      int[] temp = new int[end - start + 1];
      if (temp.length >= 0) {
        System.arraycopy(nums, start, temp, 0, temp.length);
      }
      Arrays.sort(temp);
      boolean found = true;
      for (int j = 1; j < temp.length; j++) {
        if (temp[j] - temp[j - 1] != diff) {
          list.add(false);
          found = false;
          break;
        }
      }
      if (found) {
        list.add(true);
      }
    }
    return list;
  }


}
