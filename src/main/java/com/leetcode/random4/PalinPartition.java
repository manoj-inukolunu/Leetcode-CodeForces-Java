package com.leetcode.random4;

public class PalinPartition {


  public boolean checkPartitioning(String s) {
    boolean[][] p = new boolean[s.length()][s.length()];
    genPalins(s, p);
    for (int i = 0; i < s.length(); i++) {
      if (p[0][i]) {
        for (int j = i + 1; j < s.length(); j++) {
          boolean mid = p[i + 1][j];
          if (j + 1 < s.length()) {
            boolean last = p[j + 1][s.length() - 1];
            if (mid && last) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  private void findP(String input, int j, int k, boolean[][] dp) {
    for (; j >= 0 && k < input.length(); --j, ++k) {
      if (input.charAt(j) != input.charAt(k)) {
        break;
      }
      dp[j][k] = true;
    }
  }

  public void genPalins(String input, boolean[][] dp) {
    for (int i = 0; i < input.length(); ++i) {
      findP(input, i - 1, i + 1, dp);
      findP(input, i, i + 1, dp);
    }
  }

  public static void main(String args[]) {
    PalinPartition p = new PalinPartition();
  }

}
