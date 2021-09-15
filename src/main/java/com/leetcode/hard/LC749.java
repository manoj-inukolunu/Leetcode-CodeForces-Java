package com.leetcode.hard;

import com.leetcode.common.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class LC749 {

    class Cell {
        int row;
        int col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return row == cell.row && col == cell.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    class Region {
        Set<Cell> cells;
        int walls = 0;
        Set<Cell> neighbors = new HashSet<>();

        public Region(Set<Cell> cells) {
            this.cells = cells;
        }

        public void advanceOneDay(int[][] grid) {
            Set<Cell> newCells = new HashSet<>();
            for (Cell cell : cells) {
                newCells.add(cell);
                int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
                for (int[] dir : dirs) {
                    int nextX = cell.row + dir[0];
                    int nextY = cell.col + dir[1];
                    if (inside(nextX, nextY, grid) && grid[nextX][nextY] == 0) {
                        grid[nextX][nextY] = 1;
                        newCells.add(new Cell(nextX, nextY));
                    }
                }
            }
            cells = newCells;
            walls = getNeighbors(grid);
        }

        public void add(Cell cell, int[][] grid) {
            cells.add(cell);
            int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int[] dir : dirs) {
                int nextX = cell.row + dir[0];
                int nextY = cell.col + dir[1];
                if (inside(nextX, nextY, grid) && grid[nextX][nextY] == 0) {
                    neighbors.add(new Cell(nextX, nextY));
                    walls++;
                }
            }
        }


        public int getNeighbors(int[][] grid) {
            int count = 0;
            for (Cell cell : cells) {
                int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
                for (int[] dir : dirs) {
                    int nextX = cell.row + dir[0];
                    int nextY = cell.col + dir[1];
                    if (inside(nextX, nextY, grid) && grid[nextX][nextY] == 0) {
                        count++;
                    }
                }
            }
            return count;
        }

        public void buildWalls(int[][] grid) {
            for (Cell cell : cells) {
                grid[cell.row][cell.col] = 2;
            }
        }
    }

    private boolean inside(int row, int col, int[][] grid) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[row].length;
    }

    public int containVirus(int[][] isInfected) {
        List<Region> regions = getRegions(isInfected);
        int totalWalls = 0;

        while (!regions.isEmpty() && !isFullyInfected(isInfected)) {
            regions.sort((o1, o2) -> -Integer.compare(o1.neighbors.size(), o2.neighbors.size()));
            regions.get(0).buildWalls(isInfected);
            totalWalls += regions.get(0).walls;
            regions.remove(0);
            if (regions.isEmpty()) {
                break;
            }
            for (Region region : regions) {
                region.advanceOneDay(isInfected);
            }
            regions = getRegions(isInfected);
        }
        return totalWalls;
    }

    @NotNull
    private List<Region> getRegions(int[][] isInfected) {
        List<Region> regions = new ArrayList<>();
        int n = isInfected.length, m = isInfected[0].length;
        boolean[][] vis = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!vis[i][j] && isInfected[i][j] == 1) {
                    Region region = new Region(new HashSet<>());
                    dfs(i, j, vis, isInfected, region);
                    regions.add(region);
                }
            }
        }
        return regions;
    }

    private boolean isFullyInfected(int[][] isInfected) {
        int count = 0;
        for (int[] ints : isInfected) {
            for (int anInt : ints) {
                if (anInt == 1 || anInt == 2) {
                    count++;
                }
            }
        }
        return count == isInfected.length * isInfected[0].length;
    }

    private void dfs(int row, int col, boolean[][] vis, int[][] isInfected, Region region) {
        if (!vis[row][col]) {
            vis[row][col] = true;
            region.add(new Cell(row, col), isInfected);
            int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int[] dir : dirs) {
                int nextX = row + dir[0];
                int nextY = col + dir[1];
                if (inside(nextX, nextY, isInfected) && isInfected[nextX][nextY] == 1) {
                    dfs(nextX, nextY, vis, isInfected, region);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] area = Utils.convertToTwoDIntArray("[[0,1,0,0,0,0,0,1],[0,1,0,0,0,0,0,1],[0,0,0,0,0,0,0,1],[0,0,0,0," +
                "0,0,0,0]]");
        int[][] area1 = Utils.convertToTwoDIntArray("[[1,1,1],[1,0,1],[1,1,1]]");
        int[][] area2 = Utils.convertToTwoDIntArray("[[1,1,1,0,0,0,0,0,0],[1,0,1,0,1,1,1,1,1],[1,1,1,0,0,0,0,0,0]]");
        int[][] area4 = Utils.convertToTwoDIntArray("[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,1,0,0],[1,0,0,0,0,0,0,0,0," +
                "0],[0,0,1,0,0,0,1,0,0,0],[0,0,0,0,0,0,1,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0," +
                "0,0,0,0,1,0],[0,0,0,0,1,0,1,0,0,0],[0,0,0,0,0,0,0,0,0,0]]");
        int[][] area5 = Utils.convertToTwoDIntArray("[[0,1,0,1,1,1,1,1,1,0],[0,0,0,1,0,0,0,0,0,0],[0,0,1,1,1,0,0,0,1," +
                "0],[0,0,0,1,1,0,0,1,1,0],[0,1,0,0,1,0,1,1,0,1],[0,0,0,1,0,1,0,1,1,1],[0,1,0,0,1,0,0,1,1,0],[0,1,0,1," +
                "0,0,0,1,1,0],[0,1,1,0,0,1,1,0,0,1],[1,0,1,1,0,1,0,1,0,1]]");
        LC749 l = new LC749();
        System.out.println(l.containVirus(area5));
    }
}
