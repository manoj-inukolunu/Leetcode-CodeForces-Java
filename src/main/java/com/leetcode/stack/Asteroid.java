package com.leetcode.stack;

import java.util.Arrays;
import java.util.Stack;

public class Asteroid {

  public int[] asteroidCollision(int[] asteroids) {

    Stack<Integer> stack = new Stack<>();
    int i = 0;
    while (i < asteroids.length) {
      if (stack.isEmpty()) {
        stack.push(asteroids[i]);
      } else {
        int curr = asteroids[i];
        while (!stack.isEmpty() && stack.peek() > 0 && curr < 0) {
          int val = getVal(stack.pop(), curr);
          curr = val;
          if (curr == 0) {
            break;
          }
        }
        if (curr != 0) {
          stack.push(curr);
        }
      }
      i++;
    }
    int[] ans = new int[stack.size()];
    int j = ans.length - 1;
    while (!stack.isEmpty()) {
      ans[j--] = stack.pop();
    }
    return ans;
  }

  private int getVal(int a, int b) {
    if (Math.abs(a) == Math.abs(b)) {
      return 0;
    } else if (Math.abs(a) > Math.abs(b)) {
      return a;
    } else {
      return b;
    }
  }

  public static void main(String args[]) {
    Asteroid a = new Asteroid();
    System.out.println(Arrays.toString(a.asteroidCollision(new int[]{-2, -1, 1, 2})));
  }

}


