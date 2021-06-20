package com.leetcode.dfs.medium;

import java.util.Stack;

/**
 * @author manoji on 2020-01-11.
 */
public class DecodeString {

  public String decodeString(String s) {
    Stack<Character> stack = new Stack();
    int len = s.length();
    int current = 0;
    while (current < len) {
      char currentChar = s.charAt(current);
      if (currentChar == ']') {
        stack.push(currentChar);
        StringBuffer toDecode = getStringBufferFromStack(stack);
        String str = decode(toDecode.reverse().toString());
        for (int i = 0; i < str.length(); i++) {
          stack.push(str.charAt(i));
        }
      } else {
        stack.push(currentChar);
      }
      current++;
    }
    StringBuffer finalBuffer = new StringBuffer();
    while (!stack.isEmpty()) {
      finalBuffer.append(stack.pop());
    }
    return finalBuffer.reverse().toString();
  }

  private StringBuffer getStringBufferFromStack(Stack<Character> stack) {
    StringBuffer toDecode = new StringBuffer();
    boolean digitEncountered = false;
    while (true) {
      if (!stack.isEmpty()) {
        char ch = stack.peek();
        if (Character.isDigit(ch)) {
          digitEncountered = true;
        }
        if (digitEncountered && !Character.isDigit(ch)) {
          break;
        }
        toDecode.append(stack.pop());
      } else {
        break;
      }
    }
    return toDecode;
  }


  private String decode(String s) {
    int len = s.length();
    int current = 0;
    StringBuffer k = new StringBuffer();
    StringBuffer decoded = new StringBuffer();
    Stack stack = new Stack();
    while (current < len) {
      if (Character.isDigit(s.charAt(current))) {
        k.append(s.charAt(current));
        current++;
      }
      if (s.charAt(current) == '[') {
        current++;
        while (s.charAt(current) != ']') {
          stack.push(s.charAt(current));
          current++;
        }
        current++;
        while (!stack.isEmpty()) {
          decoded.append(stack.pop());
        }
      }
    }
    decoded = decoded.reverse();
    StringBuffer finalBuffer = new StringBuffer();
    for (int i = 0; i < Integer.parseInt(k.toString()); i++) {
      finalBuffer.append(decoded);
    }
    return finalBuffer.toString();
  }

  public static void main(String args[]) {
    DecodeString decodeString = new DecodeString();
    System.out.println(decodeString.decodeString("2[abc]3[cd]ef"));
  }
}
