package com.leetcode.random1;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LargestPlusSign {

  class Pair {

    int row;
    int col;

    public Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Pair pair = (Pair) o;
      return row == pair.row && col == pair.col;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, col);
    }
  }

  public int orderOfLargestPlusSign(int N, int[][] mines) {
    Set<Pair> minesSet = new HashSet<>();
    for (int i = 0; i < mines.length; i++) {
      minesSet.add(new Pair(mines[i][0], mines[i][1]));
    }
    int[][] arr = new int[N][N];
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        if (!minesSet.contains(new Pair(i, j))) {
          arr[i][j] = 1;
        }
      }
    }
    int low = 0, high = N;
    int ans = 0;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (check(mid, arr)) {
        ans = mid;
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return ans;
  }

  private boolean check(int order, int[][] arr) {
    if (order == 0) {
      return true;
    }
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        if (arr[i][j] == 1) {
          if (order == 1) {
            return true;
          }
          int row = i, col = j - 1, curr = 0;
          //left
          while (inside(row, col, arr) && arr[row][col] == 1) {
            col--;
            curr++;
            if (curr == order - 1) {
              break;
            }
            if (col < 0) {
              break;
            }
          }
          if (curr != order - 1) {
            continue;
          }
          row = i;
          col = j + 1;
          curr = 0;
          //right
          while (inside(row, col, arr) && arr[row][col] == 1) {
            col++;
            curr++;
            if (curr == order - 1) {
              break;
            }
            if (col >= arr[i].length) {
              break;
            }

          }
          if (curr != order - 1) {
            continue;
          }
          row = i - 1;
          col = j;
          curr = 0;
          //top
          while (inside(row, col, arr) && arr[row][col] == 1) {
            row--;
            curr++;
            if (curr == order - 1) {
              break;
            }
            if (row < 0) {
              break;
            }
          }
          if (curr != order - 1) {
            continue;
          }
          row = i + 1;
          col = j;
          curr = 0;
          //bottom
          while (inside(row, col, arr) && arr[row][col] == 1) {
            row++;
            curr++;
            if (curr == order - 1) {
              break;
            }
            if (row >= arr.length) {
              break;
            }

          }
          if (curr == order - 1) {
            return true;
          }
        }
      }
    }

    return false;
  }

  boolean inside(int row, int col, int[][] arr) {
    return row >= 0 && col >= 0 && row < arr.length && col < arr[row].length;
  }

  public static void main(String args[]) {
    int[][] mines = new int[][]{{0, 1}, {0, 2}, {0, 3}, {0, 4}, {1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4}, {2, 0}, {2, 1}, {2, 3}, {2, 4}, {3, 1},
        {3, 2}, {3, 3}, {3, 4}, {4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4}};
    LargestPlusSign l = new LargestPlusSign();
    System.out.println(l.orderOfLargestPlusSign(5, mines));
  }

}
