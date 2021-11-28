package com.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

public class LC656 {

    public List<Integer> cheapestJump(int[] coins, int maxJump) {
        Integer[] dp = new Integer[coins.length];
        solve(coins, 0, maxJump, dp);
        if (dp[0] == -1) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        int currIdx = 0;
        ans.add(1);
        while (currIdx < coins.length) {
            int min = Integer.MAX_VALUE;
            int end = currIdx + maxJump;
            for (int j = currIdx + 1; j < coins.length && j <= end; j++) {
                if (coins[j] != -1) {
                    if (j == coins.length - 1) {
                        if (coins[j] < min) {
                            min = coins[j];
                            currIdx = j;
                        }
                    } else {
                        if (coins[j] + dp[j] < min) {
                            min = coins[j] + dp[j];
                            currIdx = j;
                        }
                    }
                }
            }
            ans.add(currIdx + 1);
            if (currIdx == coins.length - 1) {
                break;
            }
        }
        return ans;
    }

    private int solve(int[] arr, int idx, int maxJump, Integer[] dp) {
        if (idx == arr.length - 1) {
            return 0;
        }
        if (dp[idx] != null) {
            return dp[idx];
        }
        int best = Integer.MAX_VALUE;
        int start = idx + 1, end = idx + maxJump;
        for (int i = start; i <= end && i < arr.length; i++) {
            //jump to i;
            if (arr[i] == -1) {
                continue;
            }
            int jump = solve(arr, i, maxJump, dp);
            if (jump != Integer.MAX_VALUE) {
                best = Math.min(best, arr[i] + jump);
            }
        }
        return dp[idx] = best;
    }

    public static void main(String[] args) {
        LC656 l = new LC656();
        int[] arr = new int[]{1, 2, 4, -1, 2};
        System.out.println(l.cheapestJump(arr, 2));
    }
}
