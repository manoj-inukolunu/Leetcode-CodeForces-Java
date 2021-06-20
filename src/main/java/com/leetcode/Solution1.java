package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @author manoji on 2019-12-10.
 */
public class Solution1 {


  class Solution {

    class Data {

      String word;
      int count;

      public Data(String word, int count) {
        this.word = word;
        this.count = count;
      }
    }

    public List<String> topKFrequent(String[] words, int k) {
      HashMap<String, Integer> map = new HashMap<String, Integer>();
      for (int i = 0; i < words.length; i++) {
        map.put(words[i], map.getOrDefault(words[i], 0) + 1);
      }

      PriorityQueue<Data> pri = new PriorityQueue<Data>(new Comparator<Data>() {
        public int compare(Data lhs, Data rhs) {
          if (lhs.count < rhs.count) {
            return +1;
          }
          if (lhs.count > rhs.count) {
            return -1;
          }

          return lhs.word.compareTo(rhs.word);
        }
      });
      for (String val : map.keySet()) {
        pri.add(new Data(val, map.get(val)));
      }
      List<String> ints = new ArrayList<>();

      for (int i = 0; i < k; i++) {
        ints.add(pri.poll().word);
      }

      return ints;

    }
  }


  public int lisIterative(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    Integer maxLength = 1;

    int[] count = new int[nums.length];
    count[0] = 0;
    count[1] = nums.length;
    int dp[] = new int[nums.length];
    Arrays.fill(dp, 1);
    for (int i = 1; i < nums.length; i++) {
      for (int j = 0; j <= i; j++) {
        if (nums[i] > nums[j]) {
          if (dp[i] <= dp[j]) {
            dp[i] = dp[j] + 1;
            maxLength = Math.max(maxLength, dp[i]);
          }
          count[dp[j] + 1]++;
        }
      }
    }
    System.out.println(Arrays.toString(dp));
    System.out.println(Arrays.toString(count));

    System.out.println(count[maxLength]);
    return maxLength;
  }

  public int minSteps(int n) {
    if (n == 1) {
      return 1;
    }
    return minStepsRecursive(n, "A", 1, "A", "copy");
  }

  private int minStepsRecursive(int n, String current, int steps, String buffer, String prev) {

    System.out.println(String.format("Solving problem with n=%d,current=%s,steps=%d,buffer=%s,prev=%s", n, current, steps, buffer, prev));
    if (current.length() == n) {
//      System.out.println(current + " - " + steps);
      return steps;
    }

    if (!buffer.equalsIgnoreCase("") && !prev.equalsIgnoreCase("copy")) {
      if ((current.length() + buffer.length()) <= n) {

        return Math
            .min(minStepsRecursive(n, current, steps + 1, current, "copy"), minStepsRecursive(n, current + buffer, steps + 1, buffer, "paste"));
      }
    } else {
      if ((current.length() + buffer.length()) <= n) {
        return minStepsRecursive(n, current + buffer, steps + 1, buffer, "paste");
      }
    }
    return Integer.MAX_VALUE;
  }


  public int lengthOfLIS(int[] nums) {
    Integer[][] dp = new Integer[nums.length][nums.length + 1];
    int data = findLISRecursive(nums, 0, -1, dp);
    return data;

  }

  private int findLISRecursive(int[] nums, int currentIndex, int previousIndex, Integer[][] dp) {
    if (currentIndex == nums.length) {
      return 0;
    }
    if (dp[currentIndex][previousIndex + 1] == null) {
      int including = 0;
      if (previousIndex == -1 || nums[currentIndex] > nums[previousIndex]) {
        including = 1 + findLISRecursive(nums, currentIndex + 1, currentIndex, dp);
      }

      int excluding = findLISRecursive(nums, currentIndex + 1, previousIndex, dp);
      System.out.println(including == excluding);
      dp[currentIndex][previousIndex + 1] = Math.max(including, excluding);
    }
    return dp[currentIndex][previousIndex + 1];
  }

  public static void main(String args[]) {
    Solution1 solution = new Solution1();
    System.out.println(solution.lisIterative(new int[]{1, 3, 5, 4, 7, 9, 10}));
  }
}
