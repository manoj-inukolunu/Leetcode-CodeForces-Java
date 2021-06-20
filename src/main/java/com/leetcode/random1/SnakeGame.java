package com.leetcode.random1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SnakeGame {

  class Point {

    int row;
    int col;

    public Point(int row, int col) {
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
      Point point = (Point) o;
      return row == point.row && col == point.col;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, col);
    }

    @Override
    public String toString() {
      return "Point{" +
          "row=" + row +
          ", col=" + col +
          '}';
    }
  }

  int score = 0;

  int width;
  int height;
  int[][] food;
  int foodIdx;
  List<Point> list = new ArrayList<>();

  public SnakeGame(int width, int height, int[][] food) {
    this.width = width;
    this.height = height;
    this.food = food;
    this.foodIdx = 0;
    list.add(new Point(0, 0));
  }

  boolean inside(Point next, int width, int height) {
    return next.row >= 0 && next.col >= 0 && next.col < width && next.row < height;
  }

  private Point getNext(String dir, Point curr) {
    switch (dir) {
      case "R":
        return new Point(curr.row, curr.col + 1);
      case "D":
        return new Point(curr.row + 1, curr.col);
      case "L":
        return new Point(curr.row, curr.col - 1);
      case "U":
        return new Point(curr.row - 1, curr.col);
    }
    return null;
  }

  public int move(String direction) {
    Point head = list.get(list.size() - 1);
    Point next = getNext(direction, head);
    if (inside(next, width, height)) {
      if (isFood(next, food, foodIdx)) {
        foodIdx++;
        if (list.contains(next)) {
          return -1;
        }
        score++;
        list.add(next);
      } else {
        if (list.contains(next)) {
          return -1;
        }
        list.add(next);
        list.remove(0);
      }
    } else {
      return -1;
    }
    return score;
  }

  private boolean isFood(Point next, int[][] food, int foodIdx) {
    return next.row == food[foodIdx][0] && next.col == food[foodIdx][1];
  }

  public static void main(String args[]) {
    int[][] food = new int[][]{
        {1, 2}, {0, 1}
    };
    SnakeGame s = new SnakeGame(3, 2, food);
    System.out.println(s.move("R"));
    System.out.println(s.move("D"));
    System.out.println(s.move("R"));
    System.out.println(s.move("U"));
    System.out.println(s.move("L"));
    System.out.println(s.move("U"));
  }
}
