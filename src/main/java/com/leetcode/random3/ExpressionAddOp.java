package com.leetcode.random3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExpressionAddOp {

  Set<String> ans = new HashSet<>();


  public List<String> addOperators(String num, int target) {
    List<String> rst = new ArrayList<String>();
    if (num == null || num.length() == 0) {
      return rst;
    }
    helper(rst, "", num, target, 0, 0, 0);
    return rst;
  }

  public void helper(List<String> rst, String path, String num, int target, int pos, long eval, long multed) {
    if (pos == num.length()) {
      if (target == eval) {
        rst.add(path);
      }
      return;
    }
    for (int i = pos; i < num.length(); i++) {
      if (i != pos && num.charAt(pos) == '0') {
        break;
      }
      long cur = Long.parseLong(num.substring(pos, i + 1));
      if (pos == 0) {
        helper(rst, path + cur, num, target, i + 1, cur, cur);
      } else {
        helper(rst, path + "+" + cur, num, target, i + 1, eval + cur, cur);
        helper(rst, path + "-" + cur, num, target, i + 1, eval - cur, -cur);
        helper(rst, path + "*" + cur, num, target, i + 1, eval - multed + multed * cur, multed * cur);
      }
    }
  }


  public static void main(String args[]) {
    ExpressionAddOp ex = new ExpressionAddOp();
    System.out.println(ex.addOperators("12345678", 45));
  }

}
