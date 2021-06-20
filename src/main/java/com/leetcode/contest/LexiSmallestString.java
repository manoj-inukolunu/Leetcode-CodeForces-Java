package com.leetcode.contest;

import java.util.Arrays;

public class LexiSmallestString {

  String _str = "abcdefghijklmnopqrstuvwxyz";

  String ans = null;

  public static char[] lexo_small(int n, int k) {
    char arr[] = new char[n];
    Arrays.fill(arr, 'a');
    for (int i = n - 1; i >= 0; i--) {

      k -= i;
      if (k >= 0) {
        if (k >= 26) {
          arr[i] = 'z';
          k -= 26;
        } else {
          arr[i] = (char) (k + 97 - 1);
          k -= arr[i] - 'a' + 1;
        }
      } else {
        break;
      }

      k += i;
    }
    return arr;
  }

  public String getSmallestString(int n, int k) {
    dfs(n, k, new StringBuffer());
    return ans;
  }

  public void dfs(int n, int k, StringBuffer buffer) {
    if (k == 0 && n == 0) {
      if (ans == null) {
        ans = buffer.toString();
      } else if (ans.compareTo(buffer.toString()) > 0) {
        ans = buffer.toString();
      }
      return;
    }
    if (n > 0 && k <= 0 || n == 1 && k > 26) {
      return;
    }
    if (n < 0) {
      return;
    }

    for (int i = 1; i <= 26; i++) {
      buffer.append(_str.charAt(i - 1));
      dfs(n - 1, k - i, buffer);
      buffer.deleteCharAt(buffer.length() - 1);
    }
  }


  public static void main(String args[]) {
    LexiSmallestString l = new LexiSmallestString();
    System.out.println(l.getSmallestString(5, 73));
  }

}
