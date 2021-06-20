package com.leetcode.dfs.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author manoji on 2020-01-11.
 */
public class Cycle {

  public boolean canFinish(int numCourses, int[][] prerequisites) {
    HashMap<Integer, List<Integer>> map = new HashMap<>();
    for (int i = 0; i < numCourses; i++) {
      map.put(i, new ArrayList());
    }
    for (int[] edge : prerequisites) {
      int start = edge[0];
      int end = edge[1];
      if (map.containsKey(start)) {
        List<Integer> edges = map.get(start);
        edges.add(end);
        map.put(start, edges);
      } else {
        List<Integer> edges = map.get(start);
        edges.add(end);
        map.put(start, edges);
      }
    }

    boolean[] visited = new boolean[numCourses];
    boolean[] stack = new boolean[numCourses];

    HashSet<Integer> courses = new HashSet<>();

    for (Integer vertex : map.keySet()) {
      boolean cycle = checkCycle(map, visited, vertex, stack);
      if (!cycle || map.get(vertex).isEmpty()) {
        courses.add(vertex);
      }
    }

    return courses.size() == numCourses;

  }

  private boolean checkCycle(HashMap<Integer, List<Integer>> map, boolean[] visited, Integer current, boolean[] stack) {

    if (stack[current]) {
      return true;
    }

    if (visited[current]) {
      return false;
    }

    stack[current] = true;
    visited[current] = true;

    List<Integer> preqCourses = map.get(current);
    for (Integer course : preqCourses) {
      if (checkCycle(map, visited, course, stack)) {
        return true;
      }
    }

    stack[current] = false;
    return false;
  }


  public static void main(String args[]) {

    int[][] prereq = new int[][]{
        {0, 1},
        {1, 0}
    };

    Cycle cycle = new Cycle();

    System.out.println(cycle.canFinish(4, prereq));

  }

}
