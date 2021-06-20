package com.leetcode.random4;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SmallestRange {

  class Data {

    int numsIdx;
    int elemIdx;
    int elem;

    public Data(int numsIdx, int elemIdx, int elem) {
      this.numsIdx = numsIdx;
      this.elem = elem;
      this.elemIdx = elemIdx;
    }
  }


  public int[] smallestRange(List<List<Integer>> nums) {
    int[] p = new int[nums.size()];
    Arrays.fill(p, 0);
    int totMin = Integer.MAX_VALUE, totMax = Integer.MIN_VALUE, max = Integer.MIN_VALUE;
    PriorityQueue<Data> pq = new PriorityQueue<>(Comparator.comparing(d -> d.elem));
    for (int i = 0; i < p.length; i++) {
      pq.add(new Data(i, 0, nums.get(i).get(0)));
      totMin = Math.min(nums.get(i).get(0), totMin);
      max = Math.max(nums.get(i).get(0), max);
      totMax = Math.max(nums.get(i).get(nums.get(i).size() - 1), totMax);
    }
    int[] ans = new int[]{totMin, totMax};

    while (true) {
      int min = Integer.MAX_VALUE;
      Data curr = pq.poll();
      min = Math.min(curr.elem, min);
      curr.elemIdx++;
      if (min == max) {
        return new int[]{min, min};
      }
      if (curr.elemIdx == nums.get(curr.numsIdx).size()) {
        if (isSmaller(new int[]{min, max}, ans)) {
          ans = new int[]{min, max};
        }
        break;
      }
      curr.elem = nums.get(curr.numsIdx).get(curr.elemIdx);
      if (nums.get(curr.numsIdx).get(curr.elemIdx) > max) {
        max = nums.get(curr.numsIdx).get(curr.elemIdx);
      }
      pq.add(curr);

      if (isSmaller(new int[]{min, max}, ans)) {
        ans = new int[]{min, max};
      }
    }
    return ans;
  }

  private boolean isSmaller(int[] ans, int[] curr) {
    if (ans[1] - ans[0] == curr[1] - curr[0]) {
      return ans[0] < curr[0];
    }
    return ans[1] - ans[0] < curr[1] - curr[0];
  }


  public static void main(String args[]) {
    List<List<Integer>> list = Lists
        .newArrayList(Lists.newArrayList(4, 10, 15, 24, 26), Lists.newArrayList(0, 9, 12, 20), Lists.newArrayList(5, 18, 22, 30));
    List<List<Integer>> list1 = Lists
        .newArrayList(Lists.newArrayList(1, 2, 3), Lists.newArrayList(1, 2, 3), Lists.newArrayList(1, 2, 3));
    SmallestRange s = new SmallestRange();
    System.out.println(Arrays.toString(s.smallestRange(list)));
  }
}
