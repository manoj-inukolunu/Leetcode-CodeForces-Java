package com.leetcode.random;

import com.google.common.io.Files;
import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;

public class StringDiff {

  public boolean differByOne(String[] dict) {
    HashMap<String, Integer> set = new HashMap<>();
    for (String str : dict) {
      set.put(str, 1);
    }

    for (int i = 0; i < dict.length; i++) {
      char[] arr = dict[i].toCharArray();
      for (int j = 0; j < arr.length; j++) {
        int count = 0;
        char temp = arr[j];
        for (int k = 0; k < 26; k++) {
          char curr = (char) ('z' - k);
          if (curr != temp) {
            arr[j] = curr;
            if (set.containsKey(new String(arr))) {
              count++;
            }
          }
        }
        if (count >= 1) {
          return true;
        }
        arr[j] = temp;
      }
    }
    return false;
  }

  public static void main(String args[]) throws Exception {
    StringDiff s = new StringDiff();
    String[] arr = new String[]{"abcd", "cccc", "abyd", "abab"};
    List<String> list = Files.readLines(new File("/Users/manoji/test.txt"), Charset.defaultCharset());
    System.out.println(s.differByOne(list.get(0).split(",")));
  }

}
