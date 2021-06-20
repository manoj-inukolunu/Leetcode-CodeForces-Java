package com.leetcode.biweekly.context46;

import java.util.ArrayList;
import java.util.List;

public class ConCatArra {

  public boolean canChoose(int[][] groups, int[] nums) {

    List<String> gList = new ArrayList<>();
    for (int[] arr : groups) {
      StringBuffer b = new StringBuffer();
      for (int i = 0; i < arr.length; i++) {
        b.append(arr[i]);
        if (i != nums.length - 1) {
          b.append(",");
        }
      }
      gList.add(b.toString());
    }
    StringBuffer b = new StringBuffer();
    for (int i = 0; i < nums.length; i++) {
      b.append(nums[i]);
      if (i != nums.length - 1) {
        b.append(",");
      }
    }
    int next = 0;
    for (int i = 0; i < gList.size(); i++) {
      String curr = gList.get(i);
      int idx = b.indexOf(curr, next);
      if (idx != -1) {
        next = idx;
      } else {
        return false;
      }
    }
    return true;

  }

}
