package com.leetcode.random;

import java.util.Arrays;

public class ReverseInAString2 {

  public void reverseWords(char[] s) {
    reverse(s, 0, s.length);
    System.out.println(Arrays.toString(s));
    int start = 0;
    for (int i = 0; i < s.length; i++) {
      if (s[i] == ' ') {
        System.out.println(i);
        reverse(s, start, i);
        start = i + 1;
      }
    }
    reverse(s, start, s.length);

    System.out.println(Arrays.toString(s));
  }

  private void reverse(char[] arr, int start, int end) {
    int mid = start + (end - start) / 2;
    for (int i = start; i < mid; i++) {
      char temp = arr[i];
      arr[i] = arr[start + end - i - 1];
      arr[start + end - i - 1] = temp;
    }
  }

  public static void main(String args[]) {
    String str = "the sky is blue";
    ReverseInAString2 r = new ReverseInAString2();
    r.reverseWords(str.toCharArray());
    System.out.println(str);
  }

}
