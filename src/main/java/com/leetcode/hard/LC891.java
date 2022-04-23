package com.leetcode.hard;

import java.util.Arrays;

public class LC891 {

    public int sumSubseqWidths(int[] nums) {
        Arrays.sort(nums);
        int mod = (int) Math.pow(10, 9) + 7;
        long val = 0;
        int[] pow = new int[nums.length];
        pow[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            pow[i] = pow[i - 1] * 2;
            pow[i] %= mod;
        }
        long[] pre = new long[pow.length];
        pre[0] = pow[0];
        for (int i = 1; i < pre.length; i++) {
            pre[i] = pre[i - 1] + pow[i];
            if (pre[i] < 0) {
                System.out.println(pre[i]);
            }
        }
        long[] arr = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = (long) nums[i] * pow[i];
        }
        /*for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int size = nums[j] - nums[i];
                int numElements = j - i + 1;
                if (numElements >= 2) {
                    val += ((long) size * pow[numElements - 2]);
                    //System.out.println( (long) size * pow[numElements-2] +" " +val);
                    val %= mod;
                }
            }
        }*/
        return (int) val;
    }

    public static void main(String[] args) {
        LC891 l = new LC891();
        int[] arr = new int[(int) 1e5];
        for (int i = 0; i < 1e5; i++) {
            arr[i] = (int) 1e5;
        }
        l.sumSubseqWidths(arr);
    }
}






