/*
package com.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC2003 {

    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        HashMap<Integer, List<Integer>> tree = new HashMap<>();
        int idx = -1;
        for (int i = 0; i < parents.length; i++) {
            if (nums[i] == 1) {
                idx = 1;
            }
            int parent = parents[i];
            if (parent != -1) {
                List<Integer> list = tree.getOrDefault();
            } else {
                tree.put(-1, new ArrayList<>());
            }
        }

        int[] ans = new int[parents.length];
        Arrays.fill(ans, 1);
        if (idx == -1) {
            return ans;
        }
        int min = 1;
        for (int i = idx; i != -1; i = parents[i]) {
            Set<Integer> set = dfs(i, tree);
            for (int j = min; ; j++) {
                if (!set.contains(j)) {
                    ans[i] = j;
                    min = j;
                    break;
                }
            }
        }
        return ans;
    }

    private Set<Integer> dfs(int node, HashMap<Integer, List<Integer>> tree) {
        Set<Integer> set = new HashSet<>();
        set.add(node);
        if (tree.containsKey(node)) {
            for (int child : tree.get(node)) {
                set.addAll(dfs(child, tree));
            }
        }
        return set;
    }

    public static void main(String[] args) {
        LC2003 l = new LC2003();
        int[] parents = new int[]{-1, 0, 0, 2};
        int[] nums = new int[]{1, 2, 3, 4};
        int[] ans = l.smallestMissingValueSubtree(parents, nums);
        System.out.println(Arrays.toString(ans));
    }
}
*/
