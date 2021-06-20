package com.leetcode.random4;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TrapRainWater2 {

  class Data {

    int row;
    int col;
    int height;

    public Data(int row, int col, int height) {
      this.row = row;
      this.col = col;
      this.height = height;
    }
  }

  public int trapRainWater(int[][] heightMap) {
    boolean[][] vis = new boolean[heightMap.length][heightMap[0].length];
    PriorityQueue<Data> p = new PriorityQueue<>(Comparator.comparingInt(o -> o.height));

    for (int col = 0; col < heightMap[0].length; col++) {
      vis[0][col] = true;
      p.add(new Data(0, col, heightMap[0][col]));
      vis[heightMap.length - 1][col] = true;
      p.add(new Data(heightMap.length - 1, col, heightMap[0][col]));
    }
    for (int row = 0; row < heightMap.length; row++) {
      vis[row][0] = true;
      p.add(new Data(row, 0, heightMap[row][0]));
      vis[row][heightMap[0].length - 1] = true;
      p.add(new Data(row, heightMap[0].length - 1, heightMap[row][heightMap[0].length - 1]));
    }
    int res = 0;
    while (!p.isEmpty()) {
      Data curr = p.poll();
      int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
      for (int dir[] : dirs) {
        int nextR = dir[0] + curr.row;
        int nextC = dir[1] + curr.col;
        if (nextR > 0 && nextR < heightMap.length - 1 && nextC > 0 && nextC < heightMap[nextR].length - 1 && !vis[curr.row][curr.col]) {
          vis[curr.row][curr.col] = true;
          res += Math.max(0, curr.height - heightMap[nextR][nextC]);
          p.add(new Data(nextR, nextC, Math.max(curr.height, heightMap[nextR][nextC])));
        }
      }
    }
    return res;
  }

}
