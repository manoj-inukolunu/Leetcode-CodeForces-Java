package com.leetcode.hard;

import java.util.List;

public class LC1434 {
    int mod = (int) Math.pow(10, 9) + 7;

    public int numberWays(List<List<Integer>> hats) {
        return solve(1, 0, hats);
    }

    private int solve(int hat, int personState, List<List<Integer>> hats) {
        if (hat > 40 || personState == (1 << hats.size())) {
            return personState == (1 << hats.size()) ? 1 : 0;
        }
        long ways = 0;
        for (int i = 0; i < hats.size(); i++) {
            if ((personState & (1 << i)) == 0) {
                if (hats.get(i).contains(hat)) {
                    ways += solve(hat + 1, (personState | (1 << i)), hats);
                    ways %= mod;
                }
                ways += solve(hat + 1, personState, hats);
                ways %= mod;
            }
        }
        return (int) (ways % mod);
    }
}
