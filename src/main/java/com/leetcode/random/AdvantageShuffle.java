package com.leetcode.random;

import java.util.Arrays;
import java.util.TreeMap;

public class AdvantageShuffle {

  public int[] advantageCount(int[] A, int[] B) {
    TreeMap<Integer, Integer> map = new TreeMap<>();
    for (int num : A) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    int[] ans = new int[B.length];
    for (int i = 0; i < B.length; i++) {
      Integer higher = map.higherKey(B[i]);
      if (higher == null) {
        ans[i] = map.firstKey();
        int count = map.get(ans[i]);
        if (count - 1 == 0) {
          map.remove(ans[i]);
        } else {
          map.put(ans[i], count - 1);
        }
      } else {
        ans[i] = higher;
        int count = map.get(ans[i]);
        if (count - 1 == 0) {
          map.remove(ans[i]);
        } else {
          map.put(ans[i], count - 1);
        }
      }
    }
    return ans;
  }

  public static void main(String args[]) {
    AdvantageShuffle a = new AdvantageShuffle();
    int[] A = new int[]{2, 0, 4, 1, 2};
    int[] B = new int[]{1, 3, 0, 0, 2};
    int[] arr = a.advantageCount(A, B);
    System.out.println(Arrays.toString(arr));
  }

}
