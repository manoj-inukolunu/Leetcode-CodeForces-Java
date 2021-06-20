package com.leetcode.s60day;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenParen {

  public List<String> generateParenthesis(int n) {
    if (n == 0) {
      return new ArrayList();
    }
    if (n == 1) {
      List<String> list = new ArrayList();
      list.add("()");
      return list;
    }

    List<String> parens = generateParenthesis(n - 1);
    Set<String> set = new HashSet();
    for (int i = 0; i < parens.size(); i++) {
      String curr = parens.get(i);
      StringBuffer buff = new StringBuffer(curr);
      for (int j = 0; j < curr.length(); j++) {
        buff.insert(j, "()");
        set.add(buff.toString());
        buff.delete(j, j + 2);
      }
    }
    return new ArrayList(set);
  }

  public static void main(String args[]) {
    GenParen g = new GenParen();
    System.out.println(g.generateParenthesis(2));
  }

}
