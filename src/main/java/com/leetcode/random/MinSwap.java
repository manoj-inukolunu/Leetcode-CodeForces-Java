package com.leetcode.random;

import java.util.Stack;

public class MinSwap {

  public int minimumSwap(String s1, String s2) {
    if (s1.length() == 2) {
      if (s1.equals("xx") && s2.equals("xy") || s1.equals("xy") && s2.equals("xx")) {
        return -1;
      }
    }
    return minSwap(s1, s2);
  }

  private int minSwap(String s1, String s2) {
    StringBuffer b1 = new StringBuffer();
    StringBuffer b2 = new StringBuffer();
    int count = 0;
    Stack<Character> stackA = new Stack<>();
    Stack<Character> stackB = new Stack<>();
    for (int i = 0; i < s1.length(); i++) {
      if (s1.charAt(i) != s2.charAt(i)) {
        b1.append(s1.charAt(i));
        b2.append(s2.charAt(i));
      }
    }
    int i = 0;
    while (i < b1.length()) {
      char c1 = b1.charAt(i);
      char c2 = b2.charAt(i);
      if (stackA.isEmpty()) {
        stackA.push(c1);
        stackB.push(c2);
      } else if ((stackA.peek() == 'x' && c1 == 'x' && stackB.peek() == 'y' && c2 == 'y') || (stackA.peek() == 'y' && c1 == 'y'
          && stackB.peek() == 'x' && c2 == 'x')) {
        count++;
        stackA.pop();
        stackB.pop();
      } else {
        stackA.push(c1);
        stackB.push(c2);
      }
      i++;
    }
    b1.setLength(0);
    b2.setLength(0);
    while (!stackA.isEmpty()) {
      b1.insert(0, stackA.pop());
      b2.insert(0, stackB.pop());
    }
    if (b1.length() % 2 != 0) {
      return -1;
    }
    if (b1.length() == 2) {
      String ss1 = b1.toString();
      String ss2 = b2.toString();
      if (ss1.equals("xy") && ss2.equals("yx") || (ss1.equals("yx") && ss2.equals("xy"))) {
        count += 2;
      } else {
        return -1;
      }
    }
    int mat = 0;
    for (int j = 2; j < b1.length(); j++) {
      String ss1 = b1.substring(j - 2, j);
      String ss2 = b2.substring(j - 2, j);
      if (ss1.equals("xy") && ss2.equals("yx") || (ss1.equals("yx") && ss2.equals("xy"))) {
        mat++;
      } else {
        return -1;
      }
    }
    return count + (mat);
  }


  public static void main(String args[]) {
    MinSwap m = new MinSwap();
    System.out.println(m.minimumSwap("xyxy", "yxyx"));
  }

}
