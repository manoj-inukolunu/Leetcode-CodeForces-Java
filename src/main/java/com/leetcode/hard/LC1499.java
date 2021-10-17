package com.leetcode.hard;

import java.util.Deque;
import java.util.LinkedList;

public class LC1499 {

    public int findMaxValueOfEquation(int[][] points, int k) {
        int max = Integer.MIN_VALUE;
        Deque<int[]> deque = new LinkedList<>();
        deque.add(new int[]{points[0][1] - points[0][0], points[0][0]});
        for (int[] point : points) {
            int curr = point[0] + point[1];
            while (!deque.isEmpty() && point[0] - deque.peekFirst()[1] > k) {
                deque.pollFirst();
            }
            if (!deque.isEmpty()) {
                int maxOnQueue = deque.peekFirst()[0];
                max = Math.max(max, curr + maxOnQueue);
                int toAdd = point[1] - point[0];
                while (!deque.isEmpty() && toAdd > deque.peekLast()[0]) {
                    deque.pollLast();
                }
                deque.addLast(new int[]{toAdd, point[0]});
            }
        }
        return max;
    }
}
