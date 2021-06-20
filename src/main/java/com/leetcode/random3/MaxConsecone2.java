package com.leetcode.random3;

import java.util.ArrayList;
import java.util.List;

public class MaxConsecone2 {

  public int findMaxConsecutiveOnes(int[] nums) {
    List<int[]> list = new ArrayList<>();
    int one = 0, zero = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 1) {
        one++;
        if (i + 1 < nums.length && nums[i + 1] == 0) {
          list.add(new int[]{1, one});
          one = 0;
          zero = 0;
        }
      } else {
        zero++;
        if (i + 1 < nums.length && nums[i + 1] == 1) {
          list.add(new int[]{0, zero});
          zero = 0;
          one = 0;
        }
      }
    }
    if (zero >= 1) {
      list.add(new int[]{0, zero});
    } else if (one >= 1) {
      list.add(new int[]{1, one});
    }
    /*for (int[] i : list) {
      System.out.println(Arrays.toString(i));
    }*/
    int max = 1;
    for (int i = 0; i < list.size(); i++) {
      int[] curr = list.get(i);
      if (curr[0] == 1) {
        max = Math.max(max, curr[1]);

        if (i - 1 >= 0) {
          max = Math.max(curr[1] + 1, max);
        }
        if (i + 1 < list.size() && list.get(i + 1)[1] == 1) {
          max = Math.max(max, curr[1] + 1);
          if (i + 2 < list.size()) {
            max = Math.max(curr[1] + 1 + list.get(i + 2)[1], max);
          }
        } else if (i + 1 < list.size()) {
          max = Math.max(max, curr[1] + 1);
        }
      }
    }
    return max;
  }

  public static void main(String args[]) {
    MaxConsecone2 m = new MaxConsecone2();
    int val = m.findMaxConsecutiveOnes(new int[]{1, 1, 0, 1});
    System.out.println(val);
  }

}
