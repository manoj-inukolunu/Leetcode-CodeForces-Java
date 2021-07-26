package com.leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LC1483 {

    static class TreeAncestor {

        HashMap<Integer, List<Integer>> tree = new HashMap<>();
        int[][] binLift;

        public TreeAncestor(int n, int[] parent) {
            for (int i = 0; i < n; i++) {
                int par = parent[i];
                List<Integer> list = tree.getOrDefault(par, new ArrayList<>());
                list.add(i);
                tree.put(par, list);
            }
            tree.remove(-1);
            binLift = new int[n][20];
            binaryLift(0, -1);
        }


        void binaryLift(int node, int parent) {
            binLift[node][0] = parent;
            for (int i = 1; i < 20; i++) {
                if (binLift[node][i - 1] != -1) {
                    binLift[node][i] = binLift[binLift[node][i - 1]][i - 1];
                } else {
                    binLift[node][i] = -1;
                }
            }
            for (int child : tree.getOrDefault(node, new ArrayList<>())) {
                if (child != parent) {
                    binaryLift(child, node);
                }
            }
        }

        public int getKthAncestor(int node, int k) {
            if (node == -1 || k == 0) {
                return node;
            }
            for (int i = 19; i >= 0; i--) {
                if (k >= (1 << i)) {
                    return getKthAncestor(binLift[node][i], k - (1 << i));
                }
            }
            return -1;
        }
    }

    public static void main(String args[]) {
        TreeAncestor t = new TreeAncestor(7, new int[]{-1, 0, 0, 1, 1, 2, 2});

        System.out.println(t.getKthAncestor(5, 2));
    }
}
