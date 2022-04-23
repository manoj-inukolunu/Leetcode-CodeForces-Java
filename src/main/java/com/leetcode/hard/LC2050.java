package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class LC2050 {
    public int minimumTime(int n, int[][] relations, int[] time) {
        int[] indegree = new int[n];
        HashMap<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] relation : relations) {
            indegree[relation[1] - 1]++;
            Set<Integer> set = graph.getOrDefault(relation[0] - 1, new HashSet<>());
            set.add(relation[1] - 1);
            graph.put(relation[0] - 1, set);
        }
        int totalTime = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        int[] times = new int[n];
        while (!queue.isEmpty()) {
            List<Integer> curr = new ArrayList<>();
            while (!queue.isEmpty()) {
                int course = queue.poll();
                curr.add(course);
                times[course] += time[course];
            }
            for (int course : curr) {
                Set<Integer> next = graph.getOrDefault(course, new HashSet<>());
                for (int nextCourse : next) {
                    indegree[nextCourse]--;
                    times[nextCourse] = Math.max(times[nextCourse], times[course]);
                    if (indegree[nextCourse] == 0) {
                        queue.add(nextCourse);
                    }
                }
            }
        }
        totalTime = Arrays.stream(times).max().getAsInt();
        return totalTime;
    }

    public static void main(String[] args) {
        LC2050 l = new LC2050();
        int[][] rel = Utils.convertToTwoDIntArray("[[2,7],[2,6],[3,6],[4,6],[7,6],[2,1],[3,1],[4,1],[6,1],[7,1],[3," +
                "8],[5,8],[7,8],[1,9],[2,9],[6,9],[7,9]]");
        System.out.println(l.minimumTime(9, rel, new int[]{9, 5, 9, 5, 8, 7, 7, 8, 4}));
    }
}






