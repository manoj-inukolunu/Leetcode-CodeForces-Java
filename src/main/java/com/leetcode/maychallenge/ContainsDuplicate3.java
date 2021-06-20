package com.leetcode.maychallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;
import javax.swing.text.html.parser.Entity;

/**
 * @author manoji on 5/18/20.
 */
public class ContainsDuplicate3 {

  class Pair {

    Long number;
    int index;

    public Pair(Long number, int index) {
      this.number = number;
      this.index = index;
    }
  }


  public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

    Pair[] arr = new Pair[nums.length];

    for (int i = 0; i < nums.length; i++) {
      arr[i] = new Pair(Long.valueOf(nums[i]), i);
    }

    Arrays.sort(arr, Comparator.comparingLong(value -> value.number));

    for (int i = 0; i < arr.length; i++) {
      Pair curr = arr[i];
      for (int j = i; j < arr.length; j++) {
        Pair next = arr[j];
        if (Math.abs(curr.number - next.number) > t) {
          break;
        }
        if (curr.index != next.index && Math.abs(curr.number - next.number) <= t && (Math.abs(curr.index - next.index) <= k)) {
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String args[]) {
    ContainsDuplicate3 c = new ContainsDuplicate3();
    System.out.println(c.containsNearbyAlmostDuplicate(new int[]{1, 5, 9, 1, 5, 9}, 2, 3));
  }

}
