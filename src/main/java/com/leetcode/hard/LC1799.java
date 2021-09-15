package com.leetcode.hard;

import java.util.HashMap;
import java.util.Objects;

public class LC1799 {

    class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public int maxScore(int[] nums) {
        HashMap<Pair, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j) {
                    map.put(new Pair(nums[i], nums[j]), gcd(nums[i], nums[j]));
                }
            }
        }
        int n = nums.length / 2;
        return solve(map, nums, n, 0, 0);
    }

    private int solve(HashMap<Pair, Integer> map, int[] nums, int n, int ops, int mask) {
        if (n == ops) {
            return 0;
        }
        if (n < ops) {
            return Integer.MIN_VALUE / 2;
        }
        int score = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j && !used(i, mask, nums) && !used(j, mask, nums)) {
                    score = Math.max(score, (ops + 1) * map.get(new Pair(nums[i], nums[j])) + solve(map, nums, n,
                            ops + 1,
                            getMask(mask, i, j, nums)));
                }
            }
        }
        return score;
    }

    private boolean used(int idx, int mask, int[] nums) {
        return (mask & (1 << (nums.length - idx - 1))) > 0;
    }

    private int getMask(int mask, int i, int j, int[] nums) {
        mask = mask | (1 << (nums.length - i - 1));
        mask = mask | (1 << (nums.length - j - 1));
        return mask;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2};
        LC1799 l = new LC1799();
        System.out.println(l.maxScore(arr));
    }
}
