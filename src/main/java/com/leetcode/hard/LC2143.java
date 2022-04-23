package com.leetcode.hard;

import java.util.Objects;

public class LC2143 {
  class Data {
    int idx;
    int diff;
    int atleastOne;

    public Data(int idx, int diff, int atleastOne) {
      this.idx = idx;
      this.diff = diff;
      this.atleastOne = atleastOne;
    }

    @Override
    public int hashCode() {
      return Objects.hash(idx, diff, atleastOne);
    }

    @Override
    public boolean equals(Object that) {
      Data other = (Data) that;
      return this.idx == other.idx
          && this.diff == other.diff
          && this.atleastOne == other.atleastOne;
    }
  }

  public int countSubranges(int[] nums1, int[] nums2) {
    int total = 0;
    for (int i = 0; i < nums1.length; i++) {
      total += solve(i, 0, 0, nums1, nums2);
    }
    return total;
  }

  private int solve(int idx, int diff, int atleastOne, int[] nums1, int[] nums2) {
    if (idx >= nums1.length) {
      return (diff == 0 && atleastOne == 1) ? 1 : 0;
    }
    int total = 0;
    if (diff == 0) {
      if (atleastOne == 1) {
        total++;
      }
      total += (solve(idx + 1, diff + nums1[idx], 1, nums1, nums2));
      total += solve(idx + 1, diff - nums2[idx], 1, nums1, nums2);
    } else {
      total += (solve(idx + 1, diff + nums1[idx], 1, nums1, nums2));
      total += solve(idx + 1, diff - nums2[idx], 1, nums1, nums2);
    }
    return total;
  }

  public static void main(String[] args) {
    LC2143 l = new LC2143();
    System.out.println(l.countSubranges(new int[] {1, 2, 5}, new int[] {2, 6, 3}));
  }
}
