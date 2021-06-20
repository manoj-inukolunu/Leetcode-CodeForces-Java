package com.leetcode.binarySearch;

/**
 * @author manoji on 5/1/20.
 */
public class BadPackage {


  private boolean isBadVersion(int n) {
    if (n == 4 || n == 5) {
      return true;
    }
    return false;
  }

  public int firstBadVersion(int n) {
    int first = Integer.MAX_VALUE;
    int low = 0, high = n;
    while (low <= high) {
      int mid = low + ((high - low) / 2);
      boolean isBad = isBadVersion(mid);
      if (isBad) {
        if (mid < first) {
          first = mid;
        }
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return first;
  }

  public static void main(String[] args) {
    BadPackage b = new BadPackage();

    System.out.println(b.firstBadVersion(5));
  }

}
