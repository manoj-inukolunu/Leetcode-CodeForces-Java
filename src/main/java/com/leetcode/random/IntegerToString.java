package com.leetcode.random;

public class IntegerToString {


  String[] digits = new String[]{"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
  String[] tensS = new String[]{"Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
  String[] teens = new String[]{"Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

  public String words(int num) {
    if (num == 10) {
      return " Ten ";
    }
    StringBuffer buffer = new StringBuffer();
    int hunderds = num / 100;
    if (hunderds > 0) {
      buffer.append(digits[hunderds]).append(" ").append(" Hundred ");
    }
    num = num % 100;
    int tens = num / 10;
    if (tens > 1 && buffer.length() != 0) {
      buffer.append(" ").append(teens[tens - 1]);
    } else if (tens > 1) {
      buffer.append(teens[tens - 1]);
    }
    if (num == 10) {
      buffer.append(" Ten");
      return buffer.toString();
    }
    if (num > 10 && num < 20) {
      buffer.append(" ").append(tensS[num - 11]);
    } else {
      num = num % 10;
      if (num > 0) {
        buffer.append(" ").append(digits[num]);
      }
    }
    return buffer.toString().trim();
  }

  public String numberToWords(int num) {
    if (num == 0) {
      return "Zero";
    }

    //billions;
    StringBuffer buffer = new StringBuffer();
    int billions = num / 1000000000;
    if (billions > 0) {
      buffer.append(digits[billions]).append(" ").append(" Billion ");
    }
    num = num % 1000000000;
    int millions = num / 1000000;
    if (millions > 0) {
      String millionsString = words(millions);
      buffer.append(millionsString).append(" Million  ");
    }
    num = num % 1000000;
    int thousands = num / 1000;
    if (thousands > 0) {
      String thousandsString = words(thousands);
      buffer.append(thousandsString).append(" Thousand  ");
    }
    num = num % 1000;
    String last = words(num);
    buffer.append(" ").append(last);
    String str = buffer.toString().trim();
    str = str.replaceAll(" +", " ");
    return str.trim();
  }

  public static void main(String args[]) {
    IntegerToString i = new IntegerToString();
    System.out.println(i.numberToWords(110));
  }

}
