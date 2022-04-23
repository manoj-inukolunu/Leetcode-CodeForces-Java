package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC2065 {
    private int ans = Integer.MIN_VALUE;


    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        HashMap<Integer, Set<Integer[]>> graph = new HashMap<>();
        if (edges.length == 0) {
            return values[0];
        }
        for (int[] edge : edges) {
            Set<Integer[]> set = graph.getOrDefault(edge[0], new HashSet<>());
            set.add(new Integer[]{edge[1], edge[2]});
            graph.put(edge[0], set);
            set = graph.getOrDefault(edge[1], new HashSet<>());
            set.add(new Integer[]{edge[0], edge[2]});
            graph.put(edge[1], set);
        }
        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs(graph, values, 0, path, 0, maxTime);
        int newAns = ans == Integer.MIN_VALUE ? values[0] : ans;
        ans = 0;
        return newAns;
    }

    private void dfs(HashMap<Integer, Set<Integer[]>> graph, int[] values, int node, List<Integer> path, int time,
                     int maxTime) {
        if (time > maxTime) {
            return;
        }
        if (node == 0 && time != 0) {
            int s = 0;
            Set<Integer> unq = new HashSet<>(path);
            for (int num : unq) {
                s += values[num];
            }
            ans = Math.max(ans, s);
        }

        for (Integer[] child : graph.getOrDefault(node, new HashSet<>())) {
            path.add(child[0]);
            dfs(graph, values, child[0], path, time + child[1], maxTime);
            path.remove(path.size() - 1);
        }

    }

    public static void main(String[] args) {
        int[] values = new int[]{1, 2, 3, 4};
        int[][] edges = Utils.convertToTwoDIntArray("[[0,1,10],[1,2,11],[2,3,12],[1,3,13]]");
        LC2065 l = new LC2065();
        System.out.println(l.maximalPathQuality(values, edges, 50));
    }
}






