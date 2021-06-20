package com.educative.io;

import java.util.Arrays;

/**
 * @author manoji on 2019-10-20.
 */
public class PartitionSet {

  public int canPartition(int[] num) {
    return this.canPartitionRecursive(num, 0, 0, 0);
  }

  private int canPartitionRecursive(int[] num, int currentIndex, int sum1, int sum2) {
    System.out.println(Arrays.toString(num));
    System.out.println(currentIndex);
    System.out.println(sum1);
    System.out.println(sum2);
    System.out.println("--------");

    if (currentIndex == num.length) {
      return Math.abs(sum1 - sum2);
    }

    int diff1 = canPartitionRecursive(num, currentIndex + 1, sum1 + num[currentIndex], sum2);

    int diff2 = canPartitionRecursive(num, currentIndex + 1, sum1, sum2 + num[currentIndex]);

    return Math.min(diff1, diff2);

  }


  public static void main(String args[]) {
    PartitionSet ps = new PartitionSet();
    int[] num = {1, 2, 3, 9};
    System.out.println(ps.canPartition(num));
    num = new int[]{1, 2, 7, 1, 5};
    System.out.println(ps.canPartition(num));
    num = new int[]{1, 3, 100, 4};
    System.out.println(ps.canPartition(num));
  }

}
