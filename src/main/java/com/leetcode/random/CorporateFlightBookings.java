package com.leetcode.random;

import java.lang.reflect.Array;
import java.util.Arrays;
import org.checkerframework.checker.units.qual.C;

public class CorporateFlightBookings {

  public int[] corpFlightBookings(int[][] bookings, int n) {

    int[] ans = new int[n];
    for (int i = 0; i < bookings.length; i++) {
      int[] curr = bookings[i];
      int start = curr[0] - 1;
      int end = curr[1] - 1;
      int inc = curr[2];
      if (end + 1 < ans.length) {
        ans[end + 1] += (-inc);
      }
      ans[start] += inc;
    }

    for (int i = 1; i < ans.length; i++) {
      ans[i] = ans[i - 1] + ans[i];
    }

    return ans;

  }

  public static void main(String args[]) {
    CorporateFlightBookings c = new CorporateFlightBookings();
    int arr[][] = new int[][]{
        {1, 2, 10}, {2, 3, 20}, {2, 5, 25}
    };
    int ans[] = c.corpFlightBookings(arr, 5);
    System.out.println(Arrays.toString(ans));
  }


}
