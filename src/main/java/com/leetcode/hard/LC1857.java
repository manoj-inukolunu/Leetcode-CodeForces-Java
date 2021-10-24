package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC1857 {


    HashMap<Integer, List<Integer>> graph = new HashMap<>();

    public int largestPathValue(String colors, int[][] edges) {
        int[] incoming = new int[colors.length()];
        for (int[] edge : edges) {
            incoming[edge[1]]++;
        }
        for (int[] edge : edges) {
            List<Integer> list = graph.getOrDefault(edge[0], new ArrayList<>());
            list.add(edge[1]);
            graph.put(edge[0], list);
        }
        if (cycle(incoming, graph)) {
            return -1;
        }
        int[][] dp = new int[colors.length()][27];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        for (int[] edge : edges) {
            incoming[edge[1]]++;
        }
        int max = 0;
        for (int i = 0; i < incoming.length; i++) {
            if (incoming[i] == 0) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    max = Math.max(max, dfs(ch, i, dp, colors));
                }
            }
        }
        return max;
    }

    private int dfs(char ch, int node, int[][] dp, String colors) {
        if (dp[node][ch - 'a'] != -1) {
            return dp[node][ch - 'a'];
        }
        int curr = colors.charAt(node) == ch ? 1 : 0;
        if (graph.containsKey(node)) {
            for (int child : graph.get(node)) {
                curr += dfs(ch, child, dp, colors);
            }
        }
        return dp[node][ch - 'a'] = curr;
    }

    private boolean cycle(int[] incoming, HashMap<Integer, List<Integer>> graph) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < incoming.length; i++) {
            if (incoming[i] == 0) {
                queue.add(i);
            }
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            count++;
            if (graph.containsKey(curr)) {
                for (int child : graph.get(curr)) {
                    incoming[child]--;
                    if (incoming[child] == 0) {
                        queue.add(child);
                    }
                }
            }
        }
        return count != incoming.length;
    }

    public static void main(String[] args) {
        LC1857 l = new LC1857();
        int[][] edges = Utils.convertToTwoDIntArray("[[0,1],[0,2],[2,3],[3,4]]");
        System.out.println(l.largestPathValue("abaca", edges));
    }
}
