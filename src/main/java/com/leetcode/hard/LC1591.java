package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC1591 {
    Set<Integer> allColors = new HashSet<>();
    List<int[]> list = new ArrayList<>();
    int maxR = -1, minR = 123, maxC = -1, minC = 123;
    int times = 0;

    public boolean isPrintable(int[][] grid) {
        populateColors(grid);
        while (times <= 100) {
            int validColor = -1;
            if (allColors.size() == 0) {
                return true;
            }
            for (int color : allColors) {
                list.clear();
                maxR = -1;
                minR = 123;
                maxC = -1;
                minC = 123;
                boolean valid = true;
                boolean[][] vis = new boolean[grid.length][grid[0].length];
                for (boolean[] r : vis) Arrays.fill(r, false);
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid.length; j++) {
                        if (grid[i][j] == color && !vis[i][j]) {
                            dfs(i, j, color, grid, vis);
                        }
                    }
                }
//                System.out.println(color + " " + list.size());
                // {maxR,minC},{maxR,maxC},{minR,maxC},{minR,minC}
                int numPoints = 0;
                //potentially valid color
                for (int i = minR; i <= maxR; i++) {
                    for (int j = minC; j <= maxC; j++) {
                        numPoints++;
                        if (!vis[i][j]) {
                            valid = false;
                            break;
                        }
                    }
                }
                if (valid && numPoints <= list.size()) {
                    validColor = color;
                    break;
                }

            }
            if (validColor != -1) {
                System.out.println("Removing Color = " + validColor);
                for (int[] point : list) {
                    grid[point[0]][point[1]] = -1;
                }
                allColors.remove(validColor);
            }
            times++;
        }
        return false;
    }

    void dfs(int row, int col, int color, int[][] grid, boolean[][] vis) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[row].length) {
            return;
        }
        if (grid[row][col] == color || grid[row][col] < 0) {
            if (!vis[row][col]) {
                vis[row][col] = true;
                list.add(new int[]{row, col});
                if (grid[row][col] == color) {
                    maxR = Math.max(row, maxR);
                    maxC = Math.max(col, maxC);
                    minR = Math.min(row, minR);
                    minC = Math.min(col, minC);
                }
                int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
                for (int[] dir : dirs) {
                    dfs(row + dir[0], col + dir[1], color, grid, vis);
                }
            }
        }
    }


    void populateColors(int[][] grid) {
        for (int[] arr : grid) {
            for (int color : arr) {
                allColors.add(color);
            }
        }
    }

    public static void main(String[] args) {
        int[][] arr = Utils.convertToTwoDIntArray("[[1,1,1,1],[1,1,3,3],[1,1,3,4],[5,5,1,4]]");
        LC1591 l = new LC1591();
        System.out.println(l.isPrintable(arr));
    }
}






