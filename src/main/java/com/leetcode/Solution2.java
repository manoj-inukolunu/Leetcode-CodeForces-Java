package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author manoji on 2019-12-23.
 */
public class Solution2 {

  public int findList(int[] arr) {
    Integer[] dp = new Integer[arr.length];
    dp[0] = 1;
    int[] count = new int[arr.length];
    Arrays.fill(count, 0);
    count[0] = 0;
    count[1] = arr.length;
    for (int i = 1; i < arr.length; i++) {
      for (int j = 0; j < i; j++) {
        if (arr[i] >= arr[j]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
          count[dp[j] + 1]++;
        }
      }
    }
    System.out.println(Arrays.toString(dp));
    System.out.println(Arrays.toString(count));
    int maxLength = Collections.max(Arrays.asList(dp));
    return count[maxLength];
  }


  public int findNumberOfLIS(int[] nums) {
    int n = nums.length, res = 0, max_len = 0;
    int[] dp = new int[n], cnt = new int[n];
    for (int i = 0; i < n; i++) {
      dp[i] = cnt[i] = 1;
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          if (dp[i] == dp[j] + 1) {
            cnt[i] += cnt[j];
          }
          if (dp[i] < dp[j] + 1) {
            dp[i] = dp[j] + 1;
            cnt[i] = cnt[j];
          }
        }
      }
      if (max_len == dp[i]) {
        res += cnt[i];
      }
      if (max_len < dp[i]) {
        max_len = dp[i];
        res = cnt[i];
      }
    }
    System.out.println(Arrays.toString(cnt));
    return res;
  }

  public boolean divisorGame(int N) {
    Boolean[][] dp = new Boolean[N + 1][2];
    return divisorGameHelper(N, 0, dp);
  }

  public boolean divisorGameHelper(int num, int player, Boolean[][] dp) {
    //base case
    if (num <= 1 && player == 1) {
      return true;
    }
    player = player == 0 ? 1 : 0;
    if (dp[num][player] != null) {
      return dp[num][player];
    }
    boolean didWin = false;
    for (int i = 1; i < num; i++) {
      dp[num - i][player] = divisorGameHelper(num - i, player, dp);
      if (num % i == 0 && dp[num - i][player]) {
        didWin = true;
        break;
      }
    }
    return didWin;
  }

  public int maxProfit(int[] prices) {
    int maxProfit = 0;

    for (int i = 0; i < prices.length; i++) {
      for (int j = i + 1; j < prices.length; j++) {
        if (prices[j] > prices[i] && (maxProfit < (prices[j] - prices[i]))) {
          maxProfit = prices[j] - prices[i];
        }
      }
    }
    return maxProfit;
  }

  public int longestArithSeqLength(int[] A) {
    HashMap<Integer, Integer>[] arr = new HashMap[A.length];
    for (int i = 0; i < A.length; i++) {
      arr[i] = new HashMap<Integer, Integer>();
    }
    int maxLength = 0;
    for (int i = 1; i < A.length; i++) {
      for (int j = 0; j < i; j++) {
        int diff = A[i] - A[j];
        if (arr[j].containsKey(diff)) {
          int len = arr[j].get(diff) + 1;
          arr[i].put(diff, len);
          if (len > maxLength) {
            maxLength = len;
          }
        } else {
          arr[i].put(diff, 2);
          if (maxLength < 2) {
            maxLength = 2;
          }
        }
      }
    }
    return maxLength;
  }


  public int findTargetSumWays(int[] nums, int S) {
    HashMap<String, Integer> dp = new HashMap<>();
    return findTragetSumRecursive(nums, 0, S, dp);

  }

  private int findTragetSumRecursive(int[] nums, int currentIndex, int sum, HashMap<String, Integer> dp) {
    if (currentIndex >= nums.length && sum == 0) {
      return 1;
    }
    if (dp.containsKey(currentIndex + "|" + sum)) {
      return dp.get(currentIndex + "|" + sum);
    }
    int totalWays = 0;
    if (currentIndex < nums.length) {
      totalWays = findTragetSumRecursive(nums, currentIndex + 1, sum - nums[currentIndex], dp) + findTragetSumRecursive(nums, currentIndex + 1,
          sum + nums[currentIndex], dp);
      dp.put(currentIndex + "|" + sum, totalWays);
    }
    return totalWays;
  }

  public int longestCommonSubsequence(String text1, String text2) {
    HashMap<String, Integer> map = new HashMap<>();
    return lcsRecursive(text1, text2, 0, 0, map);
  }

  private int lcsRecursive(String text1, String text2, int index1, int index2, HashMap<String, Integer> map) {
    if (index1 >= text1.length() || index2 >= text2.length()) {
      return 0;
    }
    if (map.containsKey(index1 + "|" + index2)) {
      return map.get(index1 + "|" + index2);
    }
    if (text1.charAt(index1) == text2.charAt(index2)) {
      int val = 1 + lcsRecursive(text1, text2, index1 + 1, index2 + 1, map);
      map.put(index1 + "|" + index2, val);
      return val;
    }

    int val1 = lcsRecursive(text1, text2, index1 + 1, index2, map);
    int val2 = lcsRecursive(text1, text2, index1, index2 + 1, map);

    map.put(index1 + "|" + index2, Math.max(val1, val2));

    return Math.max(val1, val2);
  }


  public boolean wordBreak1(String s, List<String> wordDict) {

    HashMap<String, Boolean> map = new HashMap<>();
    return wordBreakRecursive(s, wordDict, map);
  }

  private boolean wordBreakRecursive(String s, List<String> wordDict, HashMap<String, Boolean> map) {
//    System.out.println(buffer);
    if (s.length() == 0) {
      return true;
    }
    if (map.containsKey(s)) {
      return map.get(s);
    }
    for (int i = 1; i <= s.length(); i++) {
      if (wordDict.contains(s.substring(0, i))) {
        String str = s.substring(i);
        Boolean exists = wordBreakRecursive(str, wordDict, map);
        map.put(str, exists);
        if (exists) {
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String args[]) {
    Solution2 solution2 = new Solution2();

//    System.out.println(solution2.findNumberOfLIS(new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9}));

//    System.out.println(solution2.maxProfit(new int[]{7, 6, 4, 3, 1}));

//    System.out.println(solution2.longestArithSeqLength(new int[]{20, 1}));

//    System.out.println(solution2.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));

//    System.out.println(solution2.longestCommonSubsequence("a", "b"));

//    new String[] {"cats", "dog", "sand", "and", "cat"}
    List<String> strings = new ArrayList<>();
    strings.add("cats");
    strings.add("dog");
    strings.add("sand");
    strings.add("and");
    strings.add("cat");

    List<String> strings1 = new ArrayList<>();
    strings1.add("leet");
    strings1.add("code");

    List<String> strings2 = new ArrayList<>();
    strings2.add("apple");
    strings2.add("pen");

    String str = "bccdbacdbdacddabbaaaadababadad";
    String[] strings3 = new String[]{"cbc",
        "bcda", "adb", "ddca", "bad", "bbb", "dad", "dac", "ba", "aa", "bd", "abab", "bb", "dbda", "cb", "caccc", "d", "dd",
        "aadb", "cc", "b", "bcc", "bcd", "cd", "cbca", "bbd", "ddd", "dabb", "ab", "acd", "a", "bbcc", "cdcbd", "cada", "dbca", "ac", "abacd", "cba"
        , "cdb", "dbac", "aada", "cdcda", "cdc", "dbc", "dbcb", "bdb", "ddbdd", "cadaa", "ddbc", "babb"};
    System.out.println(solution2.wordBreak1(str, strings));
  }


}
