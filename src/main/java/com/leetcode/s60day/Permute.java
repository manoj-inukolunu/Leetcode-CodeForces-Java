package com.leetcode.s60day;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Permute {

  List<List<Integer>> ans = new ArrayList();

  public List<List<Integer>> permute(int[] nums) {
    List<Integer> set = new ArrayList();
    for (int num : nums) {
      set.add(num);
    }

    recur(set, 0, nums.length, new ArrayList());
    return ans;
  }

  private void recur(List<Integer> set, int idx, int total, List<Integer> curr) {
    if (idx >= total) {
      ans.add(new ArrayList(curr));
      return;
    }
    int size = set.size();
    HashSet<Integer> used = new HashSet<>();
    for (int i = 0; i < size; i++) {
      Integer val = set.get(i);
      if (!used.contains(val)) {
        set.remove(val);
        curr.add(val);
        used.add(val);
        recur(set, idx + 1, total, curr);
        set.add(i, val);
        curr.remove(val);
      }
    }
  }

  public static void main(String args[]) {
    Permute p = new Permute();
    System.out.println(p.permute(new int[]{1, 2, 3}));
  }

}
