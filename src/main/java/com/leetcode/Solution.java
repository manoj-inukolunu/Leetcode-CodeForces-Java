package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author manoji on 2019-11-29.
 */
public class Solution {

  public static void main(String args[]) {
    Solution solution = new Solution();

    System.out.println(solution.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2));
  }

  public class Data {

    int val;
    int count;

    public Data(int val, int count) {
      this.val = val;
      this.count = count;
    }
  }

  public List<Integer> topKFrequent(int[] nums, int k) {
    HashMap<Integer, Integer> map = new HashMap();
    for (int i = 0; i < nums.length; i++) {
      if (map.containsKey(nums[i])) {
        map.put(nums[i], map.get(nums[i]) + 1);
      } else {
        map.put(nums[i], 1);
      }
    }

    PriorityQueue<Data> pri = new PriorityQueue<Data>((x, y) -> y.count - x.count);

    for (int val : map.keySet()) {
      pri.add(new Data(val, map.get(val)));
    }

    List<Integer> ints = new ArrayList<>();

    for (int i = 0; i < k; i++) {
      ints.add(pri.poll().val);
    }

    return ints;
  }

  public int minCostClimbingStairs(int[] cost) {
    if (cost.length == 1) {
      return cost[0];
    }
    int dp[] = new int[cost.length];
    Arrays.fill(dp, -1);
    int total1 = minCostClimbingStairsRecursive(dp, cost, 0);
    int total2 = minCostClimbingStairsRecursive(dp, cost, 1);
    return Math.min(total1, total2);
  }

  private int minCostClimbingStairsRecursive(int[] dp, int[] cost, int index) {
    if (index >= cost.length) {
      return 0;
    }
    if (dp[index] != -1) {
      return dp[index];
    }
    int total1 = minCostClimbingStairsRecursive(dp, cost, index + 1);
    int total2 = minCostClimbingStairsRecursive(dp, cost, index + 2);
    int total = cost[index] + Math.min(total1, total2);
    dp[index] = total;
    return total;
  }

  public int climbStairs(int n) {

    if (n == 0) {
      return 0;
    }
    if (n == 1) {
      return 1;
    }

    int[] dp = new int[n + 1];

    dp[0] = 0;
    dp[1] = 1;
    dp[2] = 2;

    for (int i = 3; i <= n; i++) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }

    return dp[n];

  }


  public int coinChange(int[] coins, int amount) {
    HashMap<String, Integer> map = new HashMap<>();
    int val = countChangeRecursive(coins, amount, 0, map);
    return val == Integer.MAX_VALUE ? -1 : val;
  }

  private int countChangeRecursive(int[] denominations, int total, int currentIndex, HashMap<String, Integer> map) {
    if (total == 0) {
      return 0;
    }

    if (denominations.length == 0 || currentIndex >= denominations.length) {
      return Integer.MAX_VALUE;
    }

    if (map.containsKey(currentIndex + "-" + total)) {
      return map.get(currentIndex + "-" + total);
    }
    int count1 = Integer.MAX_VALUE;
    if (denominations[currentIndex] <= total) {
      int res = countChangeRecursive(denominations, total - denominations[currentIndex], currentIndex, map);
      if (res != Integer.MAX_VALUE) {
        count1 = res + 1;
      }
    }
    int count2 = countChangeRecursive(denominations, total, currentIndex + 1, map);
    map.put(currentIndex + "-" + total, Math.min(count1, count2));
    return Math.min(count1, count2);
  }

  public int rob(int[] nums) {
    int[] dp = new int[nums.length];
    Arrays.fill(dp, -1);
    if (nums.length == 1) {
      return nums[0];
    }
    int with = robRecursive(dp, Arrays.copyOfRange(nums, 0, nums.length - 1), 0);
    System.out.println(with);
    Arrays.fill(dp, -1);
    int without = robRecursive(dp, Arrays.copyOfRange(nums, 1, nums.length), 0);
    System.out.println(without);

    return Math.max(with, without);
  }

  private int robRecursive(int[] dp, int[] nums, int currentHouse) {
    if (nums.length == 1) {
      return nums[0];
    }
    if (currentHouse >= nums.length) {
      return 0;
    }

    if (dp[currentHouse] != -1) {
      return dp[currentHouse];
    }
    int total1 = robRecursive(dp, nums, currentHouse + 2);
    int total2 = robRecursive(dp, nums, currentHouse + 1);
    dp[currentHouse] = Math.max(nums[currentHouse] + total1, total2);
    return dp[currentHouse];
  }

  private static int sumAlex = 0, sumLee = 0;

  public boolean stoneGame(int[] piles) {

    int player = 0;
    int beginIndex = 0;
    int endIndex = piles.length - 1;
    int sumAlex = 0, sumLee = 0;
    HashMap<String, Boolean> map = new HashMap<>();

    return pick(player, piles, beginIndex, endIndex, sumAlex, sumLee, map);

  }

  public boolean pick(int player, int[] piles, int beginIndex, int endIndex, int sumAlex, int sumLee, HashMap<String, Boolean> map) {
    if (beginIndex > endIndex) {
      if (sumAlex > sumLee) {
        return true;
      } else {
        return false;
      }
    }
    if (map.containsKey(player + "|" + beginIndex + "|" + endIndex)) {
      return map.get(player + "|" + beginIndex + "|" + endIndex);
    }
    if (player == 0) {
      boolean val = pick(1, piles, beginIndex + 1, endIndex, sumAlex + piles[beginIndex], sumLee, map) || pick(1, piles, beginIndex, endIndex - 1,
          sumAlex + piles[endIndex], sumLee, map);
      map.put(player + "|" + beginIndex + "|" + endIndex, val);
      return val;
    } else {
      boolean val = pick(0, piles, beginIndex + 1, endIndex, sumAlex, sumLee + piles[beginIndex], map) || pick(0, piles, beginIndex, endIndex - 1,
          sumAlex, sumLee + piles[endIndex], map);
      map.put(player + "|" + beginIndex + "|" + endIndex, val);
      return val;
    }
  }

  public int numDecodings(String s) {
    if (s.startsWith("0")) {
      return 0;
    }
    if (s.contains("00")) {
      return 0;
    }
    int[] dp = new int[s.length()];
    dp[0] = 1;
    if (s.length() == 2) {
      int val = Integer.parseInt(s);
      if (val == 10 || val == 20) {
        return 1;
      }
      if (val % 10 == 0) {
        return 0;
      }
    }
    for (int i = 1; i < s.length(); i++) {
      int intVal = Integer.parseInt(s.substring(i - 1, i + 1));
      if (intVal <= 26) {
        dp[i] = dp[i - 1] + 1;
      } else {
        dp[i] = dp[i - 1];
      }
    }

    return dp[s.length() - 1];
  }

  public int deleteAndEarn(int[] nums) {
    Arrays.sort(nums);
    int[] dp = new int[nums.length];
    Arrays.fill(dp, -1);
    return deleteAndEarnRecursive(nums, 0, dp);
  }

  private int deleteAndEarnRecursive(int[] nums, int index, int[] dp) {
    if (index >= nums.length) {
      return 0;
    }
    if (dp[index] != -1) {
      return dp[index];
    } else {
      int pick = nums[index];
      int skipEarn = index + 1;
      for (int i = skipEarn; i < nums.length; i++) {
        if (nums[i] == nums[index]) {
          pick += nums[i];
          skipEarn++;
        }
      }
      for (int i = skipEarn; i < nums.length; i++) {
        if (nums[i] == nums[index] + 1) {
          skipEarn++;
        }
      }

      pick += deleteAndEarnRecursive(nums, skipEarn, dp);

      int noPick = deleteAndEarnRecursive(nums, index + 1, dp);

      dp[index] = Math.max(pick, noPick);
      return dp[index];
    }
  }


  public int maxPick(int nums[]) {
    return maxPickRecursive(nums, 0, nums.length - 1);
  }

  private int maxPickRecursive(int[] nums, int start, int end) {
    if (start > end) {
      return 0;
    }
    if (start == end) {
      return nums[start];
    }

    if (end == start + 1) {
      return Math.max(nums[start], nums[end]);
    }

    return Math.max(Math.min(maxPickRecursive(nums, start + 2, end), maxPickRecursive(nums, start + 1, end - 1)) + nums[start],
        Math.min(maxPickRecursive(nums, start + 1, end - 1), maxPickRecursive(nums, start, start - 2)) + nums[end]);
  }

  public boolean canPartitionKSubsets(int[] nums, int k) {
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
    }
    if (sum % k != 0) {
      return false;
    }
    int subsetSum = sum / k;
    int total = canPartitionKSubsetsRecursive(nums, subsetSum, 0, new HashMap<>());
    return total == k;
  }

  private int canPartitionKSubsetsRecursive(int[] nums, int sum, int index, HashMap<String, Integer> map) {
    if (sum == 0) {
      return 1;
    }
    if (nums.length == 0 || index >= nums.length) {
      return 0;
    }
    if (map.containsKey(index + "|" + sum)) {
      return map.get(index + "|" + sum);
    }
    int subsetsIncludingCurrent = 0;
    if (nums[index] <= sum) {
      subsetsIncludingCurrent = canPartitionKSubsetsRecursive(nums, sum - nums[index], index + 1, map);
    }
    int subsetsWithoutCurrent = canPartitionKSubsetsRecursive(nums, sum, index + 1, map);
    map.put(index + "|" + sum, subsetsIncludingCurrent + subsetsWithoutCurrent);
    return subsetsIncludingCurrent + subsetsWithoutCurrent;
  }

  public static void _main(String args[]) {
    Solution solution = new Solution();
    int[] dp = new int[3];
    Arrays.fill(dp, -1);
    System.out.println(solution.rob(new int[]{1, 2, 1, 1}));
  }
}
