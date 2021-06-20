package com.leetcode.day1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author manoji on 6/2/20.
 */
public class LongestMountainArray {

  public int longestMountain(int[] A) {
    if (A.length < 3) {
      return 0;
    }
    int[] arr = new int[A.length + 1];
    for (int i = 0; i < A.length; i++) {
      arr[i] = A[i];
    }
    arr[A.length] = Integer.MAX_VALUE;
    int max = 0;
    int low = 1, high = 1;
    int mountainBegin = -1, mountainEnd = -1;
    List<Integer[]> list = new ArrayList<>();
    boolean asc = true;
    for (int i = 0; i + 1 < A.length; i++) {
      if (A[i] > A[i + 1]) {
        asc = false;
        low++;
      }
      if (A[i] < A[i + 1]) {
        if (mountainBegin != -1) {
          mountainBegin = i;
        }
        if (asc) {
          high++;
        } else {
          mountainEnd = i;
          list.add(new Integer[]{mountainBegin, mountainEnd});
          asc = true;
          if (high + low > max && high + low >= 3) {
            max = high + low;
          }
          high = 1;
          low = 1;
        }
      }
    }

    if (mountainBegin != -1 && mountainEnd == -1) {

    }

    return max;
  }


  public static void main(String args[]) {
    int[] arr = new int[]{7, 4, 8};

    LongestMountainArray l = new LongestMountainArray();

    System.out.println(l.longestMountain(arr));
  }

}
