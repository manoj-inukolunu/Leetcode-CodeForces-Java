package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class LC1284 {
    class Data {
        int[][] mat;
        int steps;

        public Data(int[][] mat, int steps) {
            this.mat = mat;
            this.steps = steps;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Data data = (Data) o;
            return Arrays.deepEquals(mat, data.mat);
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(mat);
        }
    }

    boolean inside(int row, int col, int[][] m) {
        return row >= 0 && row < m.length && col >= 0 && col < m[row].length;
    }

    public int minFlips(int[][] mat) {
        Queue<Data> queue = new LinkedList<>();
        queue.add(new Data(mat, 0));
        int[][] dest = new int[mat.length][mat[0].length];
        for (int[] row : dest) Arrays.fill(row, 0);
        HashSet<Data> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            Data curr = queue.poll();
            if (Arrays.deepEquals(curr.mat, dest)) {
                return curr.steps;
            }
            if (!visited.contains(curr)) {
                visited.add(curr);
                int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
                int[][] copy = Arrays.stream(curr.mat).map(int[]::clone).toArray(int[][]::new);
                for (int i = 0; i < curr.mat.length; i++) {
                    for (int j = 0; j < curr.mat[i].length; j++) {
                        for (int[] dir : dirs) {
                            int nextR = i + dir[0];
                            int nextC = j + dir[1];
                            if (inside(nextR, nextC, mat)) {
                                copy[nextR][nextC] = 1 - copy[nextR][nextC];
                            }
                        }
                        copy[i][j] = 1 - copy[i][j];
                        queue.add(new Data(copy, curr.steps + 1));
                        copy = Arrays.stream(curr.mat).map(int[]::clone).toArray(int[][]::new);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        LC1284 l = new LC1284();
        int[][] data = Utils.convertToTwoDIntArray("[[1,0,0],[1,0,0],[0,0,0]]");
        System.out.println(l.minFlips(data));
    }
}






