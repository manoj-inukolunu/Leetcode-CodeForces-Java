package com.leetcode.random10.sixmonths.hard;

import java.util.HashSet;
import java.util.Objects;

public class RobotRoomCleaner {

  class Pair {

    int row;
    int col;
    int dir;

    public Pair(int row, int col, int dir) {
      this.row = row;
      this.col = col;
      this.dir = dir;
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

  public void cleanRoom(Robot robot) {
    dfs(new Pair(0, 0, 0), new HashSet<Pair>(), robot);
  }

  private void dfs(Pair curr, HashSet<Pair> visited, Robot robot) {
    if (!visited.contains(curr)) {
      visited.add(curr);
      robot.clean();
      int dir = curr.dir;
      if (robot.move()) {
        if (dir == 1) {
          dfs(new Pair(curr.row - 1, curr.col, curr.dir), visited, robot);
        } else if (dir == -1) {
          dfs(new Pair(curr.row, curr.col - 1, curr.dir), visited, robot);
        } else if (dir == -2) {
          dfs(new Pair(curr.row + 1, curr.col, curr.dir), visited, robot);
        } else if (dir == -3) {
          dfs(new Pair(curr.row, curr.col + 1, curr.dir), visited, robot);
        }
      } else {

      }
    }
  }

}
