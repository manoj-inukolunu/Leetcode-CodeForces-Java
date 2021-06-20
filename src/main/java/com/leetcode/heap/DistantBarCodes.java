package com.leetcode.heap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class DistantBarCodes {

  class Pair {

    int val;
    int count;

    public Pair(int val, int count) {
      this.val = val;
      this.count = count;
    }
  }

  public int[] rearrangeBarcodes(int[] barcodes) {
    PriorityQueue<Pair> p = new PriorityQueue<>((o1, o2) -> -Integer.compare(o1.count, o2.count));
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int num : barcodes) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }
    for (int key : map.keySet()) {
      p.add(new Pair(key, map.get(key)));
    }
    int[] ans = new int[barcodes.length];
    int i = 0;
    while (!p.isEmpty()) {
      Pair curr = p.poll();
      Pair next = p.poll();
      ans[i++] = curr.val;
      curr.count = curr.count - 1;
      if (curr.count != 0) {
        p.add(curr);
      }
      if (next != null) {
        ans[i++] = next.val;
        next.count = next.count - 1;
        if (next.count != 0) {
          p.add(next);
        }
      }
    }
    return ans;
  }

  public static void main(String args[]) {
    int[] arr = new int[]{1, 1, 1, 2, 2, 2};
    DistantBarCodes d = new DistantBarCodes();
    System.out.println(Arrays.toString(d.rearrangeBarcodes(arr)));
  }
}
