package com.leetcode.string;

import java.util.Stack;

/**
 * @author manoji on 4/16/20.
 */
public class ValidString {

  public boolean checkValidString(String s) {
    //()
    //())()(
    int count = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        count++;
      }

      if (s.charAt(i) == ')') {
        if (count > 0) {
          count--;
        } else {
          return false;
        }
      }
    }

    return true;
  }

  public static void main(String args[]) {
    ValidString validString = new ValidString();
    System.out.println(validString.checkValidString("())"));
  }

}
