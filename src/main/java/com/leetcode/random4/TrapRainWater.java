package com.leetcode.random4;

import java.util.Arrays;
import java.util.Stack;

public class TrapRainWater {

  public int trap(int[] height) {
    if (height.length == 0) {
      return 0;
    }
    int area = 0;
    int i = 0, start, end = height.length - 1;
    while (i < height.length) {
      if (height[i] == 0) {
        i++;
      } else {
        break;
      }
    }
    start = i;
    int[] right = new int[height.length];
    int[] left = new int[height.length];
    Arrays.fill(right, -1);
    Arrays.fill(left, -1);
    int[] pre = new int[height.length];
    pre[0] = height[0];
    for (int j = 1; j < height.length; j++) {
      pre[j] = height[j] + pre[j - 1];
    }
    Stack<int[]> stack = new Stack<>();
    while (i < height.length) {
      if (stack.isEmpty()) {
        stack.push(new int[]{i, height[i]});
      } else if (height[i] <= stack.peek()[1]) {
        stack.push(new int[]{i, height[i]});
      } else {
        while (!stack.isEmpty() && stack.peek()[1] <= height[i]) {
          int[] curr = stack.pop();
          right[curr[0]] = i;
        }
        stack.push(new int[]{i, height[i]});
      }
      i++;
    }
    stack = new Stack<>();
    i = height.length - 1;
    while (i >= 0) {
      if (stack.isEmpty()) {
        stack.push(new int[]{i, height[i]});
      } else if (height[i] <= stack.peek()[1]) {
        stack.push(new int[]{i, height[i]});
      } else {
        while (!stack.isEmpty() && stack.peek()[1] <= height[i]) {
          int[] curr = stack.pop();
          left[curr[0]] = i;
        }
        stack.push(new int[]{i, height[i]});
      }
      i--;
    }
    System.out.println(Arrays.toString(right));
    boolean reachedEnd = true;
    for (i = start; i + 1 < right.length; i++) {
      if (right[i] != -1) {
        int currArea = height[i] * (right[i] - i - 1);
        int sub = pre[right[i] - 1] - pre[i];
        area += (currArea - sub);
        i = right[i] - 1;
      } else {
        start = i;
        reachedEnd = false;
        break;
      }
    }
    if (reachedEnd) {
      return area;
    }
    while (end >= 0) {
      if (height[end] == 0) {
        end--;
      } else {
        break;
      }
    }
    System.out.println(Arrays.toString(left));
    for (i = end; i > start; i--) {
      if (left[i] != -1 && left[i] > start) {
        int currArea = height[i] * (i - left[i] - 1);
        int sub = Math.abs(pre[i - 1] - pre[left[i]]);
        area += (currArea - sub);
        i = left[i] + 1;
      } else {
        break;
      }
    }
    return area;
  }

  public static void main(String args[]) {
    int[] arr = new int[]{6, 8, 2, 8, 8, 2};
    TrapRainWater t = new TrapRainWater();
    System.out.println(t.trap(arr));
  }

}
