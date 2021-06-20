package com.leetcode.random4;

import java.util.HashMap;

public class StrongPasswordChecker {

  HashMap<String, Integer> map = new HashMap<>();

  public int strongPasswordChecker(String password) {
    int len = password.length();
    if (password.isEmpty()) {
      return 6;
    }
    return getMin(0, 0, 0, 0, 0, '-', '-', len, password);
  }


  private int getMin(int idx, int chars, int hasLCase, int hasUCase, int hasDigit, char lastChar, char secondLastChar, int len, String s) {
    String key = idx + "|" + chars + "|" + hasLCase + "|" + hasUCase + "|" + hasDigit + "|" + lastChar + "|" + secondLastChar;
    if (map.containsKey(key)) {
      return map.get(key);
    }
    if (chars > 20) {
      return 100001;
    }
    if (idx >= s.length()) {
      if (chars < 6 || hasLCase == 0 || hasUCase == 0 || hasDigit == 0) {
        return 100001;
      }
      return 0;
    }
    int best = Integer.MAX_VALUE;
    char ch = s.charAt(idx);
    if (!(ch == lastChar && ch == secondLastChar)) {
      best = Math.min(best, getMin(idx + 1, chars + 1,
          Character.isLowerCase(ch) ? 1 : hasLCase,
          Character.isUpperCase(ch) ? 1 : hasUCase,
          Character.isDigit(ch) ? 1 : hasDigit,
          ch, lastChar, len, s));
    }
    //insert
    best = Math.min(best, getMin(idx, chars + 1, 1, hasUCase, hasDigit, lastChar == 'z' ? 'x' : 'z', lastChar, len, s) + 1);
    best = Math.min(best, getMin(idx, chars + 1, hasLCase, 1, hasDigit, lastChar == 'Z' ? 'X' : 'Z', lastChar, len, s) + 1);
    best = Math.min(best, getMin(idx, chars + 1, hasLCase, hasUCase, 1, lastChar == '1' ? '2' : '1', lastChar, len, s) + 1);

    //delete
    best = Math.min(best, getMin(idx + 1, chars, hasLCase, hasUCase, hasDigit, lastChar, secondLastChar, len, s) + 1);

    //change
    best = Math.min(best, getMin(idx + 1, chars + 1, 1, hasUCase, hasDigit, lastChar == 'z' ? 'x' : 'z', lastChar, len, s) + 1);
    best = Math.min(best, getMin(idx + 1, chars + 1, hasLCase, 1, hasDigit, lastChar == 'Z' ? 'X' : 'Z', lastChar, len, s) + 1);
    best = Math.min(best, getMin(idx + 1, chars + 1, hasLCase, hasUCase, 1, lastChar == '1' ? '2' : '1', lastChar, len, s) + 1);

    map.put(key, best);
    return best;
  }

  public static void main(String args[]) {
    StrongPasswordChecker s = new StrongPasswordChecker();
    System.out.println(s.strongPasswordChecker("1337C0d3"));
  }

}
