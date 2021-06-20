package com.leetcode.random11;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;

public class EncodeAndDecodeStrings {

  // Encodes a list of strings to a single string.
  public String encode(List<String> strs) {
    int separator = 300;
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < strs.size(); i++) {
      buffer.append(strs.get(i));
      if (i != strs.size() - 1) {
        buffer.append((char) separator);
      }
    }
    return buffer.toString();
  }

  // Decodes a single string to a list of strings.
  public List<String> decode(String s) {
    int separator = 300;
    List<String> ans = new ArrayList<>();
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == separator) {
        ans.add(buffer.toString());
        buffer.setLength(0);
      } else {
        buffer.append(s.charAt(i));
      }
    }
    return new ArrayList<>();
  }

  public static void main(String args[]) {
    EncodeAndDecodeStrings e = new EncodeAndDecodeStrings();
    String str = e.encode(Lists.newArrayList(""));
    System.out.println(e.decode(str));
  }

}
