package com.leetcode.hard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class LC502 {


    class Data {
        int profit;
        int capital;

        public Data(int profit, int capital) {
            this.profit = profit;
            this.capital = capital;
        }
    }

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        List<Data> list = new ArrayList<>();
        for (int i = 0; i < profits.length; i++) {
            list.add(new Data(profits[i], capital[i]));
        }
        list.sort(Comparator.comparingInt(o -> o.capital));
        PriorityQueue<Data> pq = new PriorityQueue<>((o1, o2) -> -Integer.compare(o1.profit, o2.profit));
        int currCapital = w, idx = 0;
        while (true) {
            while (idx < list.size() && currCapital >= list.get(idx).capital) {
                pq.add(list.get(idx++));
            }
            if (pq.isEmpty() || k == 0) {
                break;
            }
            currCapital += pq.poll().profit;
            k--;
        }
        return currCapital;
    }

    public static void main(String[] args) {
        LC502 l = new LC502();
        int[] profits = new int[]{1, 2, 3};
        int[] capital = new int[]{0, 1, 2};
        System.out.println(l.findMaximizedCapital(3, 0, profits, capital));
    }

}
