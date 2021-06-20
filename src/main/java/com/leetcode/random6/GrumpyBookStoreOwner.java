package com.leetcode.random6;

public class GrumpyBookStoreOwner {

  public int maxSatisfied(int[] customers, int[] grumpy, int X) {
    int[] pre = new int[customers.length];
    pre[0] = customers[0];
    int[] left = new int[customers.length];
    left[0] = (grumpy[0] == 1 ? 0 : customers[0]);
    int[] right = new int[customers.length];
    right[right.length - 1] = (grumpy[right.length - 1] == 1 ? 0 : customers[right.length - 1]);
    for (int i = 1, j = right.length - 2; i < customers.length && j >= 0; i++, j--) {
      pre[i] = pre[i - 1] + customers[i];
      left[i] = left[i - 1] + (grumpy[i] == 0 ? customers[i] : 0);
      right[j] = right[j + 1] + (grumpy[j] == 0 ? customers[j] : 0);
    }

    int max = Integer.MIN_VALUE;
    for (int i = 0; i + X <= customers.length; i++) {
      if (i == 0) {
        max = Math.max(getSum(pre, 0, i + X - 1) + right[i + X], max);
      } else if (i + X == customers.length) {
        max = Math.max(getSum(pre, i, i + X - 1) + left[i - 1], max);
      } else {
        max = Math.max(getSum(pre, i, i + X - 1) + right[i + X] + left[i - 1], max);
      }
    }
    return max;
  }

  int getSum(int[] pre, int start, int end) {
    if (start > 0) {
      return pre[end] - pre[start - 1];
    }
    return pre[end];
  }

  public static void main(String args[]) {
    GrumpyBookStoreOwner g = new GrumpyBookStoreOwner();
    int[] customers = new int[]{1};
    int[] grumpy = new int[]{0};
    System.out.println(g.maxSatisfied(customers, grumpy, 1));
  }


}
