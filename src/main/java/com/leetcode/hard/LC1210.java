package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class LC1210 {
    class Data {

        int r, c;
        int pos;
        int steps;

        public Data(int row, int col, int pos, int steps) {
            this.r = row;
            this.c = col;
            this.pos = pos;
            this.steps = steps;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Data data = (Data) o;
            return r == data.r && c == data.c && pos == data.pos;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c, pos);
        }
    }

    private boolean inside(int row, int col, int[][] grid) {
        return row >= 0 && col >= 0 && row < grid.length && col < grid[row].length;
    }

    public int minimumMoves(int[][] grid) {
        Queue<Data> queue = new LinkedList<>();
        int n = grid.length;
        queue.add(new Data(0, 1, 0, 0));
        HashSet<Data> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            Data curr = queue.poll();
            if (curr.r == n - 1 && curr.c == n - 1 && curr.pos == 0) {
                return curr.steps;
            }
            System.out.println(curr + " " + curr.pos);
            if (!visited.contains(curr)) {
                visited.add(curr);
                if (curr.pos == 0) {
                    //move right
                    if (inside(curr.r, curr.c + 1, grid) && grid[curr.r][curr.c + 1] == 0) {
                        queue.add(new Data(curr.r, curr.c + 1, curr.pos, curr.steps + 1));
                    }
                    //move down
                    if (inside(curr.r + 1, curr.c, grid) && inside(curr.r + 1, curr.c - 1, grid) && grid[curr.r + 1][curr.c - 1] == 0 && grid[curr.r + 1][curr.c] == 0) {
                        queue.add(new Data(curr.r + 1, curr.c, curr.pos, curr.steps + 1));
                    }
                    //rotate clockwise
                    if (inside(curr.r + 1, curr.c, grid) && inside(curr.r + 1, curr.c - 1, grid) && curr.pos == 0 && grid[curr.r + 1][curr.c] == 0 && grid[curr.r + 1][curr.c - 1] == 0) {
                        queue.add(new Data(curr.r + 1, curr.c - 1, 1, curr.steps + 1));
                    }
                } else if (curr.pos == 1) {
                    //rotate anticlockwise
                    if (inside(curr.r, curr.c + 1, grid) && inside(curr.r - 1, curr.c + 1, grid) && grid[curr.r][curr.c + 1] == 0 && grid[curr.r - 1][curr.c + 1] == 0) {
                        queue.add(new Data(curr.r - 1, curr.c + 1, 0, curr.steps + 1));
                    }
                    //move down
                    if (inside(curr.r + 1, curr.c, grid) && grid[curr.r + 1][curr.c] == 0) {
                        queue.add(new Data(curr.r + 1, curr.c, curr.pos, curr.steps + 1));
                    }
                    //move right
                    if (inside(curr.r, curr.c + 1, grid) && inside(curr.r - 1, curr.c + 1, grid) && grid[curr.r][curr.c + 1] == 0 && grid[curr.r - 1][curr.c + 1] == 0) {
                        queue.add(new Data(curr.r, curr.c + 1, curr.pos, curr.steps + 1));
                    }
                }
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = Utils.convertToTwoDIntArray("[[0,0,0,0,0,1],[1,1,0,0,1,0],[0,0,0,0,1,1],[0,0,1,0,1,0],[0,1,1," +
                "0,0,0],[0,1,1,0,0,0]]");
        LC1210 l = new LC1210();
        System.out.println(l.minimumMoves(grid));
    }
}






