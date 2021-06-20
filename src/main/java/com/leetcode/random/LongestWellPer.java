package com.leetcode.random;

public class LongestWellPer {

  public int longestWPI(int[] hours) {
    int low = 0, high = hours.length;
    int ans = 0;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (found(hours, mid)) {
        ans = mid;
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    return ans;
  }

  private boolean found(int[] arr, int len) {
    int n = 0, t = 0;
    for (int i = 0; i < len; i++) {
      if (arr[i] > 8) {
        t++;
      } else {
        n++;
      }
    }
    if (t > n) {
      return true;
    }
    for (int i = len; i < arr.length; i++) {
      int curr = arr[i];
      int prev = arr[i - len];
      if (curr > 8 && prev <= 8) {
        t++;
        n--;
      } else if (curr <= 8 && prev > 8) {
        t--;
        n++;
      }
      if (t > n) {
        return true;
      }
    }
    return false;
  }


  public static void main(String args[]) {
    int[] arr = new int[]{9, 6, 9};
    LongestWellPer l = new LongestWellPer();
    System.out.println(l.longestWPI(arr));
  }
}
