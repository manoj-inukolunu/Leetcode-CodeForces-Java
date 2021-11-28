package com.leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC1655 {
    public boolean canDistribute(int[] nums, int[] quantity) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int[] arr = new int[(1 << quantity.length)];
        for (int i = 0; i < (1 << quantity.length); i++) {
            for (int j = 0; j < quantity.length; j++) {
                if (((1 << j) & i) > 0) {
                    arr[i] += quantity[j];
                }
            }

        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        Boolean[][] dp = new Boolean[list.size()][(1 << quantity.length + 1)];
        return possible(0, 0, list, quantity, dp, arr);
    }

    private boolean possible(int idx, int personState, List<Map.Entry<Integer, Integer>> freq, int[] quantity,
                             Boolean[][] dp, int[] arr) {
        if (idx >= freq.size() || (personState == (1 << quantity.length) - 1)) {
            return personState == ((1 << quantity.length) - 1);
        }
        if (dp[idx][personState] != null) {
            return dp[idx][personState];
        }
        Map.Entry<Integer, Integer> entry = freq.get(idx);
        int curr = personState;
        while (curr > 0) {
            int val = arr[curr];
            if (val <= entry.getValue() && possible(idx + 1, personState | curr, freq, quantity, dp, arr)) {
                dp[idx][personState | curr] = true;
                return true;
            }
            curr = (curr - 1) & personState;
        }
        return dp[idx][personState] = possible(idx + 1, personState, freq, quantity, dp, arr);
    }

    public static void main(String[] args) {
        /*LC1655 l = new LC1655();
        int[] nums = new int[]{1, 1, 2, 2, 1};
        int[] quantity = new int[]{2, 3};
        System.out.println(l.canDistribute(nums, quantity));*/
        int num = Integer.valueOf("1101", 2);
        int s = num;
        while (s > 0) {
            System.out.println(Integer.toBinaryString(s));
            s = (s - 1) & num;
        }

    }
}
