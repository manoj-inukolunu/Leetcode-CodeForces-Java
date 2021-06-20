package com.leetcode.random;

public class Utf8Validation {

  public boolean validUtf8(int[] data) {
    int i = 0;
    while (i < data.length) {
      int curr = data[i];
      int nBytes = getBytes(curr);
      if (nBytes == 1) {
        i++;
        continue;
      }
      if (nBytes == -1) {
        return false;
      }
      if (i + nBytes - 1 >= data.length) {
        return false;
      }
      for (int j = i + 1; j <= i + nBytes - 1; j++) {
        if (data[j] >> 6 != 2) {
          return false;
        }
      }
      i += nBytes;
    }
    return true;
  }

  private int getBytes(int data) {
    if (data >> 7 == 0) {
      return 1;
    } else if (data >> 5 == 6) {
      return 2;
    } else if (data >> 4 == 14) {
      return 3;
    } else if (data >> 3 == 30) {
      return 4;
    } else {
      return -1;
    }
  }

  public static void main(String args[]) {
    int[] data = new int[]{235, 140, 4};
    Utf8Validation u = new Utf8Validation();
    System.out.println(u.validUtf8(data));
  }

}
