package com.leetcode.random5;

import java.util.ArrayList;
import java.util.List;

public class LongestRepChar {

  public int maxRepOpt1(String text) {
    int maxLen = 0;

    StringBuffer b = getRLE(text);
    System.out.println(b.toString());

    for (char ch = 'a'; ch <= 'z'; ch++) {
      maxLen = Math.max(maxLen, getMax(b, ch));
    }

    return maxLen;
  }

  private int getMax(StringBuffer b, char ch) {
    for (int i = 0; i < b.length(); i++) {
      if (b.charAt(i) == ch) {
        int next = b.indexOf(ch + "", i);

      }
    }
    return 0;
  }

  private StringBuffer getRLE(String str) {
    StringBuffer b = new StringBuffer();
    int count = 1;
    for (int i = 1; i < str.length(); i++) {
      if (str.charAt(i) == str.charAt(i - 1)) {
        count++;
      } else {
        b.append(str.charAt(i - 1));
        b.append(count);
        count = 1;
      }
    }
    b.append(str.charAt(str.length() - 1));
    b.append(count);
    return b;
  }

  public List<Integer> findDuplicates(int[] nums) {
    List<Integer> ans = new ArrayList();

    for (int i = 0; i < nums.length; i++) {
      int curr = nums[i];
      if (i == nums[i] - 1) {
        continue;
      }
      if (nums[curr - 1] == curr) {
        ans.add(curr);
      } else {
        int temp = nums[curr - 1];
        nums[curr - 1] = curr;
        nums[i] = temp;
        if (nums[nums[i] - 1] == i - 1) {
          ans.add(nums[i]);
        }
      }

      //System.out.println(Arrays.toString(nums));
    }
    return ans;
  }

  public static void main(String args[]) {
    LongestRepChar l = new LongestRepChar();
    System.out.println(l.findDuplicates(new int[]{4, 3, 2, 4, 7, 2, 3, 1}));
  }

}
