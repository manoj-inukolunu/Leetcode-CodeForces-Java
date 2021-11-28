package com.leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class LC1548 {

    class Node {
        String name;
        int idx;

        public Node(String name, int idx) {
            this.name = name;
            this.idx = idx;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return idx == node.idx && Objects.equals(name, node.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, idx);
        }
    }

    List<Integer> ans = null;

    HashMap<Node, List<Node>> graph = new HashMap<>();

    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        for (int[] road : roads) {
            List<Node> list = graph.getOrDefault(new Node(names[road[0]], road[0]), new ArrayList<>());
            list.add(new Node(names[road[1]], road[1]));
            graph.put(new Node(names[road[0]], road[0]), list);

            list = graph.getOrDefault(new Node(names[road[1]], road[1]), new ArrayList<>());
            list.add(new Node(names[road[0]], road[0]));
            graph.put(new Node(names[road[1]], road[1]), list);
        }
        int min = Integer.MAX_VALUE;
        Integer[][] dp = new Integer[n][targetPath.length];
        int startNode = -1;
        for (int i = 0; i < names.length; i++) {
            int val = solve(i, 0, names, targetPath, dp);
            if (val < min) {
                min = val;
                startNode = i;
            }
        }
        getPath(startNode, 0, new ArrayList<>(), targetPath, dp, names);
        return ans;
    }

    private void getPath(int currIdx, int targetIdx, List<Integer> path, String[] targetPath, Integer[][] dp,
                         String[] names) {
        if (ans == null) {
            if (targetIdx >= targetPath.length) {
                ans = new ArrayList<>(path);
                return;
            }
            int minNode = -1, minDist = Integer.MAX_VALUE;
            for (Node next : graph.get(new Node(names[currIdx], currIdx))) {
                if (next.idx != currIdx && ((next.name.equals(targetPath[targetIdx])) ? 0 : 1) + dp[next.idx][targetIdx + 1] < minDist) {
                    minDist = ((next.name.equals(targetPath[targetIdx])) ? 0 : 1) + dp[next.idx][targetIdx + 1];
                    minNode = next.idx;
                }
            }
            path.add(minNode);
            getPath(minNode, targetIdx + 1, path, targetPath, dp, names);
        }
    }

    private int solve(int currIdx, int targetIdx, String[] names, String[] targetPath, Integer[][] dp) {
        if (targetIdx >= targetPath.length) {
            return 0;
        }
        if (dp[currIdx][targetIdx] != null) {
            return dp[currIdx][targetIdx];
        }
        int best = Integer.MAX_VALUE;
        for (Node next : graph.get(new Node(names[currIdx], currIdx))) {
            if (next.idx != currIdx) {
                best = Math.min(best, ((next.name.equals(targetPath[targetIdx])) ? 0 : 1) + solve(next.idx,
                        targetIdx + 1, names, targetPath, dp));
            }
        }
        return dp[currIdx][targetIdx] = best;
    }


}
