package com.leetcode.hard;

import java.math.BigInteger;
import java.util.Arrays;

public class LC1467 {
    BigInteger[] fact;

    public double getProbability(int[] balls) {
        int sum = Arrays.stream(balls).sum();
        fact = new BigInteger[50];
        fact[0] = BigInteger.ONE;
        for (int i = 1; i < 50; i++) {
            fact[i] = fact[i - 1].multiply(BigInteger.valueOf(i));
        }
        BigInteger totalWays = comb(sum, sum / 2);
        BigInteger valid = solve(0, 0, 0, 0, 0, balls);
        return valid.doubleValue() / totalWays.doubleValue();
    }

    private BigInteger solve(int idx, int count1, int count2, int distinct1, int distinct2, int[] balls) {
        if (idx == balls.length) {
            return ((count1 == count2) && (distinct1 == distinct2)) ? BigInteger.ONE : BigInteger.ZERO;
        }
        BigInteger ans = BigInteger.ZERO;
        for (int i = 0; i <= balls[idx]; i++) {
            ans = ans.add(comb(balls[idx], i).multiply(solve(idx + 1, count1 + i,
                    count2 + (balls[idx] - i), distinct1 + (i == 0 ?
                            0 : 1), distinct2 + (i == balls[idx] ? 0 : 1), balls)));
        }
        return ans;
    }

    private BigInteger comb(int n, int k) {
        return fact[n].divide(fact[n - k].multiply(fact[k]));
    }


    public static void main(String[] args) {
        LC1467 l = new LC1467();
        System.out.println(l.getProbability(new int[]{2, 1, 1}));
    }
}






