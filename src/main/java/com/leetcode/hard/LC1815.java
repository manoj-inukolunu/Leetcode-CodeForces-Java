package com.leetcode.hard;

import java.util.Arrays;
import java.util.HashMap;

public class LC1815 {


    HashMap<String, Integer> map = new HashMap<>();


    public int maxHappyGroups(int batchSize, int[] groups) {
        int[] arr = new int[batchSize];
        for (int group : groups) {
            arr[group % batchSize]++;
        }
        return arr[0] + solve(0, arr);
    }

    private int solve(int num, int[] arr) {
        if (isFull(arr)) {
            return 0;
        }
        int best = Integer.MIN_VALUE / 2;
        if (num == 0) {
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] <= 0) {
                    continue;
                }
                int[] newFreq = Arrays.copyOf(arr, arr.length);
                newFreq[i]--;
                best = Math.max(best, 1 + solve(i, newFreq));
            }
        } else {
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > 0) {
                    int[] newFreq = Arrays.copyOf(arr, arr.length);
                    newFreq[i]--;
                    best = Math.max(best, solve((num + i) % arr.length, newFreq));
                }

            }
        }
        return best;
    }

    private boolean isFull(int[] arr) {
        int sum = 0;
        for (int i = 1; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum == 0;
    }

    public static void main(String args[]) {
        LC1815 l = new LC1815();
        int ans = l.maxHappyGroups(3, new int[]{1, 2, 3, 4, 5, 6});
        System.out.println(ans);
    }
}
