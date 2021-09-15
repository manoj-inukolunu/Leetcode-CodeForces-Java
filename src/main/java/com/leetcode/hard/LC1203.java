package com.leetcode.hard;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class LC1203 {


    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        HashMap<Integer, List<Integer>> groupItems = new HashMap<>();
        for (int i = 0; i < group.length; i++) {
            if (group[i] == -1) {
                group[i] = m++;
            }
            List<Integer> list = groupItems.getOrDefault(group[i], new ArrayList<>());
            list.add(i);
            groupItems.put(group[i], list);
        }
        HashMap<Integer, List<Integer>> itemGraph = new HashMap<>();
        int[] itemInDegree = new int[n];
        for (int i = 0; i < beforeItems.size(); i++) {
            for (int num : beforeItems.get(i)) {
                if (group[num] == group[i]) {
                    List<Integer> list = itemGraph.getOrDefault(num, new ArrayList<>());
                    list.add(i);
                    itemGraph.put(num, list);
                    itemInDegree[i]++;
                }
            }
        }
        HashMap<Integer, List<Integer>> sortedGroupItems = new HashMap<>();
        for (int groupId : groupItems.keySet()) {
            List<Integer> sortedItems = topSort(itemGraph, itemInDegree);
            if (sortedItems.size() != groupItems.get(groupId).size()) {
                return new int[]{};
            }
            sortedGroupItems.put(groupId, sortedItems);
        }
        HashMap<Integer, List<Integer>> groupGraph = new HashMap<>();
        int[] groupInDegree = new int[n];
        for (int i = 0; i < beforeItems.size(); i++) {
            for (int num : beforeItems.get(i)) {
                if (group[num] != group[i]) {
                    List<Integer> list = groupGraph.getOrDefault(group[num], new ArrayList<>());
                    list.add(i);
                    groupGraph.put(group[num], list);
                    groupInDegree[group[i]]++;
                }
            }
        }
        List<Integer> sortedGroups = topSort(groupGraph, groupInDegree);
        Set<Integer> groups = new HashSet<>();
        for (int num : group) {
            groups.add(num);
        }
        if (groups.size() != sortedGroups.size()) {
            return new int[]{};
        }
        List<Integer> ans = new ArrayList<>();
        for (int groupId : sortedGroups) {
            ans.addAll(sortedGroupItems.get(groupId));
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }

    private List<Integer> topSort(HashMap<Integer, List<Integer>> graph, int[] indegree) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            result.add(curr);
            for (int child : graph.get(curr)) {
                indegree[child]--;
                if (indegree[child] == 0) {
                    queue.add(child);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LC1203 l = new LC1203();
        int[] group = new int[]{-1, -1, 1, 0, 0, 1, 0, -1};
        List<List<Integer>> beforeItems = Lists.newArrayList(
                Lists.newArrayList(),
                Lists.newArrayList(6),
                Lists.newArrayList(5),
                Lists.newArrayList(6),
                Lists.newArrayList(3, 6),
                Lists.newArrayList(),
                Lists.newArrayList(),
                Lists.newArrayList());
        int[] ret = l.sortItems(8, 2, group, beforeItems);

        System.out.println(Arrays.toString(ret));

    }
}
