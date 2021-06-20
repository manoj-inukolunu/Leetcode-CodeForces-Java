package com.leetcode.random6;

public class BulbSwitcher4 {

  public int minFlips(String target) {

    int flips = 0;
    for (int i = 0; i < target.length(); i++) {
      int currState = (flips % 2 == 0) ? 0 : 1;
      if (Character.getNumericValue(target.charAt(i)) != currState) {
        flips++;
      }
    }

    return flips;

  }

  public static void main(String args[]) {
    BulbSwitcher4 b = new BulbSwitcher4();
    System.out.println(b.minFlips("001011101"));
  }

}
