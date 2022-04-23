package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class LC1889 {
    public int minWastedSpace(int[] packages, int[][] boxes) {
        Set<int[]> set = new HashSet<>();
        int mod = (int) Math.pow(10, 9) + 7;
        Arrays.sort(packages);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < packages.length; i++) {
            map.put(packages[i], i);
        }
        for (int[] b : boxes) {
            Arrays.sort(b);
            if (b[b.length - 1] >= packages[packages.length - 1]) {
                set.add(b);
            }
        }
        long[] pre = new long[packages.length];
        pre[0] = packages[0];
        for (int i = 1; i < packages.length; i++) {
            pre[i] = pre[i - 1] + packages[i];
        }
        BigInteger min = BigInteger.valueOf(Long.MAX_VALUE).add(BigInteger.valueOf(Long.MAX_VALUE));
        for (int[] box : set) {
            BigInteger val = BigInteger.ZERO;
            int prev = 0;
            boolean valid = true;
            for (int i = 0; i < box.length; i++) {
                Integer floor = map.floorKey(box[i]);
                if (floor == null) {
                    continue;
                }
                int j = map.get(floor);
                long size = getSum(pre, prev, j);
                BigInteger t = BigInteger.valueOf(box[i]).multiply(BigInteger.valueOf(j - prev + 1));
                t = t.subtract(BigInteger.valueOf(size));
                val = val.add(t);
                val = val.mod(BigInteger.valueOf(mod));
                prev = j + 1;

            }
            if (val.compareTo(min) < 0) {
                min = val;
            }

        }
        if (min.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0) {
            return -1;
        }
        return min.mod(BigInteger.valueOf(mod)).intValue();
    }

    private long getSum(long[] pre, int start, int end) {
        if (start > 0) {
            return pre[end] - pre[start - 1];
        }
        return pre[end];
    }

    public static void main(String[] args) {
        int[] packages = new int[]{7, 6, 5, 3, 4};
        int[][] boxes = Utils.convertToTwoDIntArray("[[2,7],[6],[10,5]]");
        LC1889 l = new LC1889();
        System.out.println(l.minWastedSpace(packages, boxes));
    }
}






