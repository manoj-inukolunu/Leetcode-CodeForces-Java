package com.leetcode.random;

import java.util.Arrays;
import java.util.LinkedList;

public class ProcessQueries {

  public int[] processQueries(int[] queries, int m) {
    LinkedList<Integer> queue = new LinkedList<>();
    for (int i = 1; i <= m; i++) {
      queue.add(i);
    }
    int[] ans = new int[queries.length];
    for (int i = 0; i < queries.length; i++) {
      ans[i] = getPos(queue, queries[i]);
      queue.addFirst(queries[i]);
    }
    return ans;
  }

  private int getPos(LinkedList<Integer> queue, int query) {
    for (int i = 0; i < queue.size(); i++) {
      if (queue.get(i) == query) {
        queue.remove(i);
        return i;
      }
    }
    return -1;
  }

  public static void main(String args[]) {
    ProcessQueries p = new ProcessQueries();
    int[] arr = p.processQueries(new int[]{7, 5, 5, 8, 3}, 8);
    System.out.println(Arrays.toString(arr));
  }

}
