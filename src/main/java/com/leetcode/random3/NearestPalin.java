package com.leetcode.random3;

public class NearestPalin {

  public String nearestPalindromic(String n) {
    Long orig = Long.parseLong(n);
    int mid = (n.length() % 2 == 0) ? n.length() / 2 : (n.length() / 2) + 1;
    Long val = Long.parseLong(n.substring(0, mid));
    Long noChange = Long.parseLong("" + val + new StringBuffer(val + "").reverse().toString());
    Long addOneMid = val + 1;
    Long larger = Long.parseLong("" + addOneMid + new StringBuffer(addOneMid + "").reverse().toString());
    Long subOneMId = val - 1;
    Long smaller = Long.parseLong("" + subOneMId + new StringBuffer(subOneMId + "").reverse().toString());
    if (noChange > orig) {
      larger = Math.min(noChange, larger);
    } else if (noChange < orig) {
      smaller = Math.max(noChange, smaller);
    }
    return String.valueOf(orig - smaller <= larger - orig ? smaller : larger);
  }

  private boolean isPalin(String str) {
    for (int i = 0; i < str.length() / 2; i++) {
      if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
        return false;
      }
    }
    return true;
  }

  public static void main(String args[]) {
    NearestPalin n = new NearestPalin();
    System.out.println("8070450532247928834".length());
    System.out.println(Long.MAX_VALUE);
    System.out.println(Long.parseLong("8070450532247928834"));
  }

}
