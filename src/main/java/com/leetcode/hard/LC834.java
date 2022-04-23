package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LC834 {

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        HashMap<Integer, Set<Integer>> tree = new HashMap<>();
        for (int[] edge : edges) {
            Set<Integer> set = tree.getOrDefault(edge[0], new HashSet<>());
            set.add(edge[1]);
            tree.put(edge[0], set);
            set = tree.getOrDefault(edge[1], new HashSet<>());
            set.add(edge[0]);
            tree.put(edge[1], set);
        }
        int[] arr = new int[n];
        int[] size = new int[n];
        int[] ret = process(0, -1, tree, arr, size);
        int[] ans = new int[n];
        ans[0] = ret[0];
        for (int child : tree.get(0)) {
            if (child != 0) {
                dfs(child, 0, tree, arr, size, ans);
            }
        }
        return ans;
    }

    private void dfs(int node, int parent, HashMap<Integer, Set<Integer>> tree, int[] subTreeAns, int[] size,
                     int[] ans) {
        int toRemove = ans[parent] - (subTreeAns[node] + size[node]);
        ans[node] = subTreeAns[node] + (toRemove + (ans.length - size[node]));
        for (int child : tree.get(node)) {
            if (child != parent) {
                dfs(child, node, tree, subTreeAns, size, ans);
            }
        }
    }

    private int[] process(int node, int parent, HashMap<Integer, Set<Integer>> tree, int[] arr, int[] size) {
        int numNodes = 1;
        int first = 0;
        for (int child : tree.getOrDefault(node, new HashSet<>())) {
            if (parent != child) {
                int[] childAns = process(child, node, tree, arr, size);
                numNodes += childAns[1];
                first += (childAns[1] + childAns[0]);
            }
        }
        size[node] = numNodes;
        arr[node] = first;
        return new int[]{first, numNodes};
    }

    public static void main(String[] args) {
        LC834 l = new LC834();
        int[][] arr = Utils.convertToTwoDIntArray("[[0,1],[0,2],[2,3],[2,4],[2,5]]");
        int[] ans = l.sumOfDistancesInTree(6, arr);
        System.out.println(Arrays.toString(ans));
    }


}
