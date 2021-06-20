package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.checkerframework.checker.units.qual.C;

/**
 * @author manoji on 4/27/20.
 */
public class CombinationSum {


  private List<List<Integer>> list = new ArrayList<>();

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    dfs(candidates, 0, 0, target, new ArrayList<>());
    return list;
  }

  private void dfs(int[] candidates, int sum, int index, int target, List<Integer> arrayList) {
    if (index >= candidates.length) {
      if (sum == target) {
        Collections.sort(arrayList);
        if (!list.contains(arrayList)) {
          ArrayList<Integer> temp = new ArrayList<>();
          for (int num : arrayList) {
            temp.add(num);
          }
          list.add(temp);
        }
      }
      return;
    }

    if (sum == target) {
      Collections.sort(arrayList);
      if (!list.contains(arrayList)) {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int num : arrayList) {
          temp.add(num);
        }
        list.add(temp);
      }
      return;
    }

    if (sum + candidates[index] <= target) {
      arrayList.add(candidates[index]);
      dfs(candidates, sum + candidates[index], index + 1, target, arrayList);
      arrayList.remove(new Integer(candidates[index]));
    }
    dfs(candidates, sum, index + 1, target, arrayList);
  }


  public static void main(String args[]) {

    List<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(1);
    list.remove(new Integer(1));

    System.out.println(list);
    CombinationSum cSum = new CombinationSum();
    System.out.println(cSum.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
  }

}
