package com.leetcode.dfs.hard;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.checkerframework.checker.units.qual.C;

/**
 * @author manoji on 5/10/20.
 */
public class CriticalConnections {

  List<List<Integer>> list = new ArrayList();


  public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
    int low[] = new int[n];
    int in[] = new int[n];
    boolean[] visited = new boolean[n];
    HashMap<Integer, List<Integer>> map = new HashMap<>();
    buildMap(connections, map);

    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        dfs(i, -1, low, in, visited, map, 0);
      }
    }

    return list;

  }

  private void buildMap(List<List<Integer>> connections, HashMap<Integer, List<Integer>> map) {
    for (int i = 0; i < connections.size(); i++) {
      List<Integer> connection = connections.get(i);
      int node1 = connection.get(0);
      int node2 = connection.get(1);
      List<Integer> adj1 = map.getOrDefault(node1, new ArrayList<>());
      adj1.add(node2);
      map.put(node1, adj1);
      List<Integer> adj2 = map.getOrDefault(node2, new ArrayList<>());
      adj2.add(node1);
      map.put(node2, adj2);
    }
  }

  private void dfs(int node, int parent, int[] low, int[] in, boolean[] visited, HashMap<Integer, List<Integer>> map, int timer) {
    visited[node] = true;
    in[node] = low[node] = timer++;
    for (int child : map.get(node)) {
      if (child == parent) {
        continue;
      }
      if (visited[child]) {
        low[node] = Math.min(low[node], in[child]);
      } else {
        dfs(child, node, low, in, visited, map, timer);
        low[node] = Math.min(low[node], low[child]);
        if (low[child] > in[node]) {
          List<Integer> bridge = new ArrayList();
          bridge.add(node);
          bridge.add(child);
          list.add(bridge);
        }
      }
    }
  }


  public static void main(String args[]) {
    int numNodes = 4;
    List<List<Integer>> list = new ArrayList<>();
    list.add(Lists.newArrayList(0, 1));
    list.add(Lists.newArrayList(1, 2));
    list.add(Lists.newArrayList(2, 0));
    list.add(Lists.newArrayList(1, 3));

    CriticalConnections c = new CriticalConnections();
    System.out.println(c.criticalConnections(numNodes, list));
  }
}
