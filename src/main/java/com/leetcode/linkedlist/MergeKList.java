package com.leetcode.linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author manoji on 5/1/20.
 */
public class MergeKList {

  public ListNode mergeKLists(ListNode[] lists) {
    PriorityQueue<ListNode> pr = new PriorityQueue<>(Comparator.comparingInt(value -> value.val));
    for (ListNode node : lists) {
      pr.add(node);
    }

    ListNode head = pr.poll();
    ListNode temp = head;
    if (temp.next != null) {
      pr.add(temp.next);
    }

    while (!pr.isEmpty()) {
      ListNode curr = pr.poll();
      temp.next = curr;
      temp = temp.next;
      if (curr.next != null) {
        pr.add(curr.next);
      }
    }

    return head;

  }

}
