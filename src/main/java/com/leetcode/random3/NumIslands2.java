package com.leetcode.random3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumIslands2 {

  int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

  private boolean inside(int row, int col, int[][] arr) {
    return row >= 0 && col >= 0 && row < arr.length && col < arr[row].length;
  }

  int area = 0;
  Set<Integer> set = new HashSet<>();

  public List<Integer> numIslands2(int m, int n, int[][] positions) {
    int[][] arr = new int[m][n];
    int maxId = 0;
    HashMap<Integer, Integer> map = new HashMap<>();
    List<Integer> ans = new ArrayList<>();
    for (int i = 0; i < positions.length; i++) {
      int[] curr = positions[i];
      if (arr[curr[0]][curr[1]] != 0) {
        ans.add(map.size());
        continue;
      }
      int max = Integer.MIN_VALUE, id = -1;
      for (int[] dir : dirs) {
        int nextR = dir[0] + curr[0];
        int nextC = dir[1] + curr[1];
        if (inside(nextR, nextC, arr) && arr[nextR][nextC] != 0) {
          if (map.get(arr[nextR][nextC]) >= max) {
            max = map.get(arr[nextR][nextC]);
            id = arr[nextR][nextC];
          }
        }
      }
      if (id == -1) {
        maxId++;
        id = maxId;
        arr[curr[0]][curr[1]] = id;
        map.put(id, 1);
      } else {
        dfs(curr[0], curr[1], arr, id);
        map.put(id, map.get(id) + area);
        for (int i1 : set) {
          if (i1 != id) {
            map.remove(i1);
          }
        }
        set.clear();
        area = 0;
      }
      ans.add(map.size());
    }
    return ans;
  }

  private void dfs(int row, int col, int[][] arr, int id) {
    if (arr[row][col] != id) {
      set.add(arr[row][col]);
      area++;
      arr[row][col] = id;
      for (int[] dir : dirs) {
        int nextR = dir[0] + row;
        int nextC = dir[1] + col;
        if (inside(nextR, nextC, arr) && arr[nextR][nextC] != 0) {
          dfs(nextR, nextC, arr, id);
        }
      }
    }
  }

  public static void main(String args[]) {
    NumIslands2 n = new NumIslands2();
    int[][] pos = new int[][]{
        {0, 5}, {5, 4}, {5, 2}, {7, 3}, {6, 0}, {5, 3}, {5, 1},
        {1, 3}, {4, 3}, {2, 3}, {3, 5}, {3, 2}, {3, 0}, {2, 4}, {0, 1}
    };
    System.out.println(n.numIslands2(8, 6, pos));
  }
}
