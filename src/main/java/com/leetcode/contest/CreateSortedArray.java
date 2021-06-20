package com.leetcode.contest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CreateSortedArray {


  int mod = (int) Math.pow(10, 9) + 7;


  public int createSortedArray(int[] instructions) {
    List<Integer> list = new ArrayList<>();
    HashMap<Integer, Integer> map = new HashMap<>();
    int total = 0;
    int length = 0;
    for (int i = 0; i < instructions.length; i++) {
      int pos = Collections.binarySearch(list, instructions[i]);
      int start, end;
      if (pos >= 0) {
        start = getPos(list, instructions[i], length);
      } else {
        start = -(pos + 1);
      }
      list.add(start, instructions[i]);
      map.put(instructions[i], map.getOrDefault(instructions[i], 0) + 1);
      end = start + map.get(instructions[i]) - 1;
      int greater = (length - end);
      int smaller = start;
      total = total % mod + Math.min(smaller, greater);
      length++;
    }
    return total;
  }

  public int getPos(List<Integer> list, int val, int high) {
    int low = 0;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if ((mid == 0 || val > list.get(mid - 1)) && list.get(mid) == val) {
        return mid;
      } else if (val > list.get(mid)) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return -1;
  }

  public static void main(String args[]) {
    int[] arr = new int[]{1, 3, 3, 3, 2, 4, 2, 1, 2};

    CreateSortedArray c = new CreateSortedArray();
    System.out.println(c.createSortedArray(arr));
  }

}
