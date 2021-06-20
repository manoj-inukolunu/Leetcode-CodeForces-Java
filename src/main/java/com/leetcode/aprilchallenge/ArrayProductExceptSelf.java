package com.leetcode.aprilchallenge;

import java.util.Arrays;

/**
 * @author manoji on 4/15/20.
 */
public class ArrayProductExceptSelf {

  public int[] prod(int[] arr) {
    int countZeroes = 0;

    int prod = 1;

    for (int i = 0; i < arr.length; i++) {
      if (arr[i] != 0) {
        prod *= arr[i];
      } else {
        countZeroes++;
        if (countZeroes >= 2) {
          break;
        }
      }
    }

    if (countZeroes > 1) {
      Arrays.fill(arr, 0);
      return arr;
    } else if (countZeroes == 1) {
      for (int i = 0; i < arr.length; i++) {
        if (arr[i] == 0) {
          arr[i] = prod;
        } else {
          arr[i] = 0;
        }
      }
      return arr;
    } else {
      for (int i = 0; i < arr.length; i++) {
        arr[i] = prod / arr[i];
      }
      return arr;
    }
  }

  public static void main(String args[]) {
    ArrayProductExceptSelf arrayProductExceptSelf = new ArrayProductExceptSelf();
    System.out.println(Arrays.toString(arrayProductExceptSelf.prod(new int[]{1, 2, 3, 4})));
  }

}
