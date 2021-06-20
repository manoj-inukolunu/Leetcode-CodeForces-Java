package com.leetcode.linkedlist;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author manoji on 5/2/20.
 */
public class SwapInPairs {


  public ListNode swapPairs(ListNode head) {
    if (head == null) {
      return null;
    }
    if (head.next != null) {
      ListNode nextNode = head.next.next;
      ListNode first = head;
      ListNode second = head.next;
      second.next = first;
      first.next = swapPairs(nextNode);
      return second;
    }
    return head;
  }

  public static void main(String args[]) {

    SwapInPairs pairs = new SwapInPairs();
    final ListNode[] curr = {new ListNode(-1)};
    ListNode temp = curr[0];
    Arrays.stream(new int[]{1, 2, 3, 4, 5}).forEach(value -> {
      curr[0].next = new ListNode(value);
      curr[0] = curr[0].next;
    });

    ListNode node = pairs.swapPairs(temp.next);

    System.out.println(node);
  }
}
