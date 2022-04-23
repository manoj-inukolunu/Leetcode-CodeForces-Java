package com.leetcode.hard;


import com.leetcode.common.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LC2097 {
    public int[][] validArrangement(int[][] pairs) {
        HashMap<Integer, LinkedList<Integer>> g = new HashMap<>();
        HashMap<Integer, Integer> degree = new HashMap<>();
        for (int[] pair : pairs) {
            LinkedList<Integer> l = g.getOrDefault(pair[0], new LinkedList<>());
            l.add(pair[1]);
            g.put(pair[0], l);
            degree.put(pair[0], degree.getOrDefault(pair[0], 0) + 1);
            degree.put(pair[1], degree.getOrDefault(pair[1], 0) - 1);
        }
        int startNode = -1;
        for (int start : g.keySet()) {
            if (degree.getOrDefault(start, 0) == 1) {
                startNode = start;
                break;
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        if (startNode != -1) {
            dfs(startNode, g, ans);
        } else {
            dfs(1, g, ans);
        }
        int[][] ret = new int[ans.size()][2];
        int i = 0;
        for (List<Integer> l : ans) {
            ret[i][0] = l.get(0);
            ret[i][1] = l.get(1);
            i++;
        }
        return ret;
    }

    private void dfs(int start, HashMap<Integer, LinkedList<Integer>> graph, List<List<Integer>> ans) {
        while (graph.containsKey(start)) {
            Integer child = graph.get(start).pop();
            if (graph.get(start).size() == 0) {
                graph.remove(start);
            }
            ans.add(Arrays.asList(start, child));
            dfs(child, graph, ans);

        }
    }

    public static void main(String[] args) {
        LC2097 l = new LC2097();
        int[][] arr = Utils.convertToTwoDIntArray("[[1,3],[3,2],[2,1]]");
        System.out.println(Arrays.deepToString(l.validArrangement(arr)));
    }
}






