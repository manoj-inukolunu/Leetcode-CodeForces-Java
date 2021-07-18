package com.leetcode.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LC1510 {
    int[][] dp = new int[100001][2];

    public boolean winnerSquareGame(int n) {
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i * i <= n; i++) {
            set.add(i * i);
        }
        return go(n, 0, set) == 0;
    }

    private int go(int n, int player, Set<Integer> set) {
        if (set.contains(n)) {
            return player;
        }
        if (dp[n][player] != -1) {
            return dp[n][player];
        }
        boolean alice = false, bob = false;
        for (int num : set) {
            if (n - num > 0) {
                int val = go(n - num, player == 0 ? 1 : 0, set);
                if (val == 1 && player == 1) {
                    bob = true;
                    break;
                } else if (val == 0 && player == 0) {
                    alice = true;
                    break;
                }
            }
        }
        if (player == 1) {
            dp[n][player] = bob ? 1 : 0;
            return dp[n][player];
        } else {
            dp[n][player] = alice ? 0 : 1;
            return alice ? 0 : 1;
        }
    }

    public static void main(String[] args) {
        LC1510 l = new LC1510();
        int n = (int) Math.pow(10, 5);
        System.out.println(n);
        System.out.println(l.winnerSquareGame(95687));
    }
}
