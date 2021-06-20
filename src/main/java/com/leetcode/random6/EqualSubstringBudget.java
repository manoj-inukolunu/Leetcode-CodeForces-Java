package com.leetcode.random6;

public class EqualSubstringBudget {

  public int equalSubstring(String s, String t, int maxCost) {
    int start = 0, end = 0, cost = 0, ans = 0;
    while (end < s.length()) {
      if (canAdd(end, s, t, cost, maxCost)) {
        cost += Math.abs(s.charAt(end) - t.charAt(end));
        ans = Math.max(ans, end - start);
        end++;
      } else if (start < end) {
        if (s.charAt(start) != t.charAt(start)) {
          cost -= Math.abs(s.charAt(start) - t.charAt(start));
        }
        ans = Math.max(ans, end - start);
        start++;
      } else {
        end++;
        start++;
        cost = 0;
      }
    }
    return ans;
  }

  private boolean canAdd(int idx, String s, String t, int cost, int maxCost) {
    if (s.charAt(idx) != t.charAt(idx)) {
      return cost + Math.abs(s.charAt(idx) - t.charAt(idx)) <= maxCost;
    } else {
      return true;
    }
  }

  public static void main(String args[]) {
    EqualSubstringBudget e = new EqualSubstringBudget();
    System.out.println(e.equalSubstring("krrgw", "zjxss", 19));
  }

}
