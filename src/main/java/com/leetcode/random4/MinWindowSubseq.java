package com.leetcode.random4;

public class MinWindowSubseq {

  public String minWindow(String s, String t) {

    String ans = s + "1";
    int sPtr = 0, tEnd = 0;
    while (sPtr < s.length()) {
      if (s.charAt(sPtr) == t.charAt(tEnd)) {
        sPtr++;
        tEnd++;
      } else {
        sPtr++;
      }

      if (tEnd >= t.length()) {
        int end = sPtr;
        tEnd--;
        sPtr--;
        while (tEnd >= 0) {
          if (s.charAt(sPtr) == t.charAt(tEnd)) {
            sPtr--;
            tEnd--;
          } else {
            sPtr--;
          }
        }
        if (end - sPtr < ans.length()) {
          ans = s.substring(sPtr + 1, end);
        }
        sPtr += 2;
        tEnd = 0;
      }
    }
    return ans;
  }


  public static void main(String args[]) {
    MinWindowSubseq m = new MinWindowSubseq();
    System.out.println(m.minWindow("abcdebdde", "bde"));
  }
}
