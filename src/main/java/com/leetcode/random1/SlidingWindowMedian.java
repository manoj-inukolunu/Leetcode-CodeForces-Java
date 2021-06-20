package com.leetcode.random1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class SlidingWindowMedian {

  private void add(PriorityQueue<Integer> l, PriorityQueue<Integer> r, int num, HashMap<Integer, Integer> left, HashMap<Integer, Integer> right) {
    l.add(num);
    left.put(num, left.getOrDefault(num, 0) + 1);
    int val = l.poll();
    left.put(val, left.get(val) - 1);
    if (left.get(val) <= 0) {
      left.remove(val);
    }
    r.add(val);
    right.put(val, right.getOrDefault(val, 0) + 1);

    while (l.size() < r.size()) {
      val = r.poll();
      right.put(val, right.get(val) - 1);
      if (right.get(val) <= 0) {
        right.remove(val);
      }
      l.add(val);
      left.put(val, left.getOrDefault(val, 0) + 1);
    }
  }

  private void remove(PriorityQueue<Integer> l, PriorityQueue<Integer> r, int num, HashMap<Integer, Integer> left, HashMap<Integer, Integer> right) {
    if (left.containsKey(num)) {
      List<Integer> hold = new ArrayList<>();
      while (!l.isEmpty()) {
        int val = l.poll();
        if (val == num) {
          l.addAll(hold);
          left.put(num, left.get(num) - 1);
          if (left.get(num) == 0) {
            left.remove(num);
          }
          break;
        } else {
          hold.add(val);
        }
      }
      int min = r.poll();
      l.add(min);
      left.put(min, left.getOrDefault(min, 0) + 1);
      right.put(min, right.get(min) - 1);
      if (right.get(min) == 0) {
        right.remove(min);
      }
    } else {
      List<Integer> hold = new ArrayList<>();
      while (!r.isEmpty()) {
        int val = r.poll();
        if (val == num) {
          r.addAll(hold);
          right.put(num, right.get(num) - 1);
          if (right.get(num) == 0) {
            right.remove(num);
          }
          break;
        } else {
          hold.add(val);
        }
      }
      int max = l.poll();
      r.add(max);
      right.put(max, right.getOrDefault(max, 0) + 1);
      left.put(max, left.get(max) - 1);
      if (left.get(max) == 0) {
        left.remove(max);
      }
    }
  }


  public double[] medianSlidingWindow(int[] nums, int k) {

    HashMap<Integer, Integer> left = new HashMap<>();
    HashMap<Integer, Integer> right = new HashMap<>();
    PriorityQueue<Integer> lP = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> rP = new PriorityQueue<>();
    for (int i = 0; i < k; i++) {
      add(lP, rP, nums[i], left, right);
    }
    List<Double> ans = new ArrayList<>();
    ans.add(getMedian(lP, rP));
    for (int i = 1; i + k <= nums.length; i++) {
      remove(lP, rP, nums[i - 1], left, right);
      add(lP, rP, nums[i + k - 1], left, right);
      ans.add(getMedian(lP, rP));
    }
    return ans.stream().mapToDouble(a -> a).toArray();
  }

  private double getMedian(PriorityQueue<Integer> maxP, PriorityQueue<Integer> minP) {
    return maxP.size() > minP.size() ? maxP.peek() : (maxP.peek() + minP.peek()) * 0.5d;
  }


  public static void main(String args[]) {
    SlidingWindowMedian s = new SlidingWindowMedian();

    double[] ans = s.medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 5);
    System.out.println(Arrays.toString(ans));
  }

}
