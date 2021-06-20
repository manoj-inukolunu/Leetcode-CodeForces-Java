package com.leetcode.random10.sixmonths.medium;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Stack;

/**
 * @author manoji on 7/21/20.
 */
public class SimplifyPath {

  public String simplifyPath(String path) {
    path = path.replaceAll("/+", "/");
    Stack<Character> stack = new Stack<>();
    StringBuffer buffer = new StringBuffer();
    int i = 0;
    while (i < path.length()) {
      if (stack.isEmpty()) {
        stack.push(path.charAt(i));
        i++;
      } else {
        Character curr = path.charAt(i);
        if (curr == '.') {
          if (stack.peek() != '.' && (i + 1 >= path.length() || path.charAt(i + 1) == '/')) {
            i = i + 2;
          } else if (stack.peek() != '.' && (i + 1 >= path.length() || path.charAt(i + 1) == '.') && (i + 2 >= path.length()
              || path.charAt(i + 2) == '/')) {
            if (stack.size() != 1) {
              stack.pop();
            }
            while (!stack.isEmpty() && stack.peek() != '/') {
              stack.pop();
            }
            i = i + 3;
          } else {
            stack.push(curr);
            i++;
          }
        } else {
          stack.push(curr);
          i++;
        }
      }
    }
    while (!stack.isEmpty()) {
      buffer.append(stack.pop());
    }

    buffer = buffer.reverse();
    if (buffer.length() > 1 && buffer.charAt(buffer.length() - 1) == '/') {
      buffer.deleteCharAt(buffer.length() - 1);
    }
    if (buffer.length() == 0) {
      return "/";
    }
    if (!buffer.toString().startsWith("/")) {
      buffer.insert(0, "/");
    }
    return buffer.toString();
  }

  private boolean current(String path, int i) {
    return path.charAt(i + 1) == '.' && path.charAt(i + 2) == '/';
  }

  public static void main(String args[]) {
    SimplifyPath s = new SimplifyPath();

    List<String> list = Lists.newArrayList("/home/../../..", "/...", "/../", "/home//foo/", "/a/./b/../../c/", "/a/../../b/../c//.//",
        "/a//b////c/d//././/..", "/."
            + ".hidden", "/.");

    list.stream().forEach(s1 -> System.out.println(s.simplifyPath(s1)));
  }

}
