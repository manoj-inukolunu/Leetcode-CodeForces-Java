package com.leetcode;

import java.util.HashMap;

/**
 * @author manoji on 2/2/20.
 */
public class LongestSubstringWithNonRepeatingCharacters {

  public int lengthOfLongestSubstring(String s) {
    int maxLength = 0;
    for (int i = 0; i < s.length(); i++) {
      for (int j = i; j < s.length(); j++) {
        if (nonRepeating(s.substring(i, j + 1))) {
          if (maxLength < (j + 1 - i)) {
            maxLength = j + 1 - i;
          }
        }
      }
    }
    return maxLength;
  }

  private boolean nonRepeating(String s) {
    HashMap<Character, Integer> map = new HashMap();
    for (Character c : s.toCharArray()) {
      if (map.containsKey(c)) {
        return false;
      } else {
        map.put(c, 1);
      }
    }
    return true;
  }


  public static void main(String args[]) {
    LongestSubstringWithNonRepeatingCharacters l = new LongestSubstringWithNonRepeatingCharacters();
    System.out.println(l.lengthOfLongestSubstring(" "));
  }
}
