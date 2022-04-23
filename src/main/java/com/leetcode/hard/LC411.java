package com.leetcode.hard;

public class LC411 {

  int min = Integer.MAX_VALUE;
  String ans = "";

  public String minAbbreviation(String target, String[] dictionary) {
    solve("", 0, 0, 0, target, dictionary);
    return ans;
  }

  void allAbbrs(String curr, int currIdx, int chars, String str) {
    if (currIdx >= str.length()) {
      System.out.println(curr);
      return;
    }
    allAbbrs(curr, currIdx + 1, chars + 1, str);
    allAbbrs(curr + (chars != 0 ? chars : "") + str.charAt(currIdx), currIdx + 1, 0, str);
  }

  private void solve(String curr, int len, int chars, int currIdx, String str, String[] dict) {
    if (currIdx >= str.length()) {
      curr = (curr + (chars != 0 ? chars : ""));
      int llen = slen(curr);
      System.out.println(curr);
      for (String s : dict) {
        if (valid(curr, s)) {
          return;
        }
      }
      if (llen < min && valid(curr, str)) {
        min = llen;
        ans = curr;
      }
      return;
    }
    int llen = slen(curr);
    if (llen > min) {
      return;
    }
    solve(curr, len, chars + 1, currIdx + 1, str, dict);
    solve(curr + (chars != 0 ? chars : "") + str.charAt(currIdx), len + 1, 0, currIdx + 1, str, dict);

  }

  private int slen(String str) {
    int len = 0, total = 0;
    for (char ch : str.toCharArray()) {
      if (Character.isDigit(ch)) {
        if (len == 0) {
          total++;
        }
        len = 1;
      } else {
        total++;
        len = 0;
      }
    }
    return total;
  }

  boolean valid(String str, String target) {
    int i = 0, j = 0, strAlen = 0;
    while (i < str.length() && j < target.length()) {
      if (str.charAt(i) == target.charAt(j)) {
        i++;
        strAlen++;
        j++;
      } else if (Character.isDigit(str.charAt(i))) {
        int skip = 0;
        while (i < str.length() && Character.isDigit(str.charAt(i))) {
          skip *= 10;
          skip += Character.getNumericValue(str.charAt(i));
          i++;
        }
        strAlen += skip;
        j += skip;
      } else {
        return false;
      }
    }
    return strAlen == target.length() && i >= str.length();
  }


  public static void main(String[] args) {
    LC411 l = new LC411();

//    l.allAbbrs("", 0, 0, "leetcoders");
    System.out.println(l.valid("9s", "gsdschcmd"));
  }




}
