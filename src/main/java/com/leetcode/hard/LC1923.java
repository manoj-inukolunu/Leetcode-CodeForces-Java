package com.leetcode.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC1923 {

    long mod = (long) (Math.pow(10, 11) + 3);

    long prime = 100003;

    private long mulMod(long a, long b, long mod) {
        return ((a % mod) * (b % mod)) % mod;
    }

    public int longestCommonSubpath(int n, int[][] paths) {
        int high = Integer.MAX_VALUE;
        for (int[] path : paths) {
            high = Math.min(path.length, high);
        }
        long[] pow = new long[high + 1];
        pow[0] = 1;
        for (int i = 1; i <= high; i++) {
            pow[i] = mulMod(pow[i - 1], prime, mod);
        }

        int ans = 0;
        int low = 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (possible(mid, paths, pow)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private boolean possible(int len, int[][] paths, long[] pow) {
        Map<Long, Long> map = new HashMap<>();
        for (int[] path : paths) {
            long hash = 0L;
            Set<Long> set = new HashSet<>();
            for (int i = 0; i < len; i++) {
                hash = (hash + mulMod(pow[len - i], (path[i] + 1), mod)) % mod;
            }
            set.add(hash);
            for (int i = len; i < path.length; i++) {
                long newHash = (hash - mulMod(pow[len], path[i - len] + 1, mod) + mod) % mod;
                newHash = mulMod(newHash, prime, mod);
                newHash = (newHash + mulMod(pow[1], path[i] + 1, mod)) % mod;
                hash = newHash;
                set.add(newHash);
            }
            for (long h : set) {
                map.put(h, map.getOrDefault(h, 0L) + 1l);
                if (map.get(h) == paths.length) {
                    return true;
                }
            }
        }

        return false;
    }

    private static long modmul(long b, long mod) {
        return (((long) 2 % mod) * (b % mod)) % mod;
    }

    public static void main(String[] args) {
        long[] modArr = new long[30000];
        modArr[modArr.length - 1] = 1;
        long mod = (int) (Math.pow(10, 9) + 7);
        Set<Long> set = new HashSet<>();
        set.add(1l);
        for (int i = modArr.length - 2; i >= 0; i--) {
            modArr[i] = modmul(modArr[i + 1], mod);
            set.add(modArr[i]);
        }

        System.out.println(set.size());
        System.out.println(Arrays.toString(modArr));
    }
}
