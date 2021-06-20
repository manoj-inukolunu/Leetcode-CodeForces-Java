package com.leetcode.random7;

public class MaxValBounded {

  public int maxValue(int n, int index, int maxSum) {
    int low = 1, high = (int) 1e9, ans = 0;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (possible(n, index, mid, maxSum)) {
        ans = mid;
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return ans;
  }

  private boolean possible(int len, int idx, int val, long maxSum) {
    long leftSum = getLeft(idx, val), rightSum = getRight(idx, len, val - 1);
    return leftSum + rightSum <= maxSum;
  }

  private long sum(long val) {
    return val * (val + 1) / 2;
  }

  private long getLeft(int idx, int val) {
    if (idx + 1 >= val) {
      return sum(val) + (idx + 1 - val);
    } else {
      return sum(val) - sum(val - idx - 1);
    }
  }

  private long getRight(int idx, int len, int val) {
    if (len - idx - 1 >= val) {
      return sum(val) + (len - idx - 1 - val);
    } else {
      return sum(val) - sum(val - (len - idx - 1));
    }
  }

  public static void main(String args[]) {
    MaxValBounded m = new MaxValBounded();
//    System.out.println(m.possible(4, 2, 2, 6));
    System.out.println(m.maxValue(6, 1, 10));
  }

}
