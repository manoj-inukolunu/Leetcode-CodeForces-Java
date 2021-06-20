package com.leetcode.random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarFleet {

  class Pair {

    int pos;
    int speed;

    public Pair(int pos, int speed) {
      this.pos = pos;
      this.speed = speed;
    }
  }

  public int carFleet(int target, int[] position, int[] speed) {

    List<Pair> list = new ArrayList<>();
    for (int i = 0; i < position.length; i++) {
      list.add(new Pair(position[i], speed[i]));
    }

    Collections.sort(list, (o1, o2) -> -Integer.compare(o1.pos, o2.pos));
    int i = 0, fleets = 0;
    while (i < list.size()) {
      Pair curr = list.get(i);
      int j = i + 1;
      if (j >= list.size()) {
        fleets++;
        break;
      }
      while (true) {
        if (canReachBefore(curr, list.get(j), target)) {
          j++;
          if (j >= list.size()) {
            fleets++;
            return fleets;
          }
        } else {
          i = j;
          fleets++;
          break;
        }
      }
    }
    return fleets;
  }

  private boolean canReachBefore(Pair curr, Pair next, int targetPos) {
    double before = (targetPos - curr.pos) / (double) curr.speed;
    if (next.speed > curr.speed) {
      double time = (curr.pos - next.pos) / (double) (next.speed - curr.speed);
      if (time >= 0 && time <= before) {
        return true;
      }
    }
    return false;
  }

  public static void main(String args[]) {
    CarFleet w = new CarFleet();
    System.out.print(w.carFleet(13, new int[]{10, 2, 5, 7, 4, 6, 11}, new int[]{7, 5, 10, 5, 9, 4, 1}));

  }

}
