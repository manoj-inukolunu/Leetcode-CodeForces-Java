package com.leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author manoji on 5/15/20.
 */
public class CourseSchedule2 {

  boolean cycle = false;

  class Edge {

    int from;
    int to;

    public Edge(int from, int to) {
      this.from = from;
      this.to = to;
    }
  }

  List<Integer> list = new ArrayList();
  HashMap<Integer, List<Edge>> map = new HashMap<>();

  public int[] findOrder(int numCourses, int[][] prerequisites) {
    for (int row[] : prerequisites) {
      Edge edge = new Edge(row[1], row[0]);
      if (map.containsKey(edge.from)) {
        List<Edge> edges = map.get(edge.from);
        edges.add(edge);
        map.put(edge.from, edges);
      } else {
        List<Edge> edges = new ArrayList<>();
        edges.add(edge);
        map.put(edge.from, edges);
      }
    }

    int[] inDegree = new int[numCourses];
    Arrays.fill(inDegree, 0);

    for (int i = 0; i < prerequisites.length; i++) {
      inDegree[prerequisites[i][0]]++;
    }

    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < inDegree.length; i++) {
      if (inDegree[i] == 0) {
        stack.push(i);
      }
    }

    while (!stack.isEmpty()) {
      int curr = stack.pop();
      list.add(curr);
      if (map.containsKey(curr)) {
        for (Edge edge : map.get(curr)) {
          inDegree[edge.to]--;
          if (inDegree[edge.to] == 0) {
            stack.push(edge.to);
          }
        }
      }
    }

    for (int i = 0; i < inDegree.length; i++) {
      if (inDegree[i] != 0) {
        return new int[]{};
      }
    }
    return list.stream().mapToInt(value -> value).toArray();
  }

  public static void main(String[] args) {
    CourseSchedule2 c = new CourseSchedule2();
    int[][] arr = new int[][]{
        {1, 0},
        {2, 0},
        {3, 1},
        {3, 2}
    };

    System.out.println(Arrays.toString(c.findOrder(4, arr)));

  }

}
