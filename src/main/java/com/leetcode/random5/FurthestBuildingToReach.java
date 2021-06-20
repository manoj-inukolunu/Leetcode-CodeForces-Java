package com.leetcode.random5;

import java.util.Collections;
import java.util.PriorityQueue;

public class FurthestBuildingToReach {

  public int furthestBuilding(int[] heights, int bricks, int ladders) {
    PriorityQueue<Integer> p = new PriorityQueue(Collections.reverseOrder());
    int i = 0;
    for (; i + 1 < heights.length; i++) {
      if (heights[i] >= heights[i + 1]) {
        continue;
      }
      int diff = heights[i + 1] - heights[i];
      if (bricks >= diff) {
        bricks -= diff;
        p.add(diff);
      } else if (ladders > 0) {
        if (!p.isEmpty() && p.peek() > diff) {
          bricks += p.poll();
          bricks -= diff;
          p.add(diff);
        }
        ladders--;
      } else {
        break;
      }
    }
    return i;
  }

  public static void main(String args[]) {
    FurthestBuildingToReach f = new FurthestBuildingToReach();
    System.out.println(f.furthestBuilding(new int[]{1, 5, 1, 2, 3, 4, 10000}, 4, 1));
  }

}
