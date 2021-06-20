package com.leetcode.random;

public class ReduceBinary {


  private boolean isEven(String str) {
    return str.charAt(str.length() - 1) == '0';
  }

  public int numSteps(String s) {
    StringBuffer buffer = new StringBuffer(s);
    int ans = 0;
    while (buffer.length() != 1) {
      ans++;
      if (isEven(buffer.toString())) {
        buffer.deleteCharAt(buffer.length() - 1);
      } else {
        int i = buffer.length() - 1;
        while (i >= 0) {
          if (buffer.charAt(i) == '0') {
            buffer.setCharAt(i, '1');
            for (int j = i + 1; j < buffer.length(); j++) {
              buffer.setCharAt(j, '0');
            }
            break;
          }
          i--;
        }
        if (i <= 0) {
          for (int j = 0; j < buffer.length(); j++) {
            buffer.setCharAt(j, '0');
          }
          buffer.insert(0, '1');
        }
      }
    }
    return ans;
  }

  public static void main(String args[]) {
    ReduceBinary r = new ReduceBinary();
    System.out.println(r.numSteps("1"));
  }

}
