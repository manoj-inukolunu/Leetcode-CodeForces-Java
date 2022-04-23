package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LC1617 {
    int farthest = -1;
    int maxDist = -1;
    int pathMask = 0;

    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        HashMap<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            edge[0]--;
            edge[1]--;
            Set<Integer> set = graph.getOrDefault(edge[0], new HashSet<>());
            set.add(edge[1]);
            graph.put(edge[0], set);
            set = graph.getOrDefault(edge[1], new HashSet<>());
            set.add(edge[0]);
            graph.put(edge[1], set);
        }
        int[] arr = new int[n];
        for (int i = 1; i <= (1 << n); i++) {
            int aVertex = -1;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    aVertex = j;
                    break;
                }
            }
            if (aVertex != -1) {
                traverse(aVertex, graph, i, 0, 0);
                if (pathMask != -1 && (pathMask ^ i) == 0 && farthest != -1 && maxDist >= 1) {
                    //subtree is valid;
                    maxDist = -1;
                    pathMask = 0;
                    traverse(farthest, graph, i, 0, 0);
                    System.out.println(maxDist);
                    arr[maxDist]++;
                }
            }
            pathMask = 0;
            maxDist = -1;
            farthest = -1;
        }
        return Arrays.copyOfRange(arr, 1, arr.length);
    }

    private void traverse(int node, HashMap<Integer, Set<Integer>> graph, int subTree, int visited, int nodeDist) {
        pathMask |= (1 << node);
        if (nodeDist > maxDist) {
            maxDist = nodeDist;
            farthest = node;
        }
        if ((visited & (1 << node)) == 0) {
            visited |= (1 << node);
            for (int child : graph.get(node)) {
                if ((visited & (1 << child)) == 0 && ((subTree & (1 << child)) > 0)) {
                    traverse(child, graph, subTree, visited, nodeDist + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        LC1617 l = new LC1617();
        int[][] edges = Utils.convertToTwoDIntArray("[[1,2],[2,3],[2,4]]");
        int[] arr = l.countSubgraphsForEachDiameter(4, edges);
        System.out.println(Arrays.toString(arr));
    }

}






