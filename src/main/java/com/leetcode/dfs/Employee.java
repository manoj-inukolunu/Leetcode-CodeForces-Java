package com.leetcode.dfs;

import java.util.List;

/**
 * @author manoji on 2020-01-05.
 */
public class Employee {

  int id;
  int importance;
  List<Integer> subordinates;

  public Employee(int id, int importance, List<Integer> subordinates) {
    this.id = id;
    this.importance = importance;
    this.subordinates = subordinates;
  }
}
