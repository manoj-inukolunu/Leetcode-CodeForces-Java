package com.leetcode.hard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class LC1263 {

    class State {
        int boxX;
        int boxY;
        int playerX;
        int playerY;
        int pushes;

        public State(int boxX, int boxY, int playerX, int playerY, int pushes) {
            this.boxX = boxX;
            this.boxY = boxY;
            this.playerX = playerX;
            this.playerY = playerY;
            this.pushes = pushes;
        }

        public State() {

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return boxX == state.boxX && boxY == state.boxY && playerX == state.playerX && playerY == state.playerY;
        }

        @Override
        public int hashCode() {
            return Objects.hash(boxX, boxY, playerX, playerY);
        }
    }

    public int minPushBox(char[][] grid) {
        State start = new State();
        int endX = -1, endY = -1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[j].length; j++) {
                if (grid[i][j] == 'S') {
                    start.playerX = i;
                    start.playerY = j;
                } else if (grid[i][j] == 'B') {
                    start.boxX = i;
                    start.boxY = j;
                } else if (grid[i][j] == 'T') {
                    endX = i;
                    endY = j;
                }
            }
        }
        Queue<State> queue = new LinkedList<>();
        queue.add(start);
        Set<State> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            State curr = queue.poll();
            if (curr.boxX == endX && curr.boxY == endY) {
                return curr.pushes;
            }
            if (!visited.contains(curr)) {
                visited.add(curr);

            }
        }
        return -1;
    }
}
