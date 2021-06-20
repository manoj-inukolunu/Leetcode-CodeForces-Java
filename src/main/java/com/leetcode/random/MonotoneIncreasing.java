package com.leetcode.random;

public class MonotoneIncreasing {

  public int monotoneIncreasingDigits(int N) {
    String str = String.valueOf(N);
    StringBuffer buffer = new StringBuffer();
    buffer.append(str.charAt(0));
    int idx = -1;
    for (int i = 1; i < str.length(); i++) {
      if (Character.getNumericValue(str.charAt(i)) < Character.getNumericValue(str.charAt(i - 1))) {
        idx = i - 1;
        while (idx - 1 >= 0) {
          if (Character.getNumericValue(str.charAt(idx)) == Character.getNumericValue(str.charAt(idx - 1))) {
            buffer.deleteCharAt(idx);
            idx--;
          } else {
            break;
          }
        }
        break;
      } else {
        buffer.append(str.charAt(i));
      }
    }
    if (idx != -1) {
      Integer integer = Character.getNumericValue(buffer.charAt(idx));
      if (integer - 1 > 0) {
        buffer.deleteCharAt(idx);
        buffer.append(integer - 1);
        for (int i = 1; i < str.length() - idx; i++) {
          buffer.append("9");
        }
      } else {
        buffer.deleteCharAt(idx);
        for (int i = 1; i < str.length() - idx; i++) {
          buffer.append("9");
        }
      }
    }
    return Integer.parseInt(buffer.toString());
  }

  public static void main(String args[]) {
    MonotoneIncreasing m = new MonotoneIncreasing();
    System.out.println(m.monotoneIncreasingDigits(11111011));
  }
}
