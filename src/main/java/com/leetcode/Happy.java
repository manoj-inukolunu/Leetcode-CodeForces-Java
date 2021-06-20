package com.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author manoji on 4/2/20.
 */
public class Happy {

  public int lengthOfLongestSubstring(String s) {
    int max = Integer.MIN_VALUE;
    char[] arr = s.toCharArray();
    HashSet<Character> set = new HashSet();
    int length = 0;
    int i = 0;
    while (i < arr.length) {
      if (!set.contains(arr[i])) {
        length++;
        set.add(arr[i]);
        i++;
      } else {
        if (length > max) {
          max = length;
          length = 0;
          set.clear();
          i--;
        } else {
          i++;
        }

      }
    }
    return max > length ? max : length;
  }


  public boolean isHappy(int n) {
    Set<Integer> set = new HashSet<>();
    while (n != 1) {
      n = getNext(n);
      if (set.contains(n)) {
        return false;
      }
      set.add(n);
    }
    return true;
  }

  private int getNext(int num) {
    int n = 0;
    while (num != 0) {
      int rem = num % 10;
      n += Math.pow(rem, 2);
      num = num / 10;
    }
    return n;
  }

  public static void main(String args[]) {
    Happy happy = new Happy();

    System.out.println(happy.lengthOfLongestSubstring("anviaj"));
  }

}
