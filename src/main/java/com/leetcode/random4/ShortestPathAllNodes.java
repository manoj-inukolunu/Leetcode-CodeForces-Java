package com.leetcode.random4;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class ShortestPathAllNodes {

  class Data {

    int mask;
    int currNode;
    int cost;

    public Data(int mask, int currNode, int cost) {
      this.mask = mask;
      this.currNode = currNode;
      this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Data data = (Data) o;
      return mask == data.mask && currNode == data.currNode;
    }

    @Override
    public int hashCode() {
      return Objects.hash(mask, currNode);
    }
  }

  public int shortestPathLength(int[][] graph) {
    Queue<Data> queue = new LinkedList<>();
    for (int i = 0; i < graph.length; i++) {
      queue.add(new Data(1 << i, i, 0));
    }
    HashSet<Data> visited = new HashSet<>();
    while (!queue.isEmpty()) {
      Data curr = queue.poll();
      if (curr.mask == (1 >> graph.length - 1)) {
        return curr.cost;
      }
      if (!visited.contains(curr)) {
        visited.add(curr);
        for (int child : graph[curr.currNode]) {
          int mask = curr.mask | (1 << child);
          queue.add(new Data(mask, child, curr.cost + 1));
        }
      }
    }
    return -1;
  }


  public static void main(String args[]) {
    int[][] arr = new int[][]{
        {1}, {0, 2, 4}, {1, 3, 4}, {2}, {1, 2}
    };
    ShortestPathAllNodes s = new ShortestPathAllNodes();
    s.shortestPathLength(arr);
  }


}
