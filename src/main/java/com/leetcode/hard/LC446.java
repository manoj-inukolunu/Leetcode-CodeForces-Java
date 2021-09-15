package com.leetcode.hard;

import java.util.HashMap;

public class LC446 {

    public int numberOfArithmeticSlices(int[] numsInt) {
        long[] nums = new long[numsInt.length];
        for (int i = 0; i < numsInt.length; i++) {
            nums[i] = numsInt[i];
        }
        HashMap<Long, Long>[] arr = new HashMap[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = new HashMap<>();
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                long diff = nums[i] - nums[j];
                arr[i].put(diff, arr[i].getOrDefault(diff, 0L) + 1);
            }
        }
        HashMap<Long, Long>[] threeSeq = new HashMap[nums.length];
        for (int i = 0; i < nums.length; i++) {
            threeSeq[i] = new HashMap<>();
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                long diff = nums[i] - nums[j];
                if (arr[j].containsKey(diff)) {
                    threeSeq[i].put(diff, threeSeq[i].getOrDefault(diff, 0L) + arr[j].get(diff));
                }
            }
        }
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            for (long val : threeSeq[i].values()) {
                total += val;
            }
            for (int j = i - 1; j >= 0; j--) {
                long diff = nums[i] - nums[j];
                if (threeSeq[j].containsKey(diff)) {
                    total += threeSeq[j].get(diff);
                    threeSeq[i].put(diff, threeSeq[i].getOrDefault(diff, 0L) + threeSeq[j].get(diff));
                }
            }
        }

        return total;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{0, 2000000000, -294967296};
        LC446 l = new LC446();
        System.out.println(l.numberOfArithmeticSlices(arr));
    }
}
