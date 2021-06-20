package com.leetcode.biweekly.context46;

public class CountAndSay {

  public String countAndSay(int n) {
    if (n == 1) {
      return "1";
    }
    if (n == 2) {
      return "11";
    }
    StringBuffer buffer = new StringBuffer("11");
    int count = 2;
    while (count < n) {
      int currLen = 1;
      StringBuffer next = new StringBuffer();
      for (int i = 1; i < buffer.length(); i++) {
        if (buffer.charAt(i - 1) == buffer.charAt(i)) {
          currLen++;
        } else {
          if (currLen != 0) {
            next.append(currLen);
            next.append(buffer.charAt(i));
          }
        }
      }
      if (currLen != 0) {
        next.append(currLen);
        next.append(buffer.charAt(buffer.length() - 1));
      }
      buffer = next;
      count++;
    }
    return buffer.toString();
  }

  private String say(int n) {
    if (n == 1) {
      return "1";
    }
    String str = say(n - 1);
    int curr = 1;
    StringBuffer buff = new StringBuffer();
    for (int i = 0; i < str.length(); i++) {
      if (i + 1 < str.length() && str.charAt(i + 1) == str.charAt(i)) {
        curr++;
      } else {
        buff.append(curr);
        buff.append(str.charAt(i));
        curr = 1;
      }
    }
    return buff.toString();
  }

  public static void main(String args[]) {
    CountAndSay c = new CountAndSay();
    System.out.println(c.say(6));
  }

}
