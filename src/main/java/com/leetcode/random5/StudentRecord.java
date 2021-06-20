package com.leetcode.random5;

public class StudentRecord {

  public int checkRecord(int n) {

    Integer[][][][] dp = new Integer[n][2][2][2];
    return dfs(1, 0, 0, 0, n, dp);

  }

  private int dfs(int day, int last, int sLast, int abs, int total, Integer[][][][] dp) {
    if (day > total) {
      return 1;
    }
    int curr = 0;
    if (dp[day][last][sLast][abs] != null) {
      return dp[day][last][sLast][abs];
    }
    if (canBeLate(last, sLast)) {
      if (abs == 0) {
        curr += dfs(day + 1, 0, last, 1, total, dp);

        curr += dfs(day + 1, 1, last, 0, total, dp);
        curr += dfs(day + 1, 0, last, 0, total, dp);
      } else {
        curr += dfs(day + 1, 1, last, abs, total, dp);
        curr += dfs(day + 1, 0, last, abs, total, dp);
      }
    } else {
      if (abs == 0) {
        curr += dfs(day + 1, 0, last, 1, total, dp);
        curr += dfs(day + 1, 0, last, abs, total, dp);
      } else {
        curr += dfs(day + 1, 0, last, abs, total, dp);
      }
    }
    dp[day][last][sLast][abs] = curr;
    return curr;
  }

  private boolean canBeLate(int last, int sLast) {
    return !(last == 1 && sLast == 1);
  }

  public static void main(String args[]) {
    StudentRecord s = new StudentRecord();
    System.out.println(s.checkRecord(50));
  }

}
