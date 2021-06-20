package com.leetcode.random10.sixmonths.medium;

import java.util.Arrays;

public class LargestNumber {

  public String largestNumber(int[] nums) {

    Integer[] arr = new Integer[nums.length];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = nums[i];
    }

    Arrays.sort(arr, (num1, num2) -> {
      String str1 = Integer.toString(num1);
      String str2 = Integer.toString(num2);
      return -Integer.compare(Integer.parseInt(str1 + str2), Integer.parseInt(str2 + str1));
    });

    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < arr.length; i++) {
      buffer.append(arr[i]);
    }
    return buffer.toString();
  }

  public static void main(String args[]) {
    int N = 1234;
    double expo = Math.log10(N);
    int expNum = (int) Math.floor(expo) + 1;  //gets the total digits in the Integer N

    System.out.println(expNum);

    LargestNumber l = new LargestNumber();
    System.out.println(l.largestNumber(new int[]{3, 30, 34, 5, 9}));
  }

}
