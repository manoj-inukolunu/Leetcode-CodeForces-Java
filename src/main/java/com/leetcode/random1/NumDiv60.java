package com.leetcode.random1;

import java.util.HashMap;

public class NumDiv60 {

  public int numPairsDivisibleBy60(int[] time) {

    for (int i = 1; i < time.length; i++) {
      for (int j = i - 1; j >= 0; j--) {
        if ((time[i] + time[j]) % 60 == 0) {
          System.out.println(time[i] + " " + time[j]);
        }
      }
    }
    int[] arr = new int[17];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = 60 * (i + 1);
    }
    int count = 0;
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < time.length; i++) {
      for (int j = 0; j < arr.length; j++) {
        if (arr[j] >= time[i] && map.containsKey(arr[j] - time[i])) {
          count += (map.get(arr[j] - time[i]));
        }
      }
      map.put(time[i], map.getOrDefault(time[i], 0) + 1);
    }
    return count;
  }

  public static void main(String args[]) {
    int[] arr = new int[]{15, 63, 451, 213, 37, 209, 343, 319};
    NumDiv60 n = new NumDiv60();
    System.out.println(n.numPairsDivisibleBy60(arr));

  }

}
