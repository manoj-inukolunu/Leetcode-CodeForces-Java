package com.leetcode.random;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.checkerframework.checker.units.qual.K;

public class KDiff {

  class Pair {

    int first;
    int second;

    public Pair(int first, int second) {
      this.first = first;
      this.second = second;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Pair pair = (Pair) o;
      return first == pair.first &&
          second == pair.second;
    }

    @Override
    public int hashCode() {
      return Objects.hash(first, second);
    }
  }

  public int findPairs(int[] nums, int k) {
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      map.put(nums[i], i);
    }

    HashSet<Pair> pairs = new HashSet<>();
    int count = 0;
    for (int i = 0; i < nums.length; i++) {
      int first = nums[i];
      if (map.containsKey(nums[i] - k)) {
        Pair pair = new Pair(Math.min(first, nums[i] - k), Math.max(first, nums[i] - k));
        if (!pairs.contains(pair) && map.get(nums[i] - k) != i) {
          count++;
          pairs.add(pair);
        }
      }
      if (map.containsKey(nums[i] + k)) {
        Pair pair = new Pair(Math.min(first, nums[i] + k), Math.max(first, nums[i] + k));
        if (!pairs.contains(pair) && map.get(nums[i] + k) != i) {
          count++;
          pairs.add(pair);
        }
      }

    }
    return count;
  }

  public static void main(String args[]) {
    int nums[] = new int[]{-1, -2, -3};

    KDiff k = new KDiff();
    System.out.println(k.findPairs(nums, 1));
  }

}
