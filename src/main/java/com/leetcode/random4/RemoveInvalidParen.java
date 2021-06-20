package com.leetcode.random4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class RemoveInvalidParen {

  Set<String> ret = new HashSet<>();
  int len = 0;

  public List<String> removeInvalidParentheses(String s) {
    isValid(s);
    dfs(s, 0, new StringBuffer());
    List<String> ans = new ArrayList<>();
    for (String str : ret) {
      if (isValid(str)) {
        ans.add(str);
      }
    }
    return ans;
  }

  private void dfs(String s, int idx, StringBuffer buffer) {
    if (idx >= s.length()) {
      if (len == buffer.toString().length()) {
        ret.add(buffer.toString());
      }
      return;
    }
    //add
    buffer.append(s.charAt(idx));
    dfs(s, idx + 1, buffer);
    buffer.deleteCharAt(buffer.length() - 1);
    dfs(s, idx + 1, buffer);
  }

  private boolean isValid(String s) {

    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (ch == '(') {
        stack.push(ch);
      } else if (ch == ')' && !stack.isEmpty() && stack.peek() == '(') {
        stack.pop();
      } else if (ch == ')') {
        stack.push(ch);
      }
    }
    len = Math.max(len, s.length() - stack.size());
    return stack.isEmpty();
  }

  public int doOperation(String s) {
    String[] opt = s.split(" ");
    if (opt[0] == "add") {
      return Integer.parseInt(opt[1]) + Integer.parseInt(opt[2]);
    } else {
      return Integer.parseInt(opt[1]) * Integer.parseInt(opt[2]);
    }
  }

  public int eval(String str) {
    Stack<Integer> st = new Stack<Integer>();
    char[] s = str.toCharArray();
    int length = str.length();
    for (int i = 0; i < str.length(); i++) {
      if (s[i] == '(') {
        st.push(i);
      } else if (s[i] == ')') {
        int temp = doOperation(str.substring(st.peek(), i));
        String rep = str.substring(st.pop(), i);
        str = str.replaceAll(rep, "" + temp);
      }
    }
    return Integer.parseInt(str);
  }

  public static void main(String args[]) {
    RemoveInvalidParen r = new RemoveInvalidParen();
    //System.out.println(r.isValid("))))"));
    System.out.println(r.eval("(add 1 (add 1 2))"));
  }

}
