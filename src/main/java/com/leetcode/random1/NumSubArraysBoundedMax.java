package com.leetcode.random1;

import java.util.Arrays;
import java.util.Stack;

public class NumSubArraysBoundedMax {

  public int numSubarrayBoundedMax(int[] A, int L, int R) {
    int[] rightMax = getNextGreatest(A);
    int[] leftMax = getNextGreatestRightToLeft(A);
    System.out.println(Arrays.toString(rightMax));
    System.out.println(Arrays.toString(leftMax));
    int total = 0;
    for (int i = 0; i < A.length; i++) {
      if (A[i] >= L && A[i] <= R) {
        int leftLen = i - leftMax[i];
        int rightLen = rightMax[i] - i;
        if (rightLen > 0 && leftLen > 0) {
          total += (rightLen * leftLen);
        } else if (leftLen > 0) {
          total += leftLen;
        } else if (rightLen > 0) {
          total += rightLen;
        }
      }
    }
    return total;
  }

  private int[] getNextGreatest(int[] A) {
    int[] rightMax = new int[A.length];
    int i = 0;
    Stack<int[]> stack = new Stack<>();
    while (i < A.length) {
      if (stack.isEmpty()) {
        stack.push(new int[]{i, A[i]});
      } else if (A[i] < stack.peek()[1]) {
        stack.push(new int[]{i, A[i]});
      } else {
        while (!stack.isEmpty() && stack.peek()[1] <= A[i]) {
          int[] top = stack.pop();
          rightMax[top[0]] = i;
        }
        stack.push(new int[]{i, A[i]});
      }
      i++;
    }
    return rightMax;
  }

  private int[] getNextGreatestRightToLeft(int[] A) {
    int[] leftMax = new int[A.length];
    int i = A.length - 1;
    Stack<int[]> stack = new Stack<>();
    while (i >= 0) {
      if (stack.isEmpty()) {
        stack.push(new int[]{i, A[i]});
      } else if (A[i] < stack.peek()[1]) {
        stack.push(new int[]{i, A[i]});
      } else {
        while (!stack.isEmpty() && stack.peek()[1] < A[i]) {
          int[] top = stack.pop();
          leftMax[top[0]] = i;
        }
        stack.push(new int[]{i, A[i]});
      }
      i--;
    }
    return leftMax;
  }

  public static void main(String args[]) {
    NumSubArraysBoundedMax n = new NumSubArraysBoundedMax();
    System.out.println(n.numSubarrayBoundedMax(new int[]{2, 9, 2, 5, 6}, 2, 8));
  }

}
