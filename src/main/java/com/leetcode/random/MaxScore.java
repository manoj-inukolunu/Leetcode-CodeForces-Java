package com.leetcode.random;

import java.util.Arrays;
import java.util.HashMap;

public class MaxScore {

  public int maxScore(int[] cardPoints, int k) {

    if(k>=cardPoints.length){
      return Arrays.stream(cardPoints).sum();
    }
    return maxScore(0, cardPoints.length - 1, k, 0, cardPoints, new HashMap<>());

  }

  private int maxScore(int start, int end, int k, int curr, int[] cardPoints,
      HashMap<String, Integer> map) {
    if (curr >= k) {
      return 0;
    }
    String key = start + ":" + end + ":" + curr;
    if (map.containsKey(key)) {
      return map.get(key);
    }
    int startMax = cardPoints[start] + maxScore(start + 1, end, k, curr + 1, cardPoints, map);
    int endMax = cardPoints[end] + maxScore(start, end - 1, k, curr + 1, cardPoints, map);

    map.put(key, Math.max(startMax, endMax));
    return Math.max(startMax, endMax);
  }

  //1 1 79


  public static void main(String args[]) {
    int arr[] = new int[]{53, 14, 91, 35, 51, 9, 80, 27, 6, 15, 77, 86, 34, 62, 55, 45, 91, 45, 23, 75, 66, 42, 62, 13, 34, 18, 89, 67, 93, 83, 100,
        14, 92, 73, 48, 2, 47, 93, 99, 100, 88, 84, 48};
    int k = 43;
    MaxScore m = new MaxScore();
    System.out.println(m.maxScore(arr, k));

  }

}
