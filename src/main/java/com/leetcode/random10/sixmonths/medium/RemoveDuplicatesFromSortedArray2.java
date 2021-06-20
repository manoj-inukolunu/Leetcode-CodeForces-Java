package com.leetcode.random10.sixmonths.medium;

public class RemoveDuplicatesFromSortedArray2 {

  public int removeDuplicates(int[] nums) {
    int count = 1;
    int start = 1;
    int end = nums.length;
    while (start < end) {
      if (nums[start] == nums[start - 1] && count == 2) {
        int k = start;
        while (k < nums.length && nums[k] == nums[start]) {
          k++;
        }
        // System.out.println(Arrays.toString(nums));
        shiftArrLeftBy(nums, start, (k - start));
        // System.out.println(Arrays.toString(nums));
        end -= (k - start);
        count = 1;
      } else if (nums[start] == nums[start - 1]) {
        count++;
      } else {
        count = 1;
      }
      start++;
    }
    // System.out.println(Arrays.toString(nums));
    return end;
  }

  private void shiftArrLeftBy(int[] arr, int start, int by) {
    int count = by;
    int idx = start + count;
    while (start < arr.length && idx < arr.length) {
      arr[start] = arr[idx];
      start++;
      idx++;
    }
    while (count > 0) {
      arr[start] = Integer.MAX_VALUE;
      count--;
      start++;
    }
  }

  public static void main(String args[]) {
    RemoveDuplicatesFromSortedArray2 r = new RemoveDuplicatesFromSortedArray2();

    System.out.println(r.removeDuplicates(new int[]{1, 1, 1}));
  }

}
