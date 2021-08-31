package com.leetcode.hard;

public class LC1515 {


    public double getMinDistSum(int[][] positions) {
        double cX = 0, cY = 0;
        for (int[] position : positions) {
            cX += position[0];
            cY += position[1];
        }
        cX /= positions.length;
        cY /= positions.length;
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        double delta = 100;
        double ans = dist(cX, cY, positions);
        for (int i = 0; i < 1000; i++) {
            boolean found = false;
            for (int[] dir : dirs) {
                double nextX = cX + (delta * dir[0]);
                double nextY = cY + (delta * dir[1]);
                double currDist = dist(nextX, nextY, positions);
                if (currDist < ans) {
                    ans = currDist;
                    cX = nextX;
                    cY = nextY;
                    found = true;
                    break;
                }
            }
            if (!found) {
                delta /= 2;
            }
        }
        return ans;
    }

    double dist(double x, double y, int[][] points) {
        double ret = 0;
        for (int[] point : points) {
            ret += (Math.sqrt((point[0] - x) * (point[0] - x) + (point[1] - y) * (point[1] - y)));
        }
        return ret;
    }
}
