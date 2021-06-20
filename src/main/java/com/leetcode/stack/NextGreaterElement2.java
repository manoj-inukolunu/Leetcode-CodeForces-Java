package com.leetcode.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * @author manoji on 5/4/20.
 */
public class NextGreaterElement2 {

  public int[] nextGreaterElements(int[] nums) {
    int[] arr = new int[nums.length << 1];
    int[] res = new int[nums.length];
    Arrays.fill(res, -1);
    for (int i = 0; i < arr.length; i++) {
      arr[i] = nums[getIndex(i, nums.length)];
    }
    Stack<Integer[]> stack = new Stack();
    for (int i = 0; i < arr.length; i++) {
      if (stack.isEmpty()) {
        stack.push(new Integer[]{arr[i], i});
      } else {
        Integer[] top = stack.peek();
        if (arr[i] > top[0]) {
          while (!stack.isEmpty() && stack.peek()[0] < arr[i]) {
            top = stack.peek();
            if (top[1] < nums.length) {
              res[top[1]] = arr[i];
            }
            stack.pop();
          }
        }
        stack.push(new Integer[]{arr[i], i});
      }
    }

    return res;
  }

  private int getIndex(int idx, int length) {
    return idx < length ? idx : idx - length;
  }


  public static void main(String args[]) {
    int[] nums = new int[]{1, 2, 1};
    NextGreaterElement2 n = new NextGreaterElement2();
    int res[] = n.nextGreaterElements(nums);

    System.out.println(Arrays.toString(res));
  }

}
