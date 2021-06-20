package com.leetcode.contest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class StoneGame4 {

  public int stoneGameVI(int[] aVal, int[] bVal) {
    int a = 0, b = 0;
    List<Integer> aList = new ArrayList<>();
    List<Integer> bList = new ArrayList<>();

    PriorityQueue<int[]> aP = new PriorityQueue<>((o1, o2) -> -Integer.compare(o1[0], o2[0]));
    PriorityQueue<int[]> bP = new PriorityQueue<>((o1, o2) -> -Integer.compare(o1[0], o2[0]));
    int i = 0;
    for (int num : aVal) {
      aList.add(num);
      aP.add(new int[]{num, i++});
    }
    i = 0;
    for (int num : bVal) {
      bList.add(num);
      bP.add(new int[]{num, i++});
    }
    int turn = 0;
    HashSet<Integer> used = new HashSet<>();
    while (!aP.isEmpty() && !bP.isEmpty()) {
      int[] aTop = aP.poll();
      int[] bTop = bP.poll();
      if (used.contains(aTop[1])) {
        bP.add(bTop);
        continue;
      }
      if (used.contains(bTop[1])) {
        aP.add(aTop);
        continue;
      }

      if (turn == 0) {
        if (aTop[0] >= bTop[0]) {
          a += aVal[aTop[1]];
          used.add(aTop[1]);
        } else {
          a += aVal[bTop[1]];
          used.add(bTop[1]);
        }
        turn = 1;
      } else {
        if (bTop[0] >= aTop[0]) {
          b += bTop[0];
          used.add(bTop[1]);
        } else {
          b += bVal[aTop[1]];
          used.add(aTop[1]);
        }
        turn = 0;
      }
    }
    if (turn == 0) {
      if (aP.isEmpty() && !bP.isEmpty()) {
        int[] val = bP.poll();
        a += aVal[val[1]];
      } else if (!aP.isEmpty()) {
        a += aP.poll()[0];
      }
    } else {
      if (aP.isEmpty() && !bP.isEmpty()) {
        b += bP.poll()[0];
      } else if (!aP.isEmpty()) {
        int[] val = aP.poll();
        b += bVal[val[1]];
      }
    }

    return Integer.compare(a, b);
  }


  public static void main(String args[]) {
    /*
    [6,5,1,2,10,6]
[7,7,7,7,3,7]
     */
    StoneGame4 s = new StoneGame4();
    int[] alice = new int[]{6, 5, 1, 2, 10, 6};
    int[] bob = new int[]{7, 7, 7, 7, 3, 7};
    System.out.println(s.stoneGameVI(alice, bob));
  }

}
