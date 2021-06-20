package com.leetcode.contest;

public class MinDelEnd {

  public int minimumLength(String s) {
    int start = 0, end = s.length() - 1;
    while (true) {
      if (start == end) {
        return 1;
      }
      if (s.charAt(start) == s.charAt(end)) {
        char c = s.charAt(start);
        while (start < s.length() && s.charAt(start) == c) {
          start++;
        }
        while (end >= 0 && end > start && s.charAt(end) == c) {
          end--;
        }
      } else {
        break;
      }
    }
    return end - start + 1;
  }

  public static void main(String args[]) {
    MinDelEnd m = new MinDelEnd();
    //"bbbbbbbbbbbbbbbbbbbbbbbbbbbabbbbbbbbbbbbbbbccbcbcbccbbabbb"
    System.out.println(m.minimumLength("bbbbbbbbbbbbbbbbbbbbbbbbbbbabbbbbbbbbbbbbbbccbcbcbccbbabbb"));
  }

}
