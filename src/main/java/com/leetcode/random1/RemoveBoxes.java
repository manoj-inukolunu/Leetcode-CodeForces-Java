package com.leetcode.random1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RemoveBoxes {

  int ans = Integer.MIN_VALUE;

  public int removeBoxes(int[] boxes) {
    List<Integer> list = new ArrayList<>();
    for (int num : boxes) {
      list.add(num);
    }

    dfs(list, new HashMap<>(), 0, 0);

    return ans;
  }

  private void dfs(List<Integer> list, HashMap<String, Integer> visited, int idx, int sum) {
    if (visited.containsKey(list.toString() + idx)) {
      return;
    }
    if (idx >= list.size() || idx < 0) {
      ans = Math.max(ans, sum);
      return;
    }

    dfs(list, visited, idx + 1, sum);
    visited.put(list.toString() + idx, 1);
    int currSum = sum, i = idx, start = i, end = i;
    int curr = list.get(i);
    while (true) {
      if (i >= list.size()) {
        break;
      }
      if (i + 1 < list.size() && list.get(i).equals(list.get(i + 1))) {
        currSum += list.get(i);
        end++;
      } else {
        currSum += list.get(i);
        break;
      }
      i++;
    }
    for (int j = start; j <= end + 2; j++) {
      if (j < list.size() && list.get(j).equals(curr)) {
        list.remove(j);
      }
    }

    dfs(list, visited, idx, currSum);
    for (int j = start; j <= end; j++) {
      list.add(start, curr);
    }
    //System.out.println(list);

  }

  public static void main(String args[]) {
    int[] arr = new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1};
    RemoveBoxes r = new RemoveBoxes();
    System.out.println(r.removeBoxes(arr));
  }
}
