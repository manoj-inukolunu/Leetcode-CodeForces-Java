package com.leetcode.random;

import java.util.ArrayList;
import java.util.List;

public class RemoveComments {

  public List<String> removeComments(String[] source) {
    StringBuffer code = new StringBuffer();
    for (int i = 0; i < source.length; i++) {
      code.append(source[i]);
      code.append("\n");
    }
    //remove //
    int i = 1;
    while (i - 1 >= 0 && i < code.length()) {
      if (code.charAt(i) == '/' && code.charAt(i - 1) == '/') {
        code.delete(i - 1, code.indexOf("\n", i));
      } else if (code.charAt(i) == '*' && code.charAt(i - 1) == '/') {
        if (code.indexOf("*/", i + 1) != -1) {
          code.delete(i - 1, code.indexOf("*/", i + 1) + 2);
        }
      } else {
        i++;
      }
    }
    List<String> ans = new ArrayList<>();
    String[] split = code.toString().split("\n");
    for (String str : split) {
      if (!str.isEmpty()) {
        ans.add(str);
      }
    }
    return ans;
  }

  public static void main(String args[]) {
    RemoveComments r = new RemoveComments();
    String arr[] = new String[]{"e/*/eabeea/*///*c*////*dc*//bcadcde/*/acbe//*d/*/*//ae//*dc//*cc//*//*eaebb*//"};
    System.out.println(r.removeComments(arr));
  }

}
