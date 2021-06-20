package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CarPooling {

  class Triplet {

    int start;
    int end;
    int num;

    public Triplet(int start, int end, int num) {
      this.start = start;
      this.end = end;
      this.num = num;
    }
  }

  public boolean carPooling(int[][] trips, int capacity) {
    List<Triplet> list = new ArrayList<>();
    for (int i = 0; i < trips.length; i++) {
      list.add(new Triplet(trips[i][1], trips[i][2], trips[i][0]));
    }

    Collections.sort(list, Comparator.comparingInt(o -> o.start));

    int curr = 0;

    boolean[] dropped = new boolean[list.size()];
    Arrays.fill(dropped, false);

    for (int i = 0; i < list.size(); i++) {
      Triplet pos = list.get(i);
      if (curr == 0) {
        curr += pos.num;
        continue;
      }
      for (int j = i - 1; j >= 0; j--) {
        if (!dropped[j]) {
          dropped[j] = true;

          curr -= list.get(j).num;
          break;
        }
      }
      if (curr + pos.num > capacity) {
        return false;
      } else {
        curr += pos.num;
      }
    }
    return true;
  }

}
