package com.leetcode.random10.sixmonths.medium;

import java.util.Arrays;

public class LongestAbsolutePath {

  public int lengthLongestPath(String input) {
    String[] data = input.split("\n");
    if (data.length == 1) {
      if (data[0].contains(".")) {
        return data[0].length();
      }
    }
    int max = 0;
    int[] lengths = new int[data.length];
    Arrays.fill(lengths, 0);
    int[] depth = new int[data.length];
    Arrays.fill(depth, 0);
    depth[0] = 0;
    int idx = 0;
    int maxDepth = 0;
    for (int i = 0; i < data.length; i++) {
      int numTs = countTs(data[i], maxDepth + 1, data, i);
      lengths[i] = data[i].length() - numTs;
      for (int j = i - 1; j >= 0; j--) {
        if (depth[j] == numTs - 1) {
          int currLen = data[i].length() - numTs;
          if (data[i].contains(".")) {
            if (lengths[j] + currLen + depth[j] + 1 > max) {
              max = lengths[j] + currLen + depth[j] + 1;
            }
          }
          lengths[i] = lengths[j] + currLen;
          break;
        }
      }
      maxDepth = Math.max(maxDepth, depth[i]);
      depth[i] = numTs;
    }
    return max == 0 ? max : max;
  }

  private String replaceSpaces(String datum, int numTs) {
    String str = datum;
    while (numTs-- > 0) {
      str = str.replaceFirst("    ", "\t");
    }
    return str;
  }

  private int countTs(String str, int maxDepth, String data[], int idx) {
    if (str.contains("\t") || str.contains("    ")) {
      int count = 0, i = 0;
      while (i < str.length() && i + 1 < str.length() && (str.charAt(i) == '\t')) {
        count++;
        i++;
      }
      if (count < maxDepth) {
        while (maxDepth-- > 0) {
          str = str.replaceFirst("    ", "\t");
          count++;
        }
        data[idx] = str;
      }
      return count;
    }
    return 0;
  }


  public static void main(String args[]) {
    LongestAbsolutePath l = new LongestAbsolutePath();

    String str = "rzzmf\nv\n\tix\n\t\tiklav\n\t\t\ttqse\n\t\t\t\ttppzf\n\t\t\t\t\tzav\n\t\t\t\t\t\tkktei\n\t\t\t\t\t\t\thhmav\n\t\t\t\t\t\t\t"
        + "\tbzvwf.txt";

    System.out.println(l.lengthLongestPath(str));
  }

}
