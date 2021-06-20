package com.leetcode.random;

import java.util.HashMap;

public class AlphabetBoardPath {


  public String alphabetBoardPath(String target) {
    HashMap<Character, Integer> rowMap = new HashMap<>();
    HashMap<Character, Integer> colMap = new HashMap<>();
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 5; j++) {
        rowMap.put((char) ('a' + (i) * 5 + j), i);
        colMap.put((char) ('a' + (i) * 5 + j), j);
      }
    }
    StringBuilder ans = new StringBuilder();
    char start = 'a';
    for (int i = 0; i < target.length(); i++) {
      char end = target.charAt(i);
      if (start == 'z' && end == 'z') {
        ans.append("!");
      } else if (start == 'z' && end != 'z') {
        StringBuilder path = getPath('u', end, rowMap, colMap);
        path.insert(0, 'U');
        ans.append(path);
      } else if (start != 'z' && end == 'z') {
        StringBuilder path = getPath(start, 'u', rowMap, colMap);
        path.deleteCharAt(path.length() - 1);
        path.append('D');
        path.append('!');
        ans.append(path);
      } else {
        ans.append(getPath(start, end, rowMap, colMap));
      }
      start = target.charAt(i);
    }
    return ans.toString();
  }

  private StringBuilder getPath(char start, char end, HashMap<Character, Integer> rMap, HashMap<Character, Integer> cMap) {
    int rDiff = rMap.get(start) - rMap.get(end);
    StringBuilder ret = new StringBuilder();
    if (rDiff < 0) {
      for (int i = 0; i < Math.abs(rDiff); i++) {
        ret.append("D");
      }
    } else if (rDiff > 0) {
      for (int i = 0; i < rDiff; i++) {
        ret.append("U");
      }
    }
    int cDiff = cMap.get(start) - cMap.get(end);
    if (cDiff < 0) {
      for (int i = 0; i < Math.abs(cDiff); i++) {
        ret.append("R");
      }
    } else if (cDiff > 0) {
      for (int i = 0; i < cDiff; i++) {
        ret.append("L");
      }
    }
    ret.append("!");
    return ret;
  }

  public static void main(String args[]) {
    AlphabetBoardPath a = new AlphabetBoardPath();
    System.out.println(Integer.parseInt("10", -2));
  }

}
