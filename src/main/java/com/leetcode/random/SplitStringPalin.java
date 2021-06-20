package com.leetcode.random;

public class SplitStringPalin {

  public boolean checkPalindromeFormation(String a, String b) {
    if (isPalin(a) || isPalin(b)) {
      return true;
    }
    if (check(a, b)) {
      return true;
    } else {
      return check(b, a);
    }
  }

  public boolean isPalin(String str) {
    for (int i = 0; i < str.length() / 2; i++) {
      if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
        return false;
      }
    }
    return true;
  }

  boolean check(String a, String b) {
    int start = 0, end = b.length() - 1;
    while (start <= end) {
      if (start == end) {
        return true;
      }
      if (a.charAt(start) != b.charAt(end)) {
        break;
      }
      start++;
      end--;
    }
    String strA = a.substring(0, start) + b.substring(start);
    String strB = a.substring(0, a.length() - start) + b.substring(a.length() - start);
    if (isPalin(strA) || isPalin(strB)) {
      return true;
    }
    return false;
  }


  public static void main(String args[]) {
    SplitStringPalin s = new SplitStringPalin();
    System.out.println(s.checkPalindromeFormation("aejbaalflrmkswrydwdkdwdyrwskmrlfqizjezd",
        "uvebspqckawkhbrtlqwblfwzfptanhiglaabjea"));

    /*System.out.println(s.checkPalindromeFormation("abdef"
        , "fecab"));*/
  }

}
