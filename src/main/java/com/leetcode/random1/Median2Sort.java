package com.leetcode.random1;

import java.util.Collections;
import java.util.PriorityQueue;

public class Median2Sort {

  private void addNum(PriorityQueue<Integer> max, PriorityQueue<Integer> min, int num) {
    max.add(num);
    min.add(max.poll());

    while (max.size() < min.size()) {
      max.add(min.poll());
    }
  }

  public double findMedianSortedArrays(int[] nums1, int[] nums2) {

    PriorityQueue<Integer> maxP = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> minP = new PriorityQueue<>();
    for (int i = 0; i < nums1.length; i++) {
      addNum(maxP, minP, nums1[i]);
    }
    for (int i = 0; i < nums2.length; i++) {
      addNum(maxP, minP, nums2[i]);
    }

    return maxP.size() > minP.size() ? maxP.peek() : (maxP.peek() + minP.peek()) * 0.5d;
  }

  public static void main(String args[]) {
    Median2Sort m = new Median2Sort();
    int[] arr1 = new int[]{1, 2};
    int[] arr2 = new int[]{3, 4};
    System.out.println(m.findMedianSortedArrays(arr1, arr2));
  }

}
