package com.algorithms.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hierholzer {

    HashMap<Integer, Set<Integer>> graph = new HashMap<>();
    int[] in, out;
    int numEdges;
    List<Integer> path = new ArrayList<>();

    public Hierholzer(HashMap<Integer, Set<Integer>> graph) {
        this.graph = graph;
    }

    public List<Integer> findEulerianPath() {
        countInOutDegrees();
        if (graphHasEulerianPath()) {
            dfs(findStartNode());
            if (path.size() == numEdges + 1) {
                return path;
            }
        }
        return new ArrayList<>();
    }

    private Integer findStartNode() {
        int start = 0;
        for (int i = 0; i < graph.size(); i++) {
            if (out[i] - in[i] == 1) {
                return i;
            }
            // Avoids starting DFS at a node without edges
            if (out[i] > 0) {
                start = i;
            }
        }
        return start;
    }

    private void dfs(Integer node) {
        while (out[node] != 0) {
            for (Integer child : graph.getOrDefault(node, new HashSet<>())) {
                out[child]--;
                dfs(child);
            }
        }
        path.add(node, 0);
    }

    private boolean graphHasEulerianPath() {
        int startNodes = 0, endNodes = 0;
        for (int i = 0; i < graph.size(); i++) {
            if (out[i] - in[i] > 1 || in[i] - out[i] > 1) {
                return false;
            } else if (out[i] - in[i] == 1) {
                startNodes++;
            } else if (in[i] - out[i] == 1) {
                endNodes++;
            }
        }
        return (endNodes == 0 && startNodes == 0) || (endNodes == 1 && startNodes == 1);
    }

    private void countInOutDegrees() {
        in = new int[graph.size()];
        out = new int[graph.size()];
        for (int key : graph.keySet()) {
            out[key] += graph.get(key).size();
            numEdges += graph.get(key).size();
            for (int vertex : graph.get(key)) {
                in[vertex]++;
            }
        }
    }



    public static void main(String[] args) {
      /*  int[][] arr = Utils.convertToTwoDIntArray("[[5,1],[4,5],[11,9],[9,4]]");
        HashMap<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] edge : arr) {
            Set<Integer> set = new HashSet<>()
        }
        Hierholzer h = new Hierholzer();
*/
    }
}






