package com.leetcode.random3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Judge24 {

  boolean found = false;

  public boolean judgePoint24(int[] nums) {
    List<Double> list = new ArrayList<>();
    for (int num : nums) {
      list.add(Double.valueOf(num));
    }
    dfs(list);
    return found;
  }

  private void dfs(List<Double> nums) {
    if (nums.size() == 1) {
      if (Math.abs(nums.get(0) - 24) < 1e-64) {
        found = true;
      }
      return;
    }

    for (int i = 0; i < nums.size(); i++) {
      for (int j = 0; j < nums.size(); j++) {
        if (i != j) {
          List<Double> curr = new ArrayList<>();
          for (int k = 0; k < nums.size(); k++) {
            if (k != i && k != j) {
              curr.add(nums.get(k));
            }
          }
          curr.add(nums.get(i) * nums.get(j));
          dfs(curr);

          curr = new ArrayList<>();
          for (int k = 0; k < nums.size(); k++) {
            if (k != i && k != j) {
              curr.add(nums.get(k));
            }
          }
          curr.add(nums.get(i) + nums.get(j));
          dfs(curr);

          curr = new ArrayList<>();
          for (int k = 0; k < nums.size(); k++) {
            if (k != i && k != j) {
              curr.add(nums.get(k));
            }
          }
          curr.add(nums.get(i) - nums.get(j));
          dfs(curr);
          curr = new ArrayList<>();
          for (int k = 0; k < nums.size(); k++) {
            if (k != i && k != j) {
              curr.add(nums.get(k));
            }
          }
          curr.add(nums.get(j) - nums.get(i));
          dfs(curr);

          curr = new ArrayList<>();
          for (int k = 0; k < nums.size(); k++) {
            if (k != i && k != j) {
              curr.add(nums.get(k));
            }
          }
          if (nums.get(j) != 0) {
            curr.add(nums.get(i) / nums.get(j));
            dfs(curr);
          }

          curr = new ArrayList<>();
          for (int k = 0; k < nums.size(); k++) {
            if (k != i && k != j) {
              curr.add(nums.get(k));
            }
          }
          if (nums.get(i) != 0) {
            curr.add(nums.get(j) / nums.get(i));
            dfs(curr);
          }
        }
      }
    }
  }

  public int process(int n) {
    StringBuffer b = new StringBuffer();
    for (int i = 1; i <= n; i++) {
      b.append(Integer.toBinaryString(i));
    }
    int mod = (int) (Math.pow(10, 9) + 7);
    return new BigInteger(b.toString()).mod(BigInteger.valueOf(mod)).intValue();
  }

  public static void main(String args[]) {
    Judge24 j = new Judge24();
    System.out.println(j.judgePoint24(new int[]{8, 1, 6, 6}));
    System.out.println(Integer.toBinaryString(6586));
    int[] arr = new int[]{66, 1, 28, 73, 53, 35, 45, 60, 100, 44, 59, 94, 27, 88, 7, 18, 83, 18, 72, 63};
    Arrays.sort(arr);
    System.out.println(Arrays.toString(arr));
  }

}
