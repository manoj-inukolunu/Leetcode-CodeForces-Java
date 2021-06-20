package com.leetcode.random1;

public class MaxRect2 {

  class Point {

    int x;
    int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  boolean IsOrthogonal(Point a, Point b, Point c) {
    return (b.x - a.x) * (b.x - c.x) + (b.y - a.y) * (b.y - c.y) == 0;
  }

  boolean IsRectangle(Point a, Point b, Point c, Point d) {
    return IsOrthogonal(a, b, c) && IsOrthogonal(b, c, d) && IsOrthogonal(c, d, a);
  }

  boolean IsRectangleAnyOrder(Point a, Point b, Point c, Point d) {
    return IsRectangle(a, b, c, d) || IsRectangle(b, c, a, d) || IsRectangle(c, a, b, d);
  }


  public double minAreaFreeRect(int[][] points) {
    double min = Integer.MAX_VALUE;
    for (int i = 0; i < points.length; i++) {
      Point a = new Point(points[i][0], points[i][1]);
      for (int j = i + 1; j < points.length; j++) {
        Point b = new Point(points[j][0], points[j][1]);
        for (int k = j + 1; k < points.length; k++) {
          Point c = new Point(points[k][0], points[k][1]);
          for (int t = k + 1; t < points.length; t++) {
            Point d = new Point(points[t][0], points[t][1]);
            if (IsRectangle(a, b, c, d)) {
              min = Math.min(min, getArea(a.x, a.y, b.x, b.y, c.x, c.y));
            } else if (IsRectangle(b, c, a, d)) {
              min = Math.min(min, getArea(b.x, b.y, c.x, c.y, a.x, a.y));
            } else if (IsRectangle(c, a, b, d)) {
              min = Math.min(min, getArea(c.x, c.y, a.x, a.y, b.x, b.y));
            }
          }
        }
      }
    }
    return min;
  }

  private double getArea(int ax, int ay, int bx, int by, int cx, int cy) {
    return Math.sqrt(Math.pow(bx - ax, 2) + Math.pow(by - ay, 2)) * Math.sqrt(Math.pow(cx - bx, 2) + Math.pow(cy - by, 2));
  }

  public static void main(String args[]) {
    int[][] arr = new int[][]{
        {1, 2}, {2, 1}, {1, 0}, {0, 1}
    };
    MaxRect2 m = new MaxRect2();
    System.out.println(m.minAreaFreeRect(arr));
  }

}
