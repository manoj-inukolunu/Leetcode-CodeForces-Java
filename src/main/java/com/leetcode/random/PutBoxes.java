package com.leetcode.random;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class PutBoxes {

  public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {

    Deque<Integer[]> deque = new LinkedList<>();
    int i = warehouse.length - 1, k = 1;
    while (i >= 0) {
      if (deque.isEmpty()) {
        deque.push(new Integer[]{k, warehouse[i], k});
        i--;
        k++;
      } else {
        while (!deque.isEmpty() && deque.peek()[1] > warehouse[i]) {
          deque.pop();
        }
        deque.push(new Integer[]{k, warehouse[i], k});
        i--;
        k++;
      }
    }

    int size = deque.size();
    while (size > 0) {
      Integer[] curr = deque.pollLast();
      System.out.print(curr[0] + " " + curr[1]);
      System.out.println();
      deque.addFirst(curr);
      size--;
    }

    Arrays.sort(boxes);
    int count = 0, total = 0;
    for (int j = 0; j < boxes.length; j++) {
      int currBox = boxes[j];
      if (deque.isEmpty()) {
        break;
      }
      Integer[] last = deque.peekLast();
      if (count < last[0] && currBox <= last[1]) {
        count++;
        total++;
      } else {
        last = deque.pollLast();
        if (!deque.isEmpty()) {
          Integer[] next = deque.pollLast();
          next[0] = next[0] - last[2];
          count = 0;
          deque.addLast(next);
          j--;
        }
      }
    }

    return total;
  }


  public static void main(String args[]) {
    PutBoxes p = new PutBoxes();
    int[] boxes = new int[]{34, 23, 13, 1, 11, 18, 37, 39, 24, 48, 23, 86, 7, 82, 67, 29, 51, 86, 10, 64, 72, 62, 74, 60, 6, 58, 26, 42, 30, 76, 31,
        4, 6, 54, 5, 91, 1, 63, 81, 14, 69, 15, 13, 36, 48, 56};
    int[] warehouse = new int[]{35, 51, 69, 64, 6, 7, 31, 1, 41, 62, 67, 15, 23, 45, 60, 37, 48, 35, 57, 74, 73, 29, 58, 9, 51, 65, 61};
    System.out.println(p.maxBoxesInWarehouse(boxes, warehouse));
  }

}
