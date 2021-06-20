package com.leetcode.random;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class RevealCards {

  public int[] deckRevealedIncreasing(int[] deck) {
    Arrays.sort(deck);
    int[] arr = new int[deck.length];
    Deque<Integer> queue = new LinkedList<>();
    for (int i = 0; i < deck.length; i++) {
      queue.add(i);
    }

    for (int i = 0; i < deck.length; i++) {
      int x = queue.poll();
      arr[x] = deck[i];
      if (!queue.isEmpty()) {
        x = queue.poll();
        queue.add(x);
      }
    }
    return arr;
  }


  public static void main(String args[]) {
    int[] arr = new int[]{17, 13, 11, 2, 3, 5, 7, 19};
    RevealCards r = new RevealCards();
    System.out.println(Arrays.toString(r.deckRevealedIncreasing(arr)));
  }

}
