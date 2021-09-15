package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class LC1851 {

    class Pair {
        int num;
        int idx;

        public Pair(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
    }

    public int[] minInterval(int[][] intervals, int[] queries) {
        int[] ans = new int[queries.length];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1] - o[0]));
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            list.add(new Pair(queries[i], i));
        }
        Arrays.sort(intervals, Comparator.comparingInt(value -> value[0]));
        list.sort(Comparator.comparingInt(value -> value.num));
        int idx = 0;
        for (Pair pair : list) {
            while (idx < intervals.length && intervals[idx][0] <= pair.num) {
                pq.add(intervals[idx]);
                idx++;
            }
            while (!pq.isEmpty() && pq.peek()[1] < pair.num) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
                int[] data = pq.peek();
                ans[pair.idx] = data[1] - data[0] + 1;
            } else {
                ans[pair.idx] = -1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] intervals = Utils.convertToTwoDIntArray("[[2,3],[2,5],[1,8],[20,25]]");
        int[] queries = new int[]{2, 19, 5, 22};
        LC1851 l = new LC1851();
        System.out.println(Arrays.toString(l.minInterval(intervals, queries)));
    }
}
