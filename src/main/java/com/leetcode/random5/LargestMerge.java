package com.leetcode.random5;

public class LargestMerge {

  public String largestMerge(String word1, String word2) {
    StringBuffer b = new StringBuffer();
    StringBuffer w1 = new StringBuffer(word1);
    StringBuffer w2 = new StringBuffer(word2);

    while (true) {
      if (w1.length() == 0) {
        b.append(w2);
        break;
      }
      if (w2.length() == 0) {
        b.append(w1);
        break;
      }
      if (w1.charAt(0) > w2.charAt(0)) {
        b.append(w1.charAt(0));
        w1.deleteCharAt(0);
      } else if (w1.charAt(0) < w2.charAt(0)) {
        b.append(w2.charAt(0));
        w2.deleteCharAt(0);
      } else {
        if (w1.toString().compareTo(w2.toString()) > 0) {
          b.append(w1.charAt(0));
          w1.deleteCharAt(0);
        } else {
          b.append(w2.charAt(0));
          w2.deleteCharAt(0);
        }
      }
    }
    return b.toString();
  }

  public static void main(String args[]) {
    LargestMerge l = new LargestMerge();
    System.out.println(l.largestMerge("uuurru", "urrrur"));
  }

}
