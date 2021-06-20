package com.leetcode.random4;

import java.util.Objects;

public class MaxPointsInCircle {


  public class Point {

    private final double x, y;

    public Point(Double x, Double y) {
      this.x = x;
      this.y = y;
    }

    public double distanceFrom(Point other) {
      double dx = x - other.x;
      double dy = y - other.y;
      return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public boolean equals(Object other) {
      if (this == other) {
        return true;
      }
      if (other == null || getClass() != other.getClass()) {
        return false;
      }
      Point point = (Point) other;
      return x == point.x && y == point.y;
    }

    @Override
    public String toString() {
      return String.format("(%.4f, %.4f)", x, y);
    }
  }

  private Point[] findCircles(Point p1, Point p2, double r) {
    if (r < 0.0) {
      return null;
    }
    if (r == 0.0 && p1 != p2) {
      return null;
    }
    if (r == 0.0) {
      return new Point[]{p1, p1};
    }
    if (Objects.equals(p1, p2)) {
      return null;
    }
    double distance = p1.distanceFrom(p2);
    double diameter = 2.0 * r;
    if (distance > diameter) {
      return null;
    }
    Point center = new Point((p1.x + p2.x) / 2.0, (p1.y + p2.y) / 2.0);
    if (distance == diameter) {
      return new Point[]{center, center};
    }
    double mirrorDistance = Math.sqrt(r * r - distance * distance / 4.0);
    double dx = (p2.x - p1.x) * mirrorDistance / distance;
    double dy = (p2.y - p1.y) * mirrorDistance / distance;
    return new Point[]{
        new Point(center.x - dy, center.y + dx),
        new Point(center.x + dy, center.y - dx)
    };
  }

  public int numPoints(int[][] points, int r) {

    int max = Integer.MIN_VALUE;

    for (int i = 0; i < points.length; i++) {
      Point a = new Point((double) points[i][0], (double) points[i][1]);
      for (int j = i + 1; j < points.length; j++) {
        Point b = new Point((double) points[j][0], (double) points[j][1]);
        Point[] centers = findCircles(a, b, r);
        if (centers != null) {
          for (Point p : centers) {
            max = Math.max(max, pointsInside(p, points, r));
          }
        }
      }
    }
    return max;
  }

  private int pointsInside(Point p, int[][] points, int r) {
    int count = 0;
    for (int i = 0; i < points.length; i++) {
      double val = (points[i][0] - p.x) * (points[i][0] - p.x) + (points[i][1] - p.y) * (points[i][1] - p.y);
      if (val <= r * r) {
        count++;
      }
    }
    return count;
  }

  public static void main(String args[]) {
    MaxPointsInCircle m = new MaxPointsInCircle();
    int[][] points = new int[][]{
        {-2, 0}, {2, 0}, {0, 2}, {0, -2}
    };
    System.out.println(m.numPoints(points, 2));
  }

}
