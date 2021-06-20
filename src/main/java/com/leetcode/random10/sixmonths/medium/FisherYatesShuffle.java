package com.leetcode.random10.sixmonths.medium;

import java.util.Arrays;
import java.util.Random;

public class FisherYatesShuffle {

  private int[] shuffle(int[] nums) {
    Random random = new Random();
    for (int i = nums.length - 1; i >= 0; i--) {
      int j = random.nextInt(i + 1);
      swap(nums, i, j);
    }
    return nums;
  }

  public void swap(int[] nums, int i, int j) {
    int temp = nums[j];
    nums[j] = nums[i];
    nums[i] = temp;
  }

  public static void main(String args[]) {
    FisherYatesShuffle f = new FisherYatesShuffle();

    System.out.println(Arrays.toString(f.shuffle(new int[]{1, 2, 3})));
  }

}
