package com.leetcode.contest;

import java.util.Arrays;
import java.util.TreeMap;

public class LowestDigit {

  public String solve(String digits, String lower) {
    TreeMap<Character, Integer> map = new TreeMap();
    for (char ch : digits.toCharArray()) {
      map.put(ch, map.getOrDefault(ch, 0) + 1);
    }
    while (lower.length() != digits.length()) {
      lower = "0" + lower;
    }

    StringBuffer b = new StringBuffer();
    for (int i = 0; i < lower.length(); i++) {
      char ch = lower.charAt(i);
      if (map.containsKey(ch)) {
        b.append(ch);
        map.put(ch, map.get(ch) - 1);
        if (map.get(ch) <= 0) {
          map.remove(ch);
        }
      } else {
        Character c = map.higherKey(ch);
        if (c != null) {
          b.append(c);
          map.put(c, map.get(c) - 1);
          if (map.get(c) <= 0) {
            map.remove(c);
          }
        }
      }
    }
    while (b.charAt(0) == '0') {
      b.deleteCharAt(0);
    }

    char[] arr = b.toString().toCharArray();
    nextPermutation(arr);
    System.out.println(new String(arr));
    return b.toString();
  }

  public void nextPermutation(char[] nums) {
    int k = nums.length - 2;
    while (k >= 0 && nums[k] >= nums[k + 1]) {
      k--;
    }
    if (k == -1) {
      Arrays.sort(nums);
      return;
    }
    for (int i = nums.length - 1; i > k; i--) {
      if (nums[i] > nums[k]) {
        char temp = nums[k];
        nums[k] = nums[i];
        nums[i] = temp;
        break;
      }
    }
    Arrays.sort(nums, k + 1, nums.length);
  }

  public static void main(String args[]) {
    LowestDigit l = new LowestDigit();
    System.out.println(l.solve("123", "123"));
  }

}
