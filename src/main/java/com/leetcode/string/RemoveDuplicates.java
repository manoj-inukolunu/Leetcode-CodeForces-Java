package com.leetcode.string;

import java.util.Stack;

/**
 * @author manoji on 4/26/20.
 */
public class RemoveDuplicates {


  class Pair {

    Character c;
    int count;

    public Pair(Character c, int count) {
      this.c = c;
      this.count = count;
    }
  }

  //deeedbbcccbdaa

  public String removeDuplicates(String s, int k) {
    Stack<Pair> stack = new Stack<>();
    int index = 0;
    stack.push(new Pair(s.charAt(index), 1));
    index++;
    while (index < s.length()) {
      Pair charPair = null;
      if (!stack.isEmpty()) {
        charPair = stack.peek();
      }
      if (charPair == null) {
        stack.push(new Pair(s.charAt(index++), 1));
        continue;
      }
      if (s.charAt(index) == charPair.c) {
        stack.push(new Pair(s.charAt(index++), charPair.count + 1));
      } else if (charPair.count == k) {
        int count = k;
        while (count > 0) {
          stack.pop();
          count--;
        }
        continue;
      } else {
        stack.push(new Pair(s.charAt(index++), 1));
      }
    }

    StringBuffer buffer = new StringBuffer();
    while (!stack.isEmpty()) {
      buffer.append(stack.pop().c);
    }
    return buffer.reverse().toString();
  }

  public static void main(String args[]) {
    RemoveDuplicates r = new RemoveDuplicates();
    System.out.println(r.removeDuplicates("deeedbbcccbdaa", 3));
  }


}
