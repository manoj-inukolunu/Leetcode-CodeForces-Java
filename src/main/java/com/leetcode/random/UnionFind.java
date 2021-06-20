package com.leetcode.random;

import java.util.Arrays;
import java.util.Comparator;

public class UnionFind {

  class UnionF {

    public UnionF(int n) {
      this.numComponents = n;
      id = new int[n];
      sz = new int[n];
      for (int i = 0; i < id.length; i++) {
        id[i] = i;
        sz[i] = 1;
      }
    }

    int[] id;
    int[] sz;
    int numComponents;

    private int find(int val) {
      int root = val;
      while (id[root] != root) {
        root = id[root];
      }
      while (val != root) {
        int next = id[val];
        id[val] = root;
        val = next;
      }
      return root;
    }

    private boolean connected(int p, int q) {
      return find(p) == find(q);
    }

    private void union(int p, int q) {
      if (connected(p, q)) {
        return;
      }
      int root1 = find(p);
      int root2 = find(q);
      if (sz[root1] < sz[root2]) {
        sz[root2] += sz[root1];
        id[root1] = root2;
      } else {
        sz[root1] += sz[root2];
        id[root2] = root1;
      }
      numComponents--;
    }
  }


  public int earliestAcq(int[][] logs, int N) {
    Arrays.sort(logs, Comparator.comparingInt(o -> o[0]));
    UnionF u = new UnionF(N);
    for (int i = 0; i < logs.length; i++) {
      int[] log = logs[i];
      u.union(log[1], log[2]);
      if (u.numComponents == 1) {
        return log[0];
      }
    }
    return -1;
  }

  public static void main(String args[]) {
    UnionFind u = new UnionFind();
    int[][] logs = new int[][]{{20190101, 0, 1}, {20190104, 3, 4}, {20190107, 2, 3}, {20190211, 1, 5}, {20190224, 2, 4}, {20190301, 0, 3},
        {20190312, 1, 2}, {20190322, 4, 5}};
    System.out.println(u.earliestAcq(logs, 6));
  }

}
