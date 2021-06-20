package com.leetcode.revision.fb.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CustomSortString {

  public String customSortString(String S, String T) {
    char[] arr = T.toCharArray();
    List<Character> list = new ArrayList<>();
    List<Character> others = new ArrayList<>();
    for (int i = 0; i < arr.length; i++) {
      if (S.indexOf(arr[i]) != -1) {
        list.add(arr[i]);
      } else {
        others.add(arr[i]);
      }
    }
    Collections.sort(list, new Comparator<Character>() {
      @Override
      public int compare(Character o1, Character o2) {
        return Integer.compare(S.indexOf(o1), S.indexOf(o2));
      }
    });

    StringBuffer b = new StringBuffer();
    for (int i = 0; i < list.size(); i++) {
      b.append(list.get(i));
    }
    for (int i = 0; i < others.size(); i++) {
      b.append(others.get(i));
    }
    return b.toString();
  }

  public static void main(String args[]) {
    CustomSortString c = new CustomSortString();
    System.out.println(c.customSortString("exv", "xwvee"));
  }
}
