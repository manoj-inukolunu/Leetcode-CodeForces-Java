package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author manoji on 7/5/20.
 */
public class GenerateParenthesis {

  public List<String> generateParenthesis(int n) {
    if (n == 1) {
      String paren = "()";
      List<String> str = new ArrayList<>();
      str.add(paren);
      return str;
    }
    List<String> result = generateParenthesis(n - 1);

    Set<String> ans = new HashSet<>();

    for (int i = 0; i < result.size(); i++) {
      StringBuffer s = new StringBuffer(result.get(i));
      for (int j = 0; j < s.length(); j++) {
        s.insert(j, "()");
        ans.add(s.toString());
        s = new StringBuffer(result.get(i));
      }

    }

    return new ArrayList<>(ans);
  }

  public static void main(String args[]) {

    GenerateParenthesis g = new GenerateParenthesis();
    System.out.println(g.generateParenthesis(3));
  }


}
