package com.leetcode.random1;

public class WildCardMatching {

  public boolean isMatch(String s, String p) {
    Boolean[][] dp = new Boolean[s.length() + 1][p.length() + 1];
    return match(s, p, 0, 0, dp);

  }

  private boolean match(String s, String p, int s1, int p1, Boolean[][] dp) {
    if (dp[s1][p1] != null) {
      return dp[s1][p1];
    }
    if (s1 >= s.length() && p1 >= p.length()) {
      return true;
    }
    if (s1 >= s.length() && p1 < p.length()) {
      for (int i = p1; i < p.length(); i++) {
        if (p.charAt(i) != '*') {
          return false;
        }
      }
      return true;
    }
    if (p1 >= p.length() && s1 < s.length()) {
      return p.charAt(p.length() - 1) == '*';
    }
    if (p.charAt(p1) == '?') {
      dp[s1][p1] = match(s, p, s1 + 1, p1 + 1, dp);
      return dp[s1][p1];
    }
    if (p.charAt(p1) == '*') {
      dp[s1][p1] = match(s, p, s1, p1 + 1, dp) || match(s, p, s1 + 1, p1, dp);
      return dp[s1][p1];
    }

    if (s.charAt(s1) == p.charAt(p1)) {
      dp[s1][p1] = match(s, p, s1 + 1, p1 + 1, dp);
      return dp[s1][p1];
    }
    dp[s1][p1] = false;
    return false;
  }

  public static void main(String args[]) {
    String s = "";
    String p = "*******";
    WildCardMatching w = new WildCardMatching();

    System.out.println(w.isMatch(s, p));


  }

}
