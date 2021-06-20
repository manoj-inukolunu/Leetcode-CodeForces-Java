package com.leetcode.random1;

import java.util.Arrays;

public class NumSubSeq {

  public int numSubseq(int[] nums, int target) {
    int mod = (int) (Math.pow(10, 9) + 7);
    int[] mods = new int[nums.length];
    mods[0] = 1 % mod;
    for (int i = 1; i < mods.length; i++) {
      mods[i] = (mods[i - 1] * 2) % mod;
    }
    Arrays.sort(nums);
    int count = 0;
    for (int i = 0; i < nums.length; i++) {
      int next = getNext(nums, nums[i], target, i, nums.length - 1);
      if (next != -1) {
        count = (count % mod + mods[next - i] % mod) % mod;
      }
    }
    return count;
  }

  private int getNext(int[] nums, int num, int target, int start, int end) {
    int ans = -1;
    while (start <= end) {
      int mid = start + (end - start) / 2;
      if (num + nums[mid] <= target) {
        ans = mid;
        start = mid + 1;
      } else {
        end = mid - 1;
      }
    }
    return ans;
  }

  public static void main(String args[]) {
    NumSubSeq n = new NumSubSeq();
    System.out.println(n.numSubseq(new int[]{5, 2, 4, 1, 7, 6, 8}, 16));
  }

}
