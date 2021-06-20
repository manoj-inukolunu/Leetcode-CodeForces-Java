package com.leetcode.linkedlist;

import com.google.common.collect.Comparators;
import com.leetcode.ListNode;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author manoji on 4/11/20.
 */
public class Merge {

  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    PriorityQueue<ListNode> pr = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
    pr.add(l1);
    pr.add(l2);
    ListNode head = pr.poll();
    ListNode temp = head;
    pr.add(temp.next);
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


  public static void main(String args[]) {
    ListNode node = new ListNode(1);
    node.next = new ListNode(2);
    node.next.next = new ListNode(4);

    ListNode root = new ListNode(1);
    root.next = new ListNode(3);
    root.next.next = new ListNode(4);
    Merge merge = new Merge();

    ListNode merged = merge.mergeTwoLists(node, root);

    merge.printList(merged);

  }

  private void printList(ListNode merged) {
    while (merged != null) {
      System.out.println(merged.val);
      merged = merged.next;
    }
  }

}
