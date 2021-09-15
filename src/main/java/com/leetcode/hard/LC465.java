package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class LC465 {

    HashMap<String, Integer> seen = new HashMap<>();

    public int minTransfers(int[][] transactions) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] transaction : transactions) {
            map.put(transaction[0], map.getOrDefault(transaction[0], 0) - transaction[2]);
            map.put(transaction[1], map.getOrDefault(transaction[1], 0) + transaction[2]);
        }
        ConcurrentHashMap<Integer, Integer> pos = new ConcurrentHashMap<>();
        ConcurrentHashMap<Integer, Integer> neg = new ConcurrentHashMap<>();
        for (int key : map.keySet()) {
            if (map.get(key) > 0) {
                pos.put(key, map.get(key));
            } else if (map.get(key) < 0) {
                neg.put(key, -map.get(key));
            }
        }
        int min = Integer.MAX_VALUE;
        for (int key : pos.keySet()) {
            min = Math.min(solve(pos, neg, key), min);
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    private int solve(ConcurrentHashMap<Integer, Integer> pos, ConcurrentHashMap<Integer, Integer> neg, int curr) {
        if (pos.isEmpty()) {
            return 0;
        }
        String dupKey = pos + neg.toString();
        if (seen.containsKey(dupKey)) {
            return seen.get(dupKey);
        }

        int minTransactions = Integer.MAX_VALUE / 2;
        if (!pos.containsKey(curr)) {
            for (int key : pos.keySet()) {
                minTransactions = Math.min(solve(pos, neg, key), minTransactions);
            }
        } else {
            int currMoney = pos.get(curr);
            for (int key : neg.keySet()) {
                if (currMoney > neg.get(key)) {
                    pos.put(curr, currMoney - neg.get(key));
                    int negKeyVal = neg.get(key);
                    neg.remove(key);
                    minTransactions = Math.min(minTransactions, 1 + solve(pos, neg, curr));
                    pos.put(curr, currMoney);
                    neg.put(key, negKeyVal);
                } else if (currMoney < neg.get(key)) {
                    pos.remove(curr);
                    int negKeyVal = neg.get(key);
                    neg.put(key, neg.get(key) - currMoney);
                    minTransactions = Math.min(minTransactions, 1 + solve(pos, neg, curr));
                    pos.put(curr, currMoney);
                    neg.put(key, negKeyVal);
                } else {
                    pos.remove(curr);
                    neg.remove(key);
                    minTransactions = Math.min(minTransactions, 1 + solve(pos, neg, curr));
                    pos.put(curr, currMoney);
                    neg.put(key, currMoney);
                }
            }
        }
        seen.put(dupKey, minTransactions);
        return minTransactions;
    }

    public static void main(String[] args) {
        int[][] transactions = Utils.convertToTwoDIntArray("[[0,1,10],[2,0,5]]");
        int[][] t = Utils.convertToTwoDIntArray("[[0,1,10],[1,0,1],[1,2,5],[2,0,5]]");
        int[][] t1 = Utils.convertToTwoDIntArray("[[1,8,1],[1,13,21],[2,8,10],[3,9,20],[4,10,61],[5,11,61],[6,12,59]," +
                "[7,13,60]]");
        LC465 l = new LC465();
        System.out.println(l.minTransfers(t1));
    }


}
