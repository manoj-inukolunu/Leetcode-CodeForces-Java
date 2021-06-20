package com.leetcode.random;

public class LC943 {

  public String shiftingLetters(String S, int[] shifts) {
    StringBuffer buffer = new StringBuffer(S);
    long[] suff = new long[shifts.length];
    suff[shifts.length - 1] = shifts[shifts.length - 1];
    for (int i = shifts.length - 2; i >= 0; i--) {
      suff[i] = suff[i + 1] + shifts[i];
    }
    // System.out.println(Arrays.toString(suff));
    for (int i = 0; i < suff.length; i++) {
      buffer.setCharAt(i, shift(buffer.charAt(i), suff[i]));
    }
    return buffer.toString();
  }

  private StringBuffer shiftBy(StringBuffer buffer, int shiftLen, int len) {
    for (int i = 0; i < len; i++) {
      buffer.setCharAt(i, shift(buffer.charAt(i), shiftLen));
    }
    return buffer;
  }

  private Character shift(char a, long i) {
    int val = (char) ((int) a + (i % 26));
    if (val > 'z') {
      val = 'a' + val - 'z' - 1;
    }
    return (char) val;
  }


  public static void main(String args[]) {
    LC943 l = new LC943();
    System.out.println(l.shiftingLetters("ruu", new int[]{26, 9, 17}));
  }


}
