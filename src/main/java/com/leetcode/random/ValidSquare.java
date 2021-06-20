package com.leetcode.random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ValidSquare {

  static class Point {

    int x, y;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Point point = (Point) o;
      return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

  }

  public Double dist(Point t, Point that) {
    return Math.pow(t.x - that.x, 2) + Math.pow(t.y - that.y, 2);
  }

  public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
    List<Point> list = new ArrayList<>();
    list.add(new Point(p1[0], p1[1]));
    list.add(new Point(p2[0], p2[1]));
    list.add(new Point(p3[0], p3[1]));
    list.add(new Point(p4[0], p4[1]));

    return permute(list, 0);

  }

  private boolean permute(List<Point> list, int curr) {
    if (curr == list.size() - 1) {
      return valid(list.get(0), list.get(1), list.get(2), list.get(3));
    }
    for (int i = curr; i < list.size(); i++) {
      Collections.swap(list, i, curr);
      permute(list, curr + 1);
      Collections.swap(list, curr, i);
    }
    return false;
  }

  public boolean valid(Point A, Point B, Point C, Point D) {
    boolean sides = dist(A, B).equals(dist(B, C)) &&
        dist(B, C).equals(dist(C, D)) &&
        dist(C, D).equals(dist(A, D));
    boolean diagA = (dist(A, B) + dist(B, C)) == (dist(A, C));
    boolean diagB = dist(B, C) + dist(C, D) == dist(B, D);
    return sides && diagB && diagA;
  }

  public static void main(String args[]) {
    ValidSquare v = new ValidSquare();
    System.out.println(v.valid(new Point(-792, -1897), new Point(-150, -3181), new Point(1134, -2539), new Point(492, -1255)));
  }

}
