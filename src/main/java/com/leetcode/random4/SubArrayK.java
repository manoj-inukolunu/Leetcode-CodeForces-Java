package com.leetcode.random4;

import java.util.HashMap;

public class SubArrayK {

  private int num(int[] arr, int start, int end, int k) {
    int ret = 0;
    for (int i = k; i <= end - start; i++) {
      ret += count(arr, start, end, i, k);
    }
    return ret;
  }

  private int count(int[] arr, int start, int end, int len, int k) {
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = start; i < start + len; i++) {
      map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
    }
    int count = 0;
    if (map.size() == k) {
      count++;
    }
    for (int i = start + 1; i + len <= end; i++) {
      map.put(arr[i - 1], map.get(arr[i - 1]) - 1);
      if (map.get(arr[i - 1]) <= 0) {
        map.remove(arr[i - 1]);
      }
      map.put(arr[i + len - 1], map.getOrDefault(arr[i + len - 1], 0) + 1);
      if (map.size() == k) {
        count++;
      }
    }
    return count;
  }

  public int subarraysWithKDistinct(int[] A, int k) {

    HashMap<Integer, Integer> map = new HashMap<>();
    int ans = 0;
    int start = 0;
    HashMap<Integer, Integer> countMap = new HashMap();
    for (int i = 0; i < A.length; i++) {
      int end = i;
      while (end < A.length) {
        if (map.size() < k) {
          map.put(A[end], map.getOrDefault(A[end], 0) + 1);
          if (map.containsKey(A[end]) && map.size() == k) {
            countMap.put(end - start + 1, countMap.getOrDefault(end - start + 1, 0) + 1);
          }
          end++;
        } else if (map.size() == k && map.containsKey(A[end])) {
          countMap.put(end - start + 1, countMap.getOrDefault(end - start + 1, 0) + 1);
          map.put(A[end], map.get(A[end]) + 1);
          end++;
        } else {
          break;
        }
      }
      int maxWindowLen = end - i - 1;
      while (map.size() == k) {
        map.put(A[i], map.get(A[i]) - 1);
        if (map.get(A[i]) == 0) {
          break;
        }
        i++;

        while (maxWindowLen >= k && map.size() == k) {
          if (countMap.containsKey(maxWindowLen)) {
            countMap.put(maxWindowLen, countMap.get(maxWindowLen) + 1);
          } else {
            countMap.put(maxWindowLen, 1);
          }
          maxWindowLen--;
        }
        maxWindowLen++;
      }
      map.clear();
    }
    for (int key : countMap.keySet()) {
      ans += countMap.get(key);
    }
    return ans;
  }

  public static void main(String args[]) {
    SubArrayK s = new SubArrayK();
    int[] arr = new int[]{1, 2, 1, 2, 3};
    System.out.println(s.subarraysWithKDistinct(arr, 2));
  }


}
