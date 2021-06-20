package com.leetcode.design;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BrowserHistory {

  Stack<String> backward = new Stack();
  Stack<String> forward = new Stack<>();

  public BrowserHistory(String homepage) {
    backward.push(homepage);
  }

  public void visit(String url) {
    forward.clear();
    backward.push(url);
  }

  public String back(int steps) {
    while (steps-- > 0) {
      if (!backward.isEmpty()) {
        forward.push(backward.pop());
      }
    }
    if (backward.isEmpty()) {
      return forward.peek();
    } else {
      return backward.peek();
    }
  }

  public String forward(int steps) {
    while (steps-- > 0) {
      if (!forward.isEmpty()) {
        backward.push(forward.pop());
      }
    }
    return backward.peek();
  }


  public static void main(String args[]) {
    BrowserHistory b = new BrowserHistory("l.com");
    b.visit("g.com");
    b.visit("f.com");
    b.visit("y.com");
    System.out.println(b.back(1));
    System.out.println(b.back(1));
    System.out.println(b.forward(1));
    b.visit("l.com");
    System.out.println(b.back(2));
    System.out.println(b.back(2));

  }
}
