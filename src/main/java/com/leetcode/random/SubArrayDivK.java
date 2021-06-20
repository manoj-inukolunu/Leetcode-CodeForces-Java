package com.leetcode.random;

import java.util.HashMap;

public class SubArrayDivK {

  public int subarraysDivByK(int[] A, int K) {
    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    int sum = 0;
    for (int i = 0; i < A.length; i++) {
      sum = (sum + A[i]) % K;
      sum = (sum + K) % K;
      map.put(sum, map.getOrDefault(sum, 0) + 1);
    }
    int ans = 0;
    for (int key : map.keySet()) {
      int m = map.get(key);
      ans += (m) * (m - 1) / 2;
    }

    return ans;
  }

}
