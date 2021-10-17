package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LC1293 {

    private boolean inside(int row, int col, int[][] grid) {
        return row >= 0 && col >= 0 && row < grid.length && col < grid[row].length;
    }

    public int shortestPath(int[][] grid, int k) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 0, 0});
        boolean[][][] vis = new boolean[grid.length][][];
        for (int i = 0; i < grid.length; i++) {
            vis[i] = new boolean[grid[i].length][k + 1];
            for (boolean[] v : vis[i]) {
                Arrays.fill(v, false);
            }
        }
        int min = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int[] data = queue.poll();
            if (data[0] == grid.length - 1 && data[1] == grid[grid.length - 1].length - 1) {
                min = Math.min(min, data[3]);
            } else if (!vis[data[0]][data[1]][data[2]]) {
                vis[data[0]][data[1]][data[2]] = true;
                int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
                for (int[] dir : dirs) {
                    int nextR = dir[0] + data[0];
                    int nextC = dir[1] + data[1];
                    if (inside(nextR, nextC, grid)) {
                        if (grid[nextR][nextC] == 0) {
                            queue.add(new int[]{nextR, nextC, data[2], data[3] + 1});
                        } else if (data[2] < k) {
                            queue.add(new int[]{nextR, nextC, data[2] + 1, data[3] + 1});
                        }
                    }
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static void main(String args[]) {
        LC1293 l = new LC1293();
        int[][] grid = Utils.convertToTwoDIntArray("[[0,1,1],\n" +
                " [1,1,1],\n" +
                " [1,0,0]]");
        int[][] arr = Utils.convertToTwoDIntArray("[[0,0],[1,0],[1,0],[1,0],[1,0],[1,0],[0,0],[0,1],[0,1],[0,1],[0," +
                "0],[1,0],[1,0],[0,0]]");
        for (int[] row : arr) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println(l.shortestPath(arr, 4));
    }
}
