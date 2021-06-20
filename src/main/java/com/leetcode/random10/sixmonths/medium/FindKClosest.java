package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author manoji on 8/4/20.
 */
public class FindKClosest {

  class Pair {

    int diff;
    int index;

    public Pair(int diff, int index) {
      this.diff = diff;
      this.index = index;
    }
  }


  public List<Integer> findClosestElements(int[] arr, int k, int x) {
    PriorityQueue<Pair> p = new PriorityQueue<>((o1, o2) -> {
      if (o1.diff != o2.diff) {
        return Integer.compare(o1.diff, o2.diff);
      } else {
        return Integer.compare(o1.index, o2.index);
      }
    });
    for (int i = 0; i < arr.length; i++) {
      if (x > arr[i]) {
        p.add(new Pair(x - arr[i], i));
      } else {
        p.add(new Pair(arr[i] - x, i));
      }
    }
    List<Integer> ans = new ArrayList<>();
    for (int i = 0; i < k; i++) {
      ans.add(arr[p.poll().index]);
    }
    Collections.sort(ans);
    return ans;
  }


  public static void main(String args[]) {
    FindKClosest f = new FindKClosest();
    System.out.println(f.findClosestElements(new int[]{0, 0, 1, 2, 3, 3, 4, 7, 7, 8}, 3, 5));
  }
}
