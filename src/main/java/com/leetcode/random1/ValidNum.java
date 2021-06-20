package com.leetcode.random1;

public class ValidNum {

  public boolean isNumber(String s) {
    s = s.trim();
    boolean dot = false, eEnc = false;
    for (int i = 0; i < s.length(); i++) {
      char curr = s.charAt(i);
      if (curr == 'e') {
        if (eEnc) {
          return false;
        }
        if (i - 1 >= 0) {
          if (!Character.isDigit(s.charAt(i - 1))) {
            return false;
          }
        } else {
          return false;
        }
        try {
          int val = Integer.parseInt(s.substring(i + 1));
          eEnc = true;
          return true;
        } catch (Exception e) {
          return false;
        }
      } else if (curr == ' ' || Character.isAlphabetic(curr)) {
        return false;
      } else if (curr == '+' || curr == '-' || curr == '.') {
        if (i + 1 >= s.length() || !Character.isDigit(s.charAt(i + 1))) {
          return false;
        }
        if (curr == '.') {
          if (!dot) {
            dot = true;
          } else {
            return false;
          }
        }
      }
    }
    return false;
  }

  public static void main(String args[]) {
    ValidNum v = new ValidNum();
    System.out.println(Double.parseDouble("123e10"));
    System.out.println(v.isNumber(".1"));
  }

}
