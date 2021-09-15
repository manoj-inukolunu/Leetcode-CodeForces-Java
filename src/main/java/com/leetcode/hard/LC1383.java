package com.leetcode.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class LC1383 {

    class Pair {
        int speed;
        int efficiency;

        public Pair(int speed, int efficiency) {
            this.speed = speed;
            this.efficiency = efficiency;
        }
    }


    int mod = (int) Math.pow(10, 9) + 7;


    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < speed.length; i++) {
            list.add(new Pair(speed[i], efficiency[i]));
        }
        list.sort((o1, o2) -> -Integer.compare(o1.efficiency, o2.efficiency));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long score = 0, totalSpeed = 0;
        for (int i = 0; i < speed.length; i++) {
            if (pq.size() < k) {
                totalSpeed += speed[i];
                score = Math.max(score, totalSpeed * efficiency[i]);
                pq.add(speed[i]);
            } else {
                totalSpeed -= pq.poll();
                totalSpeed += speed[i];
                score = Math.max(score, totalSpeed * efficiency[i]);
                pq.add(speed[i]);
            }
        }
        return (int) score % mod;
    }


    public static void main(String[] args) {
        int[] speed = new int[]{10, 5, 1, 7, 4, 2};
        int[] efficiency = new int[]{2, 1, 1, 1, 7, 3};
        LC1383 l = new LC1383();
        System.out.println(l.maxPerformance(6, speed, efficiency, 6));

    }
}
