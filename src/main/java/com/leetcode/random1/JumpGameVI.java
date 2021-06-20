package com.leetcode.random1;

import java.util.Deque;
import java.util.LinkedList;

public class JumpGameVI {


  public int maxResult(int[] nums, int k) {
    int[] dp = new int[nums.length];
    Deque<Integer> deque = new LinkedList<>();
    dp[0] = nums[0];
    int i = 1;
    while (i < nums.length) {
      while (!deque.isEmpty() && deque.peekFirst() < i - k) {
        deque.pollFirst();
      }
      dp[i] = deque.isEmpty() ? nums[i] : dp[deque.peek()];
      while (!deque.isEmpty() && dp[deque.peekLast()] < dp[i]) {
        deque.pollLast();
      }
      deque.offerLast(i);
      i++;
    }
    return dp[dp.length - 1];
  }

  public static void main(String args[]) {
    JumpGameVI j = new JumpGameVI();
    System.out.println(j.maxResult(new int[]{11, -1, -2, 4, -7, 3}, 2));
  }

}
