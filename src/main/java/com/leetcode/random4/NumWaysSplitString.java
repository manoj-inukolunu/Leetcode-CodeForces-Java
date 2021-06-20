package com.leetcode.random4;

import java.util.HashMap;

public class NumWaysSplitString {

  private int getSum(int[] pre, int start, int end) {
    if (start > 0) {
      return pre[end] - pre[start - 1];
    }
    return pre[end];
  }

  public int numWays(String s) {
    int mod = (int) (Math.pow(10, 9) + 7);

    HashMap<Integer, int[]> map = new HashMap<>();
    int[] pre = new int[s.length()];
    pre[0] = Character.getNumericValue(s.charAt(0));
    int sum = 0;
    for (int i = s.length() - 1; i >= 0; i--) {
      sum += Character.getNumericValue(s.charAt(i));
      int[] ints = map.getOrDefault(sum, new int[]{});
      if (ints.length == 0) {
        ints = new int[]{i, i};
      } else {
        ints[1] = i;
      }
      map.put(sum, ints);
    }
    for (int i = 1; i < pre.length; i++) {
      pre[i] = pre[i - 1] + Character.getNumericValue(s.charAt(i));
    }
    long ans = 0;
    for (int i = 0; i < s.length(); i++) {
      int curr = getSum(pre, 0, i);
      int nextStart = i + 1;
      if (nextStart < s.length() && map.containsKey(curr)) {
        int[] arr = map.get(curr);
        int nextEnd = Math.max(map.get(curr)[1] - 1, nextStart);
        int midSum = getSum(pre, nextStart, nextEnd);
        if (midSum == curr) {
          ans += ((arr[0] - nextStart) - (nextEnd - nextStart)) % mod;
        }
      }
    }
    return (int) ans;
  }

  public static void main(String args[]) {
    NumWaysSplitString n = new NumWaysSplitString();
    System.out.println(n.numWays("100100010100110"));
  }

}
