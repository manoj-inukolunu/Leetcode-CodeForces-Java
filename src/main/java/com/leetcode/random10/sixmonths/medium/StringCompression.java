package com.leetcode.random10.sixmonths.medium;

public class StringCompression {

  public int compress(char[] chars) {
    if (chars.length == 1) {
      return 1;
    }
    int curr = 1;
    StringBuffer buffer = new StringBuffer();
    //buffer.append(chars[0]);
    for (int i = 0; i + 1 < chars.length; i++) {
      if (chars[i] == chars[i + 1]) {
        curr++;
      } else {
        buffer.append(chars[i]);
        buffer.append(curr);
        curr = 1;
      }
    }
    buffer.append(chars[chars.length-1]);
    buffer.append(curr);
    System.out.println(buffer.toString());
    chars = buffer.toString().toCharArray();
    return chars.length;
  }

  public static void main(String args[]) {
    StringCompression s = new StringCompression();
    System.out.println(s.compress(new char[]{'a', 'a', 'a', 'b', 'b', 'a', 'a'}));
  }
}
