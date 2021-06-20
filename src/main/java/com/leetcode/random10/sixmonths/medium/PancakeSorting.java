package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.List;

public class PancakeSorting {

  public int max(int[] arr, int start, int end) {
    int max = Integer.MIN_VALUE;
    int pos = -1;
    for (int i = start; i < end; i++) {
      if (arr[i] > max) {
        max = arr[i];
        pos = i;
      }
    }
    return pos;
  }

  private void flip(int[] arr, int idx) {
    int start = 0;
    while (start < idx) {
      int temp = arr[start];
      arr[start] = arr[idx];
      arr[idx] = temp;
      start++;
      idx--;
    }
  }

  public List<Integer> pancakeSort(int[] arr) {
    int i = arr.length;
    int len = arr.length;
    List<Integer> ans = new ArrayList<>();
    while (i >= 0) {
      int k = max(arr, 0, len);
      ans.add(k);
      flip(arr, k);
      flip(arr, len - 1);
      len--;
      i--;
    }
    return ans;
  }

}
