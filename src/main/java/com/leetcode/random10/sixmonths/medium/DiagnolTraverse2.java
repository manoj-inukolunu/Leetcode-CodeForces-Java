package com.leetcode.random10.sixmonths.medium;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author manoji on 7/20/20.
 */
public class DiagnolTraverse2 {

  public int[] findDiagonalOrder(List<List<Integer>> nums) {
    HashMap<Integer, List<Integer>> map = new HashMap<>();

    int total = 0;
    int max = 0;
    for (int i = 0; i < nums.size(); i++) {
      total += nums.get(i).size();
      for (int j = 0; j < nums.get(i).size(); j++) {
        List<Integer> list = map.getOrDefault(i + j, new ArrayList<>());
        list.add(nums.get(i).get(j));
        map.put(i + j, list);
        max = Math.max(max, i + j);
      }
    }
    int[] res = new int[total];
    int k = 0;

    for (int t = 0; t <= max; t++) {
      List<Integer> list = map.get(t);
      if (list != null) {
        for (int i = list.size() - 1; i >= 0; i--) {
          res[k++] = list.get(i);
        }
      }
    }
    return res;
  }

  public static void main(String args[]) {
    DiagnolTraverse2 d = new DiagnolTraverse2();

    List<List<Integer>> lists = Lists.newArrayList(Lists.newArrayList(1, 2, 3, 4, 5), Lists.newArrayList(6, 7), Lists.newArrayList(8),
        Lists.newArrayList(9, 10, 11), Lists.newArrayList(12, 13, 14, 15, 16));

    System.out.println(Arrays.toString(d.findDiagonalOrder(lists)));


  }

}
