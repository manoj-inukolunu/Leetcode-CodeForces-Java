package com.leetcode.random3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MaxPoints {

  class Point {

    int x;
    int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
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
      return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }

    @Override
    public String toString() {
      return "Point{" +
          "x=" + x +
          ", y=" + y +
          '}';
    }
  }

  class Equation {

    String slope;
    Double constant;

    public Equation(String slope, Double constant) {
      this.slope = slope;
      this.constant = constant;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Equation equation = (Equation) o;
      return Objects.equals(slope, equation.slope) && Objects.equals(constant, equation.constant);
    }

    @Override
    public int hashCode() {
      return Objects.hash(slope, constant);
    }
  }

  public int maxPoints(int[][] points) {
    if (points.length == 0) {
      return 0;
    }
    HashMap<Equation, Set<Point>> sMap = new HashMap<>();
    HashMap<Point, Integer> cMap = new HashMap<>();
    for (int i = 0; i < points.length; i++) {
      Point a = new Point(points[i][0], points[i][1]);
      cMap.put(a, cMap.getOrDefault(a, 0) + 1);
      for (int j = i + 1; j < points.length; j++) {
        Point b = new Point(points[j][0], points[j][1]);
        Equation equation = getEquation(a, b);
        Set<Point> set = sMap.getOrDefault(equation, new HashSet<>());
        set.add(a);
        set.add(b);
        sMap.put(equation, set);
      }
    }
    int max = 1;
    for (Equation key : sMap.keySet()) {
      Set<Point> set = sMap.get(key);
      int count = 0;
      for (Point p : set) {
        count += cMap.get(p);
      }
      max = Math.max(count, max);
    }
    return max;
  }

  private Equation getEquation(Point a, Point b) {

    if (b.x == a.x) {
      return new Equation(null, (double) a.x);
    }
    BigDecimal nume = new BigDecimal(b.y - a.y);
    nume = nume.setScale(50, RoundingMode.HALF_EVEN);
    nume = nume.divide(new BigDecimal(b.x - a.x), RoundingMode.HALF_UP);
    Double constant = (double) b.y - (nume.doubleValue() * b.x);
    String slope = nume.toString();
    return new Equation(slope, constant);
  }

  public static void main(String args[]) {
    MaxPoints m = new MaxPoints();
    int[][] points = new int[][]{
        {0, 0}, {94911151, 94911150}, {94911152, 94911151}
    };
    System.out.println(m.maxPoints(points));
  }

}
