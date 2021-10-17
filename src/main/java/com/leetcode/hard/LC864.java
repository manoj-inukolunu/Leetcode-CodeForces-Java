package com.leetcode.hard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class LC864 {

    class Data {
        int row;
        int col;
        Set<Character> state;
        int dist;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Data data = (Data) o;
            return row == data.row && col == data.col && state.equals(data.state);
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col, state);
        }

        public Data(int row, int col, Set<Character> state, int dist) {
            this.row = row;
            this.col = col;
            this.state = state;
            this.dist = dist;
        }
    }

    boolean inside(int row, int col, char[][] grid) {
        return row >= 0 && col >= 0 && row < grid.length && col < grid[row].length;
    }

    public int shortestPathAllKeys(String[] grid) {
        int sRow = -1, sCol = -1;
        Set<Character> keys = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                char ch = grid[i].charAt(j);
                if (Character.isLowerCase(ch)) {
                    keys.add(ch);
                } else if (ch == '@') {
                    sRow = i;
                    sCol = j;
                }
            }
        }
        if (sRow == -1) {
            return -1;
        }
        char[][] arr = new char[grid.length][];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = grid[i].toCharArray();
        }

        Queue<Data> queue = new LinkedList<>();
        queue.add(new Data(sRow, sCol, new HashSet<>(), 0));
        Set<Data> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            Data data = queue.poll();
            if (data.state.equals(keys)) {
                return data.dist;
            }
            int row = data.row, col = data.col;
            if (!visited.contains(data)) {
                visited.add(data);
                int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
                for (int[] dir : dirs) {
                    int nextR = row + dir[0];
                    int nextC = col + dir[1];
                    if (inside(nextR, nextC, arr)) {
                        if (Character.isLowerCase(arr[nextR][nextC])) {
                            Set<Character> set = new HashSet<>(data.state);
                            set.add(arr[nextR][nextC]);
                            queue.add(new Data(nextR, nextC, set, data.dist + 1));
                        } else if ((Character.isUpperCase(arr[nextR][nextC]) &&
                                data.state.contains(Character.toLowerCase(arr[nextR][nextC]))) || arr[nextR][nextC] == '.' || arr[nextR][nextC] == '@') {
                            queue.add(new Data(nextR, nextC, new HashSet<>(data.state), data.dist + 1));
                        }
                    }
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        String[] data = new String[]{"@...a", ".###A", "b.BCc"};
        LC864 l = new LC864();
        System.out.println(l.shortestPathAllKeys(data));
    }
}
