package com.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author manoji on 3/9/20.
 */
public class PrintVertically {

  public List<String> printVertically(String s) {
    String[] splits = s.split(" ");

    int max = Integer.MIN_VALUE;
    for (int i = 0; i < splits.length; i++) {
      if (splits[i].length() > max) {
        max = splits[i].length();
      }
    }

    Character[][] sts = new Character[splits.length][max];

    for (int i = 0; i < splits.length; i++) {
      String str = splits[i];
      for (int j = 0; j < str.toCharArray().length; j++) {
        sts[i][j] = str.charAt(j);
      }
    }
    List<String> list = new ArrayList();
    for (int column = 0; column < max; column++) {
      StringBuffer buff = new StringBuffer();
      for (int row = 0; row < splits.length; row++) {
        if (sts[row][column] != null) {
          buff.append(sts[row][column]);
        } else {
          buff.append(" ");
        }
      }
      list.add(buff.toString().replaceAll("\\s+$", ""));
    }
    return list;
  }

  public String reverseWords(String s) {
    String[] splits = s.split(" ");
    for (int i = 0; i < splits.length / 2; i++) {
      String temp = splits[i];
      splits[i] = splits[splits.length - i - 1];
      splits[splits.length - i - 1] = temp;
    }
    StringBuffer buff = new StringBuffer();
    for (String str : splits) {
      buff.append(str.trim());
      buff.append(" ");
    }
    for (int i = buff.length() - 1; i >= 0; i--) {
      if (buff.charAt(i) != ' ') {
        break;
      } else {
        buff.deleteCharAt(i);
      }
    }

    return buff.toString();
  }

  public static void main(String args[]) {
    PrintVertically p = new PrintVertically();
    System.out.println(p.reverseWords("a good   example"));
  }

}
