package com.leetcode.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FourSum {

  public List<Integer[]> twoSum(int[] arr, int start, int end, int sum) {
    List<Integer[]> list = new ArrayList<>();
    while (start < end) {
      int currSum = arr[start] + arr[end];
      if (currSum == sum) {
        list.add(new Integer[]{arr[start], arr[end]});
      } else if (currSum < sum) {
        start++;
      } else {
        end--;
      }
    }
    return list;
  }

  public List<List<Integer>> fourSum(int[] nums, int target) {
    Arrays.sort(nums);
    Set<List<Integer>> ans = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        int curr = nums[i] + nums[j];
        List<Integer[]> arr = twoSum(nums, j + 1, nums.length - 1, target - curr);
        for (Integer[] list : arr) {
          List<Integer> currList = new ArrayList<>();
          currList.add(nums[i]);
          currList.add(nums[j]);
          currList.add(list[0]);
          currList.add(list[1]);
          ans.add(currList);
        }
      }
    }
    return new ArrayList<>(ans);
  }

  public static void main(String args[]) {
    int[] arr = new int[]{1, 0, -1, 0, -2, 2};
    FourSum f = new FourSum();
    System.out.println(f.fourSum(arr, 0));
  }

}
