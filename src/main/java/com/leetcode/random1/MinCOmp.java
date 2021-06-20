package com.leetcode.random1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MinCOmp {


  int ans = Integer.MAX_VALUE;

  public int minimumIncompatibility(int[] nums, int k) {
    if (nums.length == k) {
      return 0;
    }
    if (nums.length % k != 0) {
      return -1;
    }

    HashMap<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
      if (map.get(num) > k) {
        return -1;
      }
    }
    List<TreeSet<Integer>> list = new ArrayList<>();
    for (int i = 0; i < k; i++) {
      list.add(new TreeSet<>());
    }
    dfs(nums, 0, list, 0, nums.length / 2);
    return ans;

  }


  private void dfs(int[] nums, int idx, List<TreeSet<Integer>> list, int sum, int size) {
    if (idx >= nums.length) {
      ans = Math.min(ans, sum);
      return;
    }

    Set<TreeSet<Integer>> visited = new HashSet<>();
    for (TreeSet<Integer> set : list) {
      if (set.contains(nums[idx]) || visited.contains(set) || set.size() == size) {
        continue;
      }
      int curr = currImpact(set, nums[idx]);
      sum += curr;
      if (sum < ans) {
        set.add(nums[idx]);
        dfs(nums, idx + 1, list, sum, size);
        set.remove(nums[idx]);
      }
      sum -= curr;
      visited.add(set);
    }
  }

  private int currImpact(TreeSet<Integer> set, int val) {
    if (set.isEmpty()) {
      return 0;
    }
    set.add(val);
    int min = set.first();
    int max = set.last();
    set.remove(val);
    return max - min;
  }

  public static void main(String args[]) {
    MinCOmp m = new MinCOmp();
    int[] arr = new int[]{13, 4, 7, 3, 3, 1, 12, 9, 11, 10, 13, 3, 12, 7};
    System.out.println(m.minimumIncompatibility(arr, 7));
  }

}
