package com.leetcode.random10.sixmonths.medium;

/**
 * @author manoji on 7/22/20.
 */
public class MultiplyString {

  public String multiply(String num1, String num2) {
    String res = multiply(num1, Character.getNumericValue(num2.charAt(num2.length() - 1)));
    int offset = 0;
    for (int i = num2.length() - 2; i >= 0; i--) {
      String temp = multiply(num1, Character.getNumericValue(num2.charAt(i)));
      res = addStrings(res, temp, ++offset);
    }
    char[] arr = res.toCharArray();
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == '0') {
        arr[i] = ' ';
      } else {
        break;
      }
    }
    res = new String(arr).trim();
    if (res.isEmpty()) {
      return "0";
    }
    return new String(arr).trim();
  }

  private String multiply(String str, int digit) {
    if (str.length() == 1) {
      return Integer.parseInt(str) * digit + "";
    }
    StringBuffer buffer = new StringBuffer(str);
    StringBuffer res = new StringBuffer();
    int carry = 0;
    int toAdd;
    for (int i = buffer.length() - 1; i >= 0; i--) {
      Integer integer = Character.getNumericValue(buffer.charAt(i)) * digit;
      integer += carry;
      if (integer >= 10) {
        carry = integer / 10;
        toAdd = integer % 10;
        res.insert(0, toAdd);
      } else {
        carry = 0;
        res.insert(0, integer);
      }
    }
    if (carry != 0) {
      res.insert(0, carry);
    }
    return res.toString();

  }

  private String addStrings(String str1, String str2, Integer offset) {
    StringBuffer buff1 = new StringBuffer(str1);
    StringBuffer buff2 = new StringBuffer(str2);
    for (int i = 0; i < offset; i++) {
      buff2.append("0");
    }
    if (buff1.length() <= buff2.length()) {
      return getString(buff1, buff2);
    } else {
      return getString(buff2, buff1);
    }
  }

  private String getString(StringBuffer buff, StringBuffer other) {
    int carry = 0, toAdd;
    StringBuffer res = new StringBuffer();
    int i = buff.length() - 1, j = other.length() - 1;
    for (int k = 0; k < j - i; k++) {
      buff.insert(0, '0');
    }
    i = buff.length() - 1;
    while (i >= 0) {
      Integer integer = Character.getNumericValue(buff.charAt(i--)) + Character.getNumericValue(other.charAt(j--));
      integer += carry;
      if (integer >= 10) {
        carry = integer / 10;
        toAdd = integer % 10;
        res.insert(0, toAdd);
      } else {
        carry = 0;
        res.insert(0, integer);
      }
    }
    if (carry > 0) {
      res.insert(0, carry);
    }
    return res.toString();
  }


  public static void main(String[] args) {
    MultiplyString m = new MultiplyString();
    //56088
		/*
		"123456789"
		"987654321"
		 */
    System.out.println(m.multiply("0", "332620029"));
  }
}
