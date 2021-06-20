package com.leetcode.random;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class SlidingPuzzle {

  class Pair {

    String state;
    int dist;

    public Pair(String state, int dist) {
      this.state = state;
      this.dist = dist;
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
      return Objects.equals(state, pair.state);
    }

    @Override
    public int hashCode() {
      return Objects.hash(state);
    }
  }

  public int slidingPuzzle(int[][] board) {
    StringBuffer state = new StringBuffer();
    String finalState = "123450";
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        state.append(board[i][j]);
      }
    }
    Queue<Pair> queue = new LinkedList<>();
    queue.add(new Pair(state.toString(), 0));
    HashSet<String> visited = new HashSet<>();
    int ans = Integer.MAX_VALUE;
    while (!queue.isEmpty()) {
      Pair curr = queue.poll();
      if (curr.state.equals(finalState)) {
        ans = Math.min(ans, curr.dist);
      }
      if (!visited.contains(curr.state)) {
        visited.add(curr.state);
        queue.addAll(nextStates(board, curr));
      }
    }
    return ans == Integer.MAX_VALUE ? -1 : ans;
  }

  private int[] getRowFromPos(int pos) {
    int row = 0;
    int col = pos;
    if (pos > 2) {
      row = 1;
      col = pos - 3;
    }
    return new int[]{row, col};
  }

  private int getPosFromRow(int[] pos) {
    return 3 * pos[0] + pos[1];
  }

  private boolean inside(int row, int col, int[][] grid) {
    return row >= 0 && col >= 0 && row < grid.length && col < grid[row].length;
  }

  private List<Pair> nextStates(int[][] board, Pair curr) {
    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int[] pos = getRowFromPos(curr.state.indexOf('0'));
    List<Pair> list = new ArrayList<>();
    for (int[] dir : dirs) {
      int nextR = pos[0] + dir[0];
      int nextC = pos[1] + dir[1];
      if (inside(nextR, nextC, board)) {
        list.add(new Pair(swap(new StringBuffer(curr.state), getPosFromRow(new int[]{nextR, nextC}), curr.state.indexOf('0')), curr.dist + 1));
      }
    }
    return list;
  }

  private String swap(StringBuffer buffer, int posFromRow, int indexOf) {
    char ch = buffer.charAt(posFromRow);
    buffer.setCharAt(posFromRow, buffer.charAt(indexOf));
    buffer.setCharAt(indexOf, ch);
    return buffer.toString();
  }

  public static void main(String args[]) {
    int[][] board = new int[][]{
        {3, 2, 4}, {1, 5, 0}
    };
    SlidingPuzzle s = new SlidingPuzzle();
    System.out.println(s.slidingPuzzle(board));
  }

}
