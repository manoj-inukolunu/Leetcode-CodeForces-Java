package com.leetcode.graph;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author manoji on 4/20/20.
 */
public class CloneGraph {

  public Node cloneGraph(Node node) {
    HashSet<Node> visited = new HashSet<>();
    HashMap<Integer, Node> map = new HashMap();
    dfs(node, visited, map);

    System.out.println(map);
    visited.clear();
    return ddfs(node, visited, map);
  }

  private Node ddfs(Node node, HashSet<Node> visited, HashMap<Integer, Node> map) {
    if (node == null) {
      return null;
    }

    visited.add(node);
    Node curr = map.get(node.val);

    for (Node child : node.neighbors) {
      curr.neighbors.add(map.get(child.val));
      if (!visited.contains(child)) {
        ddfs(child, visited, map);
      }
    }
    return curr;
  }

  private void dfs(Node node, HashSet<Node> visited, HashMap<Integer, Node> map) {
    if (node == null) {
      return;
    }
    visited.add(node);
    map.put(node.val, new Node(node.val));
    for (Node child : node.neighbors) {
      if (!visited.contains(child)) {
        dfs(child, visited, map);
      }
    }
  }

  public static void main(String args[]) {
    Node begin = new Node(1);
    Node node = new Node(2);
    Node node1 = new Node(3);
    Node node2 = new Node(4);

    begin.neighbors.add(node);
    begin.neighbors.add(node2);

    node.neighbors.add(begin);
    node.neighbors.add(node1);

    node1.neighbors.add(node);
    node1.neighbors.add(node2);

    node2.neighbors.add(begin);
    node2.neighbors.add(node1);

    CloneGraph cloneGraph = new CloneGraph();

    Node n = cloneGraph.cloneGraph(begin);

    System.out.println(n);

  }

}
