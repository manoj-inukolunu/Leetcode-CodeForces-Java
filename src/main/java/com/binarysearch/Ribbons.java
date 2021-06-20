package com.binarysearch;

public class Ribbons {

  public int solve(int[] ribbons, int k) {
    int low = 1, high = Integer.MIN_VALUE;
    for (int i = 0; i < ribbons.length; i++) {
      low = Math.min(ribbons[i], low);
      high = Math.max(ribbons[i], high);
    }
    int ans = -1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (possible(mid, ribbons, k)) {
        ans = mid;
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return ans;
  }

  private boolean possible(int len, int[] ribbons, int k) {
    int count = 0;
    for (int i = 0; i < ribbons.length; i++) {
      count += ribbons[i] / len;
      if (count >= k) {
        return true;
      }
    }
    return false;
  }

  public static void main(String args[]) {
    Ribbons r = new Ribbons();
    System.out.println(r.solve(new int[]{1, 2, 3, 4, 9}, 5));
  }

}
