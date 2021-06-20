package com.leetcode.random;

import java.util.HashMap;

public class FourSum2 {

  public int count4(int[] A, int[] B, int[] C, int D[]) {
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < A.length; i++) {
      for (int j = 0; j < B.length; j++) {
        map.put(A[i] + B[j], map.getOrDefault(A[i] + B[j], 0) + 1);
      }
    }
    int count = 0;
    for (int i = 0; i < C.length; i++) {
      for (int j = 0; j < D.length; j++) {
        int curr = C[i] + D[i];
        if (map.containsKey(-curr)) {
          count += (map.get(-curr));
        }
      }
    }
    return count;
  }

  public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
    HashMap<Integer, Integer> dMap = new HashMap();
    for (int num : D) {
      dMap.put(num, dMap.getOrDefault(num, 0) + 1);
    }
    HashMap<Integer, Integer> cMap = new HashMap();
    for (int num : C) {
      cMap.put(num, cMap.getOrDefault(num, 0) + 1);
    }
    HashMap<Integer, Integer> bMap = new HashMap<>();
    for (int num : B) {
      bMap.put(num, bMap.getOrDefault(num, 0) + 1);
    }
    HashMap<Integer, Integer> aMap = new HashMap<>();
    for (int num : A) {
      aMap.put(num, aMap.getOrDefault(num, 0) + 1);
    }
    int count = 0;
    for (int aKey : aMap.keySet()) {
      for (int bKey : bMap.keySet()) {
        for (int cKey : cMap.keySet()) {
          int curr = aKey + bKey + cKey;
          if (dMap.containsKey(-curr)) {
            count += (aMap.get(aKey) * cMap.get(cKey) * dMap.get(-curr) * bMap.get(bKey));
          }
        }
      }
    }
    return count;
  }

}
