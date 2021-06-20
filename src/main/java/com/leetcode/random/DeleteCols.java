package com.leetcode.random;


import java.util.HashSet;
import java.util.Set;

public class DeleteCols {


  public int minDeletionSize(String[] A) {
    Set<Integer> del = new HashSet<>();
    for (int i = 0; i + 1 < A.length; i++) {
      for (int j = 0; j < A[i].length(); j++) {
        if (del.contains(j)) {
          continue;
        }
        if (A[i].charAt(j) > A[i + 1].charAt(j)) {
          del.add(j);
          i = 0;
        }
      }
    }
    return del.size();

  }


  public static void main(String args[]) {
    DeleteCols d = new DeleteCols();
    String[] str = new String[]{"xc", "yb", "za"};
    System.out.println(d.minDeletionSize(str));
  }

}
