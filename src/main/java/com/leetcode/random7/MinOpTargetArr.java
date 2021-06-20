package com.leetcode.random7;

public class MinOpTargetArr {

  public int minOperations(int[] nums) {
    int count = 0;
    while (true) {
      int numO = 0, numZ = 0;
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] == 1) {
          numO++;
        } else if (nums[i] == 0) {
          numZ++;
        } else if (nums[i] % 2 != 0) {
          count++;
          nums[i] = (nums[i] - 1) / 2;
        } else {
          nums[i] = (nums[i]) / 2;
        }
      }
      if (numO + numZ == nums.length) {
        count += (numO);
        break;
      }
      count++;
    }
    return count;
  }

  public static void main(String args[]) {
    int[] arr = new int[]{2, 4, 8, 16};
    MinOpTargetArr m = new MinOpTargetArr();
    System.out.println(m.minOperations(arr));

  }


}
