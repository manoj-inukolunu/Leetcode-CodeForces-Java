package com.leetcode.random3;

public class ValidPalindrome3 {

  public boolean isValidPalindrome(String s, int k) {
    Boolean[][][] dp = new Boolean[s.length() + 1][s.length() + 1][k + 1];
    return dfs(s, 0, s.length(), k, dp);
  }

  public boolean dfs(String s, int start, int end, int k, Boolean[][][] dp) {
    if (dp[start][end][k] != null) {
      return dp[start][end][k];
    }
    if (k == 0) {
      return isPailn(s.substring(start, end));
    }
    if (start >= end) {
      return true;
    }
    if (s.charAt(start) == s.charAt(end - 1)) {
      boolean val = dfs(s, start + 1, end - 1, k, dp);
      dp[start][end][k] = val;
      return val;
    } else {
      boolean val = dfs(s, start + 1, end, k - 1, dp) || dfs(s, start, end - 1, k - 1, dp);
      dp[start][end][k] = val;
      return val;
    }
  }

  private boolean isPailn(String s) {
    for (int i = 0; i < s.length() / 2; i++) {
      if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
        return false;
      }
    }
    return true;
  }


  public static void main(String args[]) {
    String str =
        "gacdgghhfgebicifggcdgieacbcbcebfbcgibhibafdihcicaffgbhgebfdhiiiecig"
            + "eifbcfedfehidihgdggfcadchieacbhehchidhdadhfebhhbheegfidaebefcbebfdc"
            + "dffdehibhgihffchhidgeedibehiadebacafaaecededfagcffbachcaggggcdafhihdbddi"
            + "deaciacdiebbiefdiddggdafdfcdbcadeffibgdfhdbghbgaaibeihfgcfdeaaihfeeehhfbdef"
            + "bbgggefbihgfefddbbgghfcddbeidgcibdggbfffagfgddghhgcbgaigdadggedfgffegdcfagdidh"
            + "igehcfccbfiffhidecfcabgabaebeifediahbgceieidfaiacaddiciifeefihegbicbdifhfchhfeebgdbe"
            + "cdaffbhahebdbhacgeecdedeicabighedfgadfheacfcifffcffffedfhggiiddggbhca";
    String str1 = "bbbcbabcb";
    ValidPalindrome3 v = new ValidPalindrome3();
    System.out.println(v.isValidPalindrome(str1, 1));
  }
}
