package com.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class LC1982 {
    public int[] recoverArray(int n, int[] sums) {
        Arrays.sort(sums);
        List<Integer> s = new ArrayList<>();
        for (int num : sums) {
            s.add(num);
        }
        List<Integer> ans = dfs(s);
        return ans.stream().mapToInt(i -> i).toArray();
    }

    public List<Integer> dfs(List<Integer> sums) {
        if (!sums.contains(0)) {
            return new ArrayList<>();
        }
        int n = sums.size();
        if (n == 2) {
            return sums.subList(1, sums.size());
        }
        int x = sums.get(sums.size() - 1) - sums.get(sums.size() - 2);
        //pos x
        List<Integer> newSums1 = new ArrayList<>();
        HashMap<Integer, Integer> count1 = counter(sums);
        for (int i = sums.size() - 1; i >= 0; i--) {
            int a = sums.get(i);
            int b = a - x;
            if (count1.containsKey(a) && count1.get(a) > 0) {
                if (count1.containsKey(b) && count1.get(b) > 0) {
                    newSums1.add(b);
                    count1.put(a, count1.get(a) - 1);
                    count1.put(b, count1.get(b) - 1);
                } else {
                    break;
                }
            }
        }
        List<Integer> ans1 = new ArrayList<>();
        if (newSums1.size() == sums.size() / 2) {
            Collections.reverse(newSums1);
            ans1 = dfs(newSums1);
            ans1.add(0, x);
        }
        //neg x

        List<Integer> newSums2 = new ArrayList<>();
        HashMap<Integer, Integer> count2 = counter(sums);
        for (int i = 0; i < sums.size(); i++) {
            int a = sums.get(i);
            int b = a + x;
            if (count2.containsKey(a) && count2.get(a) > 0) {
                if (count2.containsKey(b) && count2.get(b) > 0) {
                    newSums2.add(b);
                    count2.put(a, count2.get(a) - 1);
                    count2.put(b, count2.get(b) - 1);
                } else {
                    break;
                }
            }
        }
        List<Integer> ans2 = new ArrayList<>();
        if (newSums2.size() == sums.size() / 2) {
            ans2 = dfs(newSums2);
            ans2.add(0, x);
        }
        return ans1.size() > ans2.size() ? ans1 : ans2;
    }

    HashMap<Integer, Integer> counter(List<Integer> list) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : list) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return map;
    }

    public static void main(String[] args) {

        LC1982 l = new LC1982();
        System.out.println(Arrays.toString(l.recoverArray(3, new int[]{-3, -2, -1, 0, 0, 1, 2, 3})));
        int [] arr = new int[(int) 1e5];
        System.out.println(1e5);
    }
}






