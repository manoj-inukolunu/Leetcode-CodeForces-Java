package com.leetcode.random6;

public class BublSwitcher3 {

  public int numTimesAllBlue(int[] light) {
    int largestOn = -1;
    int totalOn = 0;
    int count = 0;
    for (int i = 0; i < light.length; i++) {
      int curr = light[i];
      largestOn = Math.max(largestOn, curr);
      totalOn++;
      if (largestOn == totalOn) {
        count++;
      }
    }
    return count;
  }

  public static void main(String args[]) {
    int[] bulbs = new int[]{1, 2, 3, 4, 5, 6};
    BublSwitcher3 b = new BublSwitcher3();
    System.out.println(b.numTimesAllBlue(bulbs));
  }

}
