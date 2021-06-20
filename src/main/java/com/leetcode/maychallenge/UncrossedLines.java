package com.leetcode.maychallenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author manoji on 5/25/20.
 */
public class UncrossedLines {

  public int maxUncrossedLines(int[] A, int[] B) {

    if (B.length > A.length) {
      maxUncrossedLines(B, A);
    }

    int max = 0;

    boolean[] aDp = new boolean[A.length];
    boolean[] bDp = new boolean[B.length];

    HashMap<Integer, List<Integer>> map = new HashMap<>();
    for (int i = 0; i < B.length; i++) {
      List<Integer> list = map.getOrDefault(B[i], new ArrayList<>());
      list.add(i);
      map.put(B[i], list);
    }

    for (int i = 0; i < A.length; i++) {
      if (!aDp[i]) {
        for (int j = i; j >= 0 && i < B.length; j--) {
          if (bDp[j]) {
            break;
          } else if (B[j] == A[i]) {
            List<Integer> list = map.get(B[j]);
            for (int k = 0; k < list.size(); k++) {
              if (!bDp[list.get(k)]) {
                bDp[list.get(k)] = true;
                aDp[i] = true;
                max++;
                break;
              }
            }
          }
        }
      }
    }
    return max;
  }

  public static void main(String args[]) {
    UncrossedLines u = new UncrossedLines();
    System.out.println(u.maxUncrossedLines(new int[]{1, 3, 7, 1, 7, 5}, new int[]{1, 9, 2, 5, 1}));
  }

}
