package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.util.LinkedList;
import java.util.Queue;

public class LC1970 {

    public int latestDayToCross(int row, int col, int[][] cells) {
        int low = 0, high = cells.length - 1, ans = -1;
        while (low <= high) {
            int day = low + (high - low) / 2;
            if (canCross(day, row, col, cells)) {
                ans = day;
                low = day + 1;
            } else {
                high = day - 1;
            }
        }
        return ans + 1;
    }

    private boolean canCross(int day, int rows, int cols, int[][] cells) {
        int[][] arr = new int[rows][cols];
        for (int i = 0; i <= day; i++) {
            arr[cells[i][0] - 1][cells[i][1] - 1] = 1;
        }
        boolean[][] visited = new boolean[rows][cols];
        Queue<Integer[]> queue = new LinkedList<>();
        for (int col = 0; col < cols; col++) {
            if (arr[0][col] == 0) {
                queue.add(new Integer[]{0, col});
            }
        }
        while (!queue.isEmpty()) {
            Integer[] curr = queue.poll();
            if (curr[0] == rows - 1) {
                return true;
            }
            if (!visited[curr[0]][curr[1]]) {
                visited[curr[0]][curr[1]] = true;
                int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
                for (int[] dir : dirs) {
                    int nextR = dir[0] + curr[0];
                    int nextC = dir[1] + curr[1];
                    if (inside(nextR, nextC, arr) && arr[nextR][nextC] == 0) {
                        queue.add(new Integer[]{nextR, nextC});
                    }
                }
            }
        }
        return false;
    }

    private boolean inside(int nextR, int nextC, int[][] arr) {
        return nextR >= 0 && nextR < arr.length && nextC >= 0 && nextC < arr[nextR].length;
    }

    public static void main(String[] args) {
        LC1970 l = new LC1970();
        int[][] cells = Utils.convertToTwoDIntArray("[[1,1],[1,2],[2,1],[2,2]]");
        int row = 2, col = 2;
        System.out.println(l.latestDayToCross(row, col, cells));
    }
}






