package com.leetcode.string;

/**
 * @author manoji on 5/10/20.
 */
public class IntegerToRoman {


  private String[] digit = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

  public String intToRoman(int num) {
    StringBuffer buffer = new StringBuffer();
    int rem = num % 1000;
    num = num / 1000;
    for (int i = 0; i < num; i++) {
      buffer.append("M");
    }
    return buffer.append(convert3Digits(rem)).toString();
  }

  private StringBuffer convert3Digits(int num) {

    StringBuffer buffer = new StringBuffer();
    if (num < 400) {
      int rem = num % 100;
      num = num / 100;
      for (int i = 0; i < num; i++) {
        buffer.append("C");
      }
      buffer.append(convert2Digits(rem));
    } else if (num >= 400 && num < 500) {
      buffer.append("CD");
      buffer.append(convert2Digits(num % 400));
    } else if (num >= 500 && num < 900) {
      buffer.append("CM");
      buffer.append(convert2Digits(num % 500));
    } else {
      buffer.append("CM");
      buffer.append(convert2Digits(num % 900));
    }

    return buffer;
  }

  private StringBuffer convert2Digits(int num) {
    StringBuffer buffer = new StringBuffer();
    if (num < 40) {
      int rem = num % 10;
      num = num / 10;
      for (int i = 0; i < num; i++) {
        buffer.append("X");
      }
      if (rem > 0) {
        buffer.append(digit[rem - 1]);
      }

    } else if (num >= 40 && num < 50) {
      buffer.append("XL");
      int val = num % 40;
      if (val > 0) {
        if (val == 10) {
          buffer.append("X");
        } else {
          buffer.append(digit[(num % 40) - 1]);
        }
      }

    } else if (num >= 50 && num < 90) {
      buffer.append("L");
      int val = num % 50;
      if (val > 0) {
        if (val == 10) {
          buffer.append("X");
        } else {
          buffer.append(digit[(num % 50) - 1]);
        }
      }

    } else {
      buffer.append("XC");
      int val = num % 90;
      if (val > 0) {
        if (val == 10) {
          buffer.append("X");
        } else {
          buffer.append(digit[(num % 90) - 1]);
        }
      }

    }
    return buffer;
  }


  public static void main(String args[]) {
    IntegerToRoman i = new IntegerToRoman();
    System.out.println(i.intToRoman(60));//MCMXCIV
  }

}

