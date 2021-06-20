package com.leetcode.random10.sixmonths.medium;

public class OneEditDistance {

  public boolean isOneEditDistance(String s, String t) {
    if (s.equals(t)) {
      return false;
    }
    if (Math.abs(s.length() - t.length()) > 1) {
      return false;
    }
    char[] sArr = s.toCharArray();
    char[] tArr = t.toCharArray();
    if (sArr.length < tArr.length) {
      boolean insert = false;
      int i = 0, j = 0;
      while (i < sArr.length) {
        if (sArr[i] != tArr[j] && !insert) {
          j++;
          insert = true;
        } else if (sArr[i] != tArr[j] && insert) {
          return false;
        } else {
          i++;
          j++;
        }
      }
    } else if (sArr.length > tArr.length) {
      boolean insert = false;
      int i = 0, j = 0;
      while (i < tArr.length) {
        if (tArr[i] != sArr[j] && !insert) {
          j++;
          insert = true;
        } else if (tArr[i] != sArr[j] && insert) {
          return false;
        } else {
          i++;
          j++;
        }
      }
    } else {
      int count = 0;
      int i = 0;
      while (i < sArr.length) {
        if (sArr[i] != tArr[i] && count == 0) {
          if (count > 1) {
            return false;
          }
          i++;
          count++;
        } else if (sArr[i] != tArr[i] && count != 0) {
          return false;
        } else {
          i++;
        }
      }
    }
    return true;
  }


  public static void main(String args[]) {
    OneEditDistance o = new OneEditDistance();
    System.out.println(o.isOneEditDistance("abcd", "accd"));
  }

}
