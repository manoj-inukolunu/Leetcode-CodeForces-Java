package com.leetcode.aprilchallenge;

import java.util.Stack;

/**
 * @author manoji on 4/9/20.
 */
public class BackSpaceCompare {

  public static void main(String args[]) {
    BackSpaceCompare backSpaceCompare = new BackSpaceCompare();
		/*
		"y#fo##f"
	"y#f#o##f"
		 */

    System.out.println(backSpaceCompare.backspaceCompare("y#fo##f", "y#f#o##f"));
  }

  public boolean backspaceCompare(String S, String T) {
    String val1 = getVal(S);
    String val2 = getVal(T);
    return val1.equals(val2);
  }

  private String getVal(String str) {
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == '#') {
        if (!stack.isEmpty()) {
          stack.pop();
        }
      } else {
        stack.push(str.charAt(i));
      }
    }

    StringBuffer buffer = new StringBuffer();
    while (!stack.isEmpty()) {
      buffer.append((stack.pop()));
    }

    return buffer.toString();
  }

}
