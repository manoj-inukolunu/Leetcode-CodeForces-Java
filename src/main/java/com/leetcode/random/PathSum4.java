package com.leetcode.random;

import java.util.Arrays;

public class PathSum4 {


  int sum = 0;

  public int pathSum(int[] nums) {
    int[] arr = new int[20];
    Arrays.fill(arr, -1);
    for (int i = 0; i < nums.length; i++) {
      int num = nums[i];
      int val = num % 10;
      num /= 10;
      int pos = num % 10;
      num /= 10;
      int depth = num;
      int prevNodes = depth == 1 ? 0 : (int) Math.pow(2, depth - 1) - 1;
      int arrPos = prevNodes + pos - 1;
      arr[arrPos] = val;
    }
    // System.out.println(Arrays.toString(arr));
    dfs(0, 0, arr);
    return sum;
  }

  private void dfs(int nodePos, int curr, int[] arr) {
    if (nodePos >= arr.length || arr[nodePos] == -1) {
      return;
    }
    if (isLeaf(nodePos, arr)) {
      sum += (arr[nodePos] + curr);
      return;
    }
    dfs(2 * nodePos + 1, curr + arr[nodePos], arr);
    dfs(2 * nodePos + 2, curr + arr[nodePos], arr);
  }

  private boolean isLeaf(int nodePos, int[] arr) {
    return arr[2 * nodePos + 1] == -1 && arr[2 * nodePos + 2] == -1;
  }

  public static void main(String args[]) {
    int[] arr = new int[]{111, 217, 221, 315, 415};
    PathSum4 p = new PathSum4();
    System.out.println(p.pathSum(arr));
  }

}
