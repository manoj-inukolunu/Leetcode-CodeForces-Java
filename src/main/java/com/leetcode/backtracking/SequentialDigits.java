package com.leetcode.backtracking;

import com.leetcode.trie.SearchSuggest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author manoji on 7/10/20.
 */
public class SequentialDigits {

  List<Integer> list = new ArrayList<>();

  PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

  public List<Integer> sequentialDigits(int low, int high) {
    String lowStr = low + "";
    int begin = Character.getNumericValue(lowStr.charAt(0));
    for (int i = begin; i <= 9; i++) {
      recur(i, 0, new StringBuffer(i + ""), high, low);
    }
    return new ArrayList<>(priorityQueue);
  }

  private void recur(int begin, int pos, StringBuffer buffer, int high, int low) {
    if (Integer.parseInt(buffer.toString()) <= high) {
      if (Integer.parseInt(buffer.toString()) >= low) {
        priorityQueue.add(Integer.parseInt(buffer.toString()));
      }
      if (begin + 1 < 10) {
        buffer.insert(pos + 1, begin + 1);
        recur(begin + 1, pos + 1, buffer, high, low);
      }
    }
  }

  public static void main(String args[]) {
    SequentialDigits s = new SequentialDigits();

    System.out.println(Integer.parseInt("1111", 2));
  }


}
