package com.leetcode.random;

import java.util.Arrays;

public class MaxNetworkQuality {

  class Pair implements Comparable<Pair> {

    int x;
    int y;
    Double rank;

    public Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int compareTo(Pair o) {
      if (rank.compareTo(o.rank) == 0) {
        if (x == o.x) {
          return -Integer.compare(y, o.y);
        }
        return -Integer.compare(x, o.x);
      }
      return rank.compareTo(o.rank);
    }
  }

  public int[] bestCoordinate(int[][] towers, int radius) {
    Pair max = null;
    for (int i = 0; i <= 50; i++) {
      for (int j = 0; j <= 50; j++) {
        Double currRank = 0d;
        for (int k = 0; k < towers.length; k++) {
          Double dist = Math.sqrt(Math.pow(towers[k][0] - i, 2) + Math.pow(towers[k][1] - j, 2));
          if (dist.compareTo((double) radius) > 0) {
            continue;
          }
          currRank += Math.floor(towers[k][2] / (1 + dist));
        }
        Pair curr = new Pair(i, j);
        curr.rank = currRank;
        if (max == null) {
          max = curr;
        } else if (curr.compareTo(max) > 0) {
          max = curr;
        }
      }
    }
    return new int[]{max.x, max.y};
  }

  public static void main(String args[]) {
    int[][] towers = new int[][]{
        {2, 1, 9}, {0, 1, 9}
    };
    MaxNetworkQuality m = new MaxNetworkQuality();
    System.out.println(Arrays.toString(m.bestCoordinate(towers, 2)));
  }
}
