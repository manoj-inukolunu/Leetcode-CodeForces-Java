package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.List;

public class MedianFinder {


  List<Double> list = new ArrayList();

  /**
   * initialize your data structure here.
   */
  public MedianFinder() {

  }

  public void addNum(int num) {
    insert(list, num);
  }

  public double findMedian() {

    int size = list.size();
    if (size % 2 == 0) {
      int num1 = ((size) / 2) - 1;
      int num2 = num1 + 1;
      return (list.get(num1) + list.get(num2)) / 2.0;
    } else {
      return list.get(size / 2);
    }
  }

  private void insert(List<Double> list, int val) {
    int low = 0, high = list.size();
    while (low < high) {
      int mid = low + (high - low) / 2;
      if (list.get(mid) == val) {
        list.add(mid, Double.valueOf(val));
        return;
      }
      if (list.get(mid) < val) {
        low = mid + 1;
      } else {
        high = mid;
      }
    }
    list.add(low, Double.valueOf(val));
  }


  public static void main(String args[]) {
    MedianFinder m = new MedianFinder();
    m.addNum(6);
    System.out.println(m.findMedian());
    m.addNum(10);
    System.out.println(m.findMedian());
    m.addNum(2);
    System.out.println(m.findMedian());
  }
}
