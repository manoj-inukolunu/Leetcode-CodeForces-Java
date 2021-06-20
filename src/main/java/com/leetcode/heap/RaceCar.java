package com.leetcode.heap;

public class RaceCar {

  int ans = Integer.MAX_VALUE;

  public int racecar(int target) {
    race(0, 1, target, 0);
    return ans;
  }

  private boolean race(int pos, int speed, int target, int steps) {
    if (pos > target) {
      return false;
    }
    if (pos == target) {
      ans = Math.min(ans, steps);
      return true;
    }

    int nextPos = pos + speed;
    int nextSpeed = speed * 2;
    boolean acc = race(nextPos, nextSpeed, target, steps + 1);
    if (!acc) {
      boolean rev = race(pos, speed > 0 ? -1 : 1, target, steps + 1);
      return acc || rev;
    } else {
      boolean rev = race(pos, speed > 0 ? -1 : 1, target, steps + 1);
      return acc || rev;
    }
  }

  public static void main(String args[]) {
    RaceCar r = new RaceCar();

    System.out.println(r.racecar(6));
  }

}
