package com.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

public class Fancy {

  class Hold {

    int type;
    int by;
    int maxIdx;

    public Hold(int type, int by, int maxIdx) {
      this.type = type;
      this.by = by;
      this.maxIdx = maxIdx;
    }
  }

  List<Hold> hold = new ArrayList<>();
  List<Integer> list = new ArrayList<>();
  int mod = (int) Math.pow(10, 9) + 7;

  public Fancy() {

  }

  public void append(int val) {
    list.add(val);
  }

  public void addAll(int inc) {
    hold.add(new Hold(0, inc, list.size() - 1));
  }

  public void multAll(int m) {
    hold.add(new Hold(1, m, list.size() - 1));
  }

  public int getIndex(int idx) {
    if (idx >= list.size()) {
      return -1;
    }
    int curr = list.get(idx);
    for (int i = 0; i < hold.size(); i++) {
      Hold cHold = hold.get(i);
      if (cHold.maxIdx >= idx) {
        if (cHold.type == 0) {
          curr = curr % mod + cHold.by % mod;
        } else {
          curr = curr % mod * cHold.by % mod;
        }
      }
    }
    return curr;
  }
}
