package com.leetcode.random;

public class MaskInfo {

  public String maskPII(String S) {
    if (S.contains("@")) {
      int idx = S.indexOf("@");
      String str = S.substring(0, idx);
      StringBuffer buffer = new StringBuffer();
      buffer.append(Character.toLowerCase(str.charAt(0)));
      for (int i = 1; i < str.length() - 1; i++) {
        buffer.append("*");
      }
      buffer.append(Character.toLowerCase(str.charAt(str.length() - 1)));
      buffer.append((S.substring(idx + 1)).toLowerCase());
      return buffer.toString();
    } else {
      StringBuffer buffer = new StringBuffer();
      for (int i = 0; i < S.length(); i++) {
        if (Character.isDigit(S.charAt(i))) {
          buffer.append(S.charAt(i));
        }
      }
      System.out.println(buffer.toString());
      StringBuffer ans = new StringBuffer();
      if (buffer.length() <= 10) {
        appendStar(ans);
        ans.append("-");
        appendStar(ans);
        ans.append("-");
        for (int i = buffer.length() - 4; i < buffer.length(); i++) {
          ans.append(buffer.charAt(i));
        }
        return ans.toString();
      } else if (buffer.length() > 10) {
        ans.append("+");
        for (int i = 0; i < buffer.length() - 10; i++) {
          ans.append("*");
        }
        ans.append("-");
        appendStar(ans);
        appendStar(ans);
        return ans.toString();
      }
      return "";
    }
  }

  private void appendStar(StringBuffer ans) {
    for (int i = 0; i < 3; i++) {
      ans.append("*");
    }
  }

}
