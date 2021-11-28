package com.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC1994 {

    int mod = (int) Math.pow(10, 9) + 7;

    List<Integer> primes = Arrays.asList(1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
    Set<Integer> valid = new HashSet<>(Arrays.asList(1, 2, 3, 5, 6, 7, 10, 11, 13, 14, 15, 17, 19, 21, 22, 23, 26,
            29, 30));

    public int numberOfGoodSubsets(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (valid.contains(num)) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }
        HashMap<Integer, Integer> primeMap = new HashMap<>();
        for (int i = 0; i < primes.size(); i++) {
            primeMap.put(primes.get(i), i);
        }
        long pow2Mod = 1;
        for (int i = 0; i < map.getOrDefault(1, 0); i++) {
            pow2Mod *= 2;
            pow2Mod %= mod;
        }
        HashMap<Integer, Set<Integer>> factors = new HashMap<>();
        factors.put(6, new HashSet<>(Arrays.asList(2, 3)));
        factors.put(10, new HashSet<>(Arrays.asList(2, 5)));
        factors.put(14, new HashSet<>(Arrays.asList(2, 7)));
        factors.put(15, new HashSet<>(Arrays.asList(5, 3)));
        factors.put(21, new HashSet<>(Arrays.asList(7, 3)));
        factors.put(22, new HashSet<>(Arrays.asList(2, 11)));
        factors.put(26, new HashSet<>(Arrays.asList(2, 13)));
        factors.put(30, new HashSet<>(Arrays.asList(2, 5, 3)));
        List<Integer> list = new ArrayList<>(map.keySet());
        list.remove(Integer.valueOf(1));
        return (int) (solve(list, 0, 0, map, factors, primeMap) * pow2Mod);
    }

    private int solve(List<Integer> list, int idx, int state, HashMap<Integer, Integer> freq, HashMap<Integer,
            Set<Integer>> factors, HashMap<Integer, Integer> primeMap) {
        if (idx >= list.size()) {
            return (state == 0 || state == 1) ? 0 : 1;
        }
        long count = 0;
        int curr = list.get(idx);
        Set<Integer> factorsFor = factors.get(curr);
        if (factorsFor == null) {
            //prime num
            if ((state & (1 << primeMap.get(curr))) == 0) {
                count += ((long) freq.get(curr) * solve(list, idx + 1, state | (1 << primeMap.get(curr)), freq, factors,
                        primeMap));
                count %= mod;
            }
            count += solve(list, idx + 1, state, freq, factors, primeMap);
            count %= mod;
        } else {
            boolean canAdd = true;
            int newState = state;
            for (int factor : factorsFor) {
                if ((state & (1 << primeMap.get(factor))) != 0) {
                    canAdd = false;
                    break;
                }
                newState |= (1 << primeMap.get(factor));
            }
            if (canAdd) {
                count += ((long) freq.get(curr) * solve(list, idx + 1, newState, freq, factors, primeMap));
                count %= mod;
            }
            //Not adding this number
            count += solve(list, idx + 1, state, freq, factors, primeMap);
            count %= mod;
        }
        return (int) (count % mod);
    }

    public static void main(String[] args) {
        LC1994 l = new LC1994();
        System.out.println(l.numberOfGoodSubsets(new int[]{1, 1, 2, 3, 4}));
    }
}
