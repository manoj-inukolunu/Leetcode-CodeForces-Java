package com.leetcode.random1;

public class PartitionDisjoint {

  public int partitionDisjoint(int[] arr) {
    int[] maxA = new int[arr.length];
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < arr.length; i++) {
      max = Math.max(arr[i], max);
      maxA[i] = max;
    }
    int[] minA = new int[arr.length];
    int min = Integer.MAX_VALUE;
    for (int i = arr.length - 1; i >= 0; i--) {
      min = Math.min(arr[i], min);
      minA[i] = min;
    }

    for (int i = 0; i + 1 < arr.length; i++) {
      int maxLeft = maxA[i];
      int minRight = minA[i + 1];
      if (maxLeft < minRight) {
        return i + 1;
      }
    }
    return 1;

  }

  public static void main(String args[]) {
    PartitionDisjoint p = new PartitionDisjoint();
    System.out.println(p.partitionDisjoint(new int[]{1, 1, 1, 0, 6, 12}));
  }

}
