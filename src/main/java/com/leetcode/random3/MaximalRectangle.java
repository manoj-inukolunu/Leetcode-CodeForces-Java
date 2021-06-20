package com.leetcode.random3;

import java.util.Arrays;
import java.util.Stack;

public class MaximalRectangle {

  public int maximalRectangle(char[][] matrix) {
    int[][] pre = new int[matrix.length][matrix[0].length];
    for (int row = 0; row < matrix.length; row++) {
      for (int col = 0; col < matrix[row].length; col++) {
        if (row - 1 >= 0 && pre[row - 1][col] == 1) {
          pre[row][col] = pre[row - 1][col] + 1;
        }
      }
    }
    int ans = 0;
    for (int[] row : pre) {
      ans = Math.max(largestRectangleArea(row), ans);
    }
    return ans;
  }


  public int largestRectangleArea(int[] heights) {
    int[] lSmaller = new int[heights.length];
    Arrays.fill(lSmaller, -1);
    int[] rSmaller = new int[heights.length];
    Arrays.fill(rSmaller, heights.length);
    int i = 0;
    Stack<int[]> stack = new Stack<>();
    while (i < heights.length) {
      if (stack.isEmpty()) {
        stack.push(new int[]{i, heights[i]});
      } else if (stack.peek()[1] <= heights[i]) {
        stack.push(new int[]{i, heights[i]});
      } else {
        while (!stack.isEmpty() && heights[i] < stack.peek()[1]) {
          int[] top = stack.pop();
          rSmaller[top[0]] = i;
        }
        stack.push(new int[]{i, heights[i]});
      }
      i++;
    }
    i = heights.length - 1;
    while (i >= 0) {
      if (stack.isEmpty()) {
        stack.push(new int[]{i, heights[i]});
      } else if (stack.peek()[1] <= heights[i]) {
        stack.push(new int[]{i, heights[i]});
      } else {
        while (!stack.isEmpty() && heights[i] < stack.peek()[1]) {
          int[] top = stack.pop();
          lSmaller[top[0]] = i;
        }
        stack.push(new int[]{i, heights[i]});
      }
      i--;
    }
    System.out.println();
    int ans = Integer.MIN_VALUE;
    for (int j = 0; j < lSmaller.length; j++) {
      ans = Math.max(ans, (rSmaller[j] - lSmaller[j] - 1) * heights[j]);
    }
    return ans;
  }


  public static void main(String args[]) {
    MaximalRectangle m = new MaximalRectangle();
    char[][] arr = new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};

    System.out.println(m.maximalRectangle(arr));
  }

}
