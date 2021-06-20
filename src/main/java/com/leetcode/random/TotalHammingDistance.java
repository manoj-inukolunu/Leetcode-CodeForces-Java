package com.leetcode.random;

public class TotalHammingDistance {

  class Pair {

    int zero;
    int one;

    public Pair(int zero, int one) {
      this.zero = zero;
      this.one = one;
    }
  }

  public int totalHammingDistance(int[] nums) {

    int sum = 0;

    Pair[] arr = new Pair[32];

    for (int i = 0; i < nums.length; i++) {
      int curr = nums[i];
      System.out.println(Integer.toBinaryString(curr));
      for (int j = 0; j < 32; j++) {
        if (arr[j] == null) {
          arr[j] = new Pair(0, 0);
        }
        if ((curr & (1 << j)) > 0) {
          arr[j].one++;
        } else {
          arr[j].zero++;
        }
      }
    }
    for (int i = 0; i < arr.length; i++) {
      sum += (arr[i].one * arr[i].zero);
    }
    return sum;

  }

  public static void main(String args[]) {
    int[] arr = new int[]{4, 14, 2};
    TotalHammingDistance t = new TotalHammingDistance();
    System.out.println(t.totalHammingDistance(arr));
  }


}
