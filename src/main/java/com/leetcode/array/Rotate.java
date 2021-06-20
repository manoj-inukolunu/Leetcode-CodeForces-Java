package com.leetcode.array;

import java.util.Arrays;
import java.util.LinkedList;


/**
 * @author manoji on 5/24/20.
 */
public class Rotate {

  public void rotate(int[] nums, int k) {
    LinkedList<Integer> queue = new LinkedList<>();
    for (int i = 0; i < nums.length; i++) {
      queue.add(nums[i]);
    }
    while (k-- > 0) {
      int num = queue.pollLast();
      queue.addFirst(num);
    }
    int i = 0;
    while (!queue.isEmpty()) {
      nums[i++] = queue.pollFirst();
    }
  }


  public static void main(String args[]) {
    Rotate rotate = new Rotate();

    int[] arr = new int[]{1, 2};
    rotate.rotate(arr, 3);

    System.out.println(Arrays.toString(arr));
  }

}
