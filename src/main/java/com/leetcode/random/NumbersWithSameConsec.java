package com.leetcode.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NumbersWithSameConsec {

  public int[] numsSameConsecDiff(int n, int k) {
    List<Integer> list = new ArrayList();
    Queue<String> queue = new LinkedList();
    for (int i = 1; i <= 9; i++) {
      queue.add(i + "");
    }
    HashSet<String> visited = new HashSet();
    while (!queue.isEmpty()) {
      String curr = queue.poll();
      if (curr.length() == n) {
        list.add(Integer.parseInt(curr));
      } else {
        if (!visited.contains(curr)) {
          visited.add(curr);
          StringBuffer buffer = new StringBuffer(curr);
          Integer end = Character.getNumericValue(curr.charAt(curr.length() - 1));
          if (end - k >= 0) {
            buffer.append(end - k);
            queue.add(buffer.toString());
            buffer.deleteCharAt(buffer.length() - 1);
          }
          if (end + k < 10) {
            buffer.append(end + k);
            queue.add(buffer.toString());
            buffer.deleteCharAt(buffer.length() - 1);
          }
        }
      }
    }

    return list.stream().mapToInt(i -> i).toArray();
  }

  public static void main(String args[]) {
    NumbersWithSameConsec n = new NumbersWithSameConsec();
    System.out.println(Arrays.toString(n.numsSameConsecDiff(2, 1)));
  }

}
