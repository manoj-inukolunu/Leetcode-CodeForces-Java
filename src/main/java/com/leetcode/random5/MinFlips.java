package com.leetcode.random5;

public class MinFlips {

  public int minFlips(int a, int b, int c) {

    String aStr = getFullString(Integer.toBinaryString(a));
    String bStr = getFullString(Integer.toBinaryString(b));
    String cStr = getFullString(Integer.toBinaryString(c));

    int count = 0;
    for (int i = 0; i < aStr.length(); i++) {
      char aCh = aStr.charAt(i);
      char bCh = bStr.charAt(i);
      char cCh = cStr.charAt(i);
      if (cCh == '0') {
        if ((aCh == '0' && bCh == '1') || (aCh == '1' && bCh == '0')) {
          count++;
        } else if (aCh == '1' && bCh == '1') {
          count += 2;
        }
      } else {
        if (aCh == '0' && bCh == '0') {
          count += 1;
        }
      }
    }

    return count;

  }

  private String getFullString(String str) {
    int i = str.length();
    StringBuilder strBuilder = new StringBuilder(str);
    while (i < 32) {
      strBuilder.insert(0, "0");
      i++;
    }
    str = strBuilder.toString();
    return str;
  }

  public static void main(String args[]) {
    MinFlips m = new MinFlips();
    System.out.println(m.minFlips(1, 2, 3));
  }

}
