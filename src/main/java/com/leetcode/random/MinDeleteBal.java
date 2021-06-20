package com.leetcode.random;

public class MinDeleteBal {


  public int minimumDeletions(String s) {
    int min = 0, bcnt = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == 'a' && bcnt > 0) {
        bcnt--;
        min++;
      } else if (s.charAt(i) == 'b') {
        bcnt++;
      }
    }
    return min;
  }


  public static void main(String args[]) {
    MinDeleteBal m = new MinDeleteBal();
    System.out.println(m.minimumDeletions("bbabb"));
  }


}
