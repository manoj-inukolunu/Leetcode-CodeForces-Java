package com.leetcode.random;

public class LC990 {


  private int find(int p, int[] id) {
    int root = p;
    while (id[root] != root) {
      root = id[root];
    }
    while (p != root) {
      int next = id[p];
      id[p] = root;
      p = next;
    }
    return root;
  }

  boolean connected(int p, int q, int[] id) {
    return find(p, id) == find(q, id);
  }

  void union(int p, int q, int[] id) {
    if (connected(p, q, id)) {
      return;
    }
    int r1 = find(p, id);
    int r2 = find(q, id);
    id[r2] = r1;
  }

  public boolean equationsPossible(String[] equations) {
    int[] equals = new int[26];
    for (int i = 0; i < 26; i++) {
      equals[i] = i;
    }

    for (int i = 0; i < equations.length; i++) {
      String equation = equations[i];
      int first = equation.charAt(0) - 'a';
      int second = equation.charAt(3) - 'a';
      if (first != second) {
        if (equation.contains("==")) {
          union(first, second, equals);
        }
      }
    }
    for (int i = 0; i < equations.length; i++) {
      String equation = equations[i];
      int first = equation.charAt(0) - 'a';
      int second = equation.charAt(3) - 'a';
      if (equation.contains("!=")) {
        if (first == second || connected(first, second, equals)) {
          return false;
        }
      }
    }
    return true;
  }

  public static void main(String args[]) {
    String[] str = new String[]{"a!=a"};
    LC990 l = new LC990();
    System.out.println(l.equationsPossible(str));
  }

}
