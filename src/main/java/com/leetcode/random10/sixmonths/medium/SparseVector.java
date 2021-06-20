package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.List;

public class SparseVector {



  List<Integer[]> list = new ArrayList();

  SparseVector(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        list.add(new Integer[]{i, nums[i]});
      }
    }
  }

  // Return the dotProduct of two sparse vectors
  public int dotProduct(SparseVector vec) {
    int res = 0;
    List<Integer[]> next = vec.list;
    int i = 0, j = 0;
    while (i < list.size() && j < next.size()) {
      Integer[] first = list.get(i);
      Integer[] second = next.get(j);
      if (first[0].equals(second[0])) {
        res += (first[1] * second[1]);
        i++;
        j++;
      }
      if (first[0] < second[0]) {
        i++;
      } else {
        j++;
      }
    }
    return res;
  }

  public static void main(String args[]) {
    SparseVector s1 = new SparseVector(new int[]{0, 0, 0, 3, 0, 3, 0, 4, 0});
    System.out.println(s1.dotProduct(new SparseVector(new int[]{0, 0, 0, 0, 0, 0, 3, 0, 0})));
  }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);
