package com.leetcode.random10.sixmonths.medium;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class EvaluateDivision {

  class Weight {

    String node1;
    String node2;

    public Weight(String node1, String node2) {
      this.node1 = node1;
      this.node2 = node2;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Weight weight = (Weight) o;
      return Objects.equals(node1, weight.node1) &&
          Objects.equals(node2, weight.node2);
    }

    @Override
    public int hashCode() {
      return Objects.hash(node1, node2);
    }
  }

  private double res = -1.0;

  public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
    HashMap<String, List<String>> map = new HashMap<>();
    HashMap<Weight, Double> weights = new HashMap<>();
    HashSet<String> vertices = new HashSet<>();

    for (int i = 0; i < equations.size(); i++) {
      List<String> n1 = equations.get(i);
      String node1 = n1.get(0);
      String node2 = n1.get(1);
      vertices.add(node1);
      vertices.add(node2);
      List<String> ls = map.getOrDefault(node1, new ArrayList<>());
      ls.add(node2);
      map.put(node1, ls);
      ls = map.getOrDefault(node2, new ArrayList<>());
      ls.add(node1);
      map.put(node2, ls);
      Weight w = new Weight(n1.get(0), n1.get(1));
      weights.put(w, values[i]);
      weights.put(new Weight(n1.get(1), n1.get(0)), 1.0 / values[i]);
    }

    double[] ans = new double[queries.size()];

    for (int i = 0; i < queries.size(); i++) {
      List<String> query = queries.get(i);
      String node1 = query.get(0);
      String node2 = query.get(1);
      if (vertices.contains(node1) && vertices.contains(node2)) {
        if (node1.equalsIgnoreCase(node2)) {
          ans[i] = 1;
        }
        Weight weight = new Weight(node1, node2);
        if (weights.containsKey(weight)) {
          ans[i] = weights.get(weight);
        } else {
          weight = new Weight(node2, node1);
          if (weights.containsKey(weight)) {
            ans[i] = weights.get(weight);
          } else {
            HashSet<String> visited = new HashSet<>();
            dfs(node1, node2, weights, map, 1.0, visited);
            ans[i] = res;
            res = -1;
          }
        }
      } else {
        ans[i] = -1;
      }
    }
    return ans;
  }

  private boolean dfs(String node1, String node2, HashMap<Weight, Double> weights, HashMap<String, List<String>> map, double ans,
      HashSet<String> visited) {
    if (node1.equals(node2)) {
      res = ans;
      return true;
    }
    boolean found = false;
    if (!visited.contains(node1)) {
      visited.add(node1);
      if (map.containsKey(node1)) {
        for (String next : map.get(node1)) {
          if (!visited.contains(next)) {
            ans *= weights.get(new Weight(node1, next));
            found = found || dfs(next, node2, weights, map, ans, visited);
            if (!found) {
              ans /= weights.get(new Weight(node1, next));
            }
          }
        }
      }
    }
    return found;
  }


  public static void main(String args[]) {

    EvaluateDivision e = new EvaluateDivision();
    /*double[] ans = e.calcEquation(Lists.newArrayList(Lists.newArrayList("a", "b"), Lists.newArrayList("b", "c")),
        new double[]{2.0, 3.0},
        Lists.newArrayList(Lists.newArrayList("a", "c"), Lists.newArrayList("b", "a"), Lists.newArrayList("a", "e"), Lists.newArrayList("a", "a"),
            Lists.newArrayList("x", "x"))
    );
    double[] ans = e.calcEquation(Lists.newArrayList(Lists.newArrayList("a", "e"), Lists.newArrayList("b", "e")),
        new double[]{4.0, 3.0},
        Lists.newArrayList(Lists.newArrayList("a", "b"), Lists.newArrayList("e", "e"),
            Lists.newArrayList("x", "x")));
*/
    double[] ans = e.calcEquation(Lists.newArrayList(Lists.newArrayList("x1", "x2"), Lists.newArrayList("x2", "x3"),
        Lists.newArrayList("x3", "x4"), Lists.newArrayList("x4", "x5")),
        new double[]{3.0, 4.0, 5.0, 6.0},
        Lists.newArrayList(Lists.newArrayList("x1", "x5"), Lists.newArrayList("x5", "x2"), Lists.newArrayList("x2", "x4"), Lists.newArrayList("x2",
            "x2"),
            Lists.newArrayList("x2", "x9"), Lists.newArrayList("x9", "x9"))
    );
    System.out.println(Arrays.toString(ans));
  }
}
