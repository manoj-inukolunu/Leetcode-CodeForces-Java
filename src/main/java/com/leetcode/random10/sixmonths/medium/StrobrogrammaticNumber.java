package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StrobrogrammaticNumber {


  public boolean isStrobogrammatic(String num) {
    String valid = "18690";
    char[] numChar = num.toCharArray();
    for (int i = 0; i < numChar.length; i++) {
      if (valid.indexOf(numChar[i]) == -1) {
        return false;
      } else if (numChar[i] == '6') {
        if (numChar[numChar.length - i - 1] != '9') {
          return false;
        }
      } else if (numChar[i] == '9') {
        if (numChar[numChar.length - i - 1] != '6') {
          return false;
        }
      } else if (numChar[i] != numChar[numChar.length - i - 1]) {
        return false;
      }
    }
    return true;
  }

  private Set<String> ans = new HashSet<>();

  HashMap<Character, Character> map = new HashMap<>();

  public List<String> findStrobogrammatic(int n) {
    map.put('0', '0');
    map.put('1', '1');
    map.put('6', '9');
    map.put('9', '6');
    map.put('8', '8');
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < n; i++) {
      buffer.append("0");
    }
    buildNum(buffer, 0, n);
    return new ArrayList<>(ans);
  }

  public void buildNum(StringBuffer buffer, int start, int end) {
    int len = buffer.length();
    if (start > end / 2) {
      if (buffer.length() == 1) {
        ans.add(buffer.toString());
      } else if (buffer.charAt(0) != '0') {
        ans.add(buffer.toString());
      }
      return;
    }
    for (int i = start; i <= end / 2; i++) {
      for (char key : map.keySet()) {
        if (len % 2 != 0 && i == end / 2 && (key == '6' || key == '9')) {
          continue;
        }
        Character curr = buffer.charAt(i);
        Character next = buffer.charAt(len - i - 1);
        buffer.setCharAt(i, key);
        buffer.setCharAt(len - i - 1, map.get(key));
        buildNum(buffer, i + 1, end);
        buffer.setCharAt(i, curr);
        buffer.setCharAt(len - i - 1, next);
      }
    }
  }


  public int strobogrammaticInRange(String low, String high) {
    int ans = 0;
    for (int i = low.length(); i <= high.length(); i++) {
      ans += findStrobogrammatic(i).size();
    }
    return ans;
  }

  public static void main(String args[]) {
    StrobrogrammaticNumber s = new StrobrogrammaticNumber();
    System.out.println(s.findStrobogrammatic(3));
  }

}
