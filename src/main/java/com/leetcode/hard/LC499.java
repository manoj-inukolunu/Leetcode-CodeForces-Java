package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.util.PriorityQueue;

public class LC499 {


    class Data {
        int row;
        int col;
        int dist;
        String path;

        public Data(int row, int col, int dist, String path) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.path = path;
        }

    }

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length, n = maze[0].length;
        PriorityQueue<Data> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.dist == o2.dist) {
                return o1.path.compareTo(o2.path);
            }
            return Integer.compare(o1.dist, o2.dist);
        });
        pq.add(new Data(ball[0], ball[1], 0, ""));
        Data[][] dist = new Data[maze.length][maze[0].length];
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist[i].length; j++) {
                dist[i][j] = new Data(i, j, Integer.MAX_VALUE, "A");
            }
        }
        int[][] dirs = new int[][]{{-1, 0, 'u' - 'a'}, {1, 0, 'd' - 'a'}, {0, 1, 'r' - 'a'}, {0, -1, 'l' - 'a'}};
        while (!pq.isEmpty()) {
            Data curr = pq.poll();
            if (curr.row == hole[0] && curr.col == hole[1]) {
                return curr.path;
            }
            for (int[] dir : dirs) {
                int steps = curr.dist;
                int x = curr.row;
                int y = curr.col;
                while (x + dir[0] >= 0 && x + dir[0] < m && y + dir[1] >= 0 && y + dir[1] < n && maze[x + dir[0]][y + dir[1]] != 1) {
                    x += dir[0];
                    y += dir[1];
                    steps++;
                    if (x == hole[0] && y == hole[1]) {
                        break;
                    }
                }
                if (x == ball[0] && y == ball[1]) {
                    continue;
                }
                if (dist[x][y] == null || isLessThan(new Data(x, y, steps, curr.path + (char) (dir[2] + 'a')),
                        dist[x][y])) {
                    dist[x][y] = new Data(x, y, steps, curr.path + (char) (dir[2] + 'a'));
                    pq.add(new Data(x, y, steps, curr.path + (char) (dir[2] + 'a')));
                }
            }
        }
        return "impossible";
    }

    private boolean isLessThan(Data data, Data data1) {
        if (data.dist == data1.dist) {
            return data.path.compareTo(data1.path) <= 0;
        }
        return data.dist <= data1.dist;
    }

    public static void main(String[] args) {
        LC499 l = new LC499();
        int[][] maze = Utils.convertToTwoDIntArray("[[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]]");
        int[] ball = new int[]{4, 3};
        int[] hole = new int[]{0, 1};
        System.out.println(l.findShortestWay(maze, ball, hole));
    }
}
