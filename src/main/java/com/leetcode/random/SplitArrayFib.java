package com.leetcode.random;

import java.util.ArrayList;
import java.util.List;

public class SplitArrayFib {


  List<Integer> ans = new ArrayList<>();

  public List<Integer> splitIntoFibonacci(String str) {
    for (int i = 1; i < str.length(); i++) {
      for (int j = i + 1; j < str.length(); j++) {
        try {
          String str1 = str.substring(0, i);
          if (str1.startsWith("0") && !str1.equals("0")) {
            continue;
          }
          Integer num1 = Integer.parseInt(str.substring(0, i));
          Integer num2 = Integer.parseInt(str.substring(i, j));
          if (num1 >= 0 && num2 >= 0) {
            List<Integer> list = new ArrayList<>();
            list.add(num1);
            list.add(num2);
            if (dfs(str, num1, num2, j, list)) {
              list.remove(num1);
              list.remove(num2);
              break;
            }
            list.remove(num1);
            list.remove(num2);
          }
        } catch (Exception e) {

        }
      }
    }
    return ans;
  }

  private boolean dfs(String str, Integer num1, Integer num2, int idx, List<Integer> set) {
    if (idx >= str.length()) {
      if (ans.isEmpty()) {
        ans.addAll(new ArrayList<>(set));
      }
      return true;
    }
    String val = String.valueOf(num1 + num2);
    num1 = num2;
    num2 = Integer.parseInt(val);
    if (str.indexOf(val, idx) != idx) {
      return false;
    }
    set.add(num2);
    boolean ret = dfs(str, num1, num2, idx + val.length(), set);
    set.remove(num2);
    return ret;
  }

  public static void main(String args[]) {
    SplitArrayFib s = new SplitArrayFib();

    System.out.println(s.splitIntoFibonacci("121474836472147483648"));
  }

}
