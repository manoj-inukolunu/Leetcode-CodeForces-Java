package com.leetcode.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class KStrongest {

  public int[] getStrongest(int[] arr, int k) {
    Arrays.sort(arr);
    int median = arr[((arr.length - 1) / 2)];
    PriorityQueue<Integer> p = new PriorityQueue((Comparator<Integer>) (o1, o2) -> {
      if (Math.abs(o1 - median) > Math.abs(o2 - median)) {
        return 1;
      } else if (Math.abs(o1 - median) < Math.abs(o2 - median)) {
        return -1;
      } else {
        return Integer.compare(o1, o2);
      }
    });
    for (int i = 0; i < arr.length; i++) {
      p.add(arr[i]);
    }
    List<Integer> list = new ArrayList<>();
    while (k > 0) {
      list.add(p.poll());
      k--;
    }
    return list.stream().mapToInt(i -> i).toArray();
  }

}
