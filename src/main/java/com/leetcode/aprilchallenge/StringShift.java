package com.leetcode.aprilchallenge;

/**
 * @author manoji on 4/14/20.
 */
public class StringShift {

  public String stringShift(String s, int[][] shift) {

    StringBuffer buffer = new StringBuffer(s);

    for (int i = 0; i < shift.length; i++) {
      int[] row = shift[i];
      if (row[0] == 0) {
        int sft = row[1];
        if (sft > 0) {
          buffer.append(buffer.substring(0, sft));
          buffer.delete(0, sft);
        }
      } else {
        int length = buffer.length();
        int sft = row[1];
        if (sft > 0) {
          buffer.insert(0, buffer.substring(length - sft, length));
          buffer.delete(buffer.length() - sft, buffer.length());
        }
      }
    }
    return buffer.toString();
  }

  public static void main(String args[]) {
    String str = "abcdefg";
    int[][] shifts = new int[][]{
        {1, 1},
        {1, 1},
        {0, 2},
        {1, 3}
    };

    StringShift stringShift = new StringShift();
    System.out.println(stringShift.stringShift(str, shifts));
  }

}
