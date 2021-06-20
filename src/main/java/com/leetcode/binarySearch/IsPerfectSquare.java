package com.leetcode.binarySearch;

/**
 * @author manoji on 5/9/20.
 */
public class IsPerfectSquare {

  public boolean isPerfectSquare(int num) {
    if (num == 1) {
      return true;
    }
    int low = 1, high = num;
    while (low <= high) {
      long mid = low + ((high - low) / 2);
      long square = mid * mid;
      if (square == num) {
        return true;
      }
      if (square > num) {
        high = (int) mid - 1;
      } else {
        low = (int) mid + 1;
      }
    }
    return false;
  }

  public static void main(String args[]) {
    IsPerfectSquare p = new IsPerfectSquare();
    System.out.println(Math.sqrt(808201));
    System.out.println(p.isPerfectSquare(808201));
  }

}
