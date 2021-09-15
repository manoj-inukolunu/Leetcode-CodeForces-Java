package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.util.Arrays;

public class LC913 {

    public int catMouseGame(int[][] graph) {
        boolean[][] visited = new boolean[2][graph.length + 1];
        for (boolean[] row : visited) {
            Arrays.fill(row, false);
        }
        return play(graph, 1, 2, 0, visited);
    }

    private int play(int[][] graph, int mouse, int cat, int turn, boolean[][] visited) {
        if (mouse == 0) {
            return 1;
        }
        if (turn == 0 && visited[turn][mouse]) {
            return 0;
        }
        if (cat == mouse) {
            return 2;
        }

        if (turn == 1 && visited[turn][cat]) {
            return 0;
        }
        if (turn == 0) {
            visited[turn][mouse] = true;
            int best = 2;
            for (int next : graph[mouse]) {
                if (next != mouse) {
                    int ret = play(graph, next, cat, 1, visited);
                    if (ret == 1) {
                        best = 1;
                    } else if (ret == 0 && best == 2) {
                        best = 0;
                    }
                }
            }
            return best;
        } else {
            visited[turn][cat] = true;
            int best = 1;
            for (int next : graph[cat]) {
                if (next != cat && next != 0) {
                    int ret = play(graph, mouse, next, 0, visited);
                    if (ret == 2) {
                        best = ret;
                    } else if (ret == 0 && best == 1) {
                        best = 0;
                    }
                }
            }
            return best;
        }
    }

    public static void main(String[] args) {
        int[][] graph = Utils.convertToTwoDIntArray("[[2,3],[3,4],[0,4],[0,1],[1,2]]");
        LC913 l = new LC913();
        System.out.println(l.catMouseGame(graph));
    }
}
