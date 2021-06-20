package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author manoji on 5/20/20.
 */
public class LetterCombinations {


  public List<String> letterCombinations(String digits) {
    if (digits.contains("1") || digits.contains("0")) {
      return new ArrayList<>();
    }
    HashMap<Character, List<String>> map = getLetters();
    return combinations(digits, map);
  }

  private List<String> combinations(String digits, HashMap<Character, List<String>> map) {
    if (digits.length() == 1) {
      if (map.containsKey(digits.charAt(0))) {
        return map.get(digits.charAt(0));
      } else {
        return new ArrayList<>();
      }
    }
    List<String> str = combinations(digits.substring(1), map);
    List<String> ans = new ArrayList<>();
    if (!str.isEmpty()) {
      for (String s : map.get(digits.charAt(0))) {
        for (String s1 : str) {
          ans.add(s + s1);
        }
      }
    }
    return ans;
  }

  private HashMap<Character, List<String>> getLetters() {
    HashMap<Character, List<String>> map = new HashMap<>();
    map.put('2', Arrays.asList("abc".split("")));
    map.put('3', Arrays.asList("def".split("")));
    map.put('4', Arrays.asList("ghi".split("")));
    map.put('5', Arrays.asList("jkl".split("")));
    map.put('6', Arrays.asList("mno".split("")));
    map.put('7', Arrays.asList("pqrs".split("")));
    map.put('8', Arrays.asList("tuv".split("")));
    map.put('9', Arrays.asList("wxyz".split("")));
    return map;
  }

  public static void main(String args[]) {
    LetterCombinations l = new LetterCombinations();
    System.out.println(l.letterCombinations("234"));
  }

}
