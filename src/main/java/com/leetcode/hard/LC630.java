package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC630 {

    public int scheduleCourse(int[][] courses) {
        int count = 0;
        int day = 0;
        Arrays.sort(courses, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return Integer.compare(o1[0], o2[0]);
            }
            return Integer.compare(o1[1], o2[1]);
        });
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return -Integer.compare(o1[1], o2[1]);
            }
            return -Integer.compare(o1[0], o2[0]);
        });
        for (int i = 0; i < courses.length; i++) {
            int[] curr = courses[i];
            if (day + curr[0] <= curr[1]) {
                count++;
                day += curr[0];
                pq.add(curr);
            } else if (!pq.isEmpty()) {
                int[] courseWithMaxDuration = pq.peek();
                if (day - courseWithMaxDuration[0] + curr[0] <= curr[1] && (day - courseWithMaxDuration[0] + curr[0]) <= day) {
                    day -= courseWithMaxDuration[0];
                    day += curr[0];
                    pq.add(curr);
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        LC630 l = new LC630();
        int[][] arr = Utils.convertToTwoDIntArray("[[100,200],[200,1300],[1000,1250],[2000,3200]]");
        int[][] arr1 = Utils.convertToTwoDIntArray("[[1,2]]");
        int[][] arr2 = Utils.convertToTwoDIntArray("[[3,2],[4,3]]");
        int[][] arr3 = Utils.convertToTwoDIntArray("[[1,2],[2,3]]");
        int[][] arr4 = Utils.convertToTwoDIntArray("[[5,5],[4,6],[2,6]]");
        int[][] arr5 = Utils.convertToTwoDIntArray("[[5,15],[3,19],[6,7],[2,10],[5,16],[8,14],[10,11],[2,19]]");
        int[][] arr6 = Utils.convertToTwoDIntArray("[[7,17],[3,12],[10,20],[9,10],[5,20],[10,19],[4,18]]");
        System.out.println(l.scheduleCourse(arr6));
    }
}
