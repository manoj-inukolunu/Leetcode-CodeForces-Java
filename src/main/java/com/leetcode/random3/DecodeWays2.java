package com.leetcode.random3;

import java.util.Arrays;

public class DecodeWays2 {

  public int numDecodings(String s) {
    int[] dp = new int[s.length() + 1];
    Arrays.fill(dp, -1);
    return dfs(s, 0, dp);
  }

  private int dfs(String s, int idx, int[] dp) {
    if (idx >= s.length()) {
      return 1;
    }
    if (dp[idx] != -1) {
      return dp[idx];
    }
    if (s.charAt(idx) == '0') {
      return 0;
    }
    int count = 0;
    if (s.charAt(idx) != '*') {
      count += dfs(s, idx + 1, dp);
      if (idx + 1 < s.length()) {
        if (s.charAt(idx + 1) != '*' && Character.getNumericValue(s.charAt(idx)) != '0') {
          if (s.charAt(idx) == '1') {
            count += dfs(s, idx + 2, dp);
          } else if (s.charAt(idx) == '2' && Character.getNumericValue(s.charAt(idx + 1)) <= 6) {
            count += dfs(s, idx + 2, dp);
          }
        } else if (s.charAt(idx + 1) == '*' && Character.getNumericValue(s.charAt(idx)) != '0') {
          if (Character.getNumericValue(s.charAt(idx)) == 1) {
            for (int i = 1; i <= 9; i++) {
              count += dfs(s, idx + 2, dp);
            }
          } else if (Character.getNumericValue(s.charAt(idx)) == 2) {
            for (int i = 1; i <= 6; i++) {
              count += dfs(s, idx + 2, dp);
            }
          }
        }
      }
    } else if (s.charAt(idx) == '*' && idx + 1 < s.length() && s.charAt(idx + 1) != '0') {
      for (int i = 1; i <= 9; i++) {
        count += dfs(s, idx + 1, dp);
        if (idx + 1 < s.length()) {
          if (s.charAt(idx + 1) == '*') {
            if (i == 1) {
              for (int j = 1; j <= 9; j++) {
                count += dfs(s, idx + 2, dp);
              }
            } else if (i == 2) {
              for (int j = 1; j <= 6; j++) {
                count += dfs(s, idx + 2, dp);
              }
            }
          } else {
            if (i == 1) {
              count += dfs(s, idx + 2, dp);
            } else if (i == 2 && Character.getNumericValue(s.charAt(idx + 1)) <= 6) {
              count += dfs(s, idx + 2, dp);
            }
          }
        }
      }
    } else if (s.charAt(idx) == '*' && idx + 1 < s.length() && s.charAt(idx + 1) == '0') {
      for (int i = 1; i <= 2; i++) {
        count += dfs(s, idx + 2, dp);
      }
    }

    dp[idx] = count;
    return count;
  }

  public static void main(String args[]) {
    DecodeWays2 d = new DecodeWays2();
    System.out.println(d.numDecodings("*0"));
  }

}
