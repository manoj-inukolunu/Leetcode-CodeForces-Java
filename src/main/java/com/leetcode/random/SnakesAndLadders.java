package com.leetcode.random;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class SnakesAndLadders {

  class Pair {

    public int steps;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Pair pair = (Pair) o;
      return row == pair.row &&
          col == pair.col;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, col);
    }

    int row;

    public Pair(int row, int col, int steps) {
      this.row = row;
      this.col = col;
      this.steps = steps;
    }

    int col;

  }


  public int snakesAndLadders(int[][] board) {

    Deque<Pair> queue = new LinkedList<>();
    populate(board);
    queue.add(new Pair(board.length - 1, 0, 0));
    HashSet<Pair> visited = new HashSet<>();
    int ans = Integer.MAX_VALUE;
    int checkRow = map.get(board.length * board[0].length).row;
    int checkCol = map.get(board.length * board[0].length).col;
    while (!queue.isEmpty()) {
      Pair curr = queue.poll();
      if (curr.row == checkRow && curr.col == checkCol) {
        ans = Math.min(ans, curr.steps);
      }
      if (!visited.contains(curr)) {
        visited.add(curr);
        List<Pair> next = getNext(curr, board);
        for (Pair n : next) {
          queue.add(n);
        }
      }

    }

    return ans == Integer.MAX_VALUE ? -1 : ans;

  }

  boolean inside(int row, int col, int[][] board) {
    return row >= 0 && col >= 0 && row < board.length && col < board[row].length;
  }

  HashMap<Integer, Pair> map = new HashMap<>();

  private void populate(int[][] board) {
    int count = 1, cols = board.length;
    int row = board.length - 1, col = 0, dir = 1;
    while (inside(row, col, board)) {
      map.put(count, new Pair(row, col, 0));
      if (dir == 1) {
        if (col + 1 < cols) {
          col++;
        } else {
          col = cols - 1;
          row--;
          dir = 0;
        }
      } else {
        if (col - 1 >= 0) {
          col--;
        } else {
          col = 0;
          row--;
          dir = 1;
        }
      }
      count++;
    }
  }

  private List<Pair> getNext(Pair curr, int[][] board) {
    List<Pair> hold = new ArrayList<>();
    int row = curr.row;
    int col = curr.col;
    int steps = 0;
    while (steps <= 5) {
      if (condition(board, row)) {
        if (col + 1 < board[0].length) {
          col = col + 1;
          if (inside(row, col, board) && board[row][col] == -1) {
            hold.add(new Pair(row, col, curr.steps + 1));
          } else if (inside(row, col, board)) {
            Pair next = map.get(board[row][col]);
            hold.add(new Pair(next.row, next.col, curr.steps + 1));
          }
        } else {
          col = board[0].length - 1;
          row = row - 1;
          if (inside(row, col, board) && board[row][col] == -1) {
            hold.add(new Pair(row, col, curr.steps + 1));
          } else if (inside(row, col, board)) {
            Pair next = map.get(board[row][col]);
            hold.add(new Pair(next.row, next.col, curr.steps + 1));
          }
        }
      } else {
        if (col - 1 >= 0) {
          col = col - 1;
          if (inside(row, col, board) && board[row][col] == -1) {
            hold.add(new Pair(row, col, curr.steps + 1));
          } else if (inside(row, col, board)) {
            Pair next = map.get(board[row][col]);
            hold.add(new Pair(next.row, next.col, curr.steps + 1));
          }
        } else {
          col = 0;
          row = row - 1;
          if (inside(row, col, board) && board[row][col] == -1) {
            hold.add(new Pair(row, col, curr.steps + 1));
          } else if (inside(row, col, board)) {
            Pair next = map.get(board[row][col]);
            hold.add(new Pair(next.row, next.col, curr.steps + 1));
          }
        }
      }
      steps++;
    }
    return hold;
  }

  private boolean condition(int[][] board, int row) {
    if (board.length % 2 == 0) {
      return row % 2 != 0;
    } else {
      return row % 2 == 0;
    }
  }


  public static void main(String args[]) {

    int[][] board = new int[][]{
        {-1, 1, 2, -1},
        {2, 13, 15, -1},
        {-1, 10, -1, -1},
        {-1, 6, 2, 8}
    };

    SnakesAndLadders s = new SnakesAndLadders();
    System.out.println(s.snakesAndLadders(board));
  }


}
