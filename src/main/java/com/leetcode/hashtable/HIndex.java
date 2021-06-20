package com.leetcode.hashtable;

import java.util.Arrays;

/**
 * @author manoji on 6/28/20.
 */
public class HIndex {

  public int hIndex(int[] citations) {
    Arrays.sort(citations);
    int hIdx = Integer.MIN_VALUE;
    int N = citations.length;
    //0,1,3,5,6
    for (int i = 0; i < citations.length; i++) {
      if (N - i >= citations[i] && N - citations[i] <= i) {
        if (hIdx > citations[i]) {
          hIdx = citations[i];
        }

      }
    }
    return hIdx;
  }

  public static void main(String args[]) {
    HIndex h = new HIndex();
    System.out.println(h.hIndex(new int[]{3, 0, 6, 1, 5}));
  }

}
