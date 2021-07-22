package com.leetcode.DP.hard;

public class LC629 {
    public int kInversePairs(int n, int k) {
        return go(0, k, n);
    }

    private int go(int size, int k, int n) {
        if (k == 0) {
            return 1;
        }
        if (k < 0 || size > n) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i <= size; i++) {
            count += go(size + 1, k - (size - i), n);
        }
        return count;
    }

    public static void main(String[] args) {
        LC629 l = new LC629();
        System.out.println(l.kInversePairs(3, 1));
    }
}
