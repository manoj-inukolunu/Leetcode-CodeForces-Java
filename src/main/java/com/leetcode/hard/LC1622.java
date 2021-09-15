package com.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

public class LC1622 {
    static class Fancy {

        int mod = (int) Math.pow(10, 9) + 7;

        List<int[]> pairs = new ArrayList<>();
        List<List<Integer>> buckets = new ArrayList<>();
        int bucketSize = 225;
        int total = 0;

        long mulMod(long a, long b) {
            return ((a % mod) * (b % mod)) % mod;
        }

        long addMod(long a, long b) {
            return (a % mod + b % mod) % mod;
        }

        public Fancy() {
        }

        public void append(int val) {
            total++;
            if (buckets.isEmpty()) {
                addNewBucket(val);
            } else if (buckets.get(buckets.size() - 1).size() == bucketSize) {
                addNewBucket(val);
            } else {
                List<Integer> lastBucket = buckets.get(buckets.size() - 1);
                int[] lastMultiplier = pairs.get(pairs.size() - 1);
                for (int i = 0; i < lastBucket.size(); i++) {
                    long newVal = addMod(mulMod(lastBucket.get(i), lastMultiplier[0]), lastMultiplier[1]);
                    lastBucket.set(i, (int) (newVal % mod));
                }
                buckets.get(buckets.size() - 1).add(val);
                pairs.remove(pairs.size() - 1);
                pairs.add(new int[]{1, 0});
            }
        }

        private void addNewBucket(int val) {
            List<Integer> list = new ArrayList<>();
            list.add(val);
            buckets.add(list);
            pairs.add(new int[]{1, 0});
        }

        public void addAll(int inc) {
            for (int i = 0; i < pairs.size(); i++) {
                pairs.get(i)[1] = (int) addMod(pairs.get(i)[1], inc);
            }
        }

        public void multAll(int m) {
            for (int i = 0; i < pairs.size(); i++) {
                pairs.get(i)[0] = (int) mulMod(pairs.get(i)[0], m);
                pairs.get(i)[1] = (int) mulMod(pairs.get(i)[1], m);
            }
        }

        public int getIndex(int idx) {
            if (idx >= total) {
                return -1;
            }
            int bucket = idx / bucketSize;
            int offset = idx % bucketSize;
            return (int) addMod(mulMod(buckets.get(bucket).get(offset), pairs.get(bucket)[0]), pairs.get(bucket)[1]);
        }
    }

    public static void main(String[] args) {
        Fancy f = new Fancy();
        f.append(2);
        f.addAll(3);
        f.append(7);
        f.multAll(2);
        System.out.println(f.getIndex(0));
        f.addAll(3);
        f.append(10);
        f.multAll(2);
        System.out.println(f.getIndex(0));
        System.out.println(f.getIndex(1));
        System.out.println(f.getIndex(2));

    }
}
