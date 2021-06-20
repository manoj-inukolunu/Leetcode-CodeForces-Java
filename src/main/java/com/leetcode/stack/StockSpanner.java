package com.leetcode.stack;

import java.util.Stack;

/**
 * @author manoji on 5/19/20.
 */
public class StockSpanner {

  class Pair {

    int price;
    int count;

    public Pair(int price, int count) {
      this.price = price;
      this.count = count;
    }
  }

  Stack<Pair> stack = new Stack<>();

  public StockSpanner() {

  }

  public int next(int price) {
    if (stack.isEmpty()) {
      stack.push(new Pair(price, 1));
      return 1;
    }
    Pair top = stack.peek();
    if (price < top.price) {
      stack.push(new Pair(price, 1));
      return 1;
    } else {
      int count = 1;
      while (!stack.isEmpty() && (stack.peek().price < price)) {
        top = stack.pop();
        count += top.count;
      }
      stack.push(new Pair(price, count));
      return count;
    }
  }


  public static void main(String args[]) {
    StockSpanner spanner = new StockSpanner();
    System.out.println(spanner.next(100));
    System.out.println(spanner.next(80));
    System.out.println(spanner.next(60));
    System.out.println(spanner.next(70));
    System.out.println(spanner.next(60));
    System.out.println(spanner.next(75));
    System.out.println(spanner.next(85));
  }
}
