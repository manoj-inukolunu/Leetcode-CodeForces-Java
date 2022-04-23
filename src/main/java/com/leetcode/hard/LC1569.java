package com.leetcode.hard;


import java.util.ArrayList;
import java.util.List;

public class LC1569 {
    int mod = (int) Math.pow(10, 9) + 7;
    long[][] pascal = new long[1001][1001];

    private void pascalsTriangle() {
        pascal[0][0] = 1;
        for (int i = 1; i < pascal.length; i++) {
            pascal[i][0] = 1;
            for (int j = 1; j < pascal[i].length; j++) {
                pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j];
                pascal[i][j] %= mod;
            }
        }
    }

    public int numOfWays(int[] nums) {
        pascalsTriangle();
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        return (int) solve(list) - 1;
    }

    private long solve(List<Integer> list) {
        if (list.size() <= 2) {
            return 1;
        }
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int root = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > root) {
                right.add(list.get(i));
            } else {
                left.add(list.get(i));
            }
        }
        long l = solve(left);
        long r = solve(right);
        long ret = (l * r) % mod;
        ret = (ret * pascal[list.size() - 1][left.size()]) % mod;
        return ret;
    }


    public static void main(String[] args) {
        LC1569 l = new LC1569();
        System.out.println(l.numOfWays(new int[]{3, 4, 5, 1, 2}));
    }
}



