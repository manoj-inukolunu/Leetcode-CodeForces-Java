package com.leetcode.random1;

public class ReformatNumber {

  public String reformatNumber(String number) {
    number = number.replaceAll(" *", "").replaceAll("-*", "");
    StringBuffer buffer = new StringBuffer();
    if (number.length() <= 3) {
      return number;
    } else if (number.length() == 4) {
      return number.substring(0, 2) + "-" + number.substring(2);
    }
    int blocks = number.length() / 3, left = number.length() % 3;
    if (left == 1) {
      blocks--;
    }
    int start = 0, block = 0;
    while (block < blocks) {
      buffer.append(number, start, start + 3);
      buffer.append("-");
      start += 3;
      block++;
    }
    if (left == 1) {
      buffer.append(number.substring(start, start + 2) + "-" + number.substring(start + 2, start + 4));
    } else {
      buffer.append(number, start, number.length());
    }
    if (buffer.toString().endsWith("-")) {
      buffer.deleteCharAt(buffer.length() - 1);
    }
    return buffer.toString();

  }

  public static void main(String args[]) {
    String str = "--17-5 229 35-39475 ";
    ReformatNumber r = new ReformatNumber();
    System.out.println(r.reformatNumber(str));
  }

}
