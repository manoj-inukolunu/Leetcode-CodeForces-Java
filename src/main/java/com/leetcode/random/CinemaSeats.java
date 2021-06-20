package com.leetcode.random;

import java.util.HashMap;
import java.util.HashSet;

public class CinemaSeats {

  public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
    HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
    for (int i = 0; i < reservedSeats.length; i++) {
      HashSet<Integer> set = map.getOrDefault(reservedSeats[i][0], new HashSet<>());
      set.add(reservedSeats[i][1]);
      map.put(reservedSeats[i][0], set);
    }

    int count = (n - map.size()) * 2;

    for (int key : map.keySet()) {
      HashSet<Integer> curr = map.get(key);
      if (canAdd2(curr)) {
        count += 2;
      } else if (canAdd1(curr)) {
        count += 1;
      }
    }
    return count;
  }

  private boolean canAdd1(HashSet<Integer> curr) {
    boolean add4to7 = true;
    for (int i = 4; i <= 7; i++) {
      if (curr.contains(i)) {
        add4to7 = false;
        break;
      }
    }
    boolean add2To5 = true;
    for (int i = 2; i <= 5; i++) {
      if (curr.contains(i)) {
        add2To5 = false;
        break;
      }
    }
    boolean ad6To9 = true;
    for (int i = 6; i <= 9; i++) {
      if (curr.contains(i)) {
        ad6To9 = false;
        break;
      }
    }
    return ad6To9 || add2To5 || add4to7;
  }

  private boolean canAdd2(HashSet<Integer> curr) {
    for (int i = 2; i <= 9; i++) {
      if (curr.contains(i)) {
        return false;
      }
    }
    return true;
  }

}
