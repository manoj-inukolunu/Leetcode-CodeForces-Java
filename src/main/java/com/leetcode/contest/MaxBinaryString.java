package com.leetcode.contest;

import java.util.ArrayList;
import java.util.List;

public class MaxBinaryString {

  public String maximumBinaryString(String binary) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < binary.length(); i++) {
      if (binary.charAt(i) == '0') {
        list.add(i);
      }
    }
    if (list.isEmpty()) {
      return binary;
    }
    int start = list.get(0);
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < start; i++) {
      buffer.append("1");
    }
    int j = 1;
    while (j < list.size()) {
      if (start < list.get(j)) {
        buffer.append("1");
        start++;
        j++;
      }
    }
    buffer.append('0');
    for (int i = buffer.length(); i < binary.length(); i++) {
      buffer.append("1");
    }
    return buffer.toString();
  }

  public static void main(String args[]) {
    MaxBinaryString m = new MaxBinaryString();
    System.out.println(m.maximumBinaryString("01111001100000110010"));

  }

}
