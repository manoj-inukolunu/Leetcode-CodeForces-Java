package com.leetcode.random1;

public class JumpGame2 {


  public int jump(int[] nums) {
    int[] interval = new int[]{0, 0};
    int jumps = 0;
    while (true) {
      jumps++;
      int canReach = -1;
      for (int i = interval[0]; i <= interval[1]; i++) {
        canReach = Math.max(canReach, i + nums[i]);
      }
      if (canReach >= nums.length - 1) {
        return jumps;
      }
      interval[0] = interval[1] + 1;
      interval[1] = canReach;
    }
  }


  public static void main(String args[]) {
    int[] arr = new int[]{1, 2, 1, 1, 1};
    JumpGame2 j = new JumpGame2();
    System.out.println(j.jump(arr));
  }

}
