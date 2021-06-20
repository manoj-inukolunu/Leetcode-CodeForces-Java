package com.leetcode.stack;

import com.leetcode.random10.sixmonths.medium.NestedInteger;
import java.util.Stack;

public class MiniParser {

  public NestedInteger deserialize(String s) {
    Stack<NestedInteger> stack = new Stack<>();
    int idx = 0;
    NestedInteger ans = null;
    while (idx < s.length()) {
      Character curr = s.charAt(idx);
      if (curr == ',') {
        idx++;
      }
      if (curr == '[') {
        NestedInteger n = new NestedInteger();
        if (stack.isEmpty()) {
          stack.push(n);
        } else {
          if (!stack.peek().isInteger()) {
            stack.peek().getList().add(n);
            stack.push(n);
          }
        }
        idx++;
      } else if (Character.isDigit(curr) || curr == '-') {
        StringBuffer buffer = new StringBuffer();
        while (idx < s.length() && s.charAt(idx) != ',' && s.charAt(idx) != ']') {
          buffer.append(s.charAt(idx++));
        }
        if (!stack.isEmpty()) {
          NestedInteger n = stack.peek();
          if (!n.isInteger()) {
            n.getList().add(new NestedInteger(Integer.parseInt(buffer.toString())));
          }
        } else {
          stack.push(new NestedInteger(Integer.parseInt(buffer.toString())));
        }
      } else if (curr == ']') {
        ans = stack.pop();
        idx++;
      }
    }
    while (!stack.isEmpty()) {
      ans = stack.pop();
    }
    return ans;
  }


  public static void main(String args[]) {
    MiniParser m = new MiniParser();
    NestedInteger n = m.deserialize("[123,[456,[789],1234],1234]");
    m.dfs(n);
  }

  private void dfs(NestedInteger n) {
    if (n == null) {
      return;
    }
    if (n.isInteger()) {
      System.out.println(n.getInteger());
      return;
    }
    for (NestedInteger child : n.getList()) {
      dfs(child);
    }
  }

}
