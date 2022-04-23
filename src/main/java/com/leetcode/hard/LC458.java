package com.leetcode.hard;

public class LC458 {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int pigs = 0;
        while (Math.pow(minutesToTest / minutesToDie + 1, pigs) < buckets) {
            pigs++;
        }
        return pigs;
    }



    public static void main(String[] args) {
        LC458 l = new LC458();
        System.out.println(l.poorPigs(1000, 15, 60));
    }
}






