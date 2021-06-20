package com.leetcode.random;

public class StringWithoutAAA {


  public String strWithout3a3b(int A, int B) {
    StringBuffer buffer = new StringBuffer();
    while (true) {
      if (A == 0 && B == 0) {
        return buffer.toString();
      }
      if (buffer.length() < 2) {
        if (A > B) {
          for (int i = 0; i < 2 && A > 0; i++) {
            buffer.append("a");
            A--;
          }
        } else {
          for (int i = 0; i < 2 && B > 0; i++) {
            buffer.append("b");
            B--;
          }
        }
      } else {
        int len = buffer.length();
        String last = buffer.substring(len - 2, len);
        if (last.equals("aa") || last.equals("ba")) {
          if (B >= A) {
            for (int i = 0; i < 2 && B > 0; i++) {
              buffer.append("b");
              B--;
            }
          } else {
            buffer.append("b");
            B--;
          }
        } else {
          if (A >= B) {
            for (int i = 0; i < 2 && A > 0; i++) {
              buffer.append("a");
              A--;
            }
          } else {
            buffer.append("a");
            A--;
          }
        }
      }
    }
  }


  public static void main(String args[]) {
    StringWithoutAAA s = new StringWithoutAAA();
    System.out.println(s.strWithout3a3b(2, 5));
  }

}