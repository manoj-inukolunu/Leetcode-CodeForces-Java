package com.leetcode.contest;

import java.util.Deque;
import java.util.LinkedList;

public class CountStudents {

  public int countStudents(int[] students, int[] sandwiches) {
    Deque<Integer> queue = new LinkedList<>();
    for (int i = 0; i < students.length; i++) {
      queue.add(students[i]);
    }
    int i = 0;
    int j = 0;
    while (j < 10000) {

      Integer curr = queue.poll();
      if (curr == null) {
        return 0;
      }
      if (sandwiches[i] == curr) {
        i++;
      } else {
        queue.add(curr);
      }
      j++;
    }
    return queue.size();
  }

}
