/*
package com.leetcode.random;


import com.leetcode.random.CampusBikes.Pair;
import java.util.ArrayList;
import java.util.List;


public class SpiralMatrix3 {

  boolean inside(int row, int col, int R, int C) {
    return row >= 0 && col >= 0 && row < R && col < C;
  }


  public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
    int total = 0;
    List<Pair> hold = new ArrayList<>();
    int row = r0, col = c0;
    int curr = 3, sum = 0;
    while (true) {
      if (total == R * C) {
        break;
      }
      while (sum <= curr * curr) {
        if (sum == curr * curr) {
          col++;
          break;
        }
        if (inside(row, col, R, C)) {
          Pair pair = new Pair(row, col);
          hold.add(pair);
          total++;
        }
        sum++;
      }

    }
    int[][] ans = new int[R * C][2];
    for (int i = 0; i < hold.size(); i++) {
      ans[i][0] = (int) hold.get(i).;
      ans[i][1] = (int) hold.get(i).snd;
    }
    return ans;
  }

}
*/
